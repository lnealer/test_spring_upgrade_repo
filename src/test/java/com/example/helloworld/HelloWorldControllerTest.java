package com.example.helloworld;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
@DisplayName("HelloWorldController Tests")
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("Home Endpoint Tests")
    class HomeEndpointTests {

        @Test
        @DisplayName("GET / should return welcome message")
        void testHomeEndpoint() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello World! Welcome to Spring Boot 3 with Java 17"));
        }

        @Test
        @DisplayName("GET / should return 200 OK status")
        void testHomeEndpointStatus() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("GET / should return plain text content type")
        void testHomeEndpointContentType() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(content().contentTypeCompatibleWith("text/plain;charset=UTF-8"));
        }

        @Test
        @DisplayName("GET / response should contain 'Spring Boot 3'")
        void testHomeEndpointContainsSpringBoot3() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(content().string(containsString("Spring Boot 3")));
        }

        @Test
        @DisplayName("GET / response should contain 'Java 17'")
        void testHomeEndpointContainsJava17() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(content().string(containsString("Java 17")));
        }
    }

    @Nested
    @DisplayName("Hello Endpoint Tests")
    class HelloEndpointTests {

        @Test
        @DisplayName("GET /hello with default parameter should return 'Hello, World!'")
        void testHelloEndpointWithDefaultParameter() throws Exception {
            mockMvc.perform(get("/hello"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, World!"));
        }

        @Test
        @DisplayName("GET /hello with name parameter should return personalized greeting")
        void testHelloEndpointWithNameParameter() throws Exception {
            mockMvc.perform(get("/hello").param("name", "Alice"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, Alice!"));
        }

        @Test
        @DisplayName("GET /hello with different name should return correct greeting")
        void testHelloEndpointWithDifferentName() throws Exception {
            mockMvc.perform(get("/hello").param("name", "Bob"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, Bob!"));
        }

        @Test
        @DisplayName("GET /hello with special characters in name")
        void testHelloEndpointWithSpecialCharacters() throws Exception {
            mockMvc.perform(get("/hello").param("name", "John O'Brien"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, John O'Brien!"));
        }

        @Test
        @DisplayName("GET /hello with empty string parameter should use default value")
        void testHelloEndpointWithEmptyParameter() throws Exception {
            mockMvc.perform(get("/hello").param("name", ""))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, World!"));
        }

        @Test
        @DisplayName("GET /hello with numeric name parameter")
        void testHelloEndpointWithNumericName() throws Exception {
            mockMvc.perform(get("/hello").param("name", "123"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, 123!"));
        }

        @Test
        @DisplayName("GET /hello with long name parameter")
        void testHelloEndpointWithLongName() throws Exception {
            String longName = "A".repeat(100);
            mockMvc.perform(get("/hello").param("name", longName))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello, " + longName + "!")));
        }

        @Test
        @DisplayName("GET /hello response should contain greeting format")
        void testHelloEndpointResponseFormat() throws Exception {
            mockMvc.perform(get("/hello").param("name", "Test"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(matchesPattern("Hello, .*!")));
        }
    }

    @Nested
    @DisplayName("Info Endpoint Tests")
    class InfoEndpointTests {

        @Test
        @DisplayName("GET /info should return JSON with app information")
        void testInfoEndpoint() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.name").value("Hello World Application"))
                    .andExpect(jsonPath("$.version").value("1.0.0"))
                    .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 3.3.0"))
                    .andExpect(jsonPath("$.javaVersion").value("Java 17"));
        }

        @Test
        @DisplayName("GET /info should return 200 OK status")
        void testInfoEndpointStatus() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("GET /info should return JSON content type")
        void testInfoEndpointContentType() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(content().contentType("application/json"));
        }

        @Test
        @DisplayName("GET /info response should contain application name")
        void testInfoEndpointContainsName() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(jsonPath("$.name").exists())
                    .andExpect(jsonPath("$.name").isString());
        }

        @Test
        @DisplayName("GET /info response should contain version")
        void testInfoEndpointContainsVersion() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(jsonPath("$.version").exists())
                    .andExpect(jsonPath("$.version").value("1.0.0"));
        }

        @Test
        @DisplayName("GET /info response should contain Spring Boot version")
        void testInfoEndpointContainsSpringBootVersion() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(jsonPath("$.springBootVersion").exists())
                    .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 3.3.0"));
        }

        @Test
        @DisplayName("GET /info response should contain Java version")
        void testInfoEndpointContainsJavaVersion() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(jsonPath("$.javaVersion").exists())
                    .andExpect(jsonPath("$.javaVersion").value("Java 17"));
        }

        @Test
        @DisplayName("GET /info response should have all required fields")
        void testInfoEndpointHasAllFields() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(jsonPath("$.name").exists())
                    .andExpect(jsonPath("$.version").exists())
                    .andExpect(jsonPath("$.springBootVersion").exists())
                    .andExpect(jsonPath("$.javaVersion").exists());
        }

        @Test
        @DisplayName("GET /info response should not have null values")
        void testInfoEndpointNoNullValues() throws Exception {
            mockMvc.perform(get("/info"))
                    .andExpect(jsonPath("$.name").isNotEmpty())
                    .andExpect(jsonPath("$.version").isNotEmpty())
                    .andExpect(jsonPath("$.springBootVersion").isNotEmpty())
                    .andExpect(jsonPath("$.javaVersion").isNotEmpty());
        }
    }

    @Nested
    @DisplayName("HTTP Method Tests")
    class HttpMethodTests {

        @Test
        @DisplayName("POST / should return 405 Method Not Allowed")
        void testHomeEndpointPostNotAllowed() throws Exception {
            mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/"))
                    .andExpect(status().isMethodNotAllowed());
        }

        @Test
        @DisplayName("PUT /hello should return 405 Method Not Allowed")
        void testHelloEndpointPutNotAllowed() throws Exception {
            mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/hello"))
                    .andExpect(status().isMethodNotAllowed());
        }

        @Test
        @DisplayName("DELETE /info should return 405 Method Not Allowed")
        void testInfoEndpointDeleteNotAllowed() throws Exception {
            mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/info"))
                    .andExpect(status().isMethodNotAllowed());
        }
    }

    @Nested
    @DisplayName("Edge Cases and Error Handling")
    class EdgeCasesTests {

        @Test
        @DisplayName("GET /nonexistent should return 404 Not Found")
        void testNonexistentEndpoint() throws Exception {
            mockMvc.perform(get("/nonexistent"))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("GET /hello with multiple name parameters should use first value")
        void testHelloEndpointWithMultipleParameters() throws Exception {
            mockMvc.perform(get("/hello")
                    .param("name", "Alice")
                    .param("name", "Bob"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello,")));
        }

        @Test
        @DisplayName("GET /hello with URL encoded special characters")
        void testHelloEndpointWithUrlEncodedCharacters() throws Exception {
            mockMvc.perform(get("/hello").param("name", "Test%20Name"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello,")));
        }
    }
}
