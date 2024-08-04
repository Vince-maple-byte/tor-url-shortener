package com.vince.tor_url_shortener.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Base62Test {

    //Testing to see if the Base62 characters are working properly
    @Test
    void checkIfBase62CreatesAnArrayOfBase62Values(){
        //If
        Base62 expected = new Base62(null);
        //Then
        char[] result = new char[]{
                '0','1','2','3','4','5','6','7','8','9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        assertEquals(expected.base62Values()[61], result[61]);

    }

}