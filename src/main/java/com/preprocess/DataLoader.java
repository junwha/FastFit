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
    private final static String[] occupationTable = {"other", "academic/educator", "artist",
            "clerical/admin", "college/grad student", "customer service", "doctor/health care",
            "executive/managerial", "farmer", "homemaker", "K-12 student" , "lawyer", "programmer",
            "retired", "sales/marketing", "scientist", "self-employed", "technician/engineer",
            "tradesman/craftsman", "unemployed", "writer"};

    public static void read(){
        try{
            //File stream
            File file;
            //File readers
            FileReader fileReader;// = new FileReader(ratingFile);
            BufferedReader buffReader; // = new BufferedReader(fileReader);

            String[] dirs = {"./data/movies.dat", "./data/users.dat", "./data/ratings.dat"};
            ArrayList<ArrayList<String[]>> contents = new ArrayList<ArrayList<String[]>>(0);

            //Data Reading
            for(int i=0; i<3; i++){
                file = new File(dirs[i]);
                fileReader = new FileReader(file);
                buffReader = new BufferedReader(fileReader);
                contents.add(new ArrayList<String[]>(0));

                String buffer = "";
                while((buffer = buffReader.readLine()) != null){
                    contents.get(i).add(buffer.split("::"));
                }

                fileReader.close();
                buffReader.close();
            }



            //preprocess movies
            for(String[] args : contents.get(0)){
                movies.add(new Movie(Integer.parseInt(args[0]), args[1], args[2].split("|")));
//                Movie test = movies.get(movies.size()-1);
//                System.out.println(test.title);
            }

            //preprocess users
            for(String[] args : contents.get(1)){
                Gender g = null;
                if(args[1].equals('M')) {
                    g = Gender.M;
                }else if(args[1].equals(('F'))){
                    g = Gender.F;
                }
                users.add(new User(Integer.parseInt(args[0]), g,
                        Integer.parseInt(args[2]), occupationTable[Integer.parseInt(args[3])], Integer.parseInt(args[4])));
//                User test = users.get(users.size()-1);
//                System.out.println(test.id);
            }
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
