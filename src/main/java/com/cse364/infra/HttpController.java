package com.cse364.infra;

import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.UserInfoValidationException;
import com.cse364.domain.Genre;
import com.cse364.domain.Movie;
import com.cse364.domain.UserInfo;

import com.cse364.app.AverageRatingService;
import com.cse364.app.RankingService;
import com.cse364.app.ValidationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Movie> recommendations(@RequestParam(value = "gender", defaultValue = "") String gender,
                                       @RequestParam(value = "age", defaultValue = "") String age,
                                       @RequestParam(value = "occupation", defaultValue = "") String occupation,
                                       @RequestParam(value="genreNames", defaultValue="") String genreNames) {
        //@RequestPram link GET parameter to method parameter
        return getTop10Movies(gender, age, occupation, genreNames);
    }

    List<Movie> getTop10Movies(String gender, String age, String occupation, String genreNames) {
        // Validate user info and genre names
        UserInfo userInfo;
        List<Genre> genres;

        try {
            userInfo = validationService.validateUserInfo(gender, age, occupation);
        } catch (UserInfoValidationException e) {
            System.out.format("Invalid user information for field %s: %s", e.getField(), e.getValue());
            return List.of();
        }

        try {
            genres = validationService.validateGenres(Arrays.asList(genreNames.split("\\|")));
        } catch (GenreValidationException e) {
            System.out.format("Error : The genre %s does not exist in database\n", e.getName());
            return List.of();
        }

        List<Movie> topRank = rankingService.getTopNMovie(userInfo, 10, genres);

        return topRank;
    }
}
