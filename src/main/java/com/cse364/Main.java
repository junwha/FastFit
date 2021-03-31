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
        if(args.length != 2)
        {
            System.out.println("Input Error : Input format is '[genre1\\|genre2\\| ... ] [occupation]'");
            System.exit(0);
        }

        String[] genres = args[0].toLowerCase().split("\\|");
        String ocp = args[1].toLowerCase(Locale.ROOT);

        if(!occupationTable.containsKey(ocp))
        {
            System.out.format("Error : The occupation %s does not exist in database\n", ocp);
            System.exit(0);
        }

//        for(int i=0; i<genres.length; i++)
//        {
//            System.out.println(genres[i]);
//        }
//

        DataLoader.read();

        averageRatingMovieByOCP(genres, ocp);
    }

    public static double averageRatingMovieByOCP(String[] genres, String ocp)
    {

        for(Map.Entry<Integer, Movie> movEntry : DataLoader.movies.entrySet())
        {
            Movie mov = movEntry.getValue();
            //System.out.println(mov.title);

            int genreCount = 0;
            for(String genre : genres)
            {
                if(mov.hasGenre(genre.toLowerCase(Locale.ROOT)))
                {
                    genreCount++;
                }
            }

            if(genreCount < genres.length)
            {
                continue;
            }

            int ratingCount = 0;
            int rating = 0;
            for(Rating rat : mov.ratings)
            {
                if(rat.user.occupation == occupationTable.get(ocp))
                {
                    ratingCount++;
                    rating = rating + rat.rating;
                }
            }
            
            double ratingAverage;
            if(ratingCount == 0)
            {
                ratingAverage = -1.0;
            }
            else
            {
                ratingAverage = Double.valueOf(rating) / Double.valueOf(ratingCount);
            }

            System.out.format("%s rated by %ss : %f average\n", mov.title, ocp, ratingAverage);
        }

        return 0;
    }
}
