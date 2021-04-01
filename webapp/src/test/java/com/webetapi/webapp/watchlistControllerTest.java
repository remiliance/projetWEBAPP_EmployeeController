package com.webetapi.webapp;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class watchlistControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testShowWatchlistItemForm() throws Exception {

        mockMvc.perform(get("/watchlist"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("watchlist"))
                .andExpect(model().size(1)) // model objet a un seul attribut
                .andExpect(model().attributeExists("numberOfMovies"));
    }

}
