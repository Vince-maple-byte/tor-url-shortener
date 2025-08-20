package com.vince.tor_url_shortener.dto;

import com.vince.tor_url_shortener.domain.Url;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UrlMapperTest {



    @Test
    void createAUrlDTOFromAUrlObjectEntity() {
        //Given
        UrlMapper urlMapper = new UrlMapper();
        Url url = new Url.Builder()
                .setShortenUrl("just.com/us")
                .setOriginalUrl("google.com")
                .build();

        //When
        UrlDTO urlDTO = urlMapper.toDTO(url);

        //Expect
        assertEquals(urlDTO.getOriginalUrl(), url.getOriginalUrl());
        assertEquals(urlDTO.getShortenUrl(), url.getShortenUrl());
    }

    @Test
    void createAUrlObjectEntityFromAUrlDTO() {
        //Given
        UrlMapper urlMapper = new UrlMapper();
        UrlCreation urlCreation = new UrlCreation("google.com");

        //When
        Url url = urlMapper.toEntity(urlCreation);

        //Expect
        assertEquals(urlCreation.getUrlToCreate(), url.getOriginalUrl());
    }

}