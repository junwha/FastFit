package com.cse364.database;

import com.cse364.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InDBGenreRepository extends MongoRepository<Genre, String> {

}
