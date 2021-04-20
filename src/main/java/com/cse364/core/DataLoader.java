package com.cse364.core;

import com.cse364.core.utils.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
MOVIES FILE
MovieID::Title::Genres

USER FILE
UserID::Gender::Age::Occupation::Zip-code

RATINGS FILE
UserID::MovieID::Rating::Timestamp
 */

public class DataLoader {
    private static String[] genreNames = {
        "Action", "Adventure", "Animation", "Children's", "Comedy", "Crime", "Documentary",
        "Drama", "Fantasy", "Film-Noir", "Horror", "Musical", "Mystery", "Romance", "Sci-Fi",
        "Thriller", "War", "Western",
    };

    public static final GenreStorage genreStorage = new GenreStorage(genreNames);
    public final static OccupationStorage occupationStorage = new OccupationStorage(){{
        add(new Occupation(0, "Other"));
        add(new Occupation(1, "Academic/Educator"), List.of("academic", "educator"));
        add(new Occupation(2, "Artist"));
        add(new Occupation(3, "Clerical/Admin"), List.of("clerical", "admin"));
        add(new Occupation(4, "College/Grad student"), List.of("college student", "grad student"));
        add(new Occupation(5, "Customer service"));
        add(new Occupation(6, "Doctor/Health care"), List.of("doctor", "health care"));
        add(new Occupation(7, "Executive/Managerial"), List.of("executive", "managerial"));
        add(new Occupation(8, "Farmer"));
        add(new Occupation(9, "Homemaker"));
        add(new Occupation(10, "K-12 student"));
        add(new Occupation(11, "Lawyer"));
        add(new Occupation(12, "Programmer"));
        add(new Occupation(13, "Retired"));
        add(new Occupation(14, "Sales/Marketing"), List.of("sales", "marketing"));
        add(new Occupation(15, "Scientist"));
        add(new Occupation(16, "Self-employed"));
        add(new Occupation(17, "Technician/Engineer"), List.of("technician", "engineer"));
        add(new Occupation(18, "Tradesman/Craftsman"), List.of("tradesman", "craftsman"));
        add(new Occupation(19, "Unemployed"));
        add(new Occupation(20, "Writer"));
    }};

    public static HashMap<Integer, Movie> movies = new HashMap<Integer, Movie>(0);
    public static HashMap<Integer, User> users = new HashMap<Integer, User>(0);
    public static final RatingStorage ratingStorage = new RatingStorage();

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
                occupationStorage.getOccupationById(Integer.parseInt(args[3])), args[4]
            ));
            // User test = users.get(id);
            // System.out.println(test.id);
        }
    }

    private static void parseRatings(File ratingsFile) {
        ArrayList<String[]> data = readFileData(ratingsFile);

        for (String[] args : data) {
            int movieId = Integer.parseInt(args[1]);
            int userId = Integer.parseInt(args[0]);

            ratingStorage.add(new Rating(
                movies.get(movieId),
                users.get(userId),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
            ));

            // Movie test = movies.get(movieId);
            // // System.out.println(test.ratings.get(test.ratings.size()-1).rating);
            // System.out.println(test.ratings.get(test.ratings.size()-1).user.occupation);
        }
    }

    private static void parseLinks(File linksFile) {
        ArrayList<String[]> data = readFileData(linksFile);

        for(String[] args : data) {
            int movieId = Integer.parseInt(args[0]);
            String link = "http://www.imdb.com/title/tt" + args[1];

            movies.get(movieId).setLink(link);
        }
    }

    public static void read() {
        parseMovies(new File("./data/movies.dat"));
        parseUsers(new File("./data/users.dat"));
        parseRatings(new File("./data/ratings.dat"));
        parseLinks(new File("./data/links.dat"));
    }
}
