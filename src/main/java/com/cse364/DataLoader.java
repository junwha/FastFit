package com.cse364;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

/*
MOVIES FILE
MovieID::Title::Genres

USER FILE
UserID::Gender::Age::Occupation::Zip-code

RATINGS FILE
UserID::MovieID::Rating::Timestamp
 */

public class DataLoader {
    public final static HashMap<String, Integer> occupationTable = new HashMap<String, Integer>(){
        {
            put("other", 0);
            put("academic", 1);put("educator", 1);
            put("artist", 2);
            put("clerical", 3);put("admin", 3);
            put("college", 4);put("grad student", 4);put("gradstudent", 4);
            put("customer service", 5);put("customerservice", 5);
            put("doctor", 6);put("health care", 6);put("healthcare", 6);
            put("executive", 7);put("managerial", 7);
            put("farmer", 8);
            put("homemaker", 9);
            put("k-12 student", 10);put("k-12student", 10);
            put("lawyer", 11);
            put("programmer", 12);
            put("retired", 13);
            put("sales", 14);put("marketing", 14);
            put("self-employed", 15);
            put("technician", 16);put("engineer", 16);
            put("tradesman", 17);put("craftsman", 17);
            put("unemployed", 18);
            put("writer", 19);
        }
    };

    private static String[] genreNames = {
        "Action", "Adventure", "Animation", "Children's", "Comedy", "Crime", "Documentary",
        "Drama", "Fantasy", "Film-Noir", "Horror", "Musical", "Mystery", "Romance", "Sci-Fi",
        "Thriller", "War", "Western",
    };

    public static HashMap<Integer, Movie> movies = new HashMap<Integer, Movie>(0);
    public static HashMap<Integer, User> users = new HashMap<Integer, User>(0);
    public static final GenreStorage genreStorage = new GenreStorage(genreNames);

    private static ArrayList<String[]> readFileData(File file) {
        ArrayList<String[]> contents = new ArrayList<String[]>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);

            String buffer = "";
            while ((buffer = buffReader.readLine()) != null) {
                contents.add(buffer.split("::"));
            }

            fileReader.close();
            buffReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File NOT FOUND : Please clone git again");
        } catch (IOException e) {
            System.out.println("File CRASHED : Please clone git again");
        }

        return contents;
    }

    private static void parseMovies(File moviesFile) {
        ArrayList<String[]> data = readFileData(moviesFile);

        for (String[] args : data) {
            int id = Integer.parseInt(args[0]);
            ArrayList<Genre> genres = new ArrayList();
            for (String genreName: args[2].toLowerCase().split("\\|")) {
                genres.add(genreStorage.getGenre(genreName));
            }
            movies.put(id, new Movie(id, args[1], genres));
            // Movie test = movies.get(id);
            // System.out.println(test.title);
        }
    }

    private static void parseUsers(File usersFile) {
        ArrayList<String[]> data = readFileData(usersFile);

        for (String[] args : data) {
            //Allocate genre(enum type)
            Gender g = null;
            if (args[1].equals("M")) {
                g = Gender.M;
            } else if (args[1].equals(("F"))) {
                g = Gender.F;
            }

            int id = Integer.parseInt(args[0]);
            users.put(id, new User(
                id, g, Integer.parseInt(args[2]),
                Integer.parseInt(args[3]), args[4]
            ));
            // User test = users.get(id);
            // System.out.println(test.id);
        }
    }

    private static void parseRatings(File ratingsFile) {
        ArrayList<String[]> data = readFileData(ratingsFile);

        for (String[] args : data) {
            int movieId = Integer.parseInt(args[1]);
            movies.get(movieId)
                .ratings.add(new Rating(
                    users.get(Integer.parseInt(args[0])),
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3])
                ));
            // Movie test = movies.get(movieId);
            // // System.out.println(test.ratings.get(test.ratings.size()-1).rating);
            // System.out.println(test.ratings.get(test.ratings.size()-1).user.occupation);
        }
    }

    public static void read() {
        parseMovies(new File("./data/movies.dat"));
        parseUsers(new File("./data/users.dat"));
        parseRatings(new File("./data/ratings.dat"));
    }
}
