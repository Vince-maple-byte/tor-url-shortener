package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlEncoderImplTest {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlEncoderImplTest(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public
    @Test
    void testingIfEncodeReturnsAString(){
        //If

        Base62 base62 = new Base62(null);
        UrlEncoderImpl urlEncoder = new UrlEncoderImpl(base62, urlRepository);
        String result = urlEncoder.encode("JJJ");
        //Then
        String expected = "100";
        assertEquals(expected, result);
    }

}