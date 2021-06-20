package com.cse364.database.repositories;

import com.cse364.database.schemas.RatingSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DBRatingRepository extends MongoRepository<RatingSchema, String> {
    @Query("{'movie.id': ?0}")
    List<RatingSchema> filterByMovie(int id);

    @Query("{'user.id': ?0}")
    List<RatingSchema> filterByUser(int id);
}
