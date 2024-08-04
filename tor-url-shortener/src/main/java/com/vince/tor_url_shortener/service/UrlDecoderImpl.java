package com.vince.tor_url_shortener.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.vince.tor_url_shortener.domain.Url;
import com.vince.tor_url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Optional;

public class UrlDecoderImpl implements UrlDecoder{

    /* TODO: Make this method so that it retrieves the url in the database
        TODO: Make separate method to convert the base62 encoding into decimal

     */

    private final UrlRepository urlRepository;
    private final Base62 base62;

    @Autowired
    public UrlDecoderImpl(UrlRepository urlRepository, Base62 base62){
        this.urlRepository = urlRepository;
        this.base62 = base62;
    }

    @Override
    public long decode(String urlToDecode) {
        int exponentTracker = urlToDecode.length()-1;
        long result = 0;

        for(int i = 0; i < urlToDecode.length()-1; i++){
            //result += ;
        }

        return result;
    }

    public int numericalValueOfBase62Character(String base62Letter){
        char base62Char = base62Letter.charAt(0);
        if(Character.isLetter(base62Char) && Character.isLowerCase(base62Char)){
            //Range for lowercase alphabet characters are 10 - 35
            int low = 10;
            int high = 35;

            while(low <= high){
                int mid = (low + high) / 2;

                if(base62.base62Values()[mid] == base62Char){
                    return mid;
                }
                else if(base62.base62Values()[mid] < base62Char){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }
        else if(Character.isLetter(base62Char)){
            //Range for uppercase alphabet characters are 36 - 61
            int low = 36;
            int high = 61;

            while(low <= high){
                int mid = (low + high) / 2;

                if(base62.base62Values()[mid] == base62Char){
                    return mid;
                }
                else if(base62.base62Values()[mid] < base62Char){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }
        else if(Character.isDigit(base62Char)){
            //Range for number characters are 0 - 9
            int low = 0;
            int high = 9;

            while(low <= high){
                int mid = (low + high) / 2;

                if(base62.base62Values()[mid] == base62Char){
                    return mid;
                }
                else if(base62.base62Values()[mid] < base62Char){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    @Override
    public Optional<Url> findExistingUrl(String url) {
        return urlRepository.findById(url);
    }


}
