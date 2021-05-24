package com.cse364.app;

/**
 * Exception thrown when there are no movie with the specific name
 * used in RecommendMoviesFromMovieService
 */
public class NoMovieWithGivenNameException extends Exception {
    private String value;

    public String getValue() {
        return value;

    public NoMovieWithGivenNameException(String value) {
        this.value = value;
    }
} 
