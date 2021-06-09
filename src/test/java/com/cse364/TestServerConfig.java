package com.cse364;

import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.database.repositories.DBRatingRepository;
import com.cse364.database.repositories.DBUserRepository;
import com.cse364.infra.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestServerConfig {
    Config config;

    @Bean
    public Config configBean(){
        return new Config("./data/movies.dat",
                "./data/links.dat",
                "./data/users.dat",
                "./data/ratings.dat");
    }
}
