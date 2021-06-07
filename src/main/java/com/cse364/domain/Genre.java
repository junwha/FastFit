package com.cse364.domain;
import lombok.NonNull;
import lombok.Value;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genre")
@Value
public class Genre {
    // Since each genre has no associated id, we use its display name as id.
    @Id
    @NonNull String name;
}
