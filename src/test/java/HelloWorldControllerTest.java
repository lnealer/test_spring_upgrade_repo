package com.example.helloworld;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for HelloWorldController.
 * Tests all endpoints and their behavior with various parameters.
 */
@WebMvcTest(HelloWorldController.class)
@DisplayName("HelloWorldController Tests")
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ============ Tests for home() endpoint ============

    @Test
    @DisplayName("GET / should return welcome message")
    void testHomeEndpoint_ReturnsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World! Welcome to Spring Boot 2.7 with Java 8"));
    }

    @Test
    @DisplayName("GET / should return status 200")
    void testHomeEndpoint_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET / should return plain text content type")
    void testHomeEndpoint_ReturnsPlainTextContentType() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    @DisplayName("GET / response should contain 'Hello World'")
    void testHomeEndpoint_ResponseContainsHelloWorld() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    @DisplayName("GET / response should contain 'Spring Boot'")
    void testHomeEndpoint_ResponseContainsSpringBoot() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Spring Boot")));
    }

    // ============ Tests for hello() endpoint with default parameter ============

    @Test
    @DisplayName("GET /hello without parameter should use default 'World'")
    void testHelloEndpoint_WithoutParameter_UsesDefaultValue() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello without parameter should return status 200")
    void testHelloEndpoint_WithoutParameter_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /hello without parameter should return plain text")
    void testHelloEndpoint_WithoutParameter_ReturnsPlainText() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    // ============ Tests for hello() endpoint with custom parameter ============

    @Test
    @DisplayName("GET /hello?name=Alice should return personalized greeting")
    void testHelloEndpoint_WithCustomName_ReturnsPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    @DisplayName("GET /hello?name=Bob should return personalized greeting")
    void testHelloEndpoint_WithDifferentName_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Bob"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Bob!"));
    }

    @Test
    @DisplayName("GET /hello?name=John Doe should handle names with spaces")
    void testHelloEndpoint_WithNameContainingSpaces_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John Doe!"));
    }

    @Test
    @DisplayName("GET /hello?name=123 should handle numeric names")
    void testHelloEndpoint_WithNumericName_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 123!"));
    }

    @Test
    @DisplayName("GET /hello?name=@#$% should handle special characters")
    void testHelloEndpoint_WithSpecialCharacters_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "@#$%"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, @#$%!"));
    }

    @Test
    @DisplayName("GET /hello?name= (empty string) should use default 'World' due to defaultValue behavior")
    void testHelloEndpoint_WithEmptyString_UsesDefaultValue() throws Exception {
        // Note: Spring's @RequestParam with defaultValue treats empty string as the default value
        mockMvc.perform(get("/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello?name=VeryLongNameWithManyCharacters should handle long names")
    void testHelloEndpoint_WithLongName_HandlesCorrectly() throws Exception {
        String longName = "VeryLongNameWithManyCharactersToTestHandling";
        mockMvc.perform(get("/hello").param("name", longName))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + longName + "!"));
    }

    @Test
    @DisplayName("GET /hello?name=Unicode should handle unicode characters")
    void testHelloEndpoint_WithUnicodeCharacters_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "José"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, José!"));
    }

    // ============ Tests for info() endpoint ============

    @Test
    @DisplayName("GET /info should return JSON with app information")
    void testInfoEndpoint_ReturnsAppInfo() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Hello World Application"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.7.14.RELEASE"))
                .andExpect(jsonPath("$.javaVersion").value("Java 8"));
    }

    @Test
    @DisplayName("GET /info should return status 200")
    void testInfoEndpoint_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /info should return JSON content type")
    void testInfoEndpoint_ReturnsJsonContentType() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("GET /info response should contain all required fields")
    void testInfoEndpoint_ResponseContainsAllFields() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.springBootVersion").exists())
                .andExpect(jsonPath("$.javaVersion").exists());
    }

    @Test
    @DisplayName("GET /info name field should not be empty")
    void testInfoEndpoint_NameFieldNotEmpty() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

    @Test
    @DisplayName("GET /info version field should not be empty")
    void testInfoEndpoint_VersionFieldNotEmpty() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.version").isNotEmpty());
    }

    @Test
    @DisplayName("GET /info springBootVersion field should contain 'Spring Boot'")
    void testInfoEndpoint_SpringBootVersionContainsSpringBoot() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.springBootVersion").value(containsString("Spring Boot")));
    }

    @Test
    @DisplayName("GET /info javaVersion field should contain 'Java'")
    void testInfoEndpoint_JavaVersionContainsJava() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(jsonPath("$.javaVersion").value(containsString("Java")));
    }

    // ============ Tests for non-existent endpoints ============

    @Test
    @DisplayName("GET /nonexistent should return 404 Not Found")
    void testNonExistentEndpoint_Returns404() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /hello/extra should return 404 Not Found")
    void testInvalidHelloPath_Returns404() throws Exception {
        mockMvc.perform(get("/hello/extra"))
                .andExpect(status().isNotFound());
    }

    // ============ Tests for HTTP methods ============

    @Test
    @DisplayName("POST / should return 405 Method Not Allowed")
    void testHomeEndpoint_PostMethod_Returns405() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("PUT /hello should return 405 Method Not Allowed")
    void testHelloEndpoint_PutMethod_Returns405() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/hello"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("DELETE /info should return 405 Method Not Allowed")
    void testInfoEndpoint_DeleteMethod_Returns405() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/info"))
                .andExpect(status().isMethodNotAllowed());
    }
}
