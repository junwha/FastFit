package com.cse364.infra;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import com.cse364.domain.*;

public class InMemoryRatingRepositoryTest {
    private InMemoryRatingRepository storage;
    private User user1, user2, user3;
    private Movie movie1, movie2, movie3;
    private List<Rating> ratings;

    @Before
    public void init(){
        storage = new InMemoryRatingRepository();
        user1 = new User(1, User.Gender.M, 20, new Occupation(1, "Teacher"), "10000");
        user2 = new User(2, User.Gender.F, 55, new Occupation(2, "Retired"), "10001");
        user3 = new User(3, User.Gender.M, 30, new Occupation(0, "Others"), "10002");
        movie1 = new Movie(
                1,
                "Toy Story",
                List.of(
                    new Genre("Animation"),
                    new Genre("Children's"),
                    new Genre("Comedy")
                )
        );
        movie2 = new Movie(
                2,
                "Jumanji",
                List.of(
                        new Genre("Adventure"),
                        new Genre("Children's"),
                        new Genre("Fantasy")
                )
        );
        movie3 = new Movie(
                3,
                "Grumpier Old Men",
                List.of(
                        new Genre("Comedy"),
                        new Genre("Romance")
                )
        );

        ratings = List.of(
                new Rating(movie1, user1, 5, 0),
                new Rating(movie1, user2, 4, 0),
                new Rating(movie2, user1, 3, 0),
                new Rating(movie2, user2, 2, 0));
        //Combination of all possible rating by user1 and user2 on movie1 and movie2
        storage.add(ratings.get(0));
        storage.add(ratings.get(1));
        storage.add(ratings.get(2));
        storage.add(ratings.get(3));
    }

    @Test
    public void testConstructor(){
        InMemoryRatingRepository storageByList = new InMemoryRatingRepository(ratings);

        assertEquals(storageByList.filterByUser(user1), storage.filterByUser(user1));
        assertEquals(storageByList.filterByUser(user2), storage.filterByUser(user2));
    }

    @Test
    public void testFilterByMovie(){
        ratings = storage.filterByMovie(movie1);
        for(Rating rating : ratings){
            assertEquals(rating.getMovie(), movie1);
        }
        assertEquals(storage.filterByMovie(movie3), new ArrayList<>());
    }

    @Test
    public void testFilterByUser(){
        ratings = storage.filterByUser(user1);
        for(Rating rating : ratings){
            assertEquals(rating.getUser(), user1);
        }
        assertEquals(storage.filterByUser(user3), new ArrayList<>());
    }
}
