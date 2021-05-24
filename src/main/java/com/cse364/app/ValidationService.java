package com.cse364.app;

import com.cse364.app.exceptions.*;
import com.cse364.domain.*;

import java.util.ArrayList;
import java.util.List;

public class ValidationService {
    private final GenreRepository genreRepository;
    private final OccupationRepository occupationRepository;

    public ValidationService(GenreRepository genreRepository, OccupationRepository occupationRepository) {
        this.genreRepository = genreRepository;
        this.occupationRepository = occupationRepository;
    }

    /**
     * Returns a list of genres searched by given genre names.
     * Any duplicate genres are removed from the returned list.
     * If a genre name is invalid, it throws GenreValidationException.
     */
    public List<Genre> validateGenres(List<String> genreNames) throws GenreValidationException {
        List<Genre> genres = new ArrayList<>();
        for (String name : genreNames) {
            if (name.equals("")) continue; // Ignore empty strings
            Genre genre = genreRepository.searchByName(name);
            if (genre == null) { throw new GenreValidationException(name); }
            if (!genres.contains(genre)) genres.add(genre);
        }
        return genres;
    }

    /**
     * Returns an occupation searched by a given name.
     * If an occupation name is invalid, it throws OccupationValidationException.
     */
    public Occupation validateOccupation(String occupationName) throws OccupationValidationException {
        Occupation occupation = occupationRepository.searchByName(occupationName);
        if (occupation == null) { throw new OccupationValidationException(occupationName); }
        return occupation;
    }

    public Gender validateGender(String genderString) throws GenderValidationException {
        if (genderString.equalsIgnoreCase("M")) { return Gender.M; }
        if (genderString.equalsIgnoreCase("F")) { return Gender.F; }
        throw new GenderValidationException(genderString);
    }

    public int validateAge(String ageString) throws AgeValidationException {
        try {
            int age = Integer.parseInt(ageString);
            if (age < 0) throw new AgeValidationException(ageString);
            return age;
        } catch (NumberFormatException e) {
            throw new AgeValidationException(ageString);
        }
    }

    public UserInfo validateUserInfo(
            String genderString, String ageString, String occupationString
    ) throws UserInfoValidationException {
        Gender gender = null;
        int age = -1;
        Occupation occupation = null;

        try {
            if (!genderString.equals("")) gender = validateGender(genderString);
        } catch (GenderValidationException e) {
            throw new UserInfoValidationException("gender", genderString);
        }

        try {
            if (!ageString.equals("")) age = validateAge(ageString);
        } catch (AgeValidationException e) {
            throw new UserInfoValidationException("age", ageString);
        }

        try {
            if (!occupationString.equals("")) occupation = validateOccupation(occupationString);
        } catch (OccupationValidationException e) {
            throw new UserInfoValidationException("occupation", occupationString);
        }

        return new UserInfo(gender, age, occupation, null);
    }
}
