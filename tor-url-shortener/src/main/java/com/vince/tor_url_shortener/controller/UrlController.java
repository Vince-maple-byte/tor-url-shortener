package com.vince.tor_url_shortener.controller;

import com.vince.tor_url_shortener.service.UrlEncoderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController("url")
public class UrlController {

    private final UrlEncoderImpl urlEncoder;

    @Autowired
    public UrlController(UrlEncoderImpl urlEncoder){
        this.urlEncoder = urlEncoder;
    }

    @GetMapping("/{url}")
    public RedirectView redirectToNewUrl(@PathVariable("url") String url){


        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://google.com/"+url);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    @PostMapping("/")
    public ResponseEntity<String> createNewUrl(@RequestBody UrlCreation urlCreation) {
        String response = urlEncoder.encode(urlCreation.getUrlToCreate());
        return ResponseEntity.ok(response);
    }
}
