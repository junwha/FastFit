package com.cse364;

import com.cse364.infra.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String args[]) {
//        Config config = new Config(
//                "./data/movies.dat",
//                "./data/links.dat",
//                "./data/users.dat",
//                "./data/ratings.dat"
//        );
//        ArgumentController argumentController = new ArgumentController(
//                config.averageRatingService,
//                config.rankingService,
//                config.validationService
//        );
//        argumentController.main(args);
        
        SpringApplication.run(Main.class, args);

    }
}
