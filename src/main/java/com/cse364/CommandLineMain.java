package com.cse364;

import com.cse364.infra.*;

public class CommandLineMain {
    public static void main(String args[]) {
        Config config = new Config(
                "./data/movies.dat",
                "./data/links.dat",
                "./data/users.dat",
                "./data/ratings.dat"
        );
        Controller controller = new Controller(
                config.averageRatingService,
                config.rankingService,
                config.validationService
        );
        controller.main(args);
    }
}
