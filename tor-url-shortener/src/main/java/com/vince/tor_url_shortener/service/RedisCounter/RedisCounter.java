package com.vince.tor_url_shortener.service.RedisCounter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("!test")
public class RedisCounter implements Counter{

    //RedisTemplate is an abstraction to Jedis/Lettuce
    //allows for lower level api to make direct manual redis operations while @Cacheable is an abstraction to allow for automatic caching for expensive/slow operations
    private final RedisTemplate<String, Long> redisTemplateCounter;

    @Autowired
    public RedisCounter(RedisTemplate<String, Long> redisTemplateCounter){
        this.redisTemplateCounter = redisTemplateCounter;
    }


    public long getCounter(){
        Optional<Long> optional = Optional.ofNullable(redisTemplateCounter.opsForValue().get("URL_COUNTER"));
        if(optional.isPresent()) return optional.get();
        else {
            redisTemplateCounter.opsForValue().set("URL_COUNTER",1L);
            return 1L;
        }
    }

    public long getCounterAndIncrement(){
        long prevCounter = getCounter();
        redisTemplateCounter.opsForValue().increment("URL_COUNTER");
        return prevCounter;
    }


}
