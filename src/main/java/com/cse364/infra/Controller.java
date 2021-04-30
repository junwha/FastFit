package com.cse364.infra;

import com.cse364.app.AverageRatingService;
import com.cse364.app.NoRatingForGenreException;
import com.cse364.app.RankingService;
import com.cse364.domain.Genre;
import com.cse364.domain.Occupation;
import com.cse364.domain.User;
import com.cse364.domain.Movie;
import com.cse364.domain.Gender;
import com.cse364.domain.UserInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

public class Controller {
    private static AverageRatingService averageRatingService;
    private static RankingService rankingService;
    private static InMemoryGenreRepository genreRepository;
    private static InMemoryOccupationRepository occupationRepository;
    private static InMemoryMovieRepository movieRepository;
    private static InMemoryRatingRepository ratingRepository;
    private static InMemoryUserRepository userRepository;

    public static void configure(String[] args) {
        //Select behaviour by input length
        if (args.length == 2) {
            doAverageRatingService(args);
        } else if (args.length == 3) {
            doTop10Movieuser(args);
        } else if (args.length == 4) {
            doTop10Movieusergenres(args);
        } else {
            System.out.println("Input Error : Input format is...\n" +
                                "    AverageRating : '[genre1\\|genre2\\| ... ] [occupation]'\n" + 
                                "    RankingforUser : ''\n" +
                                "    RankingforUser&Genre : ''");
            System.exit(0);
        }
    }

    static void doAverageRatingService(String[] args) {
        DataLoader.read();
        averageRatingService = new AverageRatingService(
                DataLoader.movies,
                DataLoader.ratings
        );

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

        if (occupation == null) {
            System.out.format("Error : The occupation %s does not exist in database\n", args[1]);
            System.exit(0);
        }

        printAverageRating(new ArrayList<Genre>(genres), occupation);
    }

    static void doTop10Movieuser(String[] args) {
        DataLoader.read();
        rankingService = new RankingService(
                DataLoader.movies,
                DataLoader.users,
                DataLoader.ratings
        );
        
        Gender gender = null;
        if ("".equals(args[0])) {
            gender = null;
        } else if ("M".equals(args[0])) {
            gender = Gender.M;
        } else if ("F".equals(args[0])) {
            gender = Gender.F;
        } else {
            System.out.println("WHAT???");
            System.exit(0);
        }
        
        int age = -1;
        if (!"".equals(args[1])) {age = Integer.valueOf(args[1]);}
        Occupation occupation = DataLoader.occupations.searchByName(args[2]);
        UserInfo theInfo = new UserInfo(gender, age, occupation, "00000");

        List<Movie> topRank = rankingService.getTopNMovie(theInfo, 10, Collections.emptyList());

        System.out.println("The movie we recommend are:");
        for (Movie movie : topRank) {
            System.out.format("%s\n", movie.getTitle());
        }
    }

    static void doTop10Movieusergenres(String[] args) {
        System.out.println("Not yet 2");
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
