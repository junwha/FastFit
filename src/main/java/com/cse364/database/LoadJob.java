package com.cse364.database;

import com.cse364.database.dtos.*;
import com.cse364.database.processors.*;
import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.database.repositories.DBRatingRepository;
import com.cse364.database.repositories.DBUserRepository;
import com.cse364.domain.Movie;
import com.cse364.domain.Rating;
import com.cse364.domain.User;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableBatchProcessing
@Configuration
@EnableMongoRepositories(basePackages = "com.cse364.database.repositories")
public class LoadJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job loadDB(DBMovieRepository movies, DBUserRepository users, DBRatingRepository ratings) {
        return jobBuilderFactory.get("loadDB").incrementer(new RunIdIncrementer())
                .start(stepUser())
                .next(stepMovie())
                .next(stepLink())
                .next(stepPoster())
                .next(stepRating())
                .build();
    }

    @Bean
    public Step stepUser() {
        return stepBuilderFactory.get("stepUser").<UserDto, User>chunk(10).reader(userReader())
                .processor(userProcessor()).writer(userWriter()).build();
    }

    @Bean
    public FlatFileItemReader<UserDto> userReader() {
        FlatFileItemReader<UserDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("users.csv"));
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"id", "gender", "age", "occupation", "code"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(UserDto.class);
            }});

        }});
        return reader;
    }

    @Bean
    public MongoItemWriter<User> userWriter() {
        MongoItemWriter<User> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("user");
        return writer;
    }

    @Bean
    public UserProcessor userProcessor() {
        return new UserProcessor();
    }

    @Bean
    public Step stepMovie() {
        return stepBuilderFactory.get("stepMovie").<MovieDto, Movie>chunk(10).reader(movieReader())
                .processor(movieProcessor()).writer(movieWriter()).build();
    }

    @Bean
    public FlatFileItemReader<MovieDto> movieReader() {
        FlatFileItemReader<MovieDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("movies.csv"));
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"id", "title", "genres"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(MovieDto.class);
            }});

        }});
        return reader;
    }

    @Bean
    public MongoItemWriter<Movie> movieWriter() {
        MongoItemWriter<Movie> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("movie");
        return writer;
    }

    @Bean
    public MovieProcessor movieProcessor() {
        return new MovieProcessor();
    }

    @Bean
    public Step stepRating() {
        return stepBuilderFactory.get("stepRating").<RatingDto, Rating>chunk(10).reader(ratingReader())
                .processor(ratingProcessor()).writer(ratingWriter()).build();
    }

    @Bean
    public FlatFileItemReader<RatingDto> ratingReader() {
        FlatFileItemReader<RatingDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("ratings.csv"));
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"user", "movie", "rating", "timestamp"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(RatingDto.class);
            }});

        }});
        return reader;
    }


    @Bean
    public MongoItemWriter<Rating> ratingWriter() {
        MongoItemWriter<Rating> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("rating");
        return writer;
    }

    @Bean
    public RatingProcessor ratingProcessor() {
        return new RatingProcessor();
    }


    @Bean
    public Step stepLink() {
        return stepBuilderFactory.get("stepLink").<LinkDto, Movie>chunk(10).reader(linkReader())
                .processor(linkProcessor()).writer(movieWriter()).build();
    }

    @Bean
    public FlatFileItemReader<LinkDto> linkReader() {
        FlatFileItemReader<LinkDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("links.csv"));
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"movie", "link"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(LinkDto.class);
            }});

        }});
        return reader;
    }


    @Bean
    public LinkProcessor linkProcessor() {
        return new LinkProcessor();
    }


    @Bean
    public Step stepPoster() {
        return stepBuilderFactory.get("stepPoster").<PosterDto, Movie>chunk(10).reader(posterReader())
                .processor(posterProcessor()).writer(movieWriter()).build();
    }

    @Bean
    public FlatFileItemReader<PosterDto> posterReader() {
        FlatFileItemReader<PosterDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("movie_poster.csv"));
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"movie", "poster"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(PosterDto.class);
            }});

        }});
        return reader;
    }

    @Bean
    public PosterProcessor posterProcessor() {
        return new PosterProcessor();
    }

}