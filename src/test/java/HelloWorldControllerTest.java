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

    // --- Tests for home() endpoint ---

    @Test
    void home_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void home_returnsCorrectMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string("Hello World! Welcome to Spring Boot 2.7 with Java 17"));
    }

    @Test
    void home_returnsPlainTextContent() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void home_containsSpringBootVersion() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Spring Boot 2.7")));
    }

    @Test
    void home_containsJavaVersion() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Java 17")));
    }

    // --- Tests for hello() endpoint ---

    @Test
    void hello_withoutParameter_returnsDefaultName() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void hello_withNameParameter_returnsPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello?name=Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    void hello_withDifferentName_returnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello?name=Bob"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Bob!"));
    }

    @Test
    void hello_withSpecialCharactersInName() throws Exception {
        mockMvc.perform(get("/hello?name=John%20Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John Doe!"));
    }

    @Test
    void hello_withEmptyStringParameter_returnsEmptyName() throws Exception {
        mockMvc.perform(get("/hello?name="))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, !"));
    }

    @Test
    void hello_withMultipleParameters_usesFirstName() throws Exception {
        mockMvc.perform(get("/hello?name=Alice&name=Bob"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    // --- Tests for info() endpoint ---

    @Test
    void info_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }

    @Test
    void info_returnsJsonContent() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void info_containsApplicationName() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name").value("Hello World Application"));
    }

    @Test
    void info_containsApplicationVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }

    @Test
    void info_containsSpringBootVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.7.17"));
    }

    @Test
    void info_containsJavaVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.javaVersion").value("Java 17"));
    }

    @Test
    void info_returnsAllRequiredFields() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.springBootVersion").exists())
                .andExpect(jsonPath("$.javaVersion").exists());
    }

    @Test
    void info_returnsValidJsonStructure() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$").isMap());
    }

    // --- Tests for endpoint availability ---

    @Test
    void rootEndpoint_isAccessible() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void helloEndpoint_isAccessible() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    @Test
    void infoEndpoint_isAccessible() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }

    @Test
    void invalidEndpoint_returns404() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }
}
