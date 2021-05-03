package com.cse364.domain;

public class Rating {
    private Movie movie;
    private User user;
    private int rating;
    private int timestamp;

    public Rating(Movie movie, User user, int rating, int timestamp) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    // Getters
    public Movie getMovie() { return movie; }
    public User getUser() { return user; }
    public int getRating() { return rating; }
    public int getTimestamp() { return timestamp; }
}
