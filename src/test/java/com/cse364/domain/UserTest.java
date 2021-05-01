package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    private User user1;
    private Occupation occupation;

    @Before
    public void init(){
        occupation = new Occupation(1, "Student");
        user1 = new User(1, Gender.M, 20, occupation, "00000");
    }

    @Test
    public void testConstructor(){
        assertEquals(user1.getId(), 1);
        assertEquals(user1.getGender(), Gender.M);
        assertEquals(user1.getAge(), 20);
        assertEquals(user1.getOccupation(), occupation);
        assertEquals(user1.getZipCode(), "00000");
    }
}
