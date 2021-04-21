package com.cse364.domain;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MovieTest {
    private Movie movie;
    private ArrayList<Genre> genreList;

    @Before
    public void init() {
        genreList = new ArrayList<Genre>() {{
            add(new Genre("Animation"));
            add(new Genre("Children's"));
            add(new Genre("Comedy"));
        }};

        movie = new Movie(
                1,
                "Toy Story",
                genreList
        );
    }

    @Test
    public void testConstructor() {
        assertEquals(movie.getId(), 1);
        assertEquals(movie.getTitle(), "Toy Story");
        assertEquals(movie.getGenres(), genreList);
    }

    @Test
    public void testHasGenre() {
        assertTrue(
                movie.hasGenre(new Genre("Children's"))
        );
        assertFalse(
                movie.hasGenre(new Genre("Sci-Fi"))
        );
    }
}
