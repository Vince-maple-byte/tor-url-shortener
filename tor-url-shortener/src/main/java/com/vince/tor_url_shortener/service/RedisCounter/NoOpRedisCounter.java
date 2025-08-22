package com.vince.tor_url_shortener.service.RedisCounter;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class NoOpRedisCounter implements Counter  {
    public NoOpRedisCounter(RedisTemplate<String, Long> redisTemplateCounter) {

    }

    public NoOpRedisCounter(){}

    @Override
    public long getCounter() {
        return 1L;
    }

    @Override
    public long getCounterAndIncrement() {
        return 1L;
    }
}
