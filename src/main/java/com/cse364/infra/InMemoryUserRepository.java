package com.cse364.infra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.cse364.domain.*;

public class InMemoryUserRepository implements UserRepository {
    private HashMap<Integer, User> users = new HashMap<>();

    public InMemoryUserRepository() { }

    /**
     * Adds a user to the storage.
     */
    void add(User user) {
        users.put(user.getId(), user);
    }

    public User get(int id) {
        return users.get(id);
    }

    public List<User> filterSimilarUser(User compareUser){
        List<User> userList = new ArrayList<>();
        for(User user: users.values()){
            if((Math.abs(compareUser.getAge()-user.getAge()) <= 5)
                    && (compareUser.getGender().equals(user.getGender()))
                    && (compareUser.getOccupation().equals(user.getOccupation()))){
                userList.add(user);
            }
        }

        return userList;
    }
}
