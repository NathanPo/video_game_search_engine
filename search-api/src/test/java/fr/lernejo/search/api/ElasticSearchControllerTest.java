package fr.lernejo.search.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ElasticSearchControllerTest {

    @Test
    void api_games_is_ok(@Autowired MockMvc mockMvc) throws Exception {
         mockMvc
             .perform(MockMvcRequestBuilders
                 .get("/api/games"))
             .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void api_games_research_return_good_values(@Autowired MockMvc mockMvc) throws Exception {
         mockMvc
             .perform(MockMvcRequestBuilders
                 .get("/api/games?query=developer:\"Epic Games\""))
             .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
