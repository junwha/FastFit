package com.cse364.api;

import com.cse364.cli.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ServerConfig {
    Config config;

    @PostConstruct
    public void init(){
        this.config = new Config(
                "./data/movies.dat",
                "./data/links.dat",
                "./data/users.dat",
                "./data/ratings.dat"
        );
    }

    @Bean
    public Config configBean(){
        return this.config;
    }
}
