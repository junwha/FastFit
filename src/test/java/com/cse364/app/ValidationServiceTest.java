package com.cse364.app;

import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.OccupationValidationException;
import com.cse364.domain.Genre;
import com.cse364.domain.GenreRepository;
import com.cse364.domain.Occupation;
import com.cse364.domain.OccupationRepository;
import com.cse364.infra.InMemoryGenreRepository;
import com.cse364.infra.InMemoryOccupationRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ValidationServiceTest {
    private ValidationService validationService;

    @Before
    public void init() {
        GenreRepository genres = new InMemoryGenreRepository(List.of(
                "genreA", "genreB", "genreC"
        ));
        OccupationRepository occupations = new InMemoryOccupationRepository(){{
            add(new Occupation(1, "ocpA"));
            add(new Occupation(2, "ocpB"));
            add(new Occupation(3, "ocpC"));
        }};
        validationService = new ValidationService(genres, occupations);
    }

    @Test
    public void testSearchGenres() {
        try {
            List<Genre> genres = validationService.searchGenres(List.of("genreC", "genreB", "genreA"));
            assertEquals(genres, List.of(
                    new Genre("genreC"),
                    new Genre("genreB"),
                    new Genre("genreA")
            ));
        } catch(GenreValidationException e) {
            fail("searchGenres must not throw when given valid genre names.");
        }
    }

    @Test
    public void testSearchGenresThrowsWhenGivenInvalidName() {
        try {
            validationService.searchGenres(List.of("genreA", "INVALID", "genreB"));
            fail("searchGenres must throw when given invalid genre names.");
        } catch(GenreValidationException e) {
            assertEquals(e.getName(), "INVALID");
        }
    }

    @Test
    public void testOccupationSearch() {
        try {
            Occupation occupation = validationService.searchOccupation("ocpB");
            assertEquals(occupation, new Occupation(2, "ocpB"));
        } catch(OccupationValidationException e) {
            fail("searchOccupation must not throw when given invalid occupation name.");
        }
    }

    @Test
    public void testOccupationSearchThrowsWhenGivenInvalidName() {
        try {
            validationService.searchOccupation("INVALID");
            fail("searchOccupation must throw when given invalid occupation name.");
        } catch(OccupationValidationException e) {
            assertEquals(e.getName(), "INVALID");
        }
    }
}
