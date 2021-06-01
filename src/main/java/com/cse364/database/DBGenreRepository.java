package com.cse364.database;

import com.cse364.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DBGenreRepository extends MongoRepository<Genre, String> {

}
