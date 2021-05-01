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
        assertEquals(occupations.get(10), new Occupation(10, "Job"));
        assertEquals(occupations.get(20), new Occupation(20, "Job/with/slash"));
        assertNull(occupations.get(1337));
    }

    @Test
    public void testSearchByName() {
        assertEquals(occupations.searchByName("J.O.B"), new Occupation(10, "Job"));

        assertEquals(occupations.searchByName("Job/with/slash"), new Occupation(20, "Job/with/slash"));
        assertEquals(occupations.searchByName("alias"), new Occupation(20, "Job/with/slash"));
        assertEquals(occupations.searchByName("test"), new Occupation(20, "Job/with/slash"));

        assertNull(occupations.searchByName("null job"));
        assertNull(occupations.searchByName("J/O/B"));
    }
}
