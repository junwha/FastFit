package com.cse364.app;

import com.cse364.app.exceptions.GenderValidationException;
import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.OccupationValidationException;
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
        if (genderString.equals("M")) { return Gender.M; }
        if (genderString.equals("F")) { return Gender.F; }
        throw new GenderValidationException(genderString);
    }

}
