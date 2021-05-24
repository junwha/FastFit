package com.cse364.app;

import com.cse364.domain.*;
import com.cse364.infra.InMemoryMovieRepository;
import com.cse364.infra.InMemoryRatingRepository;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Collections;
import static org.junit.Assert.*;

public class RecommendMoviesFromMovieServiceTest {
    private RecommendMoviesFromMovieService service;


    @Before
    public void init() {
        InMemoryRatingRepository ratingStorage = new InMemoryRatingRepository();
        InMemoryMovieRepository movieStorage = new InMemoryMovieRepository();

        

        service = new RecommendMoviesFromMovieService(movieStorage, ratingStorage);
    }
}
