package com.vince.tor_url_shortener.service;

import com.vince.tor_url_shortener.domain.Url;

import java.util.Optional;

/*The Decoding doesn't need to convert the base62 string into a decimal value
We just need to check if the base62 string is saved in the database,
And if it does we just need to give back the original website url string.

 */
public interface UrlDecoder {

    public long decode(String url);
}
