package com.cse364.api.dtos;

import com.cse364.domain.Genre;
import com.cse364.domain.Movie;

import java.util.List;

public class MovieDto {
    String title;
    String genre;
    String imdb;

    public MovieDto(String title, String genre, String imdb){
        this.title = title;
        this.genre = genre;
        this.imdb = imdb;
    }



    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getImdb() {
        return imdb;
    }
}
