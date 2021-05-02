package com.cse364.app.exceptions;

public class InvalidGenreNameException extends Exception {
    private String name;

    public String getName() {
        return name;
    }

    public InvalidGenreNameException(String name) {
        this.name = name;
    }
}
