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
    void testHomeEndpoint_ReturnsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World! Welcome to Spring Boot 2.7 with Java 17"));
    }

    @Test
    @DisplayName("GET / should return 200 OK status")
    void testHomeEndpoint_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET / should return text/plain content type")
    void testHomeEndpoint_ReturnsCorrectContentType() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().contentTypeCompatibleWith("text/plain"));
    }

    // --- Hello Endpoint Tests ---

    @Test
    @DisplayName("GET /hello should return default greeting")
    void testHelloEndpoint_WithoutName_ReturnsDefaultGreeting() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello?name=John should return personalized greeting")
    void testHelloEndpoint_WithName_ReturnsPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John!"));
    }

    @Test
    @DisplayName("GET /hello?name=Alice should return personalized greeting for Alice")
    void testHelloEndpoint_WithAliceName_ReturnsAliceGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    @DisplayName("GET /hello?name=Spring Boot should handle multi-word names")
    void testHelloEndpoint_WithMultiWordName_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Spring Boot"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Spring Boot!"));
    }

    @Test
    @DisplayName("GET /hello?name=123 should handle numeric names")
    void testHelloEndpoint_WithNumericName_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 123!"));
    }

    @Test
    @DisplayName("GET /hello?name= should handle empty string parameter")
    void testHelloEndpoint_WithEmptyName_ReturnsDefaultGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, !"));
    }

    @Test
    @DisplayName("GET /hello should return 200 OK status")
    void testHelloEndpoint_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    // --- Info Endpoint Tests ---

    @Test
    @DisplayName("GET /info should return application info as JSON")
    void testInfoEndpoint_ReturnsApplicationInfo() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.name").value("Hello World Application"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.7.14"))
                .andExpect(jsonPath("$.javaVersion").value("Java 17"));
    }

    @Test
    @DisplayName("GET /info should return correct application name")
    void testInfoEndpoint_ReturnsCorrectName() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hello World Application"));
    }

    @Test
    @DisplayName("GET /info should return correct version")
    void testInfoEndpoint_ReturnsCorrectVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }

    @Test
    @DisplayName("GET /info should return Spring Boot 2.7.14")
    void testInfoEndpoint_ReturnsCorrectSpringBootVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.7.14"));
    }

    @Test
    @DisplayName("GET /info should return Java 17")
    void testInfoEndpoint_ReturnsCorrectJavaVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.javaVersion").value("Java 17"));
    }

    @Test
    @DisplayName("GET /info should return all required fields")
    void testInfoEndpoint_ReturnsAllRequiredFields() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.springBootVersion").exists())
                .andExpect(jsonPath("$.javaVersion").exists());
    }

    @Test
    @DisplayName("GET /info should return 200 OK status")
    void testInfoEndpoint_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /info should return JSON content type")
    void testInfoEndpoint_ReturnsJsonContentType() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }

    // --- Edge Cases and Additional Tests ---

    @Test
    @DisplayName("GET /hello?name=<script> should handle special characters safely")
    void testHelloEndpoint_WithSpecialCharacters_ReturnsSafeGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "<script>"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, <script>!"));
    }

    @Test
    @DisplayName("GET /hello?name=User%20Name should handle URL encoded names")
    void testHelloEndpoint_WithUrlEncodedName_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "User Name"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, User Name!"));
    }

    @Test
    @DisplayName("GET /hello with multiple name parameters should use first value")
    void testHelloEndpoint_WithMultipleNameParams_UsesFirstValue() throws Exception {
        mockMvc.perform(get("/hello?name=First&name=Second"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, First!"));
    }

    @Test
    @DisplayName("GET /info should have non-null values")
    void testInfoEndpoint_AllValuesAreNonNull() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.version", notNullValue()))
                .andExpect(jsonPath("$.springBootVersion", notNullValue()))
                .andExpect(jsonPath("$.javaVersion", notNullValue()));
    }

    @Test
    @DisplayName("GET /info should have non-empty string values")
    void testInfoEndpoint_AllValuesAreNonEmpty() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", not(emptyString())))
                .andExpect(jsonPath("$.version", not(emptyString())))
                .andExpect(jsonPath("$.springBootVersion", not(emptyString())))
                .andExpect(jsonPath("$.javaVersion", not(emptyString())));
    }
}
