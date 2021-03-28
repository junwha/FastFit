package com.preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/*
RATINGS FILE
UserID::MovieID::Rating::Timestamp

USER FILE
UserID::Gender::Age::Occupation::Zip-code

MOVIES FILE
MovieID::Title::Genres
 */

public class DataLoader {
    public static void read(){
        try{
            //File stream
            File file;
            //File readers
            FileReader fileReader;// = new FileReader(ratingFile);
            BufferedReader buffReader; // = new BufferedReader(fileReader);

            String[] dirs = {"./data/movies.dat", "./data/users.dat", "./data/ratings.dat"};
            ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>(3);

            //Data Reading
            for(int i=0; i<3; i++){
                file = new File(dirs[i]);
                fileReader = new FileReader(file);
                buffReader = new BufferedReader(fileReader);

                String buffer = "";
                while((buffer = buffReader.readLine()) != null){
                    contents.get(i).add(buffer);
                }

                fileReader.close();
                buffReader.close();
            }

            //preprocess movies

            //preprocess users

            //preprocess ratings


        }catch (FileNotFoundException e){
            System.out.println("[!File NOT FOUND] Please clone git again");
        }catch (IOException e){
            System.out.println("[!File NOT CRASHED] Please clone git again");
        }
    }
}
