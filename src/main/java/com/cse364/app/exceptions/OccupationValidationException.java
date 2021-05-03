package com.cse364.app.exceptions;

public class OccupationValidationException extends Exception {
    private String name;

    public String getName() {
        return name;
    }

    public OccupationValidationException(String name) {
        this.name = name;
    }
}
