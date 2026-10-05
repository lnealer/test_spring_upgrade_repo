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
                .andExpect(content().string("Hello World! Welcome to Spring Boot 2.7 with Java 11"));
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
    @DisplayName("GET /hello without parameter should use default name 'World'")
    void testHelloEndpoint_WithoutParameter_UsesDefaultName() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello with name parameter should return personalized greeting")
    void testHelloEndpoint_WithNameParameter_ReturnsPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    @DisplayName("GET /hello with different names should return correct greetings")
    void testHelloEndpoint_WithVariousNames_ReturnsCorrectGreetings() throws Exception {
        String[] names = {"Bob", "Charlie", "Diana", "Eve"};
        
        for (String name : names) {
            mockMvc.perform(get("/hello").param("name", name))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, " + name + "!"));
        }
    }

    @Test
    @DisplayName("GET /hello with special characters in name should handle correctly")
    void testHelloEndpoint_WithSpecialCharacters_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "O'Brien"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, O'Brien!"));
    }

    @Test
    @DisplayName("GET /hello with empty string parameter should use default value")
    void testHelloEndpoint_WithEmptyParameter_UsesDefaultValue() throws Exception {
        mockMvc.perform(get("/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello with spaces in name should preserve spaces")
    void testHelloEndpoint_WithSpacesInName_PreservesSpaces() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John Doe!"));
    }

    @Test
    @DisplayName("GET /hello should return 200 OK status")
    void testHelloEndpoint_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /hello response should contain greeting format")
    void testHelloEndpoint_ResponseFormat_ContainsGreetingFormat() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello,")))
                .andExpect(content().string(containsString("!")));
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
                .andExpect(jsonPath("$.javaVersion").value("Java 11"));
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
    @DisplayName("GET /info should return correct application name")
    void testInfoEndpoint_ReturnsCorrectApplicationName() throws Exception {
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
    @DisplayName("GET /info should return correct Spring Boot version")
    void testInfoEndpoint_ReturnsCorrectSpringBootVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.springBootVersion").value("Spring Boot 2.7.14"));
    }

    @Test
    @DisplayName("GET /info should return correct Java version")
    void testInfoEndpoint_ReturnsCorrectJavaVersion() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.javaVersion").value("Java 11"));
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

    @Test
    @DisplayName("GET /info response should not contain null fields")
    void testInfoEndpoint_ResponseShouldNotContainNullFields() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.version").isNotEmpty())
                .andExpect(jsonPath("$.springBootVersion").isNotEmpty())
                .andExpect(jsonPath("$.javaVersion").isNotEmpty());
    }

    // --- Integration Tests ---

    @Test
    @DisplayName("All endpoints should be accessible and return 200 OK")
    void testAllEndpoints_AreAccessible() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/hello")).andExpect(status().isOk());
        mockMvc.perform(get("/info")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /nonexistent should return 404 Not Found")
    void testNonexistentEndpoint_Returns404() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /hello with multiple parameters should use first name parameter")
    void testHelloEndpoint_WithMultipleParameters_UsesFirstParameter() throws Exception {
        mockMvc.perform(get("/hello?name=Alice&name=Bob"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Alice")));
    }

    @Test
    @DisplayName("GET /hello with URL encoded special characters should decode correctly")
    void testHelloEndpoint_WithUrlEncodedCharacters_DecodesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "José"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, José!"));
    }

    @Test
    @DisplayName("GET /hello response content type should be text/plain")
    void testHelloEndpoint_ResponseContentType_IsTextPlain() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/plain"));
    }

    @Test
    @DisplayName("GET / response should contain Spring Boot version reference")
    void testHomeEndpoint_ResponseContainsVersionReference() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Spring Boot")))
                .andExpect(content().string(containsString("Java")));
    }

    @Test
    @DisplayName("GET /info should return valid JSON structure")
    void testInfoEndpoint_ReturnsValidJsonStructure() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.version", notNullValue()))
                .andExpect(jsonPath("$.springBootVersion", notNullValue()))
                .andExpect(jsonPath("$.javaVersion", notNullValue()));
    }

    @Test
    @DisplayName("GET /hello with numeric name should return greeting")
    void testHelloEndpoint_WithNumericName_ReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 123!"));
    }

    @Test
    @DisplayName("GET /hello with long name should return greeting")
    void testHelloEndpoint_WithLongName_ReturnsGreeting() throws Exception {
        String longName = "VeryLongNameWithManyCharactersToTestTheController";
        mockMvc.perform(get("/hello").param("name", longName))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + longName + "!"));
    }

    @Test
    @DisplayName("GET /hello with hyphenated name should return greeting")
    void testHelloEndpoint_WithHyphenatedName_ReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Mary-Jane"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Mary-Jane!"));
    }

    @Test
    @DisplayName("GET /hello with name containing numbers should return greeting")
    void testHelloEndpoint_WithNameContainingNumbers_ReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Agent007"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Agent007!"));
    }

    @Test
    @DisplayName("GET / should return exact welcome message")
    void testHomeEndpoint_ReturnsExactMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World! Welcome to Spring Boot 2.7 with Java 11")));
    }

    @Test
    @DisplayName("GET /info should have correct field types in JSON")
    void testInfoEndpoint_FieldTypesAreCorrect() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", instanceOf(String.class)))
                .andExpect(jsonPath("$.version", instanceOf(String.class)))
                .andExpect(jsonPath("$.springBootVersion", instanceOf(String.class)))
                .andExpect(jsonPath("$.javaVersion", instanceOf(String.class)));
    }

    // --- Additional Edge Case Tests ---

    @Test
    @DisplayName("GET /hello with whitespace-only parameter should use default value")
    void testHelloEndpoint_WithWhitespaceOnlyParameter_UsesDefaultValue() throws Exception {
        mockMvc.perform(get("/hello").param("name", "   "))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello,    !"));
    }

    @Test
    @DisplayName("GET /hello with unicode characters should handle correctly")
    void testHelloEndpoint_WithUnicodeCharacters_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "李明"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 李明!"));
    }

    @Test
    @DisplayName("GET /hello with emoji should handle correctly")
    void testHelloEndpoint_WithEmoji_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "User😊"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, User😊!"));
    }

    @Test
    @DisplayName("GET /hello with plus sign should handle correctly")
    void testHelloEndpoint_WithPlusSign_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "C++"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, C++!"));
    }

    @Test
    @DisplayName("GET /hello with parentheses should handle correctly")
    void testHelloEndpoint_WithParentheses_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "John (Johnny)"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John (Johnny)!"));
    }

    @Test
    @DisplayName("GET /hello with ampersand should handle correctly")
    void testHelloEndpoint_WithAmpersand_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Tom & Jerry"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Tom & Jerry!"));
    }

    @Test
    @DisplayName("GET /hello with equals sign should handle correctly")
    void testHelloEndpoint_WithEqualsSign_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "x=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, x=5!"));
    }

    @Test
    @DisplayName("GET /hello with question mark should handle correctly")
    void testHelloEndpoint_WithQuestionMark_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Who?"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Who?!"));
    }

    @Test
    @DisplayName("GET /hello with hash should handle correctly")
    void testHelloEndpoint_WithHash_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "#User"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, #User!"));
    }

    @Test
    @DisplayName("GET /hello with percent sign should handle correctly")
    void testHelloEndpoint_WithPercentSign_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "100%"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 100%!"));
    }

    @Test
    @DisplayName("GET /hello with forward slash should handle correctly")
    void testHelloEndpoint_WithForwardSlash_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "path/to/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, path/to/user!"));
    }

    @Test
    @DisplayName("GET /hello with backslash should handle correctly")
    void testHelloEndpoint_WithBackslash_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "path\\to\\user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, path\\to\\user!"));
    }

    @Test
    @DisplayName("GET /hello with tab character should handle correctly")
    void testHelloEndpoint_WithTabCharacter_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "User\tName"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello,")))
                .andExpect(content().string(containsString("!")));
    }

    @Test
    @DisplayName("GET /hello with newline character should handle correctly")
    void testHelloEndpoint_WithNewlineCharacter_HandlesCorrectly() throws Exception {
        mockMvc.perform(get("/hello").param("name", "User\nName"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello,")))
                .andExpect(content().string(containsString("!")));
    }

    @Test
    @DisplayName("GET /info response should contain Spring Boot 2.7 version")
    void testInfoEndpoint_ContainsSpringBoot27Version() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.springBootVersion").value(containsString("2.7")));
    }

    @Test
    @DisplayName("GET /info response should contain Java 11 version")
    void testInfoEndpoint_ContainsJava11Version() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.javaVersion").value(containsString("11")));
    }

    @Test
    @DisplayName("GET / response should contain Spring Boot 2.7 reference")
    void testHomeEndpoint_ContainsSpringBoot27Reference() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2.7")));
    }

    @Test
    @DisplayName("GET / response should contain Java 11 reference")
    void testHomeEndpoint_ContainsJava11Reference() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("11")));
    }

    @Test
    @DisplayName("GET /hello with single character name should return greeting")
    void testHelloEndpoint_WithSingleCharacterName_ReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello").param("name", "A"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, A!"));
    }

    @Test
    @DisplayName("GET /hello with mixed case name should preserve case")
    void testHelloEndpoint_WithMixedCaseName_PreservesCase() throws Exception {
        mockMvc.perform(get("/hello").param("name", "JoHn DoE"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, JoHn DoE!"));
    }

    @Test
    @DisplayName("GET /hello with all uppercase name should preserve case")
    void testHelloEndpoint_WithAllUppercaseName_PreservesCase() throws Exception {
        mockMvc.perform(get("/hello").param("name", "ALICE"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, ALICE!"));
    }

    @Test
    @DisplayName("GET /hello with all lowercase name should preserve case")
    void testHelloEndpoint_WithAllLowercaseName_PreservesCase() throws Exception {
        mockMvc.perform(get("/hello").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, alice!"));
    }
}
