package com.cse364.domain;

import java.util.List;

public interface MovieRepository {
    Movie get(int id);
    List<Movie> all();
}
