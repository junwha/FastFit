package com.cse364.domain;
import lombok.NonNull;
import lombok.Value;

@Value
public class Genre {
    // Since each genre has no associated id, we use its display name as id.
    @NonNull String name;
}
