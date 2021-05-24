package com.cse364.app;

import com.cse364.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendMoviesFromMovieService {
    private MovieRepository movieRepository;
    private RatingRepository ratingRepository;    


    public RecommendMoviesFromMovieService(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    /**
     * Returns a list of recommended similar movies from a movie,
     * list size is not bigger than limit,
     * ranked by how much the raters of the movie rated other movies
     */
    public List<Movie> recommendMoviesFromTitle(String title, Integer limit) throws NoMovieWithGivenNameException{
        Movie originalMovie = movieRepository.get(title);
        if (originalMovie == null) {
            throw new NoMovieWithGivenNameException(title);
        }

        List<Rating> originalMovieRatings = ratingRepository.filterByMovie(originalMovie);

        List<Rating> allRatings = getAllRatingsFromUsersOfSelectedRatings(originalMovieRatings);

        Map<Integer, MovieWithRatings> allMovies = collectAllMoviesFromRatings(allRatings);

        List<Movie> rankedMovies = rankMovies(allMovies, originalMovie, limit);

        return rankedMovies;
    }

    /**
     * Returns a list of all Ratings by users of selected ratings
     */
    List<Rating> getAllRatingsFromUsersOfSelectedRatings(List<Rating> ratings) {
        List<Rating> allRatings = new ArrayList<>();

        for (Rating rating : ratings) {
            allRatings.addAll(ratingRepository.filterByUser(rating.getUser()));
        }
        
        return allRatings;
    }

    /**
     * Returns a list of list of movies sorted by similarity with the original movie.
     */
    Map<Integer, MovieWithRatings> collectAllMoviesFromRatings(List<Rating> ratings){ 
        // MovieID -> movie with ratings
        Map<Integer, MovieWithRatings> allMovies = new HashMap<>();

        // Fill ratedMovies
        for (Rating rating : ratings) {
            int movieId = rating.getMovie().getId();
            if (allMovies.containsKey(movieId)) {
                allMovies.get(movieId).addRating(rating.getRating());
            } else {
                allMovies.put(movieId, 
                        new MovieWithRatings(rating.getMovie(), List.of(rating.getRating())));
            }
        }

        return allMovies;
    }

    /**
     * Returns a list of Movie sorted by average rating and similarity, 
     * from selected ratings
     * also present in rankingservice
     */
    List<Movie> rankMovies(Map<Integer, MovieWithRatings> movies, Movie originalMovie, Integer limit) {
        // Divide movies by their similarity to original movie
        Map<Integer, List<MovieWithRatings>> divideBySimilarity = new HashMap<>();
        int maxMatch = 0;

        for (MovieWithRatings mwr : movies.values()) {
            int matchingGenres = originalMovie.numOfMatchingGenres(mwr.movie);
            if (matchingGenres > maxMatch) { maxMatch = matchingGenres; }
            if (divideBySimilarity.containsKey(matchingGenres)) {
                divideBySimilarity.get(matchingGenres).add(mwr);
            } else {
                List<MovieWithRatings> newList = new ArrayList<>();
                newList.add(mwr);
                divideBySimilarity.put(matchingGenres, newList);
            }
        }

        // Rank each list by ratings
        for (List<MovieWithRatings> mwrList : divideBySimilarity.values()) {
            mwrList = mwrList
                    .stream()
                    .sorted(new CompareByAverageRating())
                    .collect(Collectors.toList());
            Collections.reverse(mwrList);
        }

        // Sum up to one list
        List<MovieWithRatings> rankedMovieWithRatings = new ArrayList<>();
        for (int i=maxMatch; i>-1; i--) {
            if (divideBySimilarity.containsKey(i)) {
                rankedMovieWithRatings.addAll(divideBySimilarity.get(i));
            }
        }

        // Cut to only Movie
        List<Movie> rankedMovies = new ArrayList<>();
        for (MovieWithRatings mwr : rankedMovieWithRatings) {
            if (rankedMovies.size() == limit) { break; }
            if (originalMovie.equals(mwr.movie)) { continue; }
            rankedMovies.add(mwr.movie);
        }

        return rankedMovies;
    }

}
