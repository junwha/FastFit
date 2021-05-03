package com.cse364.app;

import com.cse364.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class RankingService {
    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    public RankingService(MovieRepository movieRepository, UserRepository userRepository, RatingRepository ratingRepository){
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
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
    
    int countValidUserInfo(Gender a, Integer b, Occupation c) {
        int validNum = 0;
        if (a != null) {validNum += 1;}
        if (b>0) {validNum += 1;}
        if (c != null) {validNum += 1;}
        return validNum;
    }

    /**
     * Return Top N Movie rated by similar user
     */
    public List<Movie> getTopNMovie(UserInfo userInfo, int N, List<Genre> genres) {
        List<User> similarUser = userRepository.filterSimilarUser(userInfo);
        List<Rating> ratingsBySimilarUser = new ArrayList<>();
        
        for (User user : similarUser) {
            ratingsBySimilarUser.addAll(ratingRepository.filterByUser(user));
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
     * Return HashSet of users with i similar characteristics based on given subsetmaking Sets
     */   
    Set<User> findUserOfiSimilarUserInfo(Set<Gender> genVar, Set<Integer> ageVar, Set<Occupation> occVar, int i) {
        Set<User> users = new HashSet<>();
        for (Gender genderIter : genVar) {
            for (Integer ageIter : ageVar) {
                for (Occupation occIter : occVar) {
                    if (countValidUserInfo(genderIter, ageIter, occIter) == i) {
                        UserInfo subSimilarUser = new UserInfo(genderIter, ageIter, occIter, "00000");
                        List<User> similarUsers = userRepository.filterSimilarUser(subSimilarUser);
                        users.addAll(similarUsers);
                    }
                }
            }
        }
        return users;
    }

    /**
     * When getTopNMovie couldn't find N movies with all matching userInfo
     */
    List<Movie> secondaryTopNMovie(UserInfo userInfo, int N, List<Genre> genres) {
        int validUserInfoCount = countValidUserInfo(userInfo.getGender(), userInfo.getAge(), userInfo.getOccupation());
        
        Set<Gender> genVar= new HashSet<>();
        Set<Integer> ageVar = new HashSet<>();
        Set<Occupation> occVar = new HashSet<>();
        genVar.add(userInfo.getGender());
        genVar.add(null);
        ageVar.add(userInfo.getAge());
        ageVar.add(-1);
        occVar.add(userInfo.getOccupation());
        occVar.add(null);

        List<Movie> secondaryNMovies = new ArrayList<>();

        for (int i = validUserInfoCount-1; i >= 0; i--) {
            Set<User> secondarySimilarUser = findUserOfiSimilarUserInfo(genVar, ageVar, occVar, i);

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
