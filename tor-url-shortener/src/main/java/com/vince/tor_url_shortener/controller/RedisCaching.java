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

    /*TODO: Create the get and set methods for the caching here is a link talking about the hash map data structure in redis
        https://redis.io/docs/latest/commands/hset/
        https://redis.io/docs/latest/develop/data-types/hashes/
        Nevermind the hash data structure is overkill since it mostly is used when pertaining with multiple key value pairs that you want to store in one place
        like a hash map object.
     */

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
