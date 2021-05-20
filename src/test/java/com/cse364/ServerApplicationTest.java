package com.cse364;

import com.cse364.infra.Config;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.hasSize;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ServerApplicationTest {
    @Autowired
    Config config;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void smokeTest(){
        assertThat(config).isNotNull();

        assertThat(config.genres).isNotNull();
        assertThat(config.movies).isNotNull();
        assertThat(config.occupations).isNotNull();
        assertThat(config.ratings).isNotNull();
        assertThat(config.users).isNotNull();

        assertThat(config.averageRatingService).isNotNull();
        assertThat(config.rankingService).isNotNull();
        assertThat(config.validationService).isNotNull();

    }

    @Test
    public void controllerTest() throws Exception {
        int expectedLength = 10;
        testRecommendationResult("", "", "", "", expectedLength);
        testRecommendationResult("F", "10", "", "", expectedLength);
        testRecommendationResult("F", "20", "Doctor", "", expectedLength);

        testRecommendationResult("", "", "", "Horror", expectedLength);
        testRecommendationResult("F", "10", "", "Horror|Comedy", expectedLength);
        testRecommendationResult("F", "20", "Doctor", "Horror|Comedy|Children's", expectedLength);

        // TODO: test with invalid data after handle errors

    }

    private void testRecommendationResult(String gender, String age, String occupation, String genreNames, int expectedLength) throws Exception{
        this.mockMvc.perform(get("/users/recommendations")
                .param("gender", gender)
                .param("age", age)
                .param("occupation", occupation)
                .param("genreNames",  genreNames)
        )
                .andExpect(jsonPath("$", hasSize(expectedLength)))
                .andDo(print()).andExpect(status().isOk());
    }
}
