package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private Occupation occupation;

    @Before
    public void init(){
        occupation = new Occupation(1, "Student");
        user = new User(1, Gender.M, 20, occupation, "00000");
    }

    @Test
    public void testConstructor(){
        assertEquals(user.getId(), 1);
        assertEquals(user.getGender(), Gender.M);
        assertEquals(user.getAge(), 20);
        assertEquals(user.getOccupation(), occupation);
        assertEquals(user.getZipCode(), "00000");
    }

    @Test
    public void testEquals(){
        assertEquals(user, new User(1, Gender.F, 23, occupation, "00000"));
        assertNotEquals(user, new User(2, Gender.M, 20, occupation, "00000"));
        assertNotEquals(user, null);
    }
}
