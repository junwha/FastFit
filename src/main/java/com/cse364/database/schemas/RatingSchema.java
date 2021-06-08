package com.cse364.database.schemas;

import com.cse364.domain.Movie;
import com.cse364.domain.Rating;
import com.cse364.domain.User;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "rating")
@Value
public class RatingSchema {
    @Id
    CompositeKey id;
    @NonNull Movie movie;
    @NonNull User user;
    int rating;
    int timestamp;

    public RatingSchema(Movie movie, User user, int rating, int timestamp) {
        this.id = new CompositeKey(movie.getId(), user.getId());
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public Rating toDomainObject() {
        return new Rating(movie, user, rating, timestamp);
    }

    @Value
    static class CompositeKey implements Serializable {
        int movieId;
        int userId;
    }
}
