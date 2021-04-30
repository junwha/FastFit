package com.cse364.domain;

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

    // Getters
    public Gender getGender() { return gender; }
    public int getAge() { return age; }
    public Occupation getOccupation() { return occupation; }
    public String getZipCode() { return zipCode; }
}
