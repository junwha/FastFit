package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenreTest {
    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    @Before
    public void init(){
        genre1 = new Genre("Horror");
        genre2 = new Genre("Horror");
        genre3 = new Genre("Comedy");
    }

    @Test
    public void testConstructor(){
        assertEquals(genre1.getName(), "Horror");
    }

    @Test
    public void testEquals(){
        assertTrue(genre1.equals(genre2));
        assertFalse(genre1.equals(genre3));
    }
}
