package com.cse364;

import java.util.List;

import com.cse364.core.utils.Occupation;
import com.cse364.core.OccupationStorage;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class OccupationStorageTest {
    private OccupationStorage storage;

    @Before
    public void init() {
        storage = new OccupationStorage(){{
            add(new Occupation(10, "Job"));
            add(new Occupation(20, "Job/with/slash"), List.of("alias", "test"));
        }};
    }

    @Test
    public void testGetOccupationById() {
        assertTrue(storage.getOccupationById(10).equals(new Occupation(10, "Job")));
        assertTrue(storage.getOccupationById(20).equals(new Occupation(20, "Job/with/slash")));
        assertNull(storage.getOccupationById(1337));
    }

    @Test
    public void testGetOccupationByName() {
        assertTrue(storage.getOccupationByName("J.O.B").equals(new Occupation(10, "Job")));

        assertTrue(storage.getOccupationByName("Job/with/slash").equals(new Occupation(20, "Job/with/slash")));
        assertTrue(storage.getOccupationByName("alias").equals(new Occupation(20, "Job/with/slash")));
        assertTrue(storage.getOccupationByName("test").equals(new Occupation(20, "Job/with/slash")));

        assertNull(storage.getOccupationByName("null job"));
        assertNull(storage.getOccupationByName("J/O/B"));
    }
}
