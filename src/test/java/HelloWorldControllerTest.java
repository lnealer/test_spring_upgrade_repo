package com.example.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")))
                .andExpect(content().string(containsString("Spring Boot 3.3")))
                .andExpect(content().string(containsString("Java 17")));
    }

    @Test
    void testHelloWithDefaultName() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void testHelloWithCustomName() throws Exception {
        mockMvc.perform(get("/hello?name=Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    void testInfo() throws Exception {
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Hello World Application\",\"version\":\"1.0.0\",\"springBootVersion\":\"Spring Boot 3.3.0\",\"javaVersion\":\"Java 17\"}"));
    }
}
