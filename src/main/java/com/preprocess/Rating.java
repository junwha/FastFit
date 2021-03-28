package com.preprocess;

public class Rating {
    User user = null;
    Movie movie = null;
    int rating = -1;
    int timestamp = -1;

    Rating(User user, Movie movie, int rating, int timestamp) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}

