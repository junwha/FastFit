package com.cse364.app;

import com.cse364.domain.*;
import com.cse364.infra.InMemoryMovieRepository;
import com.cse364.infra.InMemoryRatingRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AverageRatingServiceTest {
    private AverageRatingService service;

    @Before
    public void init(){
        InMemoryRatingRepository ratingStorage = new InMemoryRatingRepository();
        InMemoryMovieRepository movieStorage = new InMemoryMovieRepository();

        List<User> users = List.of(
                new User(1, Gender.M, 20, new Occupation(1, "X"), "10000"),
                new User(2, Gender.F, 55, new Occupation(2, "Y"), "10001")
        );

        List<Movie> movies = List.of(
                new Movie(1, "Toy Story", List.of(new Genre("A"), new Genre("B"), new Genre("C"))),
                new Movie(2, "Jumanji", List.of(new Genre("A"), new Genre("B"))),
                new Movie(3, "Grumpier Old Men", List.of(new Genre("C")))
        );

        for(Movie movie : movies){
            movieStorage.add(movie);
        }

        //Combination of all possible rating by user1 and user2 on movie1 and movie2
        ratingStorage.add(new Rating(movies.get(0), users.get(0), 4, 0));
        ratingStorage.add(new Rating(movies.get(1), users.get(0), 2, 0));
        ratingStorage.add(new Rating(movies.get(2), users.get(0), 3, 0));
        ratingStorage.add(new Rating(movies.get(0), users.get(1), 1, 0));
        ratingStorage.add(new Rating(movies.get(1), users.get(1), 5, 0));
        ratingStorage.add(new Rating(movies.get(2), users.get(1), 0, 0));

        service = new AverageRatingService(movieStorage, ratingStorage);
    }

    @Test(expected=Test.None.class)
    public void testAverageRating() throws NoRatingForGenreException{
        assertTrue(service.averageRating(List.of(new Genre("A"), new Genre("B")), new Occupation(1, "X")) == Double.valueOf((4+2)/2));
    }

    @Test(expected=NoRatingForGenreException.class)
    public void testAverageRatingNullByGenre() throws NoRatingForGenreException {
        service.averageRating(List.of(new Genre("D")), new Occupation(1,"X"));
    }

//    TODO: Delete this part or implement NoRatingForUserException
//    @Test(expected=NoRatingForGenreException.class)
//    public void testAverageRatingNullByOccupation() throws NoRatingForGenreException {
//        service.averageRating(List.of(new Genre("A")), new Occupation(1,"Z"));
//    }

}
