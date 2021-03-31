package com.cse364;
enum Gender{M, F}

public class User {
    public int id;
    public Gender gender;
    public int age;
    public int occupation;
    public String zipCode;

    User(int id, Gender gender, int age, int occupation, String zipCode){
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }
}
