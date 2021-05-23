package com.cse364.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(of="id")
public class User {
    int id;
    @NonNull UserInfo info;

    public User(int id, UserInfo info) {
        this.id = id;
        this.info = info;
    }

    public User(int id, Gender gender, int age, Occupation occupation, String zipCode) {
        this.id = id;
        this.info = new UserInfo(gender, age, occupation, zipCode);
    }

    // Getters
    public Gender getGender() { return info.getGender(); }
    public int getAge() { return info.getAge(); }
    public Occupation getOccupation() { return info.getOccupation(); }
    public String getZipCode() { return info.getZipCode(); }
}
