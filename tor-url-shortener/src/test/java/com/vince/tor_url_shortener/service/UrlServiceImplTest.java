package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.domain.Url;
import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;
import com.vince.tor_url_shortener.dto.UrlMapper;
import com.vince.tor_url_shortener.exception.UrlNotFoundException;
import com.vince.tor_url_shortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*

When I need to test out a spring boot application with Dependency Injection and IoC from Spring,
I need to use @SpringBootTest and use @MockBean @AutoWired
since @ExtendWith(MockitoExtension.class) won't work if you use spring to manage Dependency Injection for you.
However if you don't specify either of these annotations you can use the regular @Mock and @InjectMocks and Mockito
would manually make the Mock objects and inject the Mock objects into the class that we are testing.
 */

@SpringBootTest
class UrlServiceImplTest {

    @MockBean
    private UrlRepository urlRepository;

    @MockBean
    private UrlEncoder urlEncoder;

    @MockBean
    private UrlMapper urlMapper;

    @MockBean
    private UrlDecoder urlDecoder;

    @Autowired
    private UrlServiceImpl urlService;

    @Test
    void Create_Valid_Url_With_A_URLDTO_Returned() {
        //Given
        String originalUrl = "google.com";
        String shortenedUrl = "bit.ly/";
        Url urlExpected = new Url.Builder()
                .setOriginalUrl(originalUrl)
                .setShortenUrl(shortenedUrl)
                .build();

        UrlDTO urlDTOExpected = new UrlDTO(originalUrl, shortenedUrl);
        UrlCreation urlCreation = new UrlCreation(originalUrl);


        Mockito.when(urlEncoder.encode(urlCreation.getUrlToCreate())).thenReturn("bit.ly/");

        Mockito.when(urlMapper.toEntity(Mockito.eq(urlCreation))).thenReturn(urlExpected);


        Mockito.when(urlMapper.toDTO(Mockito.any(Url.class))).thenReturn(urlDTOExpected);

        Mockito.when(urlRepository.save(Mockito.any(Url.class))).thenReturn(urlExpected);

        //When
        UrlDTO urlDTOResult = urlService.createUrl(urlCreation);

        //Then
        assertEquals(urlDTOResult.getOriginalUrl(), urlDTOExpected.getOriginalUrl());
        assertEquals(urlDTOResult.getShortenUrl(), urlDTOExpected.getShortenUrl());

        Mockito.verify(urlMapper, Mockito.times(1)).toEntity(Mockito.eq(urlCreation));
        Mockito.verify(urlEncoder, Mockito.times(1)).encode(urlCreation.getUrlToCreate());
        Mockito.verify(urlMapper, Mockito.times(1)).toDTO(Mockito.eq(urlExpected));

    }



    @Test
    void Check_To_See_If_getUrl_method_gives_a_valid_url() {
        //Given
        String originalUrl = "google.com";
        String shortenedUrl = "bit.ly/";
        UrlDTO urlDTOExpected = new UrlDTO(originalUrl, shortenedUrl);
        Optional<Url> urlOptional = Optional.of(new Url(originalUrl,shortenedUrl));

        //When
        Mockito.when(urlRepository.findById(shortenedUrl))
                .thenReturn(Optional.of(new Url(originalUrl,shortenedUrl)));

        Mockito.when(urlMapper.toDTO(Mockito.any(Url.class)))
                .thenReturn(urlDTOExpected);

        //Then
        UrlDTO urlDTOActual = urlService.getUrl(shortenedUrl);
        System.out.println(urlDTOActual);

        //Mockito.verify(urlMapper, Mockito.times(1)).toDTO(Mockito.eq(new Url(originalUrl,shortenedUrl)));

        assertEquals(urlDTOExpected.getShortenUrl(),urlDTOActual.getShortenUrl(), "Checking the shorten url from the get method");
        assertEquals(urlDTOExpected.getOriginalUrl(),urlDTOActual.getOriginalUrl(), "Checking the original url from the get method");
    }

    @Test
    void Check_To_See_If_getUrl_method_throws_an_exception_for_invalid_url() {
        //Given
        String originalUrl = "google.com";
        String shortenedUrl = "bit.ly/";
        UrlDTO urlDTOExpected = new UrlDTO(originalUrl, shortenedUrl);
        Optional<Url> urlOptional = Optional.of(new Url(originalUrl,shortenedUrl));

        //When
        Mockito.when(urlRepository.findById(shortenedUrl))
                .thenReturn(Optional.empty());

        Mockito.when(urlMapper.toDTO(Mockito.any(Url.class)))
                .thenReturn(urlDTOExpected);


        //UrlDTO urlDTOActual = urlService.getUrl(shortenedUrl);

        //Mockito.verify(urlMapper, Mockito.times(1)).toDTO(Mockito.eq(new Url(originalUrl,shortenedUrl)));
        UrlNotFoundException exception = assertThrows(UrlNotFoundException.class,
                () -> urlService.getUrl(shortenedUrl)
        );

        //Then
        assertEquals("Not a valid shorten url: " + shortenedUrl, exception.getMessage());
    }

}