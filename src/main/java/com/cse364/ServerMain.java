package com.cse364;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServerMain {
    public static void main(String[] args){
        SpringApplication.run(ServerMain.class, args);
    }
}
