package com.cse364.domain;

public class User {
    private int id;
    private UserInfo info;

    public User(int id, Gender gender, int age, Occupation occupation, String zipCode){
        this.id = id;
        this.info = new UserInfo(gender, age, occupation, zipCode);
    }

    // Getters
    public int getId() { return id; }
    public Gender getGender() { return info.getGender(); }
    public int getAge() { return info.getAge(); }
    public Occupation getOccupation() { return info.getOccupation(); }
    public String getZipCode() { return info.getZipCode(); }
}
