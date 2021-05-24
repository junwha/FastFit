package com.cse364.app;

import com.cse364.domain.*;
import com.cse364.infra.InMemoryMovieRepository;
import com.cse364.infra.InMemoryRatingRepository;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;

public class RecommendMoviesFromMovieServiceTest {
    private RecommendMoviesFromMovieService service;
    private List<Movie> movies;
    private List<User> users;
    private Movie distinctMovie;
    private User distinctUser;

    @Before
    public void init() {
        InMemoryRatingRepository ratingStorage = new InMemoryRatingRepository();
        InMemoryMovieRepository movieStorage = new InMemoryMovieRepository();

        movies = List.of(
                new Movie(1, "A", List.of(new Genre("X"), new Genre("Y"), new Genre("Z")), "link"),
                new Movie(2, "B", List.of(new Genre("X"), new Genre("Y")), "link"),
                new Movie(3, "C", List.of(new Genre("Z")), "link"),
                new Movie(4, "D", List.of(new Genre("X"), new Genre("Y"), new Genre("Z")), "link"),
                new Movie(5, "E", List.of(new Genre("X"), new Genre("Y")), "link"),
                new Movie(6, "F", List.of(new Genre("Z")), "link"),
                new Movie(7, "G", List.of(new Genre("X"), new Genre("Y"), new Genre("Z")), "link"),
                new Movie(8, "H", List.of(new Genre("X"), new Genre("Y")), "link"),
                new Movie(9, "I", List.of(new Genre("X")), "link"),
                new Movie(10, "J", List.of(new Genre("X"), new Genre("Y"), new Genre("Z")), "link"),
                new Movie(11, "K", List.of(new Genre("X"), new Genre("Y")), "link"),
                new Movie(12, "L", List.of(new Genre("X")), "link")
        );


        for (Movie movie : movies) {
            movieStorage.add(movie);
        }
        distinctMovie = new Movie(13, "M", List.of(new Genre("X"), new Genre("Y"), new Genre("Z")), "zelda");
        movieStorage.add(distinctMovie);

        users = new ArrayList<User>();
        for (int i=1;i<21;i++) {
            User someUser = new User(1, Gender.M, 25, new Occupation(1, "others"), "00000");
            users.add(someUser);
        }
        distinctUser = new User(21, Gender.F, 35, new Occupation(1, "others"), "00000");

        for (User user : users) {
            for (Movie movie : movies) {
                ratingStorage.add(new Rating(movie, user, 3, 0));
            }
        }
        ratingStorage.add(new Rating(distinctMovie, distinctUser, 3, 0));
        ratingStorage.add(new Rating(movies.get(0), distinctUser, 3, 0));
        ratingStorage.add(new Rating(movies.get(2), users.get(0), 4, 0));

        service = new RecommendMoviesFromMovieService(movieStorage, ratingStorage);
    }

    @Test
    public void recommendMoviesFromTitleTest() {
        // The one rated distinctMovie rated movie A only.
        assertEquals(service.recommendMoviesFromTitle(distinctMovie.getTitle(), 10), 
                List.of(movies.get(0)));
        assertEquals(service.recommendMoviesFromTitle(movies.get(0).getTitle(), 9).get(8),
                movies.get(2));
    }
}
