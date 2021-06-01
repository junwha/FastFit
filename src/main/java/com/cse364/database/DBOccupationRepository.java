package com.cse364.database;

import com.cse364.domain.Occupation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DBOccupationRepository extends MongoRepository<Occupation, Integer> {
}
