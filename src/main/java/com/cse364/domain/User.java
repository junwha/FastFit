package com.cse364.domain;

public class User {
    public static class UserInfo {
        public Gender gender;
        public int age;
        public Occupation occupation;
        public String zipCode;

        public UserInfo(Gender gender, int age, Occupation occupation, String zipCode) {
            this.gender = gender;
            this.age = age;
            this.occupation = occupation;
            this.zipCode = zipCode;
        }
    }
    
    private int id;
    private UserInfo info;

    public User(int id, Gender gender, int age, Occupation occupation, String zipCode){
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
    public int getId() { return id; }
    public Gender getGender() { return info.gender; }
    public int getAge() { return info.age; }
    public Occupation getOccupation() { return info.occupation; }
    public String getZipCode() { return info.zipCode; }
}
