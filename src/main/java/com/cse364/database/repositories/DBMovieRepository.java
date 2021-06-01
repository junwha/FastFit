package com.cse364.database.repositories;

import com.cse364.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DBMovieRepository extends MongoRepository<Movie, Integer> {
}
