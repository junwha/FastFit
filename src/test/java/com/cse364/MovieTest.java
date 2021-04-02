package com.cse364;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MovieTest {
    private Movie movie;

    @Before
    public void init() {
        movie = new Movie(
            1,
            "Toy Story",
            new String[]{"Animation", "Children's", "Comedy"}
        );
    }

    @Test
    public void testMovieHasGenre() {
        assertTrue(movie.hasGenre("Children's"));
        assertFalse(movie.hasGenre("Sci-Fi"));
    } 
}
