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
    public void testValidateGenres() {
        try {
            List<Genre> genres = validationService.validateGenres(List.of("genreC", "genreB", "genreA"));
            assertEquals(genres, List.of(
                    new Genre("genreC"),
                    new Genre("genreB"),
                    new Genre("genreA")
            ));
        } catch(GenreValidationException e) {
            fail("must not throw when given valid genre names");
        }
    }

    @Test
    public void testValidateGenresThrowsWhenGivenInvalidName() {
        try {
            validationService.validateGenres(List.of("genreA", "INVALID", "genreB"));
            fail("must throw when given invalid genre names");
        } catch(GenreValidationException e) {
            assertEquals(e.getName(), "INVALID");
        }
    }

    @Test
    public void testValidateOccupation() {
        try {
            Occupation occupation = validationService.validateOccupation("ocpB");
            assertEquals(occupation, new Occupation(2, "ocpB"));
        } catch(OccupationValidationException e) {
            fail("must not throw when given invalid occupation name");
        }
    }

    @Test
    public void testValidateOccupationThrowsWhenGivenInvalidName() {
        try {
            validationService.validateOccupation("INVALID");
            fail("must throw when given invalid occupation name");
        } catch(OccupationValidationException e) {
            assertEquals(e.getName(), "INVALID");
        }
    }
}
