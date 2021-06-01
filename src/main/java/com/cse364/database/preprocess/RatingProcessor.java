package com.cse364.database.preprocess;

import com.cse364.database.DBMovieRepository;
import com.cse364.database.DBUserRepository;
import com.cse364.database.dtos.RatingDto;
import com.cse364.domain.Rating;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingProcessor implements ItemProcessor<RatingDto, Rating> {
    @Autowired
    DBUserRepository users;
    @Autowired
    DBMovieRepository movies;

    @Override
    public Rating process(RatingDto item) {
        return new Rating(movies.findById(item.getMovie()).get(), users.findById(item.getUser()).get(), item.getRating(), item.getTimestamp());
    }
}
