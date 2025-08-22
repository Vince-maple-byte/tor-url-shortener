package com.vince.tor_url_shortener.config.Caching;

import java.util.Optional;

public interface Caching {
    public Optional<String> getCache(String shortenedUrl);
    public void setCache(String shortenedUrl, String originalUrl);
}
