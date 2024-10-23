package com.vince.tor_url_shortener.dto;

import com.vince.tor_url_shortener.domain.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {

    public UrlMapper(){}

    public UrlDTO toDTO (Url url) {
        return new UrlDTO(url.getOriginalUrl(), url.getShortenUrl());
    }

    public Url toEntity (UrlCreation urlCreation) {
        Url url = new Url();
        url.setOriginalUrl(urlCreation.getUrlToCreate());
        return url;
    }


}
