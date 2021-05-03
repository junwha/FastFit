package com.cse364.infra;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cse364.domain.*;

import java.util.List;

public class InMemoryUserRepositoryTest {
    @Test
    public void testConstructor(){
        InMemoryUserRepository storage = new InMemoryUserRepository();
        User user = new User(1, Gender.M, 20, new Occupation(1, "others"), "00000");
        storage.add(user);
        assertEquals(storage.get(1), user);
    }
    
    @Test
    public void testFilterSimilarUserByUserInfo() {
        InMemoryUserRepository storage = new InMemoryUserRepository();
        List<Integer> ageTestList = List.of(11,21,31,41,46,51,61);
        List<User> ageTestUserList = List.of(new User(1, Gender.M, -1, new Occupation(1, "X"), "00000"));
        storage.add(ageTestUserList.get(0));
        for (Integer age : ageTestList) {
            assertEquals(storage.filterSimilarUser(new UserInfo(null, age, null, "00000")), List.of());
        }
        assertEquals(storage.filterSimilarUser(new UserInfo(null, -1, new Occupation(1, "X"), "00000")), ageTestUserList);
        assertEquals(storage.filterSimilarUser(new UserInfo(null, -1, null, "00000")), ageTestUserList);
    }
}
