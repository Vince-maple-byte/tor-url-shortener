package com.vince.tor_url_shortener.controller;

import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;
import com.vince.tor_url_shortener.exception.UrlNotFoundException;
import com.vince.tor_url_shortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UrlController.class)
@ActiveProfiles("test")
class UrlControllerTest {

    @MockitoBean
    private UrlService urlService;

    @MockitoBean
    private RedisCaching redisCaching;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void TestIfGetMethodWithURLShortenedReturnsTheCorrectUrl() throws Exception {
        //Given
        String originalUrl = "google.com";
        String shortenedUrl = "123";
        UrlDTO urlDTO = new UrlDTO(originalUrl, shortenedUrl);
        when(urlService.getUrl(Mockito.anyString())).thenReturn(urlDTO);

        //When
        ResultActions request = mockMvc.perform(get("/123"));

        //Then
        request.andExpect(status().is3xxRedirection());
    }

    @Test
    void TestIfGetMethodWithUrlShortenerReturnsAInvalidErrorCode() throws Exception {
        //Given
        String originalUrl = "google.com";
        String shortenedUrl = "123";
        UrlDTO urlDTO = new UrlDTO(originalUrl, shortenedUrl);
        when(urlService.getUrl(Mockito.anyString())).thenReturn(null);

        //When
        ResultActions request = mockMvc.perform(get("/123"));

        //Then
        request.andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UrlNotFoundException))
                .andExpect(result -> assertEquals("This url has not been found", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void TestIfPostMethodCreatesANewShortUrl() throws Exception {
        //Given
        String originalUrl = "google.com";
        String shortenedUrl = "123";
        UrlDTO urlDTO = new UrlDTO(originalUrl, shortenedUrl);
        String requestBody = "{\"urlToCreate\": \"google.com\"}";

        when(urlService.createUrl(any(UrlCreation.class))).thenReturn(urlDTO);

        //When
        ResultActions request = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );
        //Then
        request.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"originalUrl\":\"google.com\",\"shortenUrl\":\"123\"}"));
    }
}