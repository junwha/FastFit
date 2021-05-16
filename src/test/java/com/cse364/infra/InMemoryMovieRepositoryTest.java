package com.cse364.infra;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.cse364.domain.*;

public class InMemoryMovieRepositoryTest {
    private InMemoryMovieRepository storage;
    private List<Movie> movies = List.of(
            new Movie(1, "A", null, "link1"),
            new Movie(2, "B", null, "link1")
    );

    @Before
    public void init() {
        storage = new InMemoryMovieRepository();
    }

    @Test
    public void testAdd(){
        storage.add(movies.get(0));
        assertEquals(storage.get(1), movies.get(0));
    }

    @Test
    public void testGetByTitle() {
        for(Movie movie : movies) {
            storage.add(movie);
        }

        assertEquals(storage.get("A"), movies.get(0));
        assertEquals(storage.get("B"), movies.get(1));
        assertNull(storage.get("ABADACADABRA"));
    }

    @Test
    public void testAll(){
        for(Movie movie : movies){
            storage.add(movie);
        }

        assertEquals(movies, storage.all());
    }
}
