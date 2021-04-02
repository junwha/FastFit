package com.cse364;

import java.util.HashMap;
import java.util.Map;

public class OccupationStorage {
    private HashMap<String, Occupation> occupations = new HashMap<>();
    /**
     * Initializes OccupationStorage.
     */

    OccupationStorage(HashMap<String, Integer> occupationTable) {
        for (Map.Entry<String, Integer> entry: occupationTable.entrySet()) {

            occupations.put(getSearchName(entry.getKey()), new Occupation(entry));
        }
    }

    /**
     * Returns a `Occupation` corresponding to the given name.
     * The search is case-insensitive.
     * Also, special characters and whitespaces are ignored.
     * If the name is not a valid occupation name, it returns `null`.
     */
    public Occupation getOccupation(String name) {
        return occupations.get(getSearchName(name));
    }

    /**
     * Returns a lowercased string of the given name,
     * with all special characters and whitespaces removed.
     * This method is for normalizing the Genre names for easier search.
     */
    private static String getSearchName(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
