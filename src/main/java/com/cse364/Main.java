package com.cse364;
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
        Genre[] Genres = {};
        for (String genreName : args[0].split("\\|")) {
            Genre genre = DataLoader.genreStorage.getGenre(genreName);
            if (genre == null) {
                // TODO: Print error message
                System.exit(0);
            }
        }
        String occupation = args[1].toLowerCase(Locale.ROOT);

        //Checking Occupation valid
        if(!DataLoader.occupationTable.containsKey(occupation))
        {
            System.out.format("Error : The occupation %s does not exist in database\n", occupation);
            System.exit(0);
        }

        // Print average rating
        double average = averageRating(genres, occupation);
        
        System.out.format("Average rating of movies with genres [%s]\n", formatGenres(genres));
        System.out.format("rated by people with occupation [%s]\n", occupation);
        System.out.format("is [%f].\n", average);
    }

    static String formatGenres(String[] genres) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.length; i++) {
            sb.append(genres[i]);
            if (i < genres.length - 1) { sb.append(", "); }
        }
        return sb.toString();
    }

    // Returns average rating for movies with specified genres,
    // rated by user having specified occupation.
    public static double averageRating(Genre[] genres, String occupation) {
        int ratSum = 0;
        int ratCnt = 0;

        //Get each entry from movies Map
        for(Map.Entry<Integer, Movie> movEntry : DataLoader.movies.entrySet())
        {
            Movie mov = movEntry.getValue();
            //System.out.println(mov.title);

            //If at least one of given genre is not included in this movie, continue
            int genreCnt = 0;
            for (Genre genre: genres) {
                if (mov.hasGenre(genre)) { genreCnt++; }
            }

            if(genreCnt < genres.length)
            {
                continue;
            }

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


        //Calculate Average
        double ratAvg;
        if(ratCnt == 0)
        {
            ratAvg = -1.0;
        }
        else
        {
            ratAvg = Double.valueOf(ratSum) / Double.valueOf(ratCnt);
        }

        return ratAvg;
    }
}
