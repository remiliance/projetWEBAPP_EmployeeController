package com.webetapi.webapp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test l'affichage de la page d'ajout d'un employee")
    public void testViewPageCreateEmployee() throws Exception {

        mockMvc.perform(get("/createEmployee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("formNewEmployee"))
               .andExpect(content().string(containsString("password")));

    }

    @Test
    @DisplayName("Test l'ajout d'un employee")
    public void testSubmitNewEmployee() throws Exception {
        mockMvc.perform(get("/createEmployee")
                .param("Prénom", "Top Gun")
                .param("Email", "5.5")
                .param("Mot de passe", "L"))
                .andExpect(view().name("formNewEmployee"))
                .andExpect(model().attributeExists("employee"));
               // .andExpect(model().attribute("Prénom","Top Gun"));


    }
}