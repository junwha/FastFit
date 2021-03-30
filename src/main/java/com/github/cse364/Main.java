package com.github.cse364;
import java.util.Map;
import java.util.Iterator;
import com.github.cse364.DataLoader;

// Input format
// java (filename) genres occupation

// multiple category input temporarily changed to ","(comma)

public class Main {
    public static void main(String[] args){

        if(args.length != 2)
        {
            System.out.println("Error : Input format is 'genre,genre occupation'");
            System.exit(0);
        }

        String[] genres = args[0].split(",");
        String occu = args[1];
        
        if(occupationCheck(occu)!=0)
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

        genre_occupation_average_rating(genres, occu);
    }

    public static int occupationCheck(String occupation)
    {
        for(int i=0; i<DataLoader.occupationTable.length; i++)
        {
            if(DataLoader.occupationTable[i].equals(occupation))
            {
                return 0;
            }
        }
        return 1;
    }
    
    public static double genre_occupation_average_rating(String[] genres, String occu)
    {
        for(Map.Entry<Integer, Movie> Mentry : DataLoader.movies.entrySet())
        {
            Movie mov = Mentry.getValue();

            //System.out.println(mov.title);

            int genrecount = 0;
            for(String genre : genres)
            {
                if(mov.hasGenre(genre))
                {
                    genrecount++;
                }
            }

            if(genrecount < genres.length)
            {
                continue;
            }

            for(Rating rat : mov.ratings)
            {
                continue;
            }
        }

        return 0;
    }
}
