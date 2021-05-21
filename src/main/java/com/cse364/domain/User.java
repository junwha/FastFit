package com.cse364.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private int id;
    private UserInfo info;

    public User(int id, Gender gender, int age, Occupation occupation, String zipCode) {
        this.id = id;
        this.info = new UserInfo(gender, age, occupation, zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) { return false; }
        User user = (User) o;
        return id == user.id;
    }

    // Getters
    public Gender getGender() { return info.getGender(); }
    public int getAge() { return info.getAge(); }
    public Occupation getOccupation() { return info.getOccupation(); }
    public String getZipCode() { return info.getZipCode(); }
}
