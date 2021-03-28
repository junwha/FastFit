package com.preprocess;
enum Gender{M, F}

public class User {
    public int id = -1;
    public Gender gender = null;
    public int age = -1;
    public String occupation = null;
    public int zipCode = -1;

    User(int id, Gender gender, int age, String occupation, int zipCode){
        this.id = id;
        this.gender = gender;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }
}
