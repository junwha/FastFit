package com.cse364.app;

import com.cse364.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendMoviesFromMovieService {
    private MovieRepository movieRepository;
    private RatingRepository ratingRepository;    


    public RecommendMoviesFromMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> recommendMoviesFromTitle(String title, Integer limit) {
        Movie refMovie = movieRepository.get(title);

        List<Genre> refGenres = refMovie.getGenres();

        List<Movie> moviesThatHaveGenres = getMoviesThatHaveGenres(refGenres);

        Map<Movie, Double> moviesRatings = new HashMap<>();

        for (Movie movie : moviesThatHaveGenres) {
            double rating = calculateAverageRatingOfAMovie(movie);
            moviesRatings.put(movie, rating);
        }

        int numOfMovies;
        if (limit == null) {
            numOfMovies = 10;
        } else {
            numOfMovies = limit;
        }

        List<Movie> recommendedMovies = rankMoviesByRatings(moviesRatings);

        if (recommendedMovies.size() > numOfMovies) {
            return recommendedMovies.subList(0, numOfMovies);
        } else {
            return recommendedMovies;
        }
    }

    List<Movie> getMoviesThatHaveGenres(List<Genre> genres) {
        List<Movie> movieWithGenres = new ArrayList<>();

        for (Movie movIter : movieRepository.all()) {
            if (movIter.hasAllGenres(genres)) {
                movieWithGenres.add(movIter);
            }
        }

        return movieWithGenres;
    }

    double calculateAverageRatingOfAMovie(Movie movie) {
        List<Rating> ratingsOfAMovie = ratingRepository.filterByMovie(movie);

        double sum = 0;
        for (Rating rating : ratingsOfAMovie) {
            sum += rating.getRating();
        }
        
        double average = sum / ratingsOfAMovie.size();
        return average;
    }

    List<Movie> rankMoviesByRatings(Map<Movie, Double> movieRatings) {
        List<Map.Entry<Movie, Double>> movieList = new ArrayList<>(movieRatings.entrySet());

        movieList.sort(Map.Entry.comparingByValue());
        Collections.reverse(movieList);
        List<Movie> movies = new ArrayList<>();

        for (Map.Entry<Movie, Double> entry : movieList) {
            movies.add(entry.getKey());
        }        

        return movies;
    }
}
