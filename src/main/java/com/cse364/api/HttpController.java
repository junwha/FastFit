package com.cse364.api;

import com.cse364.api.dtos.MovieDto;
import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.UserInfoValidationException;
import com.cse364.domain.Genre;
import com.cse364.domain.Movie;
import com.cse364.domain.UserInfo;

import com.cse364.app.AverageRatingService;
import com.cse364.app.RankingService;
import com.cse364.app.ValidationService;

import com.cse364.infra.Config;
import com.cse364.cli.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HttpController {
    private final AverageRatingService averageRatingService;
    private final RankingService rankingService;
    private final ValidationService validationService;
    /*
     * Config instance(Singleton) come from Beans of Spring
     */
    public HttpController(
            Config config
    ) {
        this.averageRatingService = config.averageRatingService;
        this.rankingService = config.rankingService;
        this.validationService = config.validationService;
    }

    /*
     * Return recommendations from user input
     */
    @GetMapping("/users/recommendations")
    public List<MovieDto> recommendations(@RequestParam(value = "gender", defaultValue = "") String gender,
                                          @RequestParam(value = "age", defaultValue = "") String age,
                                          @RequestParam(value = "occupation", defaultValue = "") String occupation,
                                          @RequestParam(value="genreNames", defaultValue="") String genreNames) {
        //@RequestPram link GET parameter to method parameter

        List<MovieDto> movies = new ArrayList<>();

        for(Movie movie : getTop10Movies(gender, age, occupation, genreNames)){
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

    @ExceptionHandler(ResponseStatusException.class)
    public Object handleError(Exception exception){
        return exception.getMessage();
    }

}
