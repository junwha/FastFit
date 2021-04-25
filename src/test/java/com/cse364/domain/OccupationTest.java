package com.cse364.domain;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class OccupationTest {
    @Test
    public void testConstructor() {
        Occupation occupation1 = new Occupation(3, "Admin");
        assertEquals(occupation1.getId(), 3);
        assertEquals(occupation1.getName(), "Admin");
    }

    @Test
    public void testEquals() {
        assertTrue(new Occupation(3, "Clerical").equals(new Occupation(3, "Admin")));
        assertFalse(new Occupation(2, "Artist").equals(new Occupation(13, "Retired")));
    }
}