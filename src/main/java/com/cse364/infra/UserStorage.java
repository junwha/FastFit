package com.cse364.infra;

import java.util.HashMap;
import com.cse364.domain.*;

public class UserStorage {
    private HashMap<Integer, User> users = new HashMap<>();

    public UserStorage() { }

    /**
     * Adds a user to the storage.
     */
    public void add(User user) {
        users.put(user.getId(), user);
    }

    /**
     * Returns a user by id.
     * If there is no user with given id, it returns `null`.
     */
    public User getUser(Integer id) {
        return users.get(id);
    }
}
