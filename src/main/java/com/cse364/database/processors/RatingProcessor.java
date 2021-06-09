package com.cse364.database.processors;

import com.cse364.database.schemas.RatingSchema;
import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.database.repositories.DBUserRepository;
import com.cse364.database.dtos.RatingDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingProcessor implements ItemProcessor<RatingDto, RatingSchema> {
    @Autowired
    DBUserRepository users;
    @Autowired
    DBMovieRepository movies;

    @Override
    public RatingSchema process(RatingDto item) {
        return new RatingSchema(
                movies.findById(item.getMovie()).get(),
                users.findById(item.getUser()).get(),
                item.getRating(),
                item.getTimestamp()
        );
    }
}
