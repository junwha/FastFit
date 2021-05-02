package com.cse364.app.exceptions;

public class InvalidOccupationNameException extends Exception {
    private String name;

    public String getName() {
        return name;
    }

    public InvalidOccupationNameException(String name) {
        this.name = name;
    }
}
