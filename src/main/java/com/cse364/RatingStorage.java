package com.cse364;

import java.util.HashMap;
import java.util.ArrayList;

public class RatingStorage {
    private final HashMap<User, ArrayList<Rating>> userMap;
    private final HashMap<Movie, ArrayList<Rating>> movieMap;

    RatingStorage(){
        userMap = new HashMap<User, ArrayList<Rating>>();
        movieMap = new HashMap<Movie, ArrayList<Rating>>();
    }

    public void add(Rating rating){
        if(!userMap.containsKey(rating.user)){
            userMap.put(rating.user, new ArrayList<Rating>());
        }
        if(!movieMap.containsKey(rating.movie)){
            movieMap.put(rating.movie, new ArrayList<Rating>());
        }

        movieMap.get(rating.movie).add(rating);
        userMap.get(rating.user).add(rating);
    }

    public ArrayList<Rating> getRating(Movie movie){
        return movieMap.get(movie);
    }

    public ArrayList<Rating> getRating(User user){
        return userMap.get(user);
    }
}
