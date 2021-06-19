package com.cse364.database.repositories;

import com.cse364.database.schemas.ValidSchema;
import com.cse364.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DBValidRepository extends MongoRepository<ValidSchema, Integer> {
}
