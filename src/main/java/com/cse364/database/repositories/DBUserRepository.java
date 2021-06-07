package com.cse364.database.repositories;

import com.cse364.database.dtos.UserDto;
import com.cse364.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DBUserRepository extends MongoRepository<User, Integer> {
}
