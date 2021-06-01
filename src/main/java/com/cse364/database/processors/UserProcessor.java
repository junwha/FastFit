package com.cse364.database.processors;

import com.cse364.database.dtos.UserDto;
import com.cse364.domain.Gender;

import com.cse364.domain.User;
import com.cse364.infra.Config;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProcessor implements ItemProcessor<UserDto, User> {
    @Autowired
    Config config;

    @Override
    public User process(UserDto item) {
        return new User(item.getId(),
                item.getGender()=="M"? Gender.M:Gender.F,
                item.getAge(),
                config.occupations.searchByName(item.getOccupation()),
                item.getCode());
    }
}
