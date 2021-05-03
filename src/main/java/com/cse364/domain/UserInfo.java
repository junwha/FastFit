package com.cse364.domain;

import java.util.Objects;

public class UserInfo {
    private Gender gender;
    private int age;
    private Occupation occupation;
    private String zipCode;

    public UserInfo(Gender gender, int age, Occupation occupation, String zipCode) {
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserInfo)) { return false; }
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(gender, userInfo.gender) &&
                age == userInfo.age &&
                Objects.equals(occupation, userInfo.occupation) &&
                Objects.equals(zipCode, userInfo.zipCode);
    }

    // Getters
    public Gender getGender() { return gender; }
    public int getAge() { return age; }
    public Occupation getOccupation() { return occupation; }
    public String getZipCode() { return zipCode; }
}
