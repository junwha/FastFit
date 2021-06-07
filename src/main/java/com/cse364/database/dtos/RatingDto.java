package com.cse364.database.dtos;

import lombok.Data;

@Data
public class RatingDto {
    int user;
    int movie;
    int rating;
    int timestamp;
}
