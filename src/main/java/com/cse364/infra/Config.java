package com.cse364.infra;

import com.cse364.app.AverageRatingService;
import com.cse364.app.NoRatingForGenreException;
import com.cse364.domain.Genre;
import com.cse364.domain.Occupation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Config {
    private static AverageRatingService averageRatingService;
    private static InMemoryGenreRepository genreRepository;
    private static InMemoryOccupationRepository occupationRepository;
    private static InMemoryMovieRepository movieRepository;
    private static InMemoryRatingRepository ratingRepository;
    private static InMemoryUserRepository userRepository;

    public static void configure(String[] args) {
        //Select behaviour by input length
        if (args.length == 2) {
            //AverageRatingService
        } else if (args.length == 3) {
            //RankingService getTop10Movie(User user)
        } else if (args.length == 4) {
            //RankingService.getTop10Movie(User user, List genres
        } else {
            System.out.println("Input Error : Input format is \n" +
                                "AverageRating : '[genre1\\|genre2\\| ... ] [occupation]'\n" + 
                                "RankingforUser : ''" +
                                "RankingforUser&Genre : ''");
            System.exit(0);
        }
        // Load all data
        DataLoader.read();
        averageRatingService = new AverageRatingService(
                DataLoader.movies,
                DataLoader.ratings
        );

        //Checking Format valid
        if (args.length != 2) {
            System.out.println("Input Error : Input format is '[genre1\\|genre2\\| ... ] [occupation]'");
            System.exit(0);
        }

        //Preprocess genres and occupation
        HashSet<Genre> genres = new HashSet();
        for (String genreName : args[0].split("\\|")) {
            Genre genre = DataLoader.genres.searchByName(genreName);
            if (genre == null) {
                System.out.format("Error : The genre %s does not exist in database\n", genreName);
                System.exit(0);
            }
            genres.add(genre);
        }

        Occupation occupation = DataLoader.occupations.searchByName(args[1]);

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
