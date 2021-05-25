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
        if (age == -1 || another.age == -1 || areAgesSimilar(age, another.age)) {
            similarity++;
        }
        if (occupation == null || another.occupation == null || occupation.equals(another.occupation)) {
            similarity++;
        }
        return similarity;
    }

    // TODO: separate Age class
    static boolean areAgesSimilar(int age1, int age2) {
        return normalizeAge(age1) == normalizeAge(age2);
    }

    static int normalizeAge(int age) {
        if (age < 18) return 1;
        else if (age < 25) return 18;
        else if (age < 35) return 25;
        else if (age < 45) return 35;
        else if (age < 50) return 45;
        else if (age < 56) return 50;
        else return 56;
    }
}
