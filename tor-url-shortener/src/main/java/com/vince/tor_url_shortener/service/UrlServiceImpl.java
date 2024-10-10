package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.domain.Url;
import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;
import com.vince.tor_url_shortener.dto.UrlMapper;
import com.vince.tor_url_shortener.exception.UrlNotFoundException;
import com.vince.tor_url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    // TODO: Make tests for the UrlServiceImpl class

    private final UrlRepository urlRepository;
    private final UrlEncoder urlEncoder;

    private final UrlDecoder urlDecoder;

    private final UrlMapper urlMapper;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, UrlEncoder urlEncoder, UrlDecoder urlDecoder, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.urlEncoder = urlEncoder;
        this.urlDecoder = urlDecoder;
        this.urlMapper = urlMapper;
    }

    @Override
    public UrlDTO getUrl(String shortenUrl) {
        //We find the url and return the UrlDTO to the controller
        Optional<Url> urlOptional = urlRepository.findById(shortenUrl);
        if(urlOptional.isPresent()) return urlMapper.toDTO(urlOptional.get());
        else throw new UrlNotFoundException("Not a valid shorten url: " + shortenUrl );
    }

    @Override
    public UrlDTO createUrl(UrlCreation urlToCreate) {

        //Get the shortened url from encoder
        String shortenedUrl = urlEncoder.encode(urlToCreate.getUrlToCreate());
        System.out.println(shortenedUrl);

        //Create the Url object
        Url urlCreated = urlMapper.toEntity(urlToCreate);
        System.out.println(urlCreated);

        //Add the shortened url to urlCreated
        urlCreated.setShortenUrl(shortenedUrl);

        //Save the urlCreated to the database
        urlRepository.save(urlCreated);

        //Make the UrlDTO
        return urlMapper.toDTO(urlCreated);
    }
}
