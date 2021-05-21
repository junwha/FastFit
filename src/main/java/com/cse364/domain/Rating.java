package com.cse364.domain;

import lombok.Value;

@Value
public class Rating {
    Movie movie;
    User user;
    int rating;
    int timestamp;
}
