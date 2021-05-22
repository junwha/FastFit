package com.cse364.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class Rating {
    @NonNull Movie movie;
    @NonNull User user;
    int rating;
    int timestamp;
}
