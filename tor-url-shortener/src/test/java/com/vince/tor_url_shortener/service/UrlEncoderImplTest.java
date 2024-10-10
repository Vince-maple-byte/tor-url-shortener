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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UrlEncoderImplTest {

    @Mock
    private RedisCounter redisCounter;


    @InjectMocks
    private UrlEncoderImpl urlEncoder;

    @BeforeEach
    void setUp() {
        // Mock the RedisCounter behavior (e.g., mock getCounter to return 100)
        Mockito.when(redisCounter.getCounterAndIncrement()).thenReturn(1L);
    }

    public
    @Test
    void testingIfEncodeReturnsAString(){
        //If

        Base62 base62 = new Base62(null);
        //urlEncoder = new UrlEncoderImpl(base62, redisCounter);
        String result = urlEncoder.encode("JJJ");
        //Then
        String expected = "1";
        assertEquals(expected, result);
    }

}