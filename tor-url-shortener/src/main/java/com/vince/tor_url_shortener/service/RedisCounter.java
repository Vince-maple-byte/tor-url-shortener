package com.vince.tor_url_shortener.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisCounter {

    //RedisTemplate is an abstraction to Jedis/Lettuce
    //allows for lower level api to make direct manual redis operations while @Cacheable is an abstraction to allow for automatic caching for expensive/slow operations
    private final RedisTemplate<String, Long> redisTemplate;

    @Autowired
    public RedisCounter(RedisTemplate<String, Long> redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    public long getCounter(){
        Optional<Long> optional = Optional.ofNullable(redisTemplate.opsForValue().get("URL_COUNTER"));
        if(optional.isPresent()) return optional.get();
        else return -1;
    }

    public long getCounterAndIncrement(){
        long prevCounter = getCounter();
        redisTemplate.opsForValue().increment("URL_COUNTER");
        return prevCounter;
    }


}
