package com.cse364.api;

import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.database.repositories.DBRatingRepository;
import com.cse364.database.repositories.DBUserRepository;
import com.cse364.database.repositories.DBValidRepository;
import com.cse364.domain.ValidRepository;
import com.cse364.infra.Config;
import com.cse364.infra.DBValidRepositoryAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;

@Profile("!test")
@Configuration
public class ServerConfig {
    @Bean
    public ValidRepository validRepository(DBValidRepository dbValidRepository){
        return new DBValidRepositoryAdaptor(dbValidRepository);
    }

    @Bean
    public Config configBean(DBMovieRepository movies, DBUserRepository users, DBRatingRepository ratings){
        return new Config(movies, users, ratings);
    }

}
