package com.github.cse364;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
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
    public static HashMap<Integer, Movie> movies = new HashMap<Integer, Movie>(0);
    public static HashMap<Integer, User> users = new HashMap<Integer, User>(0);

    private final static String[] occupationTable = {"other", "academic/educator", "artist",
            "clerical/admin", "college/grad student", "customer service", "doctor/health care",
            "executive/managerial", "farmer", "homemaker", "K-12 student", "lawyer", "programmer",
            "retired", "sales/marketing", "scientist", "self-employed", "technician/engineer",
            "tradesman/craftsman", "unemployed", "writer"};


    private static ArrayList<String[]> parseFile(File file) {
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
            System.out.println("[!File NOT FOUND] Please clone git again");
        } catch (IOException e) {
            System.out.println("[!File NOT CRASHED] Please clone git again");
        }

        return contents;
    }

    public static void read() {
        ArrayList<String[]> movieContents = parseFile(new File("./data/movies.dat"));
        ArrayList<String[]> userContents = parseFile(new File("./data/users.dat"));
        ArrayList<String[]> ratingContents = parseFile(new File("./data/ratings.dat"));

        var contents = new ArrayList<ArrayList<String[]>>();
        contents.add(movieContents);
        contents.add(userContents);
        contents.add(ratingContents);

        //preprocess movies
        for (String[] args : contents.get(0)) {
            int id = Integer.parseInt(args[0]);
            movies.put(id, new Movie(id, args[1], args[2].split("|")));
//            Movie test = movies.get(id);
//            System.out.println(test.title);
        }

        //preprocess users
        for (String[] args : contents.get(1)) {
            //Allocate genre(enum type)
            Gender g = null;
            if (args[1].equals('M')) {
                g = Gender.M;
            } else if (args[1].equals(('F'))) {
                g = Gender.F;
            }

            int id = Integer.parseInt(args[0]);
            users.put(id, new User(id, g,
                    Integer.parseInt(args[2]), occupationTable[Integer.parseInt(args[3])], args[4]));
//                User test = users.get(id);
//                System.out.println(test.id);
        }

        //preprocess ratings
        for (String[] args : contents.get(2)) {
            int movieId = Integer.parseInt(args[1]);
            movies.get(movieId)
                    .ratings.add(new Rating(users.get(Integer.parseInt(args[0])), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
//            Movie test = movies.get(movieId);
//            // System.out.println(test.ratings.get(test.ratings.size()-1).rating);
//            System.out.println(test.ratings.get(test.ratings.size()-1).user.occupation);
        }

    }
}
