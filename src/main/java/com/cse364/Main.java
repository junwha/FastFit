package com.cse364;
import java.util.Map;
import java.util.Iterator;
import com.cse364.DataLoader;

// Input format
// java (filename) genres occupation

// multiple category input "|"(bar)

public class Main {
    public static void main(String[] args){

        if(args.length != 2)
        {
            System.out.println("Input Error : Input format is '[genre1\\|genre2\\| ... ] [occupation]'");
            System.exit(0);
        }

        String[] genres = args[0].split("\\|");
        String occu = args[1];
        
        if(!occupationCheck(occu))
        {
            System.out.format("Error : The occupation %s does not exist in database\n", occu);
            System.exit(0);
        }

        for(int i=0; i<genres.length; i++)
        {
              System.out.println(genres[i]);
        }
        System.out.println(occu);

/*-----------------------------------------------------------------------*/

        DataLoader.read();

        averageRatingGenreOccupation(genres, occu);
    }

    public static boolean occupationCheck(String occupation)
    {
        for(int i=0; i<DataLoader.occupationTable.length; i++)
        {
            if(DataLoader.occupationTable[i].equals(occupation))
            {
                return true;
            }
        }
        return false;
    }
    
    public static double averageRatingGenreOccupation(String[] genres, String occu)
    {
        for(Map.Entry<Integer, Movie> movieEntry : DataLoader.movies.entrySet())
        {
            Movie mov = movieEntry.getValue();

            //System.out.println(mov.title);

            int genreCount = 0;
            for(String genre : genres)
            {
                if(mov.hasGenre(genre))
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
                if(rat.user.occupation.equals(occu))
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

            System.out.format("%s rated by %ss : %f average\n", mov.title, occu, ratingAverage);
        }

        return 0;
    }
}
