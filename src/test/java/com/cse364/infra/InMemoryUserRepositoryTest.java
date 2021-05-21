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
        InMemoryUserRepository storage2 = new InMemoryUserRepository();

        List<Integer> ageTestList = List.of(11,21,31,41,46,51,61);
        List<Integer> changedAge = List.of(1,18,25,35,45,50,56);

        List<User> ageTestUserList = List.of(new User(1, Gender.M, -1, new Occupation(1, "X"), "00000"));
        List<User> ageTestUserList2 = List.of(
                new User(1, Gender.M, 1, new Occupation(1, "X"), "00000"),
                new User(1, Gender.M, 18, new Occupation(1, "X"), "00000"),
                new User(1, Gender.M, 25, new Occupation(1, "X"), "00000"),
                new User(1, Gender.M, 35, new Occupation(1, "X"), "00000"),
                new User(1, Gender.M, 45, new Occupation(1, "X"), "00000"),
                new User(1, Gender.M, 50, new Occupation(1, "X"), "00000"),
                new User(1, Gender.M, 56, new Occupation(1, "X"), "00000")
        );

        storage.add(ageTestUserList.get(0));

        for (Integer age : ageTestList) {
            assertEquals(
                    storage.getSimilarUsers(new UserInfo(null, age, null, "00000"), 0),
                    List.of()
            );
        }
        
        for (int i=0; i<7; i++) {
            storage2.add(ageTestUserList2.get(i));
            assertEquals((Object) storage2.getSimilarUsers(new UserInfo(null, ageTestList.get(i), null, "00000"), 0).get(0).getAge(), changedAge.get(i));
        }
        
        assertEquals(storage.getSimilarUsers(new UserInfo(null, -1, new Occupation(1, "X"), "00000"), 0), ageTestUserList);
        assertEquals(storage.getSimilarUsers(new UserInfo(null, -5, null, "00000"), 0), ageTestUserList);
    }
}
