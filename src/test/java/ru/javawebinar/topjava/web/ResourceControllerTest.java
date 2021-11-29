package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    void getStyle() throws Exception {
        perform(get("/resources/css/style.css"))
                .andDo(print())
                .andExpect(status().isOk());
        String actualMimeType = this.perform(MockMvcRequestBuilders.get("/resources/css/style.css", 1))
                .andReturn().getResponse().getContentType();

        Assertions.assertEquals("text/css;charset=UTF-8", actualMimeType);
    }
}
