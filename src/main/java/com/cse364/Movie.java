package com.cse364;
import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class Movie {
    public ArrayList<Rating> ratings;
    //Fields from file
    public int id;
    public String title;
    public String[] genres;

    Movie(int id, String title, String[] genres){
        this.id = id;
        this.title = title;
        this.genres = genres;
        ratings = new ArrayList<Rating>(0);
    }

    // Check if this movie has given genre
    public boolean hasGenre(String genre){
        for(String g : genres){
            if(g.equals(genre)){
                return true;
            }
        }
        return false;
    }

}

