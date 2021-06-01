package com.cse364.database.preprocess;

import com.cse364.database.dtos.MovieDto;
import com.cse364.database.dtos.UserDto;
import com.cse364.domain.Movie;
import com.cse364.domain.User;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableBatchProcessing
@Configuration
@EnableMongoRepositories
public class LoadJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readCSVFile() {
        return jobBuilderFactory.get("load").incrementer(new RunIdIncrementer()).start(stepUser()).next(stepMovie())
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
    public UserProcessor userProcessor(){
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
    public MovieProcessor movieProcessor(){
        return new MovieProcessor();
    }

}