package com.cse364.app;

import com.cse364.app.exceptions.GenderValidationException;
import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.OccupationValidationException;
import com.cse364.app.exceptions.UserInfoValidationException;
import com.cse364.domain.*;
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

    @Test
    public void testValidateGender() {
        try {
            assertEquals(validationService.validateGender("M"), Gender.M);
            assertEquals(validationService.validateGender("F"), Gender.F);
        } catch(GenderValidationException e) {
            fail("must not throw when given invalid gender string");
        }
    }

    @Test
    public void testValidateGenderThrowsWhenGivenInvalidName() {
        try {
            validationService.validateGender("INVALID");
            fail("must throw when given invalid gender string");
        } catch(GenderValidationException e) {
            assertEquals(e.getValue(), "INVALID");
        }
    }

    @Test
    public void testValidateUserInfo() {
        try {
            UserInfo userInfo = validationService.validateUserInfo("F", "10", "ocpC");
            UserInfo expected = new UserInfo(Gender.F, 10, new Occupation(3, "ocpC"), null);
            assertEquals(userInfo, expected);
        } catch(UserInfoValidationException e) {
            fail("must not throw when given valid user info");
        }
    }

    @Test
    public void testValidateUserInfoThrows() {
        try {
            UserInfo userInfo = validationService.validateUserInfo("INVALID", "10", "ocpC");
            fail("must throw when given invalid gender");
        } catch(UserInfoValidationException e) {
            assertEquals(e.getField(), "gender");
            assertEquals(e.getValue(), "INVALID");
        }

        try {
            UserInfo userInfo = validationService.validateUserInfo("F", "INVALID", "ocpC");
            fail("must throw when given invalid age");
        } catch(UserInfoValidationException e) {
            assertEquals(e.getField(), "age");
            assertEquals(e.getValue(), "INVALID");
        }

        try {
            UserInfo userInfo = validationService.validateUserInfo("F", "10", "INVALID");
            fail("must throw when given invalid occupation");
        } catch(UserInfoValidationException e) {
            assertEquals(e.getField(), "occupation");
            assertEquals(e.getValue(), "INVALID");
        }
    }
}
