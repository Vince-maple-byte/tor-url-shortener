package com.vince.tor_url_shortener.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlEncoderImplTest {


    @Test
    void testingIfEncodeReturnsAString(){
        //If
        Base62 base62 = new Base62(null);
        UrlEncoderImpl urlEncoder = new UrlEncoderImpl(base62);
        String result = urlEncoder.encode("JJJ");
        //Then
        String expected = "4c92";
        assertEquals(expected, result);
    }

}