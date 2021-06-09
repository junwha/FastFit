package com.cse364.app;

import com.cse364.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class RankingService {
    private MovieRepository movieRepository;
    private RatingRepository ratingRepository;
    private UserService userService;

    public RankingService(
            MovieRepository movieRepository,
            RatingRepository ratingRepository,
            UserService userService
    ) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    /**
     * Returns a list of MovieWithRatings,
     * sorted by their average ratings (in descending order)
     * given the ratings information.
     */
    List<MovieWithRatings> rankMovies(List<Rating> ratings) {
        // movie id -> movie with ratings
        Map<Integer, MovieWithRatings> ratedMovies = new HashMap<>();

        // Fill ratedMovies
        for (Rating rating : ratings) {
            int movieId = rating.getMovie().getId();
            if (ratedMovies.containsKey(movieId)) {
                ratedMovies.get(movieId).addRating(rating.getRating());
            } else {
                ratedMovies.put(movieId, new MovieWithRatings(
                        rating.getMovie(),
                        List.of(rating.getRating())
                ));
            }
        }

        // Sort movies by average rating
        List<MovieWithRatings> rankedMovies = ratedMovies.values()
                .stream()
                .sorted(new CompareByAverageRating())
                .collect(Collectors.toList());

        Collections.reverse(rankedMovies);
        return rankedMovies;
    }

    /**
     * Return Top N Movie rated by similar user
     */
    public List<Movie> getTopNMovie(UserInfo userInfo, int N, List<Genre> genres) {
        List<User> similarUser = userService.getSimilarUsers(userInfo, 3);
        List<Rating> ratingsBySimilarUser = new ArrayList<>();

        // Iterating all users is time consuming, so let's sample some of them.
        List<User> sampledUsers = new ArrayList<>(similarUser);
        Collections.shuffle(sampledUsers, new Random(12345));

        for (int i = 0; i < Math.min(similarUser.size(), 100); i++) {
            ratingsBySimilarUser.addAll(ratingRepository.filterByUser(sampledUsers.get(i)));
        }

        List<MovieWithRatings> rankedMovies = rankMovies(ratingsBySimilarUser);
        List<Movie> topNMovies = new ArrayList<>();

        for (MovieWithRatings movieWithRatings : rankedMovies) {
            Movie movie = movieWithRatings.movie;
            if (genres.isEmpty() || movie.hasOneOfGenres(genres)) {
                topNMovies.add(movie);
            }

            if (topNMovies.size() >= N) { return topNMovies; }
        }

        List<Movie> secondaryNMovies = secondaryTopNMovie(userInfo, N, genres);

        for (Movie movie : secondaryNMovies) {
            if (!topNMovies.contains(movie)) {
                topNMovies.add(movie);
            }
            
            if (topNMovies.size() >= N) { return topNMovies; }
        }

        //You really should't reach this point, but anyways...
        return topNMovies;
    }

    /**
     * When getTopNMovie couldn't find N movies with all matching userInfo
     */
    List<Movie> secondaryTopNMovie(UserInfo userInfo, int N, List<Genre> genres) {
        List<Movie> secondaryNMovies = new ArrayList<>();

        for (int similarity = 3 - 1; similarity >= 0; similarity--) {
            Set<User> secondarySimilarUser = new HashSet<>(userService.getSimilarUsers(userInfo, similarity));

            List<Rating> secondaryRatingsBySimilarUser = new ArrayList<>();

            for (User user : secondarySimilarUser) {
                secondaryRatingsBySimilarUser.addAll(ratingRepository.filterByUser(user));
            }

            List<MovieWithRatings> rankedMovies2 = rankMovies(secondaryRatingsBySimilarUser);

            for (MovieWithRatings movieWithRatings : rankedMovies2) {
                Movie movie = movieWithRatings.movie;
                if (genres.isEmpty() || movie.hasOneOfGenres(genres)) {
                    if (!secondaryNMovies.contains(movie)) {
                        secondaryNMovies.add(movie);
                    }
                }

                if (secondaryNMovies.size() >= N) { return secondaryNMovies; }
            }
        }

        //You really can't reach this point...but anyways
        return secondaryNMovies;
    }
}
