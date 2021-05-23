package com.cse364.app;

import com.cse364.domain.User;
import com.cse364.domain.UserInfo;
import com.cse364.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<User> getSimilarUsers(UserInfo userInfo, int similarity) {
        List<User> userList = new ArrayList<>();
        for (User user : userRepository.all()) {
            if (user.getInfo().getSimilarity(userInfo) == similarity) {
                userList.add(user);
            }
        }
        return userList;
    }
}
