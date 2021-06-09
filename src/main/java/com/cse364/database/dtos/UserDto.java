package com.cse364.database.dtos;

import lombok.Data;

@Data
public class UserDto {
    int id;
    String gender;
    int age;
    int occupation;
    String code;
}
