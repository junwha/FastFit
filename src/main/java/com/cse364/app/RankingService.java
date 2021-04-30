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

    private List<Movie> getRankedMovieListFromSelectRatings(List<Rating> ratings) {
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
        
        List<Movie> movieRankingList = new ArrayList<>();
        for(List<Integer> movieIds : movieRankingMap.values()) {
            for(Integer movieId : movieIds) {
                movieRankingList.add(movieRepository.get(movieId));
            }
        }

        return movieRankingList;
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

        List<Movie> movieRanking = getRankedMovieListFromSelectRatings(ratingsBySimilarUser);

        if (!genres.isEmpty()) {
            for (int i = 0; i < movieRanking.size(); i++) {
                if (!movieRanking.get(i).hasOneOfGenres(genres)) {
                    movieRanking.remove(i);
                    i = i - 1;
                }
            }
        }

        if (movieRanking.size() >= N) {
            return movieRanking.subList(0, N);
        } else {
            return movieRanking;
        }
    }
}
