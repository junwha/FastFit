package com.cse364.core;

public class Rating {
    Movie movie;
    User user;
    int rating;
    int timestamp;


    Rating(Movie movie, User user, int rating, int timestamp) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}

