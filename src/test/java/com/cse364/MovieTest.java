package com.cse364;

import java.util.List;

import com.cse364.core.Genre;
import com.cse364.core.Movie;
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
            List.of(
                new Genre("Animation"),
                new Genre("Children's"),
                new Genre("Comedy")
            )
        );
    }

    @Test
    public void testMovieHasGenre() {
        assertTrue(
            movie.hasGenre(new Genre("Children's"))
        );
        assertFalse(
            movie.hasGenre(new Genre("Sci-Fi"))
        );
    }

    @Test
    public void testMovieHasGenres() {
        assertTrue(
            movie.hasGenres(new Genre[]{
                new Genre("Comedy"),
                new Genre("Animation"),
            })
        );
        assertFalse(
            movie.hasGenres(new Genre[]{
                new Genre("Animation"),
                new Genre("Sci-Fi"),
            })
        );
    }
}
