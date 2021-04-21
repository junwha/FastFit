package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RatingTest {
    private Rating testObject1;
    private Rating testObject2;

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
        User user = new User(1, User.Gender.M, 20, new Occupation(1, "Student"), "00000");
        testObject1 = new Rating(movie, user, 5, 70072);
        testObject2 = new Rating(movie, user, 5, 70072);
    }

    @Test
    public void testConstructor(){
        assertSame(testObject1.getMovie(), testObject2.getMovie());
        assertSame(testObject1.getUser(), testObject2.getUser());
        assertEquals(testObject1.getRating(), testObject2.getRating());
        assertEquals(testObject1.getTimestamp(), testObject2.getTimestamp());
    }

}
