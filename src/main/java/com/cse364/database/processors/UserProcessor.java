package com.cse364.database.processors;

import com.cse364.database.dtos.UserDto;
import com.cse364.domain.Gender;

import com.cse364.domain.User;
import com.cse364.infra.Config;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class UserProcessor implements ItemProcessor<UserDto, User> {
    @Autowired
    Config config;
    Map<Integer, User> userMap;

    public UserProcessor(Map<Integer, User> userMap){
        this.userMap = userMap;
    }

    @Override
    public User process(UserDto item) {
        User user = new User(item.getId(),
                item.getGender()=="M"? Gender.M:Gender.F,
                item.getAge(),
                config.occupations.get(item.getOccupation()),
                item.getCode());
        userMap.put(user.getId(), user);

        return user;
    }
}
