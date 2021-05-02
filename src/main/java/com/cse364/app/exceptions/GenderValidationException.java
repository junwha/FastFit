package com.cse364.app.exceptions;

public class GenderValidationException extends Exception {
    private String value;

    public String getValue() {
        return value;
    }

    public GenderValidationException(String value) {
        this.value = value;
    }
}
