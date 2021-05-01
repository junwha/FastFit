package com.cse364.infra.dtos;

public class RatingDto {
    public int user;
    public int movie;
    public int rating;
    public int timestamp;

    public RatingDto(int user, int movie, int rating, int timestamp) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RatingDto)) { return false; }
        RatingDto ratingDto = (RatingDto) o;
        return user == ratingDto.user &&
                movie == ratingDto.movie &&
                rating == ratingDto.rating &&
                timestamp == ratingDto.timestamp;
    }
}