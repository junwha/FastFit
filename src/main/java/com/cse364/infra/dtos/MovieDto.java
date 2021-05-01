package com.cse364.infra.dtos;

import java.util.List;

public class MovieDto {
    public int id;
    public String title;
    public List<String> genres;
    public String link;

    public MovieDto(int id, String title, List<String> genres, String link) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MovieDto)) { return false; }
        MovieDto movieDto = (MovieDto) o;
        return id == movieDto.id &&
                title.equals(movieDto.title) &&
                genres.equals(movieDto.genres) &&
                link.equals(movieDto.link);
    }
}