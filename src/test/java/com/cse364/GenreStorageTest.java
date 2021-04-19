package com.cse364;

import org.junit.Test;
import static org.junit.Assert.*;

public class GenreStorageTest {
    @Test
    public void testGetGenre() {
        GenreStorage storage = new GenreStorage(
            new String[]{"Animation", "Children's", "WHITESPACED genre"}
        );

        // Searches case-insensitively, ignoring special characters and whitespaces.
        String message = "Can't Search case-insensitively"
        assertNotNull(message, storage.getGenre("ANIMAtion"));
        assertNotNull(message, storage.getGenre("child'rens"));
        assertNotNull(message, storage.getGenre("white-spaced gen re"));

        message = "Invalid name";
        // Returns null if the name is invalid.
        assertNull(message, storage.getGenre("invalid name"));
    }
}
