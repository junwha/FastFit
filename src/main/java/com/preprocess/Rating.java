package com.preprocess;

public class Rating {
    User user = null;
    int rating = -1;
    int timestamp = -1;

    Rating(User user, int rating, int timestamp) {
        this.user = user;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}

