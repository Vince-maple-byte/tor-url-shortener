package com.vince.tor_url_shortener.controller;

import com.vince.tor_url_shortener.dto.UrlCreation;
import com.vince.tor_url_shortener.dto.UrlDTO;
import com.vince.tor_url_shortener.exception.UrlNotFoundException;
import com.vince.tor_url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.Optional;

//Class that handles incoming http requests from the nginx server
@RestController("url")
public class UrlController {

    private final UrlService urlService;
    private final RedisCaching redisCaching;

    //Dependency injection the beans for UrlService and RedisCaching
    @Autowired
    public UrlController(UrlService urlService, RedisCaching redisCaching){
        this.urlService = urlService;
        this.redisCaching = redisCaching;
    }

    //The request to get an original url from the shortened one provided
    @GetMapping("/{shortenUrl}")
    public RedirectView redirectToNewUrl(@PathVariable("shortenUrl") String shortenUrl){
        UrlDTO redirectUrl = null;
        Optional<String> cache = redisCaching.getCache(shortenUrl);
        redirectUrl = cache.map(s -> new UrlDTO(s, shortenUrl))
                .orElseGet(() -> urlService.getUrl(shortenUrl));

        RedirectView redirectView = new RedirectView();
        /*NOTE: Might be able to clean up this code better by having the UrlNotFoundException be called in the urlService class
        instead of the controller since we don't know that in the future another controller might need to handle that exception.
        Doing this would remove redundancy and potentially security faults because this would remove the need for each new
        controller to having to explicitly now that they have to deal with the error.


         */
        if(redirectUrl != null && cache.isPresent()){
            redirectView.setUrl(redirectUrl.getOriginalUrl());
        }
        else if(redirectUrl != null){
            redirectView.setUrl(redirectUrl.getOriginalUrl());
            redisCaching.setCache(shortenUrl, redirectUrl.getOriginalUrl());
        }
        else {
            //This is just in case the url does not exist
            //After throwing this exception the Global Exception Handler is going to send a 400 based error code

            throw new UrlNotFoundException("This url has not been found");
        }
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;


    }

    //TODO:Create the home page with thymeleaf
     @GetMapping("/")
     public String homePage(Model model) {
         return "home";
     }

    //The post request to handle creating a new url to be shortened
    //UrlCreation is used to capture the Json object being sent and converting it to a java object for us to use

    //We needed to change @RequestBody to @RequestParam because @RequestBody can't handle application/x-www-form-urlencoded
    //Since RequestParam handles query parameters and form data while RequestBody handles the entire request body (JSON, XML)
    //TODO:Create a web page to give the user the new url to redirect to
    @PostMapping("/")
    public ResponseEntity<UrlDTO> createNewUrl(@RequestParam Map<String, String> originalUrl ) {
        UrlCreation urlCreation = new UrlCreation(originalUrl.get("originalUrl"));
        UrlDTO response = urlService.createUrl(urlCreation);
        redisCaching.setCache(response.getShortenUrl(), response.getOriginalUrl());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // @PostMapping("/delete") 
    // public ResponseEntity<String> deleteUrl(@RequestParam Map<String, String> url) {

    // }
}
