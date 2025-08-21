package com.vince.tor_url_shortener.confg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ActiveProfiles("test") //This makes sure that this test case only runs in the test profile which doesn't include the redis config file
public class TestRedisConfig {

    @Bean
    @Primary
    public RedisTemplate<String, String> redisTemplate() {
        // Return a mock RedisTemplate for tests
        return Mockito.mock(RedisTemplate.class);
    }

    @Bean
    @Primary
    public RedisTemplate<String, Long> redisTemplateCounter() {
        // Return a mock RedisTemplate for tests
        return Mockito.mock(RedisTemplate.class);
    }
}