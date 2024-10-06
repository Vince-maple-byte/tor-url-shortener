package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;
import com.vince.tor_url_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

public interface UrlService {

    public UrlDTO getUrl(String shortenUrl);
    public UrlDTO createUrl(UrlCreation originalUrl);


}
