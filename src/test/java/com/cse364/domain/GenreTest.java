package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenreTest {
    private Genre testedObject1;
    private Genre testedObject2;
    private Genre testedObject3;

    @Before
    public void init(){
        testedObject1 = new Genre("Horror");
        testedObject2 = new Genre("Horror");
        testedObject3 = new Genre("Comedy");
    }

    @Test
    public void testConstructor(){
        assertEquals(testedObject1.getName(), "Horror");
    }

    @Test
    public void testEquals(){
        assertTrue(testedObject1.equals(testedObject2));
        assertFalse(testedObject1.equals(testedObject3));
    }


}
