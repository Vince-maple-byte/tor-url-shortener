package com.vince.tor_url_shortener.controller;

import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;
import com.vince.tor_url_shortener.exception.UrlNotFoundException;
import com.vince.tor_url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController("url")
public class UrlController {

    //TODO: Create caching for database retrieval querying in the get methods using redisTemplate. Make sure to use a DTO with this??

    private final UrlService urlService;
    private final RedisCaching redisCaching;

    @Autowired
    public UrlController(UrlService urlService, RedisCaching redisCaching){
        this.urlService = urlService;
        this.redisCaching = redisCaching;
    }

    //The request to get an original url from the shortened one provided
    @GetMapping("/{shortenUrl}")
    public RedirectView redirectToNewUrl(@PathVariable("shortenUrl") String shortenUrl){
        UrlDTO redirectUrl= null;
        Optional<String> cache = redisCaching.getCache(shortenUrl);
        if(cache.isPresent()){
            redirectUrl = new UrlDTO(cache.get(), shortenUrl );
        }
        else {
            redirectUrl = urlService.getUrl(shortenUrl);
        }

        RedirectView redirectView = new RedirectView();
        if(redirectUrl != null && cache.isPresent()){
            redirectView.setUrl(redirectUrl.getOriginalUrl());
        }
        else if(redirectUrl != null){
            redirectView.setUrl(redirectUrl.getOriginalUrl());
            redisCaching.setCache(shortenUrl, redirectUrl.getOriginalUrl());
        }
        else {
            //This is just in case the url does not exist if anything I will change this to a 404 message
            throw new UrlNotFoundException("This url has not been found");
        }
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;


    }

    //This was supposed to send the html home page to the client, but I don't need this since I have nginx doing that
    @GetMapping("/")
    public String homePage() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                	<head>
                		<meta charset="UTF-8" />
                		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
                		<title>Document</title>
                	</head>
                	<body>
                		<h1>Hello world</h1>
                	</body>
                </html>
                                
                                
                """;
    }

    //The post request to handle creating a new url to be shortened
    //UrlCreation is used to capture the Json object being sent and converting it to a java object for us to use
    
    @PostMapping("/")
    public ResponseEntity<UrlDTO> createNewUrl(@RequestBody UrlCreation urlCreation) {
        UrlDTO response = urlService.createUrl(urlCreation);
        redisCaching.setCache(response.getShortenUrl(), response.getOriginalUrl());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
