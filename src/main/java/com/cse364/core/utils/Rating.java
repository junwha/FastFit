package com.cse364.core.utils;

public class Rating {
    public Movie movie;
    public User user;
    public int rating;
    public int timestamp;


    public Rating(Movie movie, User user, int rating, int timestamp) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}

