package com.example.filmproject;import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FilmProjectApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getFilm() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/film")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Order(2)
    public void getFilmById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/film/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        Matchers.equalTo("The Lord of the rings")));

    }

    @Test
    @Order(3)
    public void postFilm() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post
                                        ("/film")
                                .content(asJsonString(new Film(1L, "film1", "comedy", 1L)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void putFilm() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.put
                                        ("/film/1")
                                .content(asJsonString(new Film(1L, "film4", "action", 1L)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.price", Matchers.equalTo(1.)));

    }

    @Test
    @Order(5)
    public void deleteFilm() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/film/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


