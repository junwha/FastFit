package com.preprocess;
enum Gender{M, F}

public class User {
    public int id = -1;
    public Gender gender = null;
    public int age = -1;
    public String occupation = null;
    public String zipCode = null;

    User(int id, Gender gender, int age, String occupation, String zipCode){
        this.id = id;
        this.gender = gender;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }
}
