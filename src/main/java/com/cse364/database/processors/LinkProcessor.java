package com.cse364.database.processors;

import com.cse364.database.dtos.LinkDto;
import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.domain.Movie;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;


public class LinkProcessor implements ItemProcessor<LinkDto, Movie> {
    @Autowired
    DBMovieRepository movies;

    @Override
    public Movie process(LinkDto item) {
        Movie movie = movies.findById(item.getMovie()).get();
        return new Movie(movie.getId(), movie.getTitle(), movie.getGenres(), "http://www.imdb.com/title/tt"+item.getLink(), movie.getPoster());
    }
}
