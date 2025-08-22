package com.vince.tor_url_shortener.config.Caching;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("test")
public class NoOpRedisCaching implements Caching {


    public NoOpRedisCaching(RedisTemplate<String, String> redisTemplate) {}

    public NoOpRedisCaching() {
    }

    @Override
    public Optional<String> getCache(String shortenedUrl) {
        return Optional.empty();
    }

    @Override
    public void setCache(String shortenedUrl, String originalUrl) {
    }

}
