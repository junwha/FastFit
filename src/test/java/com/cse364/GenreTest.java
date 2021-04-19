package com.cse364;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenreTest {
    Genre testedObject1;
    Genre testedObject2;
    Genre testedObject3;

    @Before
    public void init(){
        testedObject1 = new Genre("Horror");
        testedObject2 = new Genre("Horror");
        testedObject3 = new Genre("Comedy");
    }

    @Test
    public void testConstructor(){
        assertEquals(testedObject1.getName(), testedObject2.getName());
    }

    @Test
    public void testEquals(){
        assertTrue(testedObject1.equals(testedObject2));
        assertFalse(testedObject1.equals(testedObject3));
    }


}
