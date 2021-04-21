package com.cse364.domain;

import java.util.Map;

public class Occupation {
    private int id;
    private String name;

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }

    public Occupation(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public boolean equals(Occupation occupation) {
        return this.id == occupation.id;
    }
}
