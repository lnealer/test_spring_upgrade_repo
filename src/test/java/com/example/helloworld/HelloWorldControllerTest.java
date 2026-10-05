package com.example.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for HelloWorldController.
 * Tests all endpoints and their responses.
 */
@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // --- Tests for home() endpoint ---

    @Test
    void testHomeEndpoint_returnsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World! Welcome to Spring Boot 2.7 with Java 11"));
    }

    @Test
    void testHomeEndpoint_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void testHomeEndpoint_containsSpringBootVersion() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Spring Boot 2.7")));
    }

    @Test
    void testHomeEndpoint_containsJavaVersion() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Java 11")));
    }

    // --- Tests for hello() endpoint with default parameter ---

    @Test
    void testHelloEndpoint_withoutParameter_returnsDefaultGreeting() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void testHelloEndpoint_withoutParameter_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    // --- Tests for hello() endpoint with custom parameter ---

    @Test
    void testHelloEndpoint_withNameParameter_returnsCustomGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John!"));
    }

    @Test
    void testHelloEndpoint_withDifferentName_returnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    void testHelloEndpoint_withSpecialCharacters_returnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John-Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John-Doe!"));
    }

    @Test
    void testHelloEndpoint_withEmptyString_usesDefaultValue() throws Exception {
        // When name parameter is empty, the default value "World" is used
        mockMvc.perform(get("/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void testHelloEndpoint_withSpaces_returnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John Doe!"));
    }

    @Test
    void testHelloEndpoint_withUnicodeCharacters_returnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "José"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, José!"));
    }

    // --- Tests for info() endpoint ---

    @Test
    void testInfoEndpoint_returnsAppInfo() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Hello World Application"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.7.14"))
                .andExpect(jsonPath("$.javaVersion").value("Java 11"));
    }

    @Test
    void testInfoEndpoint_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }

    @Test
    void testInfoEndpoint_returnsJsonContentType() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testInfoEndpoint_hasAllRequiredFields() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.springBootVersion").exists())
                .andExpect(jsonPath("$.javaVersion").exists());
    }

    @Test
    void testInfoEndpoint_nameFieldIsNotEmpty() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

    @Test
    void testInfoEndpoint_versionFieldIsNotEmpty() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.version").isNotEmpty());
    }

    @Test
    void testInfoEndpoint_springBootVersionContains2_7() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.springBootVersion").value(containsString("2.7")));
    }

    @Test
    void testInfoEndpoint_javaVersionContains11() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.javaVersion").value(containsString("11")));
    }

    // --- Tests for endpoint availability ---

    @Test
    void testAllEndpointsAreAccessible() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/hello")).andExpect(status().isOk());
        mockMvc.perform(get("/info")).andExpect(status().isOk());
    }

    @Test
    void testInvalidEndpoint_returns404() throws Exception {
        mockMvc.perform(get("/invalid"))
                .andExpect(status().isNotFound());
    }

    // --- Tests for response format ---

    @Test
    void testHelloEndpoint_returnsPlainText() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void testHomeEndpoint_returnsPlainText() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }
}
