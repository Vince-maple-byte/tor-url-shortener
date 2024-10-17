package com.vince.tor_url_shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

public class RedisCaching {

    private RedisTemplate<String, String> redisTemplate;

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

    public Optional<String> getCache(String shortenedUrl){
        Optional<String> shortenUrlCache = Optional.ofNullable("redisTemplate");

        return null;
    }

    public void setCache(String shortenedUrl) {

    }
}
