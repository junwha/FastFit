package com.cse364.infra;

import java.util.List;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import com.cse364.domain.*;

public class InMemoryOccupationRepositoryTest {
    private InMemoryOccupationRepository occupations;

    @Before
    public void init() {
        occupations = new InMemoryOccupationRepository(){{
            add(new Occupation(10, "Job"));
            add(new Occupation(20, "Job/with/slash"), List.of("alias", "test"));
        }};
    }

    @Test
    public void testGetOccupationById() {
        assertTrue(occupations.get(10).equals(new Occupation(10, "Job")));
        assertTrue(occupations.get(20).equals(new Occupation(20, "Job/with/slash")));
        assertNull(occupations.get(1337));
    }

    @Test
    public void testSearchByName() {
        assertTrue(occupations.searchByName("J.O.B").equals(new Occupation(10, "Job")));

        assertTrue(occupations.searchByName("Job/with/slash").equals(new Occupation(20, "Job/with/slash")));
        assertTrue(occupations.searchByName("alias").equals(new Occupation(20, "Job/with/slash")));
        assertTrue(occupations.searchByName("test").equals(new Occupation(20, "Job/with/slash")));

        assertNull(occupations.searchByName("null job"));
        assertNull(occupations.searchByName("J/O/B"));
    }
}
