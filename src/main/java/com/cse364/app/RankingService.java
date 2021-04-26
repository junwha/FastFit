package com.cse364.app;

import com.cse364.domain.*;
import com.cse364.infra.InMemoryMovieRepository;
import com.cse364.infra.InMemoryRatingRepository;

import java.util.*;

public class RankingService {
    private MovieRepository movieRepository; // Movie Repository can be changed from initialized one
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    public RankingService(MovieRepository movieRepository, UserRepository userRepository, RatingRepository ratingRepository){
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    private List<Movie> averageRating(RatingRepository ratingRepository) {
        // This map sort by key. High ratings saved to the front of map
        // KEY: AverageRating / VALUE: Movie
        Map<Double, List<Movie>> movieRankingMap = new TreeMap<>(Collections.reverseOrder());
        for(Movie movie : movieRepository.all()) {
            double average = ratingRepository.filterByMovie(movie).stream().mapToInt(value -> value.getRating()).average().orElse(0.0);
            // Check whether duplicate key exists
            if(!movieRankingMap.containsKey(average)) {
                movieRankingMap.put(average, new ArrayList<>());
            }
            movieRankingMap.get(average).add(movie);
        }

        List<Movie> movieRankingList = new ArrayList<>();

        // Flatten
        for(List<Movie> movies : movieRankingMap.values()) {
            for(Movie movie : movies){
                movieRankingList.add(movie);
            }
        }

        return movieRankingList;
    }

    /*
     * Return Top 10 Movie rated By Similar User
     */
    public List<Movie> getTop10Movie(User user) {
        List<User> similarUser = userRepository.filterSimilarUser(user);
        List<Rating> ratingsBySimilarUser = new ArrayList<>();

        for(User userEntity : similarUser) {
            ratingsBySimilarUser.addAll(ratingRepository.filterByUser(userEntity));
        }

        InMemoryRatingRepository ratingBySimilarUserRepository = new InMemoryRatingRepository(ratingsBySimilarUser);
        List<Movie> movieRanking = averageRating(ratingBySimilarUserRepository);

        if(movieRanking.size() >= 10){
            return movieRanking.subList(0, 10);
        }else{
            return movieRanking; // TODO: Delete this code and implement exception
        }

    }

    /*
     * Return Top 10 Movie which has one of the given genres rated By Similar User
     */
    public List<Movie> getTop10Movie(User user, List<Genre> genres){
        InMemoryMovieRepository filteredByGenreRepository = new InMemoryMovieRepository();

        // Filter By Genres
        for(Movie movie : movieRepository.all()){
            if(movie.hasOneOfGenres(genres)){
                filteredByGenreRepository.add(movie);
            }
        }

        this.movieRepository = filteredByGenreRepository;

        return getTop10Movie(user);
    }
}
