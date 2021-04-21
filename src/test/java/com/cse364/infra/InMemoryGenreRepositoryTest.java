package com.cse364.infra;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import com.cse364.domain.*;

public class InMemoryGenreRepositoryTest {
    @Test
    public void testSearchByName() {
        InMemoryGenreRepository storage = new InMemoryGenreRepository(
            List.of("Animation", "Children's", "WHITESPACED genre")
        );

        // Searches case-insensitively, ignoring special characters and whitespaces.
        assertNotNull(storage.searchByName("ANIMAtion"));
        assertNotNull(storage.searchByName("child'rens"));
        assertNotNull(storage.searchByName("white-spaced gen re"));
        
        // Returns null if the name is invalid.
        assertNull(storage.searchByName("invalid name"));
    }
}
