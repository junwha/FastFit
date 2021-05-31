package com.cse364.api;

import com.cse364.api.dtos.MovieDto;
import com.cse364.app.*;
import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.UserInfoValidationException;
import com.cse364.domain.Genre;
import com.cse364.domain.Movie;
import com.cse364.domain.UserInfo;

import com.cse364.infra.Config;
import com.cse364.cli.Controller;
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
    }
    
    @RequestMapping("/")
    public String index() {
        return "index.html";
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
    @ExceptionHandler(ResponseStatusException.class)
    public Object handleError(ResponseStatusException exception){
        Map<String, String> jsonObject = new HashMap<>();

        jsonObject.put("message", exception.getReason());
        jsonObject.put("status", String.valueOf(exception.getStatus().value()));

        return ResponseEntity.status(exception.getStatus()).body(jsonObject);
    }

}
