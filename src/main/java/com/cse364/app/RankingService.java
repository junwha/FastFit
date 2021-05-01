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
    private List<MovieWithRatings> rankMovies(List<Rating> ratings) {
        // movie id -> movie with ratings
        HashMap<Integer, MovieWithRatings> ratedMovies = new HashMap<>();

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

        List<MovieWithRatings> rankedMovies = rankMovies(ratingsBySimilarUser);
        List<Movie> topNMovies = new ArrayList<>();

        for (MovieWithRatings movieWithRatings : rankedMovies) {
            Movie movie = movieWithRatings.movie;
            if (genres.isEmpty() || movie.hasOneOfGenres(genres)) {
                topNMovies.add(movie);
            }

            if (topNMovies.size() >= N) { return topNMovies; }
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

            List<MovieWithRatings> rankedMovies2 = rankMovies(secondaryRatingsBySimilarUser);

            for (MovieWithRatings movieWithRatings : rankedMovies2) {
                Movie movie = movieWithRatings.movie;
                if (genres.isEmpty() || movie.hasOneOfGenres(genres)) {
                    if (!topNMovies.contains(movie)) {
                        topNMovies.add(movie);
                    }
                }

                if (topNMovies.size() >= N) { return topNMovies; }
            }
        }

        //You can't really reach this point... but anyways
        return topNMovies;
    }
}
