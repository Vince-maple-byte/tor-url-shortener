package com.vince.tor_url_shortener.repository;

import com.vince.tor_url_shortener.domain.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//Test for the UrlRepository to work
@SpringBootTest
public class UrlRepositoryTest {

    private Url url;

    private final UrlRepository urlRepository;

    @Autowired
    public UrlRepositoryTest(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    @BeforeEach
    void setUp(){
        url = new Url.Builder()
                .setShortenUrl("tinyurl.com/812wx")
                .setOriginalUrl("abcdefghi.com")
                .build();

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