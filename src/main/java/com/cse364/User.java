package com.cse364;
enum Gender{M, F}

public class User {
    private int id;
    private Gender gender;
    private int age;
    private int occupation;
    private String zipCode;

    User(int id, Gender gender, int age, int occupation, String zipCode){
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getOccupation() {
        return occupation;
    }

    public String getZipCode() {
        return zipCode;
    }

}
