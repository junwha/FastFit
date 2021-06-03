package com.cse364.domain;

import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rating")
@Value
public class Rating {
    @NonNull Movie movie;
    @NonNull User user;
    int rating;
    int timestamp;
}
