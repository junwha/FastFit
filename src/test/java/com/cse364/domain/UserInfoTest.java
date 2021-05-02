package com.cse364.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserInfoTest {
    @Test
    public void testConstructor() {
        UserInfo userInfo = new UserInfo(Gender.M, 25, new Occupation(1, "ocpA"), "00000");

        assertEquals(userInfo.getGender(), Gender.M);
        assertEquals(userInfo.getAge(), 25);
        assertEquals(userInfo.getOccupation(), new Occupation(1, "ocpA"));
        assertEquals(userInfo.getZipCode(), "00000");
    }

    @Test
    public void testEquals() {
        UserInfo userInfo1 = new UserInfo(Gender.M, 25, new Occupation(1, "ocpA"), "00000");
        UserInfo userInfo2 = new UserInfo(Gender.M, 25, new Occupation(1, "ocpA"), "00000");
        UserInfo userInfo3 = new UserInfo(Gender.F, 40, new Occupation(2, "ocpB"), "11111");

        assertNotSame(userInfo1, userInfo2);
        assertEquals(userInfo1, userInfo2);
        assertNotEquals(userInfo1, userInfo3);
        assertNotEquals(userInfo1, null);
    }
}