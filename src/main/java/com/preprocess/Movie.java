package com.preprocess;
import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class Movie {
    public ArrayList<Rating> ratings;
    //Fields from file
    public int id = -1;
    public String title = null;
    public String[] genres = null;

    Movie(int id, String title, String[] genres){
        this.id = id;
        this.title = title;
        this.genres = genres;
        ratings = new ArrayList<Rating>(0);
    }

    //Method to check if this movie is included in given genre
    public boolean isIncludeGenre(String genre){
        for(String g : genres){
            if(g.equals(genre)){
                return true;
            }
        }
        return false;
    }

}

