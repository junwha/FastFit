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
    
    private int countValidUserInfo(Gender a, Integer b, Occupation c) {
        int validNum = 0;
        if (a != null) {validNum += 1;}
        if (b>0) {validNum += 1;}
        if (c != null) {validNum += 1;}
        return validNum;
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
        //Iterate through rest of the List
        int validUserInfoCount = countValidUserInfo(userInfo.getGender(), userInfo.getAge(), userInfo.getOccupation());
        
        List<Gender> genVar= new ArrayList<>();
        List<Integer> ageVar = new ArrayList<>();
        List<Occupation> occVar = new ArrayList<>();
        if (userInfo.getGender() != null) {genVar.add(userInfo.getGender());}
        genVar.add(null);
        if (userInfo.getAge() != -1) {ageVar.add(userInfo.getAge());}
        ageVar.add(-1);
        if (userInfo.getOccupation() != null) {occVar.add(userInfo.getOccupation());}
        occVar.add(null);

        for (int i = validUserInfoCount-1; i >= 0; i--) {
            HashSet<User> secondarySimilarUser = new HashSet<>();
            for (Gender genderIter : genVar) {
                for (Integer ageIter : ageVar) {
                    for (Occupation occIter : occVar) {
                        if (countValidUserInfo(genderIter, ageIter, occIter) == i) {
                            for (User user : userRepository.filterSimilarUser(new UserInfo(genderIter, ageIter, occIter, "00000"))) {
                                secondarySimilarUser.add(user);
                            }
                        }
                    }
                }
            }

            List<Rating> secondaryRatingsBySimilarUser = new ArrayList<>();

            for (User user : secondarySimilarUser) {
                secondaryRatingsBySimilarUser.addAll(ratingRepository.filterByUser(user));
            }

            Map<Double, List<Integer>> secondaryRankingMap = getRankedMovieMapFromSelectRatings(secondaryRatingsBySimilarUser);
            
            for (List<Integer> movieIds : secondaryRankingMap.values()) {
                for (Integer movieId : movieIds) {
                    if (genres.isEmpty() || movieRepository.get(movieId).hasOneOfGenres(genres)) {
                        if (!movieRankingList.contains(movieRepository.get(movieId))) {
                            movieRankingList.add(movieRepository.get(movieId));
                        }
                    }
                    if (movieRankingList.size() >= N) {return movieRankingList;}
                }
            }
        }

        //You can't really reach this point... but anyways
        return movieRankingList;
    }
}
