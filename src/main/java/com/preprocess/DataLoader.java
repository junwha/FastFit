package com.preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class DataLoader {
    public static void main(String args[]){
        try{
            //File streams
            File ratingFile = new File("./data/ratings.dat");
            File userFile = new File("./data/users.dat");
            File movieFile = new File("./data/movies.dat");

            //File readers
            FileReader fileReader = new FileReader(ratingFile);
            BufferedReader buffReader = new BufferedReader(fileReader);

            String buffer = "";

            //print ratings.dat
            while((buffer = buffReader.readLine()) != null){
                System.out.println(buffer);
            }

            //print users.dat
            fileReader = new FileReader(userFile);
            buffReader = new BufferedReader(fileReader);
            while((buffer = buffReader.readLine()) != null){
                System.out.println(buffer);
            }

            //print movies.dat
            fileReader = new FileReader(movieFile);
            buffReader = new BufferedReader(fileReader);
            while((buffer = buffReader.readLine()) != null){
                System.out.println(buffer);
            }

            //close readers
            fileReader.close();
            buffReader.close();

        }catch (FileNotFoundException e){
            System.out.println("[!File NOT FOUND] Please clone git again");
        }catch (IOException e){
            System.out.println("[!File NOT CRASHED] Please clone git again");
        }
    }
}
