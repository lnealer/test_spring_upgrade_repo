package com.example.helloworld;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(HelloWorldController.class)
@DisplayName("HelloWorldController Tests")
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // --- Home Endpoint Tests ---

    @Test
    @DisplayName("GET / should return welcome message")
    void testHome_ReturnsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World! Welcome to Spring Boot 3.0 with Java 17"));
    }

    @Test
    @DisplayName("GET / should return 200 OK status")
    void testHome_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET / should have correct content type")
    void testHome_HasCorrectContentType() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    // --- Hello Endpoint Tests (with default parameter) ---

    @Test
    @DisplayName("GET /hello without parameter should use default 'World'")
    void testHello_WithoutParameter_UsesDefaultValue() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello should return 200 OK status")
    void testHello_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    // --- Hello Endpoint Tests (with custom parameter) ---

    @Test
    @DisplayName("GET /hello?name=Alice should return personalized greeting")
    void testHello_WithCustomName_ReturnsPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    @DisplayName("GET /hello?name=Bob should return personalized greeting")
    void testHello_WithDifferentName_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Bob"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Bob!"));
    }

    @Test
    @DisplayName("GET /hello with special characters in name")
    void testHello_WithSpecialCharacters_ReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John-Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John-Doe!"));
    }

    @Test
    @DisplayName("GET /hello with numeric name")
    void testHello_WithNumericName_ReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 123!"));
    }

    @Test
    @DisplayName("GET /hello with empty string parameter should use default")
    void testHello_WithEmptyString_UsesDefault() throws Exception {
        mockMvc.perform(get("/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, !"));
    }

    @Test
    @DisplayName("GET /hello with long name")
    void testHello_WithLongName_ReturnsGreeting() throws Exception {
        String longName = "VeryLongNameWithManyCharactersForTesting";
        mockMvc.perform(get("/hello").param("name", longName))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + longName + "!"));
    }

    // --- Info Endpoint Tests ---

    @Test
    @DisplayName("GET /info should return JSON with app information")
    void testInfo_ReturnsAppInfo() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Hello World Application"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 3.0.0.RELEASE"))
                .andExpect(jsonPath("$.javaVersion").value("Java 17"));
    }

    @Test
    @DisplayName("GET /info should return 200 OK status")
    void testInfo_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /info should have correct content type")
    void testInfo_HasCorrectContentType() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("GET /info should contain all required fields")
    void testInfo_ContainsAllRequiredFields() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.springBootVersion").exists())
                .andExpect(jsonPath("$.javaVersion").exists());
    }

    @Test
    @DisplayName("GET /info name field should not be empty")
    void testInfo_NameFieldNotEmpty() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name", not(emptyString())));
    }

    @Test
    @DisplayName("GET /info version field should not be empty")
    void testInfo_VersionFieldNotEmpty() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.version", not(emptyString())));
    }

    @Test
    @DisplayName("GET /info should confirm Spring Boot 3.0.0")
    void testInfo_ConfirmsSpringBootVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.springBootVersion").value(containsString("3.0.0")));
    }

    @Test
    @DisplayName("GET /info should confirm Java 17")
    void testInfo_ConfirmsJavaVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.javaVersion").value("Java 17"));
    }

    // --- Non-existent Endpoint Tests ---

    @Test
    @DisplayName("GET /nonexistent should return 404 Not Found")
    void testNonexistentEndpoint_Returns404() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /hello/extra should return 404 Not Found")
    void testHelloWithExtraPath_Returns404() throws Exception {
        mockMvc.perform(get("/hello/extra"))
                .andExpect(status().isNotFound());
    }
}
