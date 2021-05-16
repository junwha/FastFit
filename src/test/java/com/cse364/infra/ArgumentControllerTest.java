//package com.cse364.infra;
//
//import org.junit.Test;
//
//public class ArgumentControllerTest {
//    @Test
//    public void smokeTest() {
//        Config config = new Config(
//                "./src/test/mock_data/movies.dat",
//                "./src/test/mock_data/links.dat",
//                "./src/test/mock_data/users.dat",
//                "./src/test/mock_data/ratings.dat"
//        );
//        ArgumentController argumentController = new ArgumentController(
//                config.averageRatingService,
//                config.rankingService,
//                config.validationService
//        );
//        argumentController.main(new String[]{"adventure|children's", "artist"});
//        argumentController.main(new String[]{"F", "25", "grad student"});
//        argumentController.main(new String[]{"", "", ""});
//        argumentController.main(new String[]{"F", "25", "grad student", "animation|comedy"});
//        argumentController.main(new String[]{"F", "25", "grad student", ""});
//    }
//}
