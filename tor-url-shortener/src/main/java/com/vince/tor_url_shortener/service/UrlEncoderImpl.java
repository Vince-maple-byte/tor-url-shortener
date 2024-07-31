package com.vince.tor_url_shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//This is where we encode the url
@Service
public class UrlEncoderImpl implements UrlEncoder{
    private final base62 base62;

    //We are going to manage the value of COUNTER using redis to manage the value of counter
    //throughout all of our nodes, and then we are just going to pull the value of Counter from
    //There and use incr in redis
    public static long COUNTER = 1_000_000;

    @Autowired
    public UrlEncoderImpl(base62 base62){
        this.base62 = base62;
    }

    //This makes the encoded base62 string and sends it back to the user so that they know
    //Which one is going to be stored in the database.
    //Just need to save this in a database and the original url in the database so that we
    //can link them together.
    @Override
    public String encode(String url) {
        StringBuilder encodedUrl = new StringBuilder();
        //We are going to do the base 10 to base 62 conversion here
        long i = COUNTER;
        while (i > 0){
            int decimalVal = (int) i % 62;
            encodedUrl.append(base62.base62Values()[decimalVal]);
            i = i / 62;
        }
        COUNTER++;
        return encodedUrl.reverse().toString();
    }
}
