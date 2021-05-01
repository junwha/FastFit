package com.cse364.app;

import com.cse364.domain.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MovieWithRatings {
    Movie movie;
    List<Integer> ratings;

    public MovieWithRatings(Movie movie, List<Integer> ratings) {
        this.movie = movie;
        this.ratings = new ArrayList<>(ratings);
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public double getAverageRating() {
        int sum = 0;
        for (Integer rating: ratings) {
            sum = sum + rating;
        }
        return sum / ratings.size();
    }
}

class CompareByAverageRating implements Comparator<MovieWithRatings> {
    @Override
    public int compare(MovieWithRatings o1, MovieWithRatings o2) {
        return Double.valueOf(o1.getAverageRating())
                .compareTo(Double.valueOf(o2.getAverageRating()));
    }
}