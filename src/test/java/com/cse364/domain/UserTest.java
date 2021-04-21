package com.cse364.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    private User testObject1;
    private User testObject2;

    @Before
    public void init(){
        testObject1 = new User(1, User.Gender.M, 20, new Occupation(1, "Student"), "00000");
        testObject2 = new User(1, User.Gender.M, 20, new Occupation(1, "Student"), "00000");
    }

    @Test
    public void testConstructor(){
        assertEquals(testObject1.getId(), testObject2.getId());
        assertEquals(testObject1.getGender(), testObject2.getGender());
        assertEquals(testObject1.getAge(), testObject2.getAge());
        assertTrue(testObject1.getOccupation().equals(testObject2.getOccupation()));
        assertEquals(testObject1.getZipCode(), testObject2.getZipCode());
    }

}
