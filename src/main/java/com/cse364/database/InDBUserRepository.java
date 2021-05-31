package com.cse364.database;

import com.cse364.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InDBUserRepository extends MongoRepository<User, Integer> {
}
