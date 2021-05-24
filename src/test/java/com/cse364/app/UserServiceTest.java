package com.cse364.app;

import com.cse364.domain.*;
import com.cse364.infra.InMemoryUserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    UserService userService;
    List<UserInfo> userInfos;

    @Before
    public void init() {
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        userService = new UserService(userRepository);
        userInfos = List.of(
                new UserInfo(Gender.M, 25, new Occupation(1, "A"), "00000"),
                new UserInfo(Gender.M, 25, new Occupation(1, "A"), "00000"),
                new UserInfo(Gender.F, 40, new Occupation(2, "B"), "00000"),
                new UserInfo(null, -1, null, "00000")
        );
        for (int i = 0; i < userInfos.size(); i++) {
            userRepository.add(new User(i, userInfos.get(i)));
        }
    }

    @Test
    public void testGetSimilarUsers() {
        var similarUsers = userService.getSimilarUsers(userInfos.get(0), 3)
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        assertEquals(similarUsers, new HashSet<>(List.of(0, 1, 3)));
    }
}
