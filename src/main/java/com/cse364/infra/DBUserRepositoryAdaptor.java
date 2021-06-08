package com.cse364.infra;

import com.cse364.database.repositories.DBUserRepository;
import com.cse364.domain.User;
import com.cse364.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUserRepositoryAdaptor implements UserRepository {
    @Autowired
    DBUserRepository users;

    public DBUserRepositoryAdaptor() { }

    /**
     * Adds a user to the storage.
     */
    public void add(User user) {
        users.insert(user);
    }

    public User get(int id) {
        return users.findById(id).get();
    }

    public List<User> all() {
        return users.findAll();
    }
}
