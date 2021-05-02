package com.cse364.domain;

import java.util.List;

public interface UserRepository {
    /**
     * Returns a user by id.
     * If there is no user with given id, it returns `null`.
     */
    User get(int id);

    List<User> filterSimilarUser(UserInfo compareUser);
}
