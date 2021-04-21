package com.cse364.domain;

import java.util.List;

public interface OccupationRepository {
    /**
     * Returns a Occupation by id.
     * If there is no occupation with given id, it returns `null`.
     */
    Occupation get(int id);

    /**
     * Returns a Occupation corresponding to the given name or alias.
     * The search is case-insensitive.
     * Also, special characters (except for '/') and whitespaces are ignored.
     * If the name is not a valid occupation name, it returns `null`.
     */
    Occupation searchByName(String name);
}
