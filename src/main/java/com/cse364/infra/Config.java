package com.cse364.infra;

import com.cse364.domain.*;
import com.cse364.app.*;
import com.cse364.infra.*;
import com.cse364.infra.dtos.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Config {
    public GenreRepository genres = new InMemoryGenreRepository(List.of(
            "Action", "Adventure", "Animation", "Children's", "Comedy", "Crime", "Documentary",
            "Drama", "Fantasy", "Film-Noir", "Horror", "Musical", "Mystery", "Romance", "Sci-Fi",
            "Thriller", "War", "Western"
    ));
    public OccupationRepository occupations = new InMemoryOccupationRepository(){{
        add(new Occupation(0, "Other"));
        add(new Occupation(1, "Academic/Educator"), List.of("academic", "educator"));
        add(new Occupation(2, "Artist"));
        add(new Occupation(3, "Clerical/Admin"), List.of("clerical", "admin"));
        add(new Occupation(4, "College/Grad student"), List.of("college student", "grad student"));
        add(new Occupation(5, "Customer service"));
        add(new Occupation(6, "Doctor/Health care"), List.of("doctor", "health care"));
        add(new Occupation(7, "Executive/Managerial"), List.of("executive", "managerial"));
        add(new Occupation(8, "Farmer"));
        add(new Occupation(9, "Homemaker"));
        add(new Occupation(10, "K-12 student"));
        add(new Occupation(11, "Lawyer"));
        add(new Occupation(12, "Programmer"));
        add(new Occupation(13, "Retired"));
        add(new Occupation(14, "Sales/Marketing"), List.of("sales", "marketing"));
        add(new Occupation(15, "Scientist"));
        add(new Occupation(16, "Self-employed"));
        add(new Occupation(17, "Technician/Engineer"), List.of("technician", "engineer"));
        add(new Occupation(18, "Tradesman/Craftsman"), List.of("tradesman", "craftsman"));
        add(new Occupation(19, "Unemployed"));
        add(new Occupation(20, "Writer"));
    }};

    public MovieRepository movies;
    public UserRepository users;
    public RatingRepository ratings;

    public AverageRatingService averageRatingService;
    public RankingService rankingService;
    public ValidationService validationService;
    public RecommendByMovieService recommendByMovieService;

    public Config(String moviesDb, String linksDb, String usersDb, String ratingsDb) {
        loadRepositories(moviesDb, linksDb, usersDb, ratingsDb);
        this.averageRatingService = new AverageRatingService(movies, ratings);
        this.rankingService = new RankingService(movies, ratings, new UserService(users));
        this.validationService = new ValidationService(genres, occupations);
        this.recommendByMovieService = new RecommendByMovieService(movies, ratings);
    }

    public void loadRepositories(String moviesDb, String linksDb, String usersDb, String ratingsDb) {
        // Open data files
        FileReader moviesFile = null;
        FileReader linksFile = null;
        FileReader usersFile = null;
        FileReader ratingsFile = null;
        try {
            moviesFile = new FileReader(moviesDb);
            linksFile = new FileReader(linksDb);
            usersFile = new FileReader(usersDb);
            ratingsFile = new FileReader(ratingsDb);
        } catch(FileNotFoundException e) {
            System.out.println("ERROR: Some data file is missing. Please try to clone the repo again.");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // Load data from files
        List<MovieDto> loadedMovies = DataLoader.loadMovies(moviesFile, linksFile, "http://www.imdb.com/title/tt");
        List<UserDto> loadedUsers = DataLoader.loadUsers(usersFile);
        List<RatingDto> loadedRatings = DataLoader.loadRatings(ratingsFile);

        // Fill repositories
        InMemoryMovieRepository movies = new InMemoryMovieRepository();
        InMemoryUserRepository users = new InMemoryUserRepository();
        InMemoryRatingRepository ratings = new InMemoryRatingRepository();

        for (MovieDto movie: loadedMovies) {
            movies.add(new Movie(
                    movie.id,
                    movie.title,
                    movie.genres.stream().map(name -> new Genre(name)).collect(Collectors.toList()),
                    movie.link
            ));
        }
        for (UserDto user: loadedUsers) {
            Occupation occupation = occupations.get(user.occupation);
            users.add(new User(
                    user.id,
                    user.gender.equals("M") ? Gender.M : Gender.F,
                    user.age,
                    occupation,
                    user.zipCode
            ));
        }
        for (RatingDto rating: loadedRatings) {
            ratings.add(new Rating(
                    movies.get(rating.movie),
                    users.get(rating.user),
                    rating.rating,
                    rating.timestamp
            ));
        }

        this.movies = movies;
        this.users = users;
        this.ratings = ratings;
    }
}
