package com.cse364.storage;

enum Gender{M, F}

public class User {
    private int id;
    private Gender gender;
    private int age;
    private Occupation occupation;
    private String zipCode;

    User(int id, Gender gender, int age, Occupation occupation, String zipCode){
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }

    // Getters
    public int getId() { return id; }
    public Gender getGender() { return gender; }
    public int getAge() { return age; }
    public Occupation getOccupation() { return occupation; }
    public String getZipCode() { return zipCode; }
}
