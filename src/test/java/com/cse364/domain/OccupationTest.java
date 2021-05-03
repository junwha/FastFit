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
        assertEquals(new Occupation(3, "Clerical"), new Occupation(3, "Admin"));
        assertNotEquals(new Occupation(2, "Artist"), new Occupation(13, "Retired"));
        assertNotEquals(new Occupation(3, "Admin"), null);
    }
}
