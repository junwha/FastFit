package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RatingTest {
    private Rating testObject1;
    private Movie movie;
    private User user;

    @Before
    public void init(){
        movie = new Movie(
                1,
                "Toy Story",
                new ArrayList<Genre>() {{
                    add(new Genre("Animation"));
                    add(new Genre("Children's"));
                    add(new Genre("Comedy"));
                }}
        );
        user = new User(1, User.Gender.M, 20, new Occupation(1, "Student"), "00000");
        testObject1 = new Rating(movie, user, 5, 70072);
    }

    @Test
    public void testConstructor(){
        assertSame(testObject1.getMovie(), movie);
        assertSame(testObject1.getUser(), user);
        assertEquals(testObject1.getRating(), 5);
        assertEquals(testObject1.getTimestamp(), 70072);
    }

}
