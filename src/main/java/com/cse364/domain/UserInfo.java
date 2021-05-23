package com.cse364.domain;

import lombok.Value;

@Value
public class UserInfo {
    Gender gender;
    int age;
    Occupation occupation;
    String zipCode;

    public int getSimilarity(UserInfo another) {
        int similarity = 0;
        if (gender == null || another.gender == null || gender.equals(another.gender)) {
            similarity++;
        }
        if (age == -1 || another.age == -1 || age == another.age) { // TODO: check age similarity
            similarity++;
        }
        if (occupation == null || another.occupation == null || occupation.equals(another.occupation)) {
            similarity++;
        }
        return similarity;
    }
}
