package com.cse364;

import com.cse364.domain.ValidRepository;
import com.cse364.infra.Config;
import com.cse364.infra.DBValidRepositoryForTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestServerConfig {
    @Bean
    public ValidRepository validRepository(){
        return new DBValidRepositoryForTest();
    }

    @Bean
    public Config configBean(){
        return new Config("./data/movies.dat",
                "./data/links.dat",
                "./data/users.dat",
                "./data/ratings.dat");
    }
}
