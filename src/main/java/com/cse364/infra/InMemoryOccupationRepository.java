package com.cse364.infra;

import java.util.HashMap;
import java.util.List;
import com.cse364.domain.*;

public class InMemoryOccupationRepository implements OccupationRepository {
    private HashMap<Integer, Occupation> occupationById = new HashMap<>();
    private HashMap<String, Occupation> occupationByAlias = new HashMap<>();

    InMemoryOccupationRepository() { }

    /**
     * Adds an occupation to the storage.
     * It can be searched by its name only.
     */
    void add(Occupation occupation) {
        occupationById.put(occupation.getId(), occupation);
        occupationByAlias.put(getSearchName(occupation.getName()), occupation);
    }
    
    /**
     * Adds an occupation to the storage.
     * It can be searched by given name aliases.
     */
    void add(Occupation occupation, List<String> aliases) {
        occupationById.put(occupation.getId(), occupation);
        occupationByAlias.put(getSearchName(occupation.getName()), occupation);
        for (String name : aliases) {
            occupationByAlias.put(getSearchName(name), occupation);
        }
    }

    public Occupation get(int id) {
        return occupationById.get(id);
    }

    public Occupation searchByName(String name) {
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
