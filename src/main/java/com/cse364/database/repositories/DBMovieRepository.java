package com.cse364.database.repositories;

import com.cse364.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface DBMovieRepository extends MongoRepository<Movie, Integer> {
    @Query("{'title': ?0)")
    Movie get(String title);


}
