package com.cse364.infra;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.cse364.domain.*;

import java.util.List;

public class InMemoryUserRepositoryTest {
    InMemoryUserRepository userRepository;
    User user1;
    User user2;

    @Before
    public void init() {
        userRepository = new InMemoryUserRepository();
        user1 = new User(1, Gender.M, 20, new Occupation(1, "others"), "00000");
        user2 = new User(2, Gender.F, 20, new Occupation(1, "others"), "00000");
        userRepository.add(user1);
        userRepository.add(user2);
    }

    @Test
    public void testGet() {
        assertEquals(userRepository.get(1), user1);
    }

    @Test
    public void testAll() {
        assertEquals(userRepository.all(), List.of(user1, user2));
    }
}
