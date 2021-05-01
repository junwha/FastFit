package com.cse364.infra.dtos;

public class UserDto {
    public int id;
    public String gender;
    public int age;
    public int occupation;
    public String zipCode;

    public UserDto(int id, String gender, int age, int occupation, String zipCode) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserDto)) { return false; }
        UserDto userDto = (UserDto) o;
        return id == userDto.id &&
                gender.equals(userDto.gender) &&
                age == userDto.age &&
                occupation == userDto.occupation &&
                zipCode.equals(userDto.zipCode);
    }
}