package com.vince.tor_url_shortener.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlTest {

    @Test
    void testIfAnUrlObjectIsCreatedWithTheAllArgsConstructor(){
        //if
        Url url = new Url("google.com", "tyt.com/asdc");
        //then
        assertEquals(url.getOriginalUrl(), "google.com");
        assertEquals(url.getShortenUrl(), "tyt.com/asdc");
    }

    @Test
    void testIfAnUrlObjectIsCreatedWithTheBuilder(){
        //if
        Url url = new Url.Builder()
                .setOriginalUrl("google.com")
                        .setShortenUrl("tyt.com/asdc")
                                .build();
        //then
        assertEquals(url.getOriginalUrl(), "google.com");
        assertEquals(url.getShortenUrl(), "tyt.com/asdc");
    }

}