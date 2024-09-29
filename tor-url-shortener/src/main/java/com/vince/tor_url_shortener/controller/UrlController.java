package com.vince.tor_url_shortener.controller;

import com.vince.tor_url_shortener.domain.Url;
import com.vince.tor_url_shortener.service.UrlDecoderImpl;
import com.vince.tor_url_shortener.service.UrlEncoderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController("url")
public class UrlController {

    //TODO: Create caching for database retrieval querying in the get methods using @Cachable in redis

    private final UrlEncoderImpl urlEncoder;
    private final UrlDecoderImpl urlDecoder;

    @Autowired
    public UrlController(UrlEncoderImpl urlEncoder, UrlDecoderImpl urlDecoder){
        this.urlEncoder = urlEncoder;
        this.urlDecoder = urlDecoder;
    }

    //The request to get an original url from the shortened one provided
    @GetMapping("/{shortenUrl}")
    public RedirectView redirectToNewUrl(@PathVariable("shortenUrl") String shortenUrl){
        Optional<Url> existingUrl = urlDecoder.findExistingUrl(shortenUrl);
        RedirectView redirectView = new RedirectView();
        if(existingUrl.isPresent()){
            redirectView.setUrl(existingUrl.get().getOriginalUrl());
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return redirectView;
        }

        //This is just in case the url does not exist if anything I will change this to a 404 message
        redirectView.setUrl("https://google.com/"+shortenUrl);
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

    //The post request to handle creating an new url to be shortened
    //UrlCreation is used to capture the Json object being sent and converting it to a java object for us to use
    
    @PostMapping("/")
    public ResponseEntity<String> createNewUrl(@RequestBody UrlCreation urlCreation) {
        String response = urlEncoder.encode(urlCreation.getUrlToCreate());
        return ResponseEntity.ok(response);
    }
}
