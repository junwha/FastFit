package com.cse364;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Iterator;

// Input format
// java (filename) genres occupation

// multiple category input "|"(bar)

public class Main {
    public static void main(String[] args) {
        // Load all data
        DataLoader.read();
        
        //Checking Format valid
        if(args.length != 2)
        {
            System.out.println("Input Error : Input format is '[genre1\\|genre2\\| ... ] [occupation]'");
            System.exit(0);
        }
        
        //Preprocess genres and occupation
        List<Genre> genres = new ArrayList();
        for (String genreName : args[0].split("\\|")) {
            Genre genre = DataLoader.genreStorage.getGenre(genreName);
            if (genre == null) {
                System.out.format("Error : The genre %s does not exist in database\n", genreName);
                System.exit(0);
            }
            genres.add(genre);
        }

        String occupation = args[1].toLowerCase(Locale.ROOT);

        //Checking Occupation valid
        if(!DataLoader.occupationTable.containsKey(occupation))
        {
            System.out.format("Error : The occupation %s does not exist in database\n", occupation);
            System.exit(0);
        }

        // Print average rating
        double average = 0;
        try {
            average = averageRating(genres, occupation);
        } catch (NoRatingForTheGenreException e) {
            System.out.format("Error : There were no ratings given to movies with genre [%s] by [%s]\n",
                formatGenres(genres), occupation);
            System.exit(0);
        }
        
        System.out.format("Average rating of movies with genres [%s]\n", formatGenres(genres));
        System.out.format("rated by people with occupation [%s]\n", occupation);
        System.out.format("is [%f].\n", average);
    }

    
    static String formatGenres(List<Genre> genres) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            sb.append(genres.get(i).getName());
            if (i < genres.size() - 1) { sb.append(", "); }
        }
        return sb.toString();
    }

    // Returns average rating for movies with specified genres,
    // rated by user having specified occupation.
    public static double averageRating(List<Genre> genres, String occupation) throws NoRatingForTheGenreException {
        int ratSum = 0;
        int ratCnt = 0;

        //Get each entry from movies Map
        for(Map.Entry<Integer, Movie> movEntry : DataLoader.movies.entrySet())
        {
            Movie mov = movEntry.getValue();

            if (!mov.hasGenres(genres)) { continue; }

            //Check occupations of rating
            for(Rating rat : mov.ratings)
            {
                if(rat.user.occupation == DataLoader.occupationTable.get(occupation))
                {
                    ratCnt++;
                    ratSum += rat.rating;
                }
            }
        }

        if (ratCnt == 0) {
            throw new NoRatingForTheGenreException();
        }

        //Calculate Average
        double ratAvg;
        
        ratAvg = Double.valueOf(ratSum) / Double.valueOf(ratCnt);
        return ratAvg;
    }
}
