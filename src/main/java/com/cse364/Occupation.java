package com.cse364;

import java.util.Map;
public class Occupation {
    private int category;
    private String name;

    /**
     * WARNING: Don't use this constructor directly!
     * Please access Genre data using GenreStorage instead.
     */
    Occupation(String name, int category) {
        this.category = category;
        this.name = name;
    }

    Occupation(Map.Entry<String, Integer> entry) {
        this.category = entry.getValue();
        this.name = entry.getKey();
    }

    boolean equals(Occupation occupation) {
        return this.category == occupation.category;
    }
    boolean equals(int category) {
        return this.category == category;
    }

    // Getters
    String getName() { return name; }
    int getCategory() { return category; }
}
