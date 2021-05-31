package com.cse364.domain;

import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Value
public class Rating {
    @DBRef
    @NonNull Movie movie;
    @DBRef
    @NonNull User user;
    int rating;
    int timestamp;
}
