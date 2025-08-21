package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UrlDecoderImplTest {

    @Mock
    private Base62 base62;

    @InjectMocks
    private UrlDecoderImpl urlDecoder;

    @Test
    void testingIfNumericalValueOfBase62CharacterReturnsTheCorrectDecimalValue(){
        //If
        //UrlDecoderImpl urlDecoder = new UrlDecoderImpl(base62);
        Mockito.when(base62.base62Values()).thenReturn(new char[]{
                '0','1','2','3','4','5','6','7','8','9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        });
        int expectedLocation1 = urlDecoder.numericalValueOfBase62Character('z');
        int expectedLocation2 = urlDecoder.numericalValueOfBase62Character('S');
        int expectedLocation3 = urlDecoder.numericalValueOfBase62Character('7');
        //Then
        int result1 = 35;
        int result2 = 54;
        int result3 = 7;

        assertEquals(result1, expectedLocation1, "The location of z");
        assertEquals(result2, expectedLocation2, "The location of S");
        assertEquals(result3, expectedLocation3, "The location of 7");
    }

    @Test
    void testingIfDecoderReturnsTheCorrectDecimalValue(){
        //If
        Mockito.when(base62.base62Values()).thenReturn(new char[]{
                '0','1','2','3','4','5','6','7','8','9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        });
        long expected1 = urlDecoder.decode("ZZ");
        long expected2 = urlDecoder.decode("101");
        //Then
        long result1 = 3843;
        long result2 = 3845;

        assertEquals(result1, expected1);
        assertEquals(result2, expected2);
    }

}