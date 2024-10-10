package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.domain.Url;
import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;
import com.vince.tor_url_shortener.dto.UrlMapper;
import com.vince.tor_url_shortener.repository.UrlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {

    }

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

        //System.out.println(urlEncoder.encode(urlCreation.getUrlToCreate()));
        //System.out.println(urlMapper.toEntity(urlCreation));
        //urlExpected.setShortenUrl("bitl/.com");
        //assertNotNull(urlEncoder.encode(urlCreation.getUrlToCreate()), "urlEncoder.encode(urlCreation.getUrlToCreate()) is returning null");
        //assertNotNull(urlMapper.toEntity(urlCreation), "UrlMapper.toEntity() is returning null");
        //assertNotNull(urlMapper.toEntity(urlCreation), "UrlMapper.toEntity() is returning null");


//        Mockito.when(url.s)

        //When
        UrlDTO urlDTOResult = urlService.createUrl(urlCreation);

        //Then
       assertEquals(urlDTOResult.getOriginalUrl(), urlDTOExpected.getOriginalUrl());
       assertEquals(urlDTOResult.getShortenUrl(), urlDTOExpected.getShortenUrl());

        Mockito.verify(urlMapper, Mockito.times(1)).toEntity(Mockito.eq(urlCreation));
        Mockito.verify(urlEncoder, Mockito.times(1)).encode(urlCreation.getUrlToCreate());
        //Mockito.verify(urlMapper, Mockito.times(1)).toDTO(Mockito.eq(urlExpected));

    }



    @Test
    void getUrl() {
        //assertEquals(1, 1);
    }

    @Test
    void createUrl() {
    }
}