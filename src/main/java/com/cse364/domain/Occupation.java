package com.cse364.domain;

import lombok.NonNull;
import lombok.Value;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Value
public class Occupation {
    @Id
    int id;
    @NonNull String name;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Occupation)) { return false; }
        Occupation occupation = (Occupation) o;
        return this.id == occupation.id;
    }
}
