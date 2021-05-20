package com.cse364.infra.dtos;

import com.cse364.domain.Genre;
import com.cse364.domain.Movie;

import java.util.List;

public class MovieViewDto {
    String title;
    String genre;
    String imdb;

    public MovieViewDto(String title, String genre, String imdb){
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
