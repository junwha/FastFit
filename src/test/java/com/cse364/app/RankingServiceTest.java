package com.cse364.app;

import com.cse364.domain.*;
import com.cse364.infra.InMemoryMovieRepository;
import com.cse364.infra.InMemoryRatingRepository;
import com.cse364.infra.InMemoryUserRepository;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class RankingServiceTest {
    private RankingService service;
    private List<Movie> ratedBySimilarUsers;
    private List<Movie> ratedByNotSimilarUsers;

    @Before
    public void init(){
        InMemoryRatingRepository ratingStorage = new InMemoryRatingRepository();
        InMemoryMovieRepository movieStorage = new InMemoryMovieRepository();
        InMemoryUserRepository userStorage = new InMemoryUserRepository();

        List<User> similarUsers = List.of(
                new User(1, User.Gender.M, 25, new Occupation(1, "others"), "00000"),
                new User(2, User.Gender.M, 25, new Occupation(1, "others"), "00000"),
                new User(3, User.Gender.M, 25, new Occupation(1, "others"), "00000")
        );
        List<User> notSimilarUsers = List.of(
                new User(4, User.Gender.F, 25, new Occupation(1, "others"), "00000"),
                new User(5, User.Gender.M, 35, new Occupation(1, "others"), "00000"),
                new User(6, User.Gender.M, 25, new Occupation(2, "X"), "00000")
        );

        ratedBySimilarUsers = List.of(
                new Movie(1, "A", List.of(new Genre("X"), new Genre("Y"), new Genre("Z"))),
                new Movie(2, "B", List.of(new Genre("X"), new Genre("Y"))),
                new Movie(3, "C", List.of(new Genre("Z"))),
                new Movie(4, "D", List.of(new Genre("X"), new Genre("Y"), new Genre("Z"))),
                new Movie(5, "E", List.of(new Genre("X"), new Genre("Y"))),
                new Movie(6, "F", List.of(new Genre("Z"))),
                new Movie(7, "G", List.of(new Genre("X"), new Genre("Y"), new Genre("Z"))),
                new Movie(8, "H", List.of(new Genre("X"), new Genre("Y"))),
                new Movie(9, "I", List.of(new Genre("X"))),
                new Movie(10, "J", List.of(new Genre("X"), new Genre("Y"), new Genre("Z")))
        );

        ratedByNotSimilarUsers = List.of(
                new Movie(11, "K", List.of(new Genre("X"), new Genre("Y"))),
                new Movie(12, "L", List.of(new Genre("X")))
        );

        for(Movie movie : ratedBySimilarUsers){
            movieStorage.add(movie);
        }

        for(Movie movie : ratedByNotSimilarUsers){
            movieStorage.add(movie);
        }

        for(User user : similarUsers) {
            userStorage.add(user);
            ratingStorage.add(new Rating(ratedBySimilarUsers.get(0), user, 5, 0));
            ratingStorage.add(new Rating(ratedBySimilarUsers.get(9), user, 1, 0));

            for(Movie movie : ratedBySimilarUsers.subList(1, 9)){
                ratingStorage.add(new Rating(movie, user, 3, 0));
            }
            for(Movie movie : ratedByNotSimilarUsers){
                ratingStorage.add(new Rating(movie, user, 1, 0));
            }
        }

        for(User user : notSimilarUsers) {
            userStorage.add(user);
            for(Movie movie : ratedByNotSimilarUsers){
                ratingStorage.add(new Rating(movie, user, 5, 0));
            }
            for(Movie movie : ratedBySimilarUsers){
                ratingStorage.add(new Rating(movie, user, 1, 0));
            }
        }

        service = new RankingService(movieStorage, userStorage, ratingStorage);
    }

    @Test
    public void testGetTop10MovieByUser(){
        assertEquals(ratedBySimilarUsers.get(0).getId(),
                service.getTop10Movie(new User(1, User.Gender.M, 27, new Occupation(1, "others"), "00000")).get(0).getId());
        assertEquals(ratedBySimilarUsers.get(9).getId(),
                service.getTop10Movie(new User(1, User.Gender.M, 27, new Occupation(1, "others"), "00000")).get(9).getId());
    }

    @Test
    public void testGetTop10MovieByGenres(){
        List<Movie> movieRanking = service.getTop10Movie(new User(1, User.Gender.M, 27, new Occupation(1, "others"), "00000"), List.of(new Genre("X"), new Genre("Y")));
        for(Movie movie : movieRanking){
            assertTrue(movie.hasOneOfGenres(List.of(new Genre("X"), new Genre("Y"))));
        }

    }

//    rankingService.getTop10Movie(
//            new User(1,User.Gender.M, 14,DataLoader.occupations.get(1), "00000"),
//            List.of(DataLoader.genres.searchByName("Horror"), DataLoader.genres.searchByName("Childrens"))
}
