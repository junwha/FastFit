package com.cse364.database;

import com.cse364.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InDBMovieRepository extends MongoRepository<Movie, Integer> {
}
