package com.cse364.infra;

import com.cse364.domain.*;
import com.cse364.infra.dtos.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class DataLoader {
    private static List<String[]> parseData(Reader reader) {
        List<String[]> contents = new ArrayList<>();

        try (BufferedReader buffReader = new BufferedReader(reader)) {
            String buffer;
            while ((buffer = buffReader.readLine()) != null) {
                contents.add(buffer.split("::"));
            }
        } catch (IOException e) {
            System.out.println("Unexpected IO exception occurred while reading file data.");
        }

        return contents;
    }

    public static List<MovieDto> loadMovies(Reader moviesReader, Reader linksReader, String linkPrefix) {
        List<String[]> moviesData = parseData(moviesReader);
        List<String[]> linksData = parseData(linksReader);
        TreeMap<Integer, MovieDto> movies = new TreeMap<>();

        for (String[] args : moviesData) {
            int id = Integer.parseInt(args[0]);
            String title = args[1];
            List<String> genres = Arrays.asList(args[2].split("\\|"));
            movies.put(id, new MovieDto(id, title, genres, null));
        }

        for (String[] args : linksData) {
            int id = Integer.parseInt(args[0]);
            if (movies.containsKey(id)) {
                movies.get(id).link = linkPrefix + args[1];
            }
        }

        return new ArrayList<>(movies.values());
    }

    public static List<UserDto> loadUsers(Reader usersReader) {
        List<String[]> data = parseData(usersReader);
        List<UserDto> users = new ArrayList<>();

        for (String[] args : data) {
            int id = Integer.parseInt(args[0]);
            String gender = args[1];
            int age = Integer.parseInt(args[2]);
            int occupation = Integer.parseInt(args[3]);
            String zipCode = args[4];
            users.add(new UserDto(id, gender, age, occupation, zipCode));
        }

        return users;
    }

    public static List<RatingDto> loadRatings(Reader ratingsReader) {
        List<String[]> data = parseData(ratingsReader);
        List<RatingDto> ratings = new ArrayList<>();

        for (String[] args : data) {
            int user = Integer.parseInt(args[0]);
            int movie = Integer.parseInt(args[1]);
            int rating = Integer.parseInt(args[2]);
            int timestamp = Integer.parseInt(args[3]);
            ratings.add(new RatingDto(user, movie, rating, timestamp));
        }

        return ratings;
    }
}