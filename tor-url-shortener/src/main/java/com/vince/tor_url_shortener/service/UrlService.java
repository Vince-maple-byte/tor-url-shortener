package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;

public interface UrlService {

    public UrlDTO getUrl(String shortenUrl);
    public UrlDTO createUrl(UrlCreation originalUrl);


}
