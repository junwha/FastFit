package com.cse364;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Iterator;

// Input format
// java (filename) genres occupation

// multiple category input "|"(bar)

public class Main {
    public static void main(String[] args){
        //Checking Format valid
        if(args.length != 2)
        {
            System.out.println("Input Error : Input format is '[genre1\\|genre2\\| ... ] [occupation]'");
            System.exit(0);
        }

        //Preprocess genres and occupation
        String[] genres = args[0].toLowerCase().split("\\|");
        String ocp = args[1].toLowerCase(Locale.ROOT);

        //Checking Occupation valid
        if(!DataLoader.occupationTable.containsKey(ocp))
        {
            System.out.format("Error : The occupation %s does not exist in database\n", ocp);
            System.exit(0);
        }

        //DataLoader will load all date to "HashMap<Integer, Movie> movies" and "HashMap<Integer, User> users" from .dat files
        DataLoader.read();

        // Print average rating
        double avg = averageRating(genres, ocp);
        
        System.out.format(
            "Average rating of movies(genres: %ss) rated by %ss: %f\n",
            args[0], args[1], avg
        );
    }

    // Returns average rating for movies with specified genres,
    // rated by user having specified occupation.
    public static double averageRating(String[] genres, String occupation) {
        int ratSum = 0;
        int ratCnt = 0;

        //Get each entry from movies Map
        for(Map.Entry<Integer, Movie> movEntry : DataLoader.movies.entrySet())
        {
            Movie mov = movEntry.getValue();
            //System.out.println(mov.title);

            //If at least one of given genre is not included in this movie, continue
            int genreCnt = 0;
            for(String genre : genres)
            {
                if(mov.hasGenre(genre.toLowerCase(Locale.ROOT)))
                {
                    genreCnt++;
                }
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
