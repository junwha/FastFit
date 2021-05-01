package com.cse364.infra.dtos;

public class GenreDto {
    public String name;

    public GenreDto(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GenreDto)) { return false; }
        GenreDto genreDto = (GenreDto) o;
        return name.equals(genreDto.name);
    }
}