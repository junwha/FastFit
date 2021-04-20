package com.cse364;

import com.cse364.core.storage.GenreStorage;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenreStorageTest {
    @Test
    public void testGetGenre() {
        GenreStorage storage = new GenreStorage(
            new String[]{"Animation", "Children's", "WHITESPACED genre"}
        );

        // Searches case-insensitively, ignoring special characters and whitespaces.
        assertNotNull(storage.getGenre("ANIMAtion"));
        assertNotNull(storage.getGenre("child'rens"));
        assertNotNull(storage.getGenre("white-spaced gen re"));
        
        // Returns null if the name is invalid.
        assertNull(storage.getGenre("invalid name"));
    }
}
