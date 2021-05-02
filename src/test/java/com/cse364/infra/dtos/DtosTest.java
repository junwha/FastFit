package com.cse364.infra.dtos;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DtosTest {
    private MovieDto oriMovieDto;
    private RatingDto oriRatingDto;
    private UserDto oriUserDto;
    
    private List<MovieDto> movieDtos;
    private List<RatingDto> ratingDtos;
    private List<UserDto> userDtos;

    @Before
    public void init() {
        oriMovieDto = new MovieDto(7, "Sabrina (1995)", List.of("Comedy", "Romance"), "movie/link/0114319");
        movieDtos = List.of(
                new MovieDto(5, "Sabrina (1995)", List.of("Comedy", "Romance"), "movie/link/0114319"),
                new MovieDto(7, "Sabertooth (1995)", List.of("Comedy", "Romance"), "movie/link/0114319"),
                new MovieDto(7, "Sabrina (1995)", List.of("Comedian", "Romance"), "movie/link/0114319"),
                new MovieDto(7, "Sabrina (1995)", List.of("Comedy", "Romance"), "movie/zelda/0114319")
        );
        
        oriRatingDto = new RatingDto(1, 608, 4, 978301398);
        ratingDtos = List.of(
                new RatingDto(2, 608, 4, 978301398),
                new RatingDto(1, 404, 4, 978301398),
                new RatingDto(1, 608, 3, 978301398),
                new RatingDto(1, 608, 4, 123456789)
        );
        
        oriUserDto = new UserDto(4, "M", 45, 7, "02460");
        userDtos = List.of(
                new UserDto(5, "M", 45, 7, "02460"),
                new UserDto(4, "F", 45, 7, "02460"),
                new UserDto(4, "M", 47, 7, "02460"),
                new UserDto(4, "M", 45, 9, "02460"),
                new UserDto(4, "M", 45, 7, "24680")
        );
    }
    
    @Test
    public void testMovieDto() {
        assertEquals(oriMovieDto, new MovieDto(7, "Sabrina (1995)", List.of("Comedy", "Romance"), "movie/link/0114319"));
        assertNotEquals(oriMovieDto, null);
        for (MovieDto mov : movieDtos) {
            assertNotEquals(oriMovieDto, mov);
        }
    }
    
    @Test
    public void testRatingDto() {
        assertEquals(oriRatingDto, new RatingDto(1, 608, 4, 978301398));
        assertNotEquals(oriRatingDto, null);
        for (RatingDto rat : ratingDtos) {
            assertNotEquals(oriRatingDto, rat);
        }
    }
    
    @Test
    public void testUserDto() {
        assertEquals(oriUserDto, new UserDto(4, "M", 45, 7, "02460"));
        assertNotEquals(oriUserDto, null);
        for (UserDto use : userDtos) {
            assertNotEquals(oriUserDto, use);
        }
    }
}
