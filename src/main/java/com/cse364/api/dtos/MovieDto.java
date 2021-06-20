package com.cse364.api.dtos;

import com.cse364.domain.Genre;
import com.cse364.domain.Movie;
import lombok.Value;

import java.util.List;

@Value
public class MovieDto {
    String title;
    String genres;
    String imdb;
    String poster;

    public static MovieDto fromMovie(Movie movie) {
        return new MovieDto(
                movie.getTitle(),
                formatGenres(movie.getGenres(), "|"),
                movie.getLink(),
                movie.getPoster()
        );
    }

    static String formatGenres(List<Genre> genres, String divider) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            sb.append(genres.get(i).getName());
            if (i < genres.size() - 1) { sb.append(divider); }
        }
        return sb.toString();
    }
}
