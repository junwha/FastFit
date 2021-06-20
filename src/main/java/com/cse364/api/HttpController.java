package com.cse364.api;

import com.cse364.api.dtos.MovieDto;
import com.cse364.app.*;
import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.UserInfoValidationException;
import com.cse364.database.repositories.DBValidRepository;
import com.cse364.domain.*;

import com.cse364.infra.Config;
import com.cse364.cli.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class HttpController {
    private final AverageRatingService averageRatingService;
    private final RankingService rankingService;
    private final ValidationService validationService;
    private final RecommendByMovieService recommendByMovieService;

    private MovieRepository movies;

    @Autowired
    ValidRepository validRepository;

    /*
     * Config instance(Singleton) come from Beans of Spring
     */
    public HttpController(
            Config config
    ) {
        this.averageRatingService = config.averageRatingService;
        this.rankingService = config.rankingService;
        this.validationService = config.validationService;
        this.recommendByMovieService = config.recommendByMovieService;
        this.movies = config.movies;
    }

    private void checkDB(){
        if(!validRepository.isValid()){
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "Database is not completely loaded yet. Please wait about 10 minute.\n"
            );
        }
    }

    @RequestMapping(value={"", "/", "/index.html"})
    public String index(Model model) {
        checkDB();
        indexFill(model);
        return "index";
    }

    void indexFill(Model model) {
        //Top 10 all genres
        List<Movie> top10all = getTop10Movies("", "", "", "");
        top10all = posterPlaceholder(top10all);
        model.addAttribute("top10all", top10all);

        //Top 10 trending genres
        List<String> trendingGenres = List.of("Action", "Drama", "Animation");
        Map<String, List<Movie>> top10trending = new TreeMap<>();
        for (String genre : trendingGenres) {
            top10trending.put(genre, posterPlaceholder(getTop10Movies("", "", "", genre)));
        }
        model.addAttribute("top10trending", top10trending);
    }

    @RequestMapping("/users/recommendations.html")
    public String usersrecommendations(@RequestParam(name="gender", defaultValue="") String gender,
                                        @RequestParam(name="age", defaultValue="") String age,
                                        @RequestParam(name="occupation", defaultValue="") String occupation,
                                        @RequestParam(name="genres", defaultValue="") String genres,
                                        Model model) {
        checkDB();
        usersrecommendationsFill(gender, age, occupation, genres, model);
        return "usersrecommendations";
    }

    void usersrecommendationsFill(String gender, String age, String occupation, String genres, Model model) {

        UserInfo userInfo = null;
        List<Genre> genres_list = null;
        List<String> wrongInput = new ArrayList<>();

        try {
            userInfo = validationService.validateUserInfo(gender, age, occupation);
        } catch (UserInfoValidationException e) {
            if (e.getField().equals("gender")) {
                wrongInput.add("Invalid gender: \"" + e.getValue() + "\". Please enter either \"F\" or \"M\".");
            }
            if (e.getField().equals("age")) {
                wrongInput.add("Invalid age: \"" + e.getValue() + "\". Age should be a non-negative integer.");
            }
            if (e.getField().equals("occupation")) {
                wrongInput.add("Invalid occupation: \"" + e.getValue() + "\". " +
                        "Please check the \"Available Inputs\" page in the documentation.");
            }
        }

        try {
            genres_list = validationService.validateGenres(Arrays.asList(genres.split("\\|")));
        } catch (GenreValidationException e) {
            wrongInput.add("Invalid genre name: \"" + e.getName() + "\". " +
                    "Please check the \"Available Inputs\" page in the documentation.");
        }

        List<Movie> top10custom;
        try {
            top10custom = posterPlaceholder(rankingService.getTopNMovie(userInfo, 10, genres_list));
        } catch (Exception e) {
            top10custom = new ArrayList<>();
        }
        model.addAttribute("top10custom", top10custom);
        model.addAttribute("wrongInput", wrongInput);
    }

    @RequestMapping("/movies/recommendations.html")
    public String moviesrecommendations(@RequestParam(name="title", defaultValue="") String title, Model model) {
        checkDB();
        moviesrecommendationsFill(title, model);
        return "moviesrecommendations";
    }

    void moviesrecommendationsFill(String title, Model model) {

        // Top 10 search results
        List<Movie> top10custom;
        String wrong = "";
        try {
            top10custom = posterPlaceholder(recommendByMovieService.recommendMoviesFromTitle(title, 10));
        } catch (NoMovieWithGivenNameException exception) {
            top10custom = new ArrayList<>();
            if (!title.equals("")) {
                wrong = "A movie named \"" + title + "\" does not exist in the database. " +
                        "Note that you have to include the year information, e.g. \"Toy Story (1995)\".";
            }
        }
        model.addAttribute("top10custom", top10custom);
        model.addAttribute("wrong", wrong);
    }

    List<Movie> posterPlaceholder(List<Movie> movieList) {

        String magic_poster = "https://via.placeholder.com/100x150";

        List<Movie> newList = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getPoster().equals("")) {
                newList.add(new Movie(movie.getId(), movie.getTitle(), movie.getGenres(),
                        movie.getLink(), magic_poster));
            } else {
                newList.add(movie);
            }
        }
        
        return newList;
    }
    /*
     * Return recommendations from user input
     */
    @GetMapping("/users/recommendations")
    @ResponseBody
    public List<MovieDto> recommendationsByUserinfo(@RequestBody Map<String, String> jsonObject) {
        checkDB();
        String gender = jsonObject.get("gender");
        String age = jsonObject.get("age");
        String occupation = jsonObject.get("occupation");
        String genre = jsonObject.get("genres");

        if (gender == null || age == null || occupation == null || genre == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "At least one of gender, age, occupation or genres are not specified.\n"
            );
        }

        return getTop10Movies(gender, age, occupation, genre)
                .stream()
                .map(MovieDto::fromMovie)
                .collect(Collectors.toUnmodifiableList());
    }

    List<Movie> getTop10Movies(String gender, String age, String occupation, String genreNames) {
        // Validate user info and genre names
        UserInfo userInfo;
        List<Genre> genres;

        try {
            userInfo = validationService.validateUserInfo(gender, age, occupation);
        } catch (UserInfoValidationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format("Invalid user information for field %s: %s", e.getField(), e.getValue()));
        }

        try {
            genres = validationService.validateGenres(Arrays.asList(genreNames.split("\\|")));
        } catch (GenreValidationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format("Error : The genre %s does not exist in database\n", e.getName()));
        }

        List<Movie> topRank = rankingService.getTopNMovie(userInfo, 10, genres);

        return topRank;
    }

    @GetMapping("/movies/recommendations")
    @ResponseBody
    public List<MovieDto> recommendationsByMovie(@RequestBody Map<String, String> jsonObject) {
        checkDB();
        String title = jsonObject.get("title");

        if (title == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Title should be specified"
            );
        }
        int limit;
        try {
            if (jsonObject.containsKey("limit")) {
                limit = Integer.parseInt(jsonObject.get("limit"));
            } else {
                limit = 10;
            }
            
            if (limit <= 0) { throw new NumberFormatException(); }
        } catch (NumberFormatException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "'limit' input must be positive integer\n"
            );
        }

        List<Movie> movies;

        try {
            movies = recommendByMovieService.recommendMoviesFromTitle(title, limit);
        } catch (NoMovieWithGivenNameException exception) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST, String.format("Error : The movie %s does not exist in database\n", title)
           );
        }

        return movies
                .stream()
                .map(MovieDto::fromMovie)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/movies")
    @ResponseBody
    public List<MovieDto> getAllMovies() {
        checkDB();
        return movies.all()
                .stream()
                .map(MovieDto::fromMovie)
                .collect(Collectors.toUnmodifiableList());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Object handleError(ResponseStatusException exception){
        Map<String, String> jsonObject = new HashMap<>();

        jsonObject.put("message", exception.getReason());
        jsonObject.put("status", String.valueOf(exception.getStatus().value()));

        return ResponseEntity.status(exception.getStatus()).body(jsonObject);
    }

}
