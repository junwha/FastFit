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
    public void add(User user) {
        users.put(user.getId(), user);
    }

    public User get(int id) {
        return users.get(id);
    }

    public List<User> filterSimilarUser(User compareUser){
        int age = compareUser.getAge();
        if(age < 18){ age = 1; }
        else if(age < 25){ age = 18; }
        else if(age < 35){ age = 25; }
        else if(age < 45){ age = 35; }
        else if(age < 50){ age = 45; }
        else if(age < 56){ age = 50; }
        else{ age = 56; }

        List<User> userList = new ArrayList<>();
        for(User user: users.values()){
            if(age == user.getAge()
                    && (compareUser.getGender().equals(user.getGender()))
                    && (compareUser.getOccupation().equals(user.getOccupation()))){
                userList.add(user);
            }
        }

        return userList;
    }
}
