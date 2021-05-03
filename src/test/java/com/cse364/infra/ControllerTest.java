package com.cse364.infra;

import org.junit.Test;

public class ControllerTest {
    @Test
    public void smokeTest() {
        Config config = new Config(
                "./src/test/mock_data/movies.dat",
                "./src/test/mock_data/links.dat",
                "./src/test/mock_data/users.dat",
                "./src/test/mock_data/ratings.dat"
        );
        Controller controller = new Controller(
                config.averageRatingService,
                config.rankingService,
                config.validationService
        );
        controller.main(new String[]{"adventure|children's", "artist"});
        controller.main(new String[]{"F", "25", "grad student"});
        controller.main(new String[]{"", "", ""});
        controller.main(new String[]{"F", "25", "grad student", "animation|comedy"});
        controller.main(new String[]{"F", "25", "grad student", ""});
    }
}
