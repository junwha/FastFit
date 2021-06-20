package com.cse364.database.processors;

import com.cse364.database.dtos.MovieDto;
import com.cse364.domain.Genre;
import com.cse364.domain.Movie;


import com.cse364.infra.Config;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieProcessor implements ItemProcessor<MovieDto, Movie> {
    @Autowired
    Config config;

    Map<Integer, Movie> movieMap;

    public MovieProcessor(Map<Integer, Movie> movieMap){
        this.movieMap = movieMap;
    }

    @Override
    public Movie process(MovieDto item) throws Exception {
        List<Genre> genreList = new ArrayList<>();
        for(String genre : item.getGenres().split("\\|")){
            genreList.add(config.genres.searchByName(genre));

        }

        Movie movie = new Movie(item.getId(), item.getTitle(), genreList, "", "");
        movieMap.put(movie.getId(), movie);

        return movie;
    }
}
