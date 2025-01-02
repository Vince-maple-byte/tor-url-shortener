package com.vince.tor_url_shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class RedisCaching {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisCaching(RedisTemplate<String,String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }



    public Optional<String> getCache(String shortenedUrl) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(shortenedUrl));
    }

    public void setCache(String shortenedUrl, String originalUrl) {
        //States that we want to save this cache for 5 minutes.
        long cacheDuration = 5L;
        Duration duration = Duration.ofMinutes(cacheDuration);
        
        //Set has three params that we can add String key, String value, and Duration time
        redisTemplate.opsForValue().set(shortenedUrl, originalUrl, duration);

    }
}
