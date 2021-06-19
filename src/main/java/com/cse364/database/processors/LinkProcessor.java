package com.cse364.database.processors;

import com.cse364.database.dtos.LinkDto;
import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.domain.Movie;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


public class LinkProcessor implements ItemProcessor<LinkDto, Movie> {
    Map<Integer, Movie> movieMap;

    public LinkProcessor(Map<Integer, Movie> movieMap){
        this.movieMap = movieMap;
    }

    @Override
    public Movie process(LinkDto item) {
        Movie movie = movieMap.get(item.getMovie());
        movie = new Movie(movie.getId(), movie.getTitle(), movie.getGenres(), "http://www.imdb.com/title/tt"+item.getLink(), movie.getPoster());
        movieMap.put(movie.getId(), movie);
        return movie;
    }
}
