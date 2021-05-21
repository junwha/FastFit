package com.cse364.domain;

import lombok.Value;

@Value
public class Occupation {
    int id;
    String name;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Occupation)) { return false; }
        Occupation occupation = (Occupation) o;
        return this.id == occupation.id;
    }
}
