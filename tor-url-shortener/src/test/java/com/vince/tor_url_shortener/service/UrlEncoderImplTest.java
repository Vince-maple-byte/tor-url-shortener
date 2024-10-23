package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlEncoderImplTest {

    @MockBean
    private RedisCounter redisCounter;

    @MockBean
    private Base62 base62;
    @Autowired
    private UrlEncoderImpl urlEncoder;

    @BeforeEach
    void setUp() {
        // Mock the RedisCounter behavior (e.g., mock getCounter to return 100)
        Mockito.when(redisCounter.getCounterAndIncrement()).thenReturn(1L);
        Mockito.when(base62.base62Values()).thenReturn(new char[]{
                '0','1','2','3','4','5','6','7','8','9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        });
    }

    @Test
    void testingIfEncodeReturnsAString(){
        //Given
        String expected = "1";

        //When
        String result = urlEncoder.encode("JJJ");

        //Then
        assertEquals(expected, result);
    }

}