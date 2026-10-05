package com.example.helloworld;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(content().string(containsString("Hello World!")))
                .andExpect(content().string(containsString("Spring Boot 2.3")))
                .andExpect(content().string(containsString("Java 8")));
    }

    @Test
    @DisplayName("GET / should return 200 OK status")
    void testHome_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET / should return plain text content type")
    void testHome_ReturnsPlainTextContentType() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/plain"));
    }

    // --- Hello Endpoint Tests ---

    @Test
    @DisplayName("GET /hello should return greeting with default name")
    void testHello_WithoutParameter_ReturnsDefaultGreeting() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello?name=John should return personalized greeting")
    void testHello_WithNameParameter_ReturnsPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John!"));
    }

    @Test
    @DisplayName("GET /hello?name=Alice should return personalized greeting for Alice")
    void testHello_WithDifferentName_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    @DisplayName("GET /hello?name=Spring Boot should handle names with spaces")
    void testHello_WithNameContainingSpaces_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Spring Boot"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Spring Boot!"));
    }

    @Test
    @DisplayName("GET /hello?name=123 should handle numeric names")
    void testHello_WithNumericName_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 123!"));
    }

    @Test
    @DisplayName("GET /hello?name= should handle empty name parameter")
    void testHello_WithEmptyNameParameter_ReturnsDefaultGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, !"));
    }

    @Test
    @DisplayName("GET /hello with special characters should be handled correctly")
    void testHello_WithSpecialCharacters_ReturnsCorrectGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John@123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John@123!"));
    }

    // --- Info Endpoint Tests ---

    @Test
    @DisplayName("GET /info should return JSON with app information")
    void testInfo_ReturnsAppInfo() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.name").value("Hello World Application"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.3.12.RELEASE"))
                .andExpect(jsonPath("$.javaVersion").value("Java 8"));
    }

    @Test
    @DisplayName("GET /info should return all required fields")
    void testInfo_ReturnsAllRequiredFields() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.springBootVersion").exists())
                .andExpect(jsonPath("$.javaVersion").exists());
    }

    @Test
    @DisplayName("GET /info should return correct application name")
    void testInfo_ReturnsCorrectApplicationName() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hello World Application"));
    }

    @Test
    @DisplayName("GET /info should return correct version")
    void testInfo_ReturnsCorrectVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }

    @Test
    @DisplayName("GET /info should return Spring Boot version information")
    void testInfo_ReturnsSpringBootVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.3.12.RELEASE"));
    }

    @Test
    @DisplayName("GET /info should return Java version information")
    void testInfo_ReturnsJavaVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.javaVersion").value("Java 8"));
    }

    @Test
    @DisplayName("GET /info should return 200 OK status")
    void testInfo_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk());
    }

    // --- Error Handling Tests ---

    @Test
    @DisplayName("GET /nonexistent should return 404 Not Found")
    void testNonexistentEndpoint_Returns404() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST / should return 405 Method Not Allowed")
    void testHome_WithPostMethod_Returns405() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("PUT /hello should return 405 Method Not Allowed")
    void testHello_WithPutMethod_Returns405() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/hello"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("DELETE /info should return 405 Method Not Allowed")
    void testInfo_WithDeleteMethod_Returns405() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/info"))
                .andExpect(status().isMethodNotAllowed());
    }

    // --- Content Type Tests ---

    @Test
    @DisplayName("GET /hello should return text/plain content type")
    void testHello_ReturnsCorrectContentType() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/plain"));
    }

    @Test
    @DisplayName("GET /info should return application/json content type")
    void testInfo_ReturnsJsonContentType() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }
}
