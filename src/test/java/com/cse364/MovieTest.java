package com.cse364;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MovieTest {
    private Movie movie;

    ArrayList<Genre> genreList;
    Genre[] genreArray;

    @Before
    public void init() {
        genreList = new ArrayList<Genre>() {{
            add(new Genre("Animation"));
            add(new Genre("Children's"));
            add(new Genre("Comedy"));
        }};

        genreArray = new Genre[]{
                new Genre("Comedy"),
                new Genre("Animation")
        };
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

        assertTrue(
                movie.hasGenres(new ArrayList<Genre>(Arrays.asList(
                        new Genre("Comedy"),
                        new Genre("Animation")
                )))
        );
        assertFalse(
                movie.hasGenres(new ArrayList<Genre>(Arrays.asList(
                        new Genre("Animation"),
                        new Genre("Sci-Fi")
                )))
        );
    }
}
