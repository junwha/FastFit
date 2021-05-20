package com.cse364.infra;

import com.cse364.app.AverageRatingService;
import com.cse364.app.NoRatingForGenreException;
import com.cse364.app.RankingService;
import com.cse364.app.ValidationService;
import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.OccupationValidationException;
import com.cse364.app.exceptions.UserInfoValidationException;
import com.cse364.domain.Genre;
import com.cse364.domain.Movie;
import com.cse364.domain.Occupation;
import com.cse364.domain.UserInfo;

import java.util.Arrays;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HTTPController {
    private final AverageRatingService averageRatingService;
    private final RankingService rankingService;
    private final ValidationService validationService;

    public HTTPController(
            AverageRatingService averageRatingService,
            RankingService rankingService,
            ValidationService validationService
    ) {
        this.averageRatingService = averageRatingService;
        this.rankingService = rankingService;
        this.validationService = validationService;
    }

    @GetMapping("/users/recommendations")
    public List<Movie> recommendations(@RequestParam(value = "gender", defaultValue = "") String gender,
                     @RequestParam(value = "age", defaultValue = "") String age,
                     @RequestParam(value = "occupation", defaultValue = "") String occupation,
                     @RequestParam(value="genreNames", defaultValue="") String genreNames) {

        return getTop10Movies(gender, age, occupation, genreNames);
    }

    void getAverageRating(String genreNames, String occupationName) {
        // Search and validate genre/occupation
        List<Genre> genres;
        Occupation occupation;
        try {
            genres = validationService.validateGenres(Arrays.asList(genreNames.split("\\|")));
            occupation = validationService.validateOccupation(occupationName);
        } catch(GenreValidationException e) {
            System.out.format("Error : The genre %s does not exist in database\n", e.getName());
            return;
        } catch(OccupationValidationException e) {
            System.out.format("Error : The occupation %s does not exist in database\n", e.getName());
            return;
        }

        // Get the average rating
        try {
            double average = averageRatingService.averageRating(genres, occupation);

            System.out.format("Average rating of movies with genres [%s]\n", formatGenres(genres, ", "));
            System.out.format("rated by people with occupation [%s]\n", occupation.getName());
            System.out.format("is [%f].\n", average);
        } catch (NoRatingForGenreException e) {
            System.out.format(
                    "Error : There were no ratings given to movies with genre [%s] by people with occupation [%s]\n",
                    formatGenres(genres, ", "), occupation.getName()
            );
        }
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

    /**
     * return String of genres combined with divider
     */
    String formatGenres(List<Genre> genres, String divider) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            sb.append(genres.get(i).getName());
            if (i < genres.size() - 1) { sb.append(divider); }
        }
        return sb.toString();
    }
}
