package com.cse364.storage;

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

    //Getters
    public Movie getMovie() {
        return movie;
    }
    public User getUser() {
        return user;
    }
    public int getRating() {
        return rating;
    }
    public int getTimestamp() {
        return timestamp;
    }
}

