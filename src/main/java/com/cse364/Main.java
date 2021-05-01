package com.cse364;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import com.cse364.domain.*;
import com.cse364.app.*;
import com.cse364.infra.*;

public class Main {
    private static AverageRatingService averageRatingService;

    public static void main(String[] args) {
        // Load all data
        Config config = new Config();
        averageRatingService = new AverageRatingService(
            config.movies,
            config.ratings
        );

        //Checking Format valid
        if (args.length != 2) {
            System.out.println("Input Error : Input format is '[genre1\\|genre2\\| ... ] [occupation]'");
            System.exit(0);
        }

        //Preprocess genres and occupation
        HashSet<Genre> genres = new HashSet();
        for (String genreName : args[0].split("\\|")) {
            Genre genre = config.genres.searchByName(genreName);
            if (genre == null) {
                System.out.format("Error : The genre %s does not exist in database\n", genreName);
                System.exit(0);
            }
            genres.add(genre);
        }

        Occupation occupation = config.occupations.searchByName(args[1]);

        //Checking Occupation valid
        if (occupation == null) {
            System.out.format("Error : The occupation %s does not exist in database\n", args[1]);
            System.exit(0);
        }

        printAverageRating(new ArrayList<Genre>(genres), occupation);
    }

    /**
     * return String of genres combined with divider
     */
    static String formatGenres(List<Genre> genres, String divider) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            sb.append(genres.get(i).getName());
            if (i < genres.size() - 1) { sb.append(divider); }
        }
        return sb.toString();
    }

    static void printAverageRating(List<Genre> genres, Occupation occupation){
        double average = 0;
        try {
            average = averageRatingService.averageRating(genres, occupation);
        } catch (NoRatingForGenreException e) {
            System.out.format(
                "Error : There were no ratings given to movies with genre [%s] by people with occupation [%s]\n",
                formatGenres(genres, ", "), occupation.getName()
            );
            System.exit(0);
        }

        System.out.format("Average rating of movies with genres [%s]\n", formatGenres(genres, ", "));
        System.out.format("rated by people with occupation [%s]\n", occupation.getName());
        System.out.format("is [%f].\n", average);
    }
}
