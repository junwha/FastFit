package com.cse364.app.exceptions;

public class UserInfoValidationException extends Exception {
    private String field;
    private String value;

    public String getField() {
        return field;
    }
    public String getValue() {
        return value;
    }

    public UserInfoValidationException(String field, String value) {
        this.field = field;
        this.value = value;
    }
}
