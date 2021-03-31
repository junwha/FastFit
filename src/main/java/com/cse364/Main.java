package com.cse364;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Iterator;

// Input format
// java (filename) genres occupation

// multiple category input "|"(bar)

public class Main {
//    public final static String[] occupationTable = {"other", "academic/educator", "artist",
//            "clerical/admin", "college/grad student", "customer service", "doctor/health care",
//            "executive/managerial", "farmer", "homemaker", "K-12 student", "lawyer", "programmer",
//            "retired", "sales/marketing", "scientist", "self-employed", "technician/engineer",
//            "tradesman/craftsman", "unemployed", "writer"};

    private final static HashMap<String, Integer> occupationTable = new HashMap<String, Integer>(){
        {
            put("other", 0);
            put("academic", 1);put("educator", 1);
            put("artist", 2);
            put("clerical", 3);put("admin", 3);
            put("college", 4);put("grad student", 4);put("gradstudent", 4);
            put("customer service", 5);put("customerservice", 5);
            put("doctor", 6);put("health care", 6);put("healthcare", 6);
            put("executive", 7);put("managerial", 7);
            put("farmer", 8);
            put("homemaker", 9);
            put("k-12 student", 10);put("k-12student", 10);
            put("lawyer", 11);
            put("programmer", 12);
            put("retired", 13);
            put("sales", 14);put("marketing", 14);
            put("self-employed", 15);
            put("technician", 16);put("engineer", 16);
            put("tradesman", 17);put("craftsman", 17);
            put("unemployed", 18);
            put("writer", 19);
        }};

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
        if(!occupationTable.containsKey(ocp))
        {
            System.out.format("Error : The occupation %s does not exist in database\n", ocp);
            System.exit(0);
        }

        //DataLoader will load all date to "HashMap<Integer, Movie> movies" and "HashMap<Integer, User> users" from .dat files
        DataLoader.read();

        // Print average rating
        double averageRating = averageRatingMovieByOCP(genres, ocp, args);
        
        System.out.format(
            "Average rating of movies(genres: %ss) rated by %ss: %f\n",
            args[0], args[1], averageRating
        );
    }

    public static double averageRatingMovieByOCP(String[] genres, String ocp, String[] args)
    {

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
                if(rat.user.occupation == occupationTable.get(ocp))
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
