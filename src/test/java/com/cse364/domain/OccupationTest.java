package com.cse364.domain;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;


public class OccupationTest {
    @Test
    public void testConstructor(){
        int id = 3;
        String name = "Admin";
        Occupation testObject1 = new Occupation(id, name);
        Occupation testObject2 = new Occupation(id, name);
        assertSame(testObject1.getId(), testObject2.getId());
        assertSame(testObject1.getName(), testObject2.getName());

        testObject2 = new Occupation(0, "Others");
        assertNotSame(testObject1.getId(), testObject2.getId());
        assertNotSame(testObject1.getName(), testObject2.getName());
    }

    @Test
    public void testEquals(){
        assertTrue(new Occupation(3, "Clerical").equals(new Occupation(3, "Admin")));
        assertFalse(new Occupation(2, "Artist").equals(new Occupation(13, "Retired")));
    }

    @Test
    public void testGetId(){
        assertEquals(new Occupation(3, "Clerical").getId(), 3);
        assertNotSame(new Occupation(2, "Clerical").getId(), 1);
    }

    @Test
    public void testGetName(){
        assertEquals(new Occupation(3, "Clerical").getName(), "Clerical");
        assertNotSame(new Occupation(1, "Retired").getName(), "Artist");
    }
}
