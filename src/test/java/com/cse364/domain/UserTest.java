package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    private User testObject1;
    private Occupation occupation;

    @Before
    public void init(){
        occupation = new Occupation(1, "Student");
        testObject1 = new User(1, User.Gender.M, 20, occupation, "00000");
    }

    @Test
    public void testConstructor(){
        assertEquals(testObject1.getId(), 1);
        assertEquals(testObject1.getGender(), User.Gender.M);
        assertEquals(testObject1.getAge(), 20);
        assertTrue(testObject1.getOccupation().equals(occupation));
        assertEquals(testObject1.getZipCode(), "00000");
    }

}
