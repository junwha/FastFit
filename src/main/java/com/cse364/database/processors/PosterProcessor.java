package com.cse364.database.processors;

import com.cse364.database.dtos.PosterDto;
import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.domain.Movie;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;


public class PosterProcessor implements ItemProcessor<PosterDto, Movie> {
    @Autowired
    DBMovieRepository movies;

    @Override
    public Movie process(PosterDto item) {
        Movie movie = movies.findById(item.getMovie()).get();
        return new Movie(movie.getId(), movie.getTitle(), movie.getGenres(), movie.getLink(), item.getPoster());
    }
}
