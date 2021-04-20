package com.cse364;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RatingTest {
    Rating testObject1;
    Rating testObject2;

    @Before
    public void init(){
        Movie movie = new Movie(
                1,
                "Toy Story",
                new ArrayList<Genre>() {{
                    add(new Genre("Animation"));
                    add(new Genre("Children's"));
                    add(new Genre("Comedy"));
                }}
        );
        User user = new User(1, Gender.M, 20, new Occupation(1, "Student"), "00000");
        testObject1 = new Rating(movie, user, 5, 70072);
        testObject2 = new Rating(movie, user, 5, 70072);
    }

    @Test
    public void testConstructor(){
        assertSame(testObject1.movie, testObject2.movie);
        assertSame(testObject1.user, testObject2.user);
        assertEquals(testObject1.rating, testObject2.rating);
        assertEquals(testObject1.timestamp, testObject2.timestamp);
    }

}
