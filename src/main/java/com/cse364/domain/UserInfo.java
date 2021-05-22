package com.cse364.domain;

import lombok.Value;

@Value
public class UserInfo {
    Gender gender;
    int age;
    Occupation occupation;
    String zipCode;
}
