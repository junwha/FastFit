package com.cse364;

public class Rating {
    User user;
    int rating;
    int timestamp;

    Rating(User user, int rating, int timestamp) {
        this.user = user;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}

