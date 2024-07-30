package com.vince.tor_url_shortener.service;

import org.springframework.beans.factory.annotation.Autowired;

//This is where we encode the url
public class UrlEncoderImpl implements UrlEncoder{
    private final base62 base62;
    public static long COUNTER = 1_000_000;

    @Autowired
    public UrlEncoderImpl(base62 base62){
        this.base62 = base62;
    }
    @Override
    public String encode(String url) {
        StringBuilder encodedUrl = new StringBuilder();
        //We are going to do the base 10 to base 62 conversion here
        long i = COUNTER;
        while (i > 0){
            int r = (int) i % 62;
            encodedUrl.append(base62.base62Values()[r]);
            i = i / 62;
        }
        COUNTER++;
        return encodedUrl.reverse().toString();
    }
}
