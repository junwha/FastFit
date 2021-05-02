package com.cse364.app;

import com.cse364.app.exceptions.GenreValidationException;
import com.cse364.app.exceptions.OccupationValidationException;
import com.cse364.domain.Genre;
import com.cse364.domain.GenreRepository;
import com.cse364.domain.Occupation;
import com.cse364.domain.OccupationRepository;

import java.util.ArrayList;
import java.util.List;

public class ValidationService {
    private GenreRepository genreRepository;
    private OccupationRepository occupationRepository;

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
     * Returns a occupation searched by a given name.
     * If a genre name is invalid, it throws GenreValidationException.
     */
    public Occupation validateOccupation(String occupationName) throws OccupationValidationException {
        Occupation occupation = occupationRepository.searchByName(occupationName);
        if (occupation == null) { throw new OccupationValidationException(occupationName); }
        return occupation;
    }
}
