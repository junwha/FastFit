package com.cse364.app.exceptions;

public class GenreValidationException extends Exception {
    private String name;

    public String getName() {
        return name;
    }

    public GenreValidationException(String name) {
        this.name = name;
    }
}
