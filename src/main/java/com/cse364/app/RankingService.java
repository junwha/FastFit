package com.cse364.app;

import com.cse364.domain.*;

import java.util.*;

public class RankingService {
    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    public RankingService(MovieRepository movieRepository, UserRepository userRepository, RatingRepository ratingRepository){
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    private Map<Double, List<Integer>> getRankedMovieMapFromSelectRatings(List<Rating> ratings) {
        HashMap<Integer, List<Integer>> ratingsPerMovie = new HashMap<>();
        
        for (Rating rating : ratings) {
            if (ratingsPerMovie.containsKey(rating.getMovie().getId())) {
                ratingsPerMovie.get(rating.getMovie().getId()).add(rating.getRating());
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(rating.getRating());
                ratingsPerMovie.put(rating.getMovie().getId(), newList);
            }
        }

        Map<Double, List<Integer>> movieRankingMap = new TreeMap<>(Collections.reverseOrder());
        for (Integer movieId : ratingsPerMovie.keySet()) {
            List<Integer> numbers = ratingsPerMovie.get(movieId);
            int sum = 0;
            for (Integer num : numbers) {
                sum = sum + num;
            }
            double average = sum / numbers.size();
            if(!movieRankingMap.containsKey(average)) {
                movieRankingMap.put(average, new ArrayList<>());
            }
            movieRankingMap.get(average).add(movieId);
        }
        
        return movieRankingMap;
    }
    
    /*
     * Return Top N Movie rated by similar user
     */
    public List<Movie> getTopNMovie(UserInfo userInfo, int N, List<Genre> genres) {
        List<User> similarUser = userRepository.filterSimilarUser(userInfo);
        List<Rating> ratingsBySimilarUser = new ArrayList<>();
        
        for (User user : similarUser) {
            ratingsBySimilarUser.addAll(ratingRepository.filterByUser(user));
        }

        Map<Double, List<Integer>> movieRankingMap = getRankedMovieMapFromSelectRatings(ratingsBySimilarUser);
        
        List<Movie> movieRankingList = new ArrayList<>();
        for (List<Integer> movieIds : movieRankingMap.values()) {
            for (Integer movieId : movieIds) {
                if (genres.isEmpty() || movieRepository.get(movieId).hasOneOfGenres(genres)) {
                    movieRankingList.add(movieRepository.get(movieId));
                }
                
                if (movieRankingList.size() >= N) {return movieRankingList;}
            }
        }

        //At this point, First search with all given input gave less than N movies.

        return movieRankingList;        //TODO : implement less input results 
    }
}
