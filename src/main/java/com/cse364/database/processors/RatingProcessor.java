package com.cse364.database.processors;

import com.cse364.database.schemas.RatingSchema;
import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.database.repositories.DBUserRepository;
import com.cse364.database.dtos.RatingDto;
import com.cse364.domain.Movie;
import com.cse364.domain.Rating;
import com.cse364.domain.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RatingProcessor implements ItemProcessor<RatingDto, RatingSchema> {
    Map<Integer, Movie> movieMap;
    Map<Integer, User> userMap;

    public RatingProcessor(Map<Integer, Movie> movieMap, Map<Integer, User> userMap){
        this.movieMap = movieMap;
        this.userMap = userMap;
    }

    @Override
    public RatingSchema process(RatingDto item) {
        return new RatingSchema(
                movieMap.get(item.getMovie()),
                userMap.get(item.getUser()),
                item.getRating(),
                item.getTimestamp()
        );
    }
}
