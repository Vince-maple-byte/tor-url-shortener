package com.vince.tor_url_shortener.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UrlEncoderImplTest {


    @Test
    void testingIfEncodeReturnsAString(){
        //If
        base62 base62 = new base62(null);
        UrlEncoderImpl urlEncoder = new UrlEncoderImpl(base62);
        String result = urlEncoder.encode("JJJ");
        //Then
        String expected = "4c92";
        assertEquals(expected, result);
    }

}