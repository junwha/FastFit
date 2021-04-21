package com.cse364.domain;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;


public class OccupationTest {
    @Test
    public void testConstructor(){
        Occupation occupation1 = new Occupation(3, "Admin");
        assertSame(occupation1.getId(), 3);
        assertNotSame(occupation1.getId(), 0);
        assertSame(occupation1.getName(), "Admin");
        assertNotSame(occupation1.getName(), "Others");
    }

    @Test
    public void testEquals(){
        assertTrue(new Occupation(3, "Clerical").equals(new Occupation(3, "Admin")));
        assertFalse(new Occupation(2, "Artist").equals(new Occupation(13, "Retired")));
    }
}
