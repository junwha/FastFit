package com.cse364.api;

import com.cse364.api.dtos.MovieDto;
import com.cse364.app.*;
import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.UserInfoValidationException;
import com.cse364.domain.Genre;
import com.cse364.domain.Movie;
import com.cse364.domain.MovieRepository;
import com.cse364.domain.UserInfo;
import com.cse364.domain.GenreRepository;

import com.cse364.infra.Config;
import com.cse364.cli.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@org.springframework.stereotype.Controller
public class HttpController {
    private final AverageRatingService averageRatingService;
    private final RankingService rankingService;
    private final ValidationService validationService;
    private final RecommendByMovieService recommendByMovieService;

    private MovieRepository movies;
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
    
    @RequestMapping(value={"", "/", "/index.html"})
    public String index(Model model) {

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
            wrongInput.add(e.getValue());
            //TODO:error
        }

        try {
            genres_list = validationService.validateGenres(Arrays.asList(genres.split("\\|")));
        } catch (GenreValidationException e) {
            wrongInput.add(e.getName());
            //TODO error
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
        moviesrecommendationsFill(title, model);
        return "moviesrecommendations";
    }

    void moviesrecommendationsFill(String title, Model model) {

        // Top 10 search results
        List<Movie> top10custom = new ArrayList<>();
        try {
            top10custom = posterPlaceholder(recommendByMovieService.recommendMoviesFromTitle(title, 10));
        } catch (NoMovieWithGivenNameException exception) {
            //TODO : Movie with given name not found
            return;
        }
        model.addAttribute("top10custom", top10custom);
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
        String gender = jsonObject.get("gender");
        String age = jsonObject.get("age");
        String occupation = jsonObject.get("occupation");
        String genre = jsonObject.get("genres");

        if (gender == null || age == null || occupation == null || genre == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "At least one of gender, age, occupation or genres are not specified.\n"
            );
        } 

        List<MovieDto> movies = new ArrayList<>();

        for(Movie movie : getTop10Movies(gender, age, occupation, genre)){
            movies.add(new MovieDto(movie.getTitle(), Controller.formatGenres(movie.getGenres(), "|"), movie.getLink()));
        }
        return movies;
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

        List<MovieDto> movies = new ArrayList<>();

        try {
           for (Movie movie : recommendByMovieService.recommendMoviesFromTitle(title, limit)) {
               movies.add(new MovieDto(movie.getTitle(), Controller.formatGenres(movie.getGenres(), "|"), movie.getLink()));
           }
        } catch (NoMovieWithGivenNameException exception) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST, String.format("Error : The movie %s does not exist in database\n", title)
           );
        }

        return movies;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movies.all();
    }
    @ExceptionHandler(ResponseStatusException.class)
    public Object handleError(ResponseStatusException exception){
        Map<String, String> jsonObject = new HashMap<>();

        jsonObject.put("message", exception.getReason());
        jsonObject.put("status", String.valueOf(exception.getStatus().value()));

        return ResponseEntity.status(exception.getStatus()).body(jsonObject);
    }

}
