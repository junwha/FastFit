package com.cse364.infra;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cse364.domain.*;

import java.util.List;

public class InMemoryUserRepositoryTest {
    @Test
    public void testConstructor(){
        InMemoryUserRepository storage = new InMemoryUserRepository();
        User user = new User(1, User.Gender.M, 20, new Occupation(1, "others"), "00000");
        storage.add(user);
        assertEquals(storage.get(1), user);
    }

    @Test
    public void testFilterSimilarUser(){
        InMemoryUserRepository storage = new InMemoryUserRepository();
        List<User> similarUsers = List.of(
                new User(1, User.Gender.M, 25, new Occupation(1, "others"), "00000"),
                new User(2, User.Gender.M, 28, new Occupation(1, "others"), "00000"),
                new User(3, User.Gender.M, 35, new Occupation(1, "others"), "00000")
        );
        List<User> notSimilarUsers = List.of(
                new User(4, User.Gender.F, 25, new Occupation(1, "others"), "00000"),
                new User(5, User.Gender.M, 14, new Occupation(1, "others"), "00000"),
                new User(6, User.Gender.M, 35, new Occupation(2, "X"), "00000")
        );

        for(User user : similarUsers){
            storage.add(user);
        }
        for(User user : notSimilarUsers){   
            storage.add(user);
        }

        assertEquals(storage.filterSimilarUser(new User(7, User.Gender.M, 30, new Occupation(1, "others"), "00000")), similarUsers);
        assertEquals(storage.filterSimilarUser(new User(8, User.Gender.F, 10, new Occupation(3, "Y"), "00000")), List.of());
    }
}
