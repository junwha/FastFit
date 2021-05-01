package com.cse364.infra;

import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.cse364.domain.*;


public class LegacyDataLoader {
    public static GenreRepository genres = new InMemoryGenreRepository(List.of(
        "Action", "Adventure", "Animation", "Children's", "Comedy", "Crime", "Documentary",
        "Drama", "Fantasy", "Film-Noir", "Horror", "Musical", "Mystery", "Romance", "Sci-Fi",
        "Thriller", "War", "Western"
    ));
    public static OccupationRepository occupations = new InMemoryOccupationRepository(){{
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

    public static UserRepository users;
    public static MovieRepository movies;
    public static RatingRepository ratings;

    private static List<String[]> parseData(Reader reader) {
        List<String[]> contents = new ArrayList<String[]>();

        try (BufferedReader buffReader = new BufferedReader(reader)) {
            String buffer = "";
            while ((buffer = buffReader.readLine()) != null) {
                contents.add(buffer.split("::"));
            }
        } catch (IOException e) {
            System.out.println("Unexpected IO exception occurred while reading file data.");
        }

        return contents;
    }

    private static MovieRepository getMovieRepository(Reader moviesReader) {
        InMemoryMovieRepository movies = new InMemoryMovieRepository();
        List<String[]> data = parseData(moviesReader);

        for (String[] args : data) {
            int id = Integer.parseInt(args[0]);
            ArrayList<Genre> movieGenres = new ArrayList();
            for (String genreName: args[2].toLowerCase().split("\\|")) {
                movieGenres.add(genres.searchByName(genreName));
            }
            movies.add(new Movie(id, args[1], movieGenres));
        }
        
        return movies;
    }

    private static UserRepository getUserRepository(Reader usersReader) {
        InMemoryUserRepository users = new InMemoryUserRepository();
        List<String[]> data = parseData(usersReader);

        for (String[] args : data) {
            Gender g = null;
            if (args[1].equals("M")) {
                g = Gender.M;
            } else if (args[1].equals(("F"))) {
                g = Gender.F;
            }
            Occupation occupation = occupations.get(Integer.parseInt(args[3]));

            int id = Integer.parseInt(args[0]);
            users.add(
                new User(id, g, Integer.parseInt(args[2]), occupation, args[4])
            );
        }

        return users;
    }

    private static RatingRepository getRatingRepository(Reader ratingsReader) {
        InMemoryRatingRepository ratings = new InMemoryRatingRepository();
        List<String[]> data = parseData(ratingsReader);

        for (String[] args : data) {
            int movieId = Integer.parseInt(args[1]);
            int userId = Integer.parseInt(args[0]);

            ratings.add(new Rating(
                movies.get(movieId),
                users.get(userId),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
            ));
        }

        return ratings;
    }

    private static void parseLinks(Reader linksReader) {
        List<String[]> data = parseData(linksReader);

        for (String[] args : data) {
            int movieId = Integer.parseInt(args[0]);
            String link = "http://www.imdb.com/title/tt" + args[1];

            movies.get(movieId).setLink(link);
        }
    }

    public static void read() {
        try {
            movies = getMovieRepository(new FileReader("./data/movies.dat"));
            users = getUserRepository(new FileReader("./data/users.dat"));
            ratings = getRatingRepository(new FileReader("./data/ratings.dat"));
            parseLinks(new FileReader("./data/links.dat"));
        } catch (FileNotFoundException e) {
            System.out.println("Some data file is missing. Please try to clone the repo again.");
            System.out.println(e.getMessage());
        }
    }
}