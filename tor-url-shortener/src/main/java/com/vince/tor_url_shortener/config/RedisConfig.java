package com.vince.tor_url_shortener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    //TODO: FIX THE REDIS CONNECTION TO WORK FOR DOCKER
//    @Value("${spring.data.redis.host}")
//    private String hostName;
//
//    @Value("${spring.data.redis.port}")
//    private int port;
//
//
//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        // ! You have to provide the redisStandaloneConfiguration or else the app wont
//        // ! work with docker
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(hostName);
//        redisStandaloneConfiguration.setPort(port);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }

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
