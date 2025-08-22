package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.service.RedisCounter.Counter;
import com.vince.tor_url_shortener.service.RedisCounter.RedisCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//This is where we encode the url
@Service
public class UrlEncoderImpl implements UrlEncoder{
    private final Base62 base62;

    //We accomplish the redis counter distribution in the RedisCounter class
    private final Counter redisCounter;

    @Autowired
    public UrlEncoderImpl(Base62 base62, Counter redisCounter){
        this.base62 = base62;
        this.redisCounter = redisCounter;
    }

    //This makes the encoded base62 string and sends it back to the user so that they know
    //Which one is going to be stored in the database.
    //Just need to save this in a database and the original url in the database so that we
    //can link them together.
    @Override
    public String encode(String url) {
        StringBuilder encodedUrl = new StringBuilder();
        //We are going to do the base 10 to base 62 conversion here
        long i = redisCounter.getCounterAndIncrement();
        while (i > 0){
            int decimalVal = (int) i % 62;
            encodedUrl.append(base62.base62Values()[decimalVal]);
            i = i / 62;
        }
        return encodedUrl.reverse().toString();
    }


}
