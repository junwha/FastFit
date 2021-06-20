package com.cse364.database.processors;

import com.cse364.database.dtos.PosterDto;
import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.domain.Movie;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


public class PosterProcessor implements ItemProcessor<PosterDto, Movie> {
    Map<Integer, Movie> movieMap;

    public PosterProcessor(Map<Integer, Movie> movieMap){
        this.movieMap = movieMap;
    }

    @Override
    public Movie process(PosterDto item) {
        Movie movie = movieMap.get(item.getMovie());
        movie = new Movie(movie.getId(), movie.getTitle(), movie.getGenres(), movie.getLink(), item.getPoster());

        movieMap.put(movie.getId(), movie);
        return movie;
    }
}
