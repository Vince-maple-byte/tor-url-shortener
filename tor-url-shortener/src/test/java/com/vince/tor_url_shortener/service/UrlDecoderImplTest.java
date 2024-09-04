package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlDecoderImplTest {

    private final UrlRepository urlRepository;
    private final Base62 base62;

    @Autowired
    public UrlDecoderImplTest(UrlRepository urlRepository, Base62 base62) {
        this.urlRepository = urlRepository;
        this.base62 = base62;
    }

    @Test
    void testingIfNumericalValueOfBase62CharacterReturnsTheCorrectDecimalValue(){
        //If
        UrlDecoderImpl urlDecoder = new UrlDecoderImpl(urlRepository, base62);
        int expectedLocation1 = urlDecoder.numericalValueOfBase62Character('z');
        int expectedLocation2 = urlDecoder.numericalValueOfBase62Character('S');
        int expectedLocation3 = urlDecoder.numericalValueOfBase62Character('7');
        //Then
        int result1 = 35;
        int result2 = 54;
        int result3 = 7;

        assertEquals(expectedLocation1, result1, "The location of z");
        assertEquals(expectedLocation2, result2, "The location of S");
        assertEquals(expectedLocation3, result3, "The location of 7");
    }

    @Test
    void testingIfDecoderReturnsTheCorrectDecimalValue(){
        //If
        UrlDecoderImpl urlDecoder = new UrlDecoderImpl(urlRepository, base62);
        long expected1 = urlDecoder.decode("ZZ");
        long expected2 = urlDecoder.decode("101");
        //Then
        long result1 = 3843;
        long result2 = 3845;

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

}