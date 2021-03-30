package com.github.cse364;
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
}
