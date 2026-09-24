package com.example.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World! Welcome to Spring Boot 2.7 with Java 17"));
    }

    @Test
    void testHelloEndpointWithDefaultName() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void testHelloEndpointWithCustomName() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Pirate"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Pirate!"));
    }

    @Test
    void testHelloEndpointWithSpecialCharacters() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Captain Jack"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Captain Jack!"));
    }

    @Test
    void testInfoEndpointReturnsJson() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Hello World Application"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.7.17"))
                .andExpect(jsonPath("$.javaVersion").value("Java 17"));
    }

    @Test
    void testInfoEndpointContainsCorrectSpringBootVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.springBootVersion").value(containsString("2.7.17")));
    }

    @Test
    void testInfoEndpointContainsCorrectJavaVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.javaVersion").value(containsString("17")));
    }

    @Test
    void testHelloEndpointWithEmptyName() throws Exception {
        mockMvc.perform(get("/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, !"));
    }

    @Test
    void testHelloEndpointWithLongName() throws Exception {
        String longName = "A".repeat(100);
        mockMvc.perform(get("/hello").param("name", longName))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello,")))
                .andExpect(content().string(containsString(longName)));
    }

    @Test
    void testMultipleEndpointsSequentially() throws Exception {
        // Test home endpoint
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        // Test hello endpoint
        mockMvc.perform(get("/hello").param("name", "Test"))
                .andExpect(status().isOk());

        // Test info endpoint
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }
}
