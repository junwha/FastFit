package com.preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/*
MOVIES FILE
MovieID::Title::Genres

USER FILE
UserID::Gender::Age::Occupation::Zip-code

RATINGS FILE
UserID::MovieID::Rating::Timestamp
 */

public class DataLoader {
    public static ArrayList<Movie> movies = new ArrayList<Movie>(0);
    public static ArrayList<User> users = new ArrayList<User>(0);
    public static ArrayList<Rating> ratings = new ArrayList<Rating>(0);

    public static void read(){
        try{
            //File stream
            File file;
            //File readers
            FileReader fileReader;// = new FileReader(ratingFile);
            BufferedReader buffReader; // = new BufferedReader(fileReader);

            String[] dirs = {"./data/movies.dat", "./data/users.dat", "./data/ratings.dat"};
            ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>(0);

            //Data Reading
            for(int i=0; i<3; i++){
                file = new File(dirs[i]);
                fileReader = new FileReader(file);
                buffReader = new BufferedReader(fileReader);
                contents.add(new ArrayList<String>(0));

                String buffer = "";
                while((buffer = buffReader.readLine()) != null){
                    System.out.println(buffer);

                    contents.get(i).add(buffer);
                }

                fileReader.close();
                buffReader.close();
            }



            //preprocess movies
//            for(String movieData : contents.get(0)){
//                System.out.println(movieData);
//            }
////
//            //preprocess users
//            for(String userData : contents.get(0)){
//
//            }
//
//            //preprocess ratings
//            for(String ratingData : contents.get(0)){
//
//            }

        }catch (FileNotFoundException e){
            System.out.println("[!File NOT FOUND] Please clone git again");
        }catch (IOException e){
            System.out.println("[!File NOT CRASHED] Please clone git again");
        }catch(Exception e){

        }
    }
}
