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
    public void testFilterSimilarUser(){
        InMemoryUserRepository storage = new InMemoryUserRepository();
        List<User> similarUsers = List.of(
                new User(1, Gender.M, 56, new Occupation(1, "others"), "00000"),
                new User(2, Gender.M, 56, new Occupation(1, "others"), "00000"),
                new User(3, Gender.M, 56, new Occupation(1, "others"), "00000")
        );
        List<User> notSimilarUsers = List.of(
                new User(4, Gender.F, 25, new Occupation(1, "others"), "00000"),
                new User(5, Gender.M, 35, new Occupation(1, "others"), "00000"),
                new User(6, Gender.M, 25, new Occupation(2, "X"), "00000")
        );

        for(User user : similarUsers){
            storage.add(user);
        }
        for(User user : notSimilarUsers){
            storage.add(user);
        }

        //Similar
        assertEquals(storage.filterSimilarUser(new User(7, Gender.M, 56, new Occupation(1, "others"), "00000")), similarUsers);
        assertEquals(storage.filterSimilarUser(new User(7, Gender.M, 100, new Occupation(1, "others"), "00000")), similarUsers);

        //Not Similar
        assertEquals(storage.filterSimilarUser(new User(8, Gender.F, 12, new Occupation(3, "Y"), "00000")), List.of());
        assertEquals(storage.filterSimilarUser(new User(8, Gender.M, 21, new Occupation(3, "Y"), "00000")), List.of());
        assertEquals(storage.filterSimilarUser(new User(8, Gender.F, 37, new Occupation(1, "others"), "00000")), List.of());
        assertEquals(storage.filterSimilarUser(new User(8, Gender.F, 48, new Occupation(3, "Y"), "00000")), List.of());
        assertEquals(storage.filterSimilarUser(new User(8, Gender.F, 51, new Occupation(3, "Y"), "00000")), List.of());
    }
    
    @Test
    public void testFilterSimilarUserByUserInfo() {
        InMemoryUserRepository storage = new InMemoryUserRepository();
        List<Integer> ageTestList = List.of(11,21,31,41,51,61);
        List<User> ageTestUserList = List.of(new User(1, Gender.M, -1, new Occupation(1, "X"), "00000"));
        storage.add(ageTestUserList.get(0));
        for (Integer age : ageTestList) {
            assertEquals(storage.filterSimilarUser(new UserInfo(null, age, null, "00000")), List.of());
        }
        /*
        List<Gender> genVar = List.of(
                null,
                Gender.F
        );
        List<Integer> ageVar = List.of(
                null.
                -1
        );
        List<Occupation> occVar = List.of(
                null,
                new Occupation(1, "X")
        );
        UserInfo baseInfo1 = new UserInfo()
        List<User> userList1 = new ArrayList<>();
        for (int i=-1; i<65; i = i + 10) {
            userList1.add(new User(i, Gender.M, i, new Occupation(1, "X"), "00000"));
        }
        */
    }
}
