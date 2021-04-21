package com.cse364.domain;

public interface UserRepository {
    /**
     * Returns a user by id.
     * If there is no user with given id, it returns `null`.
     */
    User get(int id);
}
