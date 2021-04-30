package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RatingTest {
    private Rating rating1;
    private Movie movie;
    private User user;

    @Before
    public void init(){
        movie = new Movie(
            1,
            "Toy Story",
            List.of(
                new Genre("Animation"),
                new Genre("Children's"),
                new Genre("Comedy")
            )
        );
        user = new User(1, Gender.M, 20, new Occupation(1, "Student"), "00000");
        rating1 = new Rating(movie, user, 5, 70072);
    }

    @Test
    public void testConstructor(){
        assertSame(rating1.getMovie(), movie);
        assertSame(rating1.getUser(), user);
        assertEquals(rating1.getRating(), 5);
        assertEquals(rating1.getTimestamp(), 70072);
    }
}
