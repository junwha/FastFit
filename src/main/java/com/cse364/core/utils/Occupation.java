package com.cse364.core.utils;

import java.util.Map;
public class Occupation {
    private int id;
    private String name;

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }

    /**
     * WARNING: Don't use this constructor directly!
     * Please access Genre data using GenreStorage instead.
     */
    public Occupation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Occupation(Map.Entry<String, Integer> entry) {
        this.id = entry.getValue();
        this.name = entry.getKey();
    }

    public boolean equals(Occupation occupation) {
        return this.id == occupation.id;
    }
}
