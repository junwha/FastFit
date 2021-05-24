package com.cse364.app.exceptions;

public class AgeValidationException extends Exception {
    private String value;

    public String getValue() {
        return value;
    }

    public AgeValidationException(String value) {
        this.value = value;
    }
}
