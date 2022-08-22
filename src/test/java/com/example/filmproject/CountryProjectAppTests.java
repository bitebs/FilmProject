package com.example.filmproject;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CountryProjectAppTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getCountry() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/country")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Order(2)
    public void getCountryById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/country/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country_name",
                        Matchers.equalTo("USA")));

    }

    @Test
    @Order(3)
    public void postCountry() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post
                                        ("/country")
                                .content(asJsonString(new Country(6L, "country1")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void putCountry() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.put
                                        ("/country/6")
                                .content(asJsonString(new Country(6L, "country4")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.price", Matchers.equalTo(1.)));

    }

    @Test
    @Order(5)
    public void deleteCountry() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/country/6"))
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
