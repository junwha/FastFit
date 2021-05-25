package com.cse364.api.dtos;

import lombok.Value;

@Value
public class MovieDto {
    String title;
    String genres;
    String imdb;
}
