package com.cse364.app;

import java.util.List;
import com.cse364.domain.*;

public class AverageRatingService {
    private MovieRepository movies;
    private RatingRepository ratings;

    public AverageRatingService(MovieRepository movies, RatingRepository ratings) {
        this.movies = movies;
        this.ratings = ratings;
    }

    /**
     * Returns average rating for movies with specified genres,
     * rated by user having specified occupation.
     */
    public double averageRating(List<Genre> genres, Occupation occupation) throws NoRatingForGenreException {
        int ratingSum = 0;
        int ratingCnt = 0;

        for (Movie movie : movies.all()) {
            if (!movie.hasAllGenres(genres)) { continue; }

            //Check occupations of rating
            for (Rating rating : ratings.filterByMovie(movie)) {
                if (occupation.equals(rating.getUser().getOccupation())) {
                    ratingCnt++;
                    ratingSum += rating.getRating();
                }
            }
        }

        if (ratingCnt == 0) {
            throw new NoRatingForGenreException();
        }

        return Double.valueOf(ratingSum) / Double.valueOf(ratingCnt);
  }
}
