package com.cse364.domain;

import java.util.List;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MovieTest {
    private Movie movie;
    private List<Genre> genreList;

    @Before
    public void init() {
        genreList = List.of(
            new Genre("Animation"),
            new Genre("Children's"),
            new Genre("Comedy")
        );

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
