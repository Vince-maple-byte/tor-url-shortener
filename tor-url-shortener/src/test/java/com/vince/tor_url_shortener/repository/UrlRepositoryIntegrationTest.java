package com.vince.tor_url_shortener.repository;

import com.vince.tor_url_shortener.domain.Url;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//Test for the UrlRepository to work
@SpringBootTest
@ActiveProfiles("test") //This makes sure that this test case only runs in the test profile which doesn't include the redis config file
public class UrlRepositoryIntegrationTest {

    private Url url;

    private final UrlRepository urlRepository;

    @Autowired
    public UrlRepositoryIntegrationTest(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    @BeforeEach
    void setUp(){
        url = new Url.Builder()
                .setShortenUrl("tinyurl.com/812wx")
                .setOriginalUrl("abcdefghi.com")
                .build();

    }

    @AfterEach
    void tearDown() {
        url = null;
    }


    @Test
    void doesTheEntityGetSavedIntoTheDatabase(){
        //assert urlRepository != null;
        urlRepository.save(url);
        Optional<Url> result = urlRepository.findById("tinyurl.com/812wx");
        assertThat(result).isPresent();
        assertThat(result.get().getOriginalUrl()).isEqualTo(url.getOriginalUrl());

    }

}