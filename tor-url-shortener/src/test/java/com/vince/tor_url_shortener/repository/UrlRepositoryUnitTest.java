package com.vince.tor_url_shortener.repository;

import com.vince.tor_url_shortener.domain.Url;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UrlRepositoryUnitTest {

    @Mock
    private UrlRepository urlRepository;

    //OpenMocks initialize mocks from the annotated fields
    @BeforeEach
    void openMocks(){
        AutoCloseable closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void UrlRepositoryCheckIfFindByIdIsCalled(){
        //Given
        String urlId = "string.com";
        Optional<Url> url = Optional.of(new Url());
        when(urlRepository.findById(urlId)).thenReturn(url);

        //When
        urlRepository.findById("string.com");

        //Then
        verify(urlRepository).findById(urlId);

    }

    @Test
    void UrlRepositoryCheckIfSaveIsCalled() {
        //Given
        Url url = new Url();

        //When
        urlRepository.save(url);

        //then
        verify(urlRepository).save(url);
    }


}
