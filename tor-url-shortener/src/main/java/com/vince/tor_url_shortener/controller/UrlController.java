package com.vince.tor_url_shortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController("url")
public class UrlController {

    @GetMapping("/{url}")
    public RedirectView redirectToNewUrl(@PathVariable("url") String url){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://google.com/"+url);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
