package com.cse364.app;

import com.cse364.app.exceptions.InvalidGenreNameException;
import com.cse364.app.exceptions.InvalidOccupationNameException;
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
     * If a genre name is invalid, it throws InvalidGenreNameException.
     */
    public List<Genre> searchGenres(List<String> genreNames) throws InvalidGenreNameException {
        List<Genre> genres = new ArrayList<>();
        for (String name : genreNames) {
            Genre genre = genreRepository.searchByName(name);
            if (genre == null) { throw new InvalidGenreNameException(name); }
            if (!genres.contains(genre)) genres.add(genre);
        }
        return genres;
    }

    public Occupation searchOccupation(String occupationName) throws InvalidOccupationNameException {
        Occupation occupation = occupationRepository.searchByName(occupationName);
        if (occupation == null) { throw new InvalidOccupationNameException(occupationName); }
        return occupation;
    }
}
