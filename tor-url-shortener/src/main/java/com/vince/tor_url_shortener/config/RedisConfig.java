package com.vince.tor_url_shortener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, Long> redisTemplateCounter(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());  // Serialize keys as strings
        template.setValueSerializer(new org.springframework.data.redis.serializer.GenericToStringSerializer<>(Long.class));  // Serialize values as Longs
        return template;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate (RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());  // Serialize keys as strings
        template.setValueSerializer(new org.springframework.data.redis.serializer.GenericToStringSerializer<>(String.class));  // Serialize values as Longs
        return template;
    }
}
