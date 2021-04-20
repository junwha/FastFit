package com.cse364.core.storage;

import com.cse364.core.utils.Occupation;

import java.util.HashMap;
import java.util.List;

public class OccupationStorage {
    private HashMap<Integer, Occupation> occupationById = new HashMap<>();
    private HashMap<String, Occupation> occupationByAlias = new HashMap<>();

    /**
     * Initializes OccupationStorage.
     */
    public OccupationStorage() { }

    /**
     * Adds an occupation to the storage.
     * It can be searched by its name only.
     */
    public void add(Occupation occupation) {
        occupationById.put(occupation.getId(), occupation);
        occupationByAlias.put(getSearchName(occupation.getName()), occupation);
    }
    
    /**
     * Adds an occupation to the storage.
     * It can be searched by given name aliases.
     */
    public void add(Occupation occupation, List<String> aliases) {
        occupationById.put(occupation.getId(), occupation);
        occupationByAlias.put(getSearchName(occupation.getName()), occupation);
        for (String name : aliases) {
            occupationByAlias.put(getSearchName(name), occupation);
        }
    }

    /**
     * Returns a Occupation by id.
     * If there is no occupation with given id, it returns `null`.
     */
    public Occupation getOccupationById(int id) {
        return occupationById.get(id);
    }

    /**
     * Returns a Occupation corresponding to the given name or alias.
     * The search is case-insensitive.
     * Also, special characters (except for '/') and whitespaces are ignored.
     * If the name is not a valid occupation name, it returns `null`.
     */
    public Occupation getOccupationByName(String name) {
        return occupationByAlias.get(getSearchName(name));
    }

    /**
     * Returns a lowercased string of the given name,
     * with all special characters (except for '/') and whitespaces removed.
     * This method is for normalizing the Genre names for easier search.
     */
    private static String getSearchName(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9\\/]", "");
    }
}
