package com.vince.tor_url_shortener.dto;

public class UrlCreation {
    private String urlToCreate;

    public UrlCreation(){
    }

    public UrlCreation(String urlToCreate){
        this.urlToCreate = urlToCreate;
    }

    public String getUrlToCreate() {
        return urlToCreate;
    }

    public void setUrlToCreate(String urlToCreate) {
        this.urlToCreate = urlToCreate;
    }
}
