package com.vince.tor_url_shortener.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Url")
public class Url {

    private String originalUrl;

    @Id
    @Column(nullable = false)
    private String shortenUrl;

    public Url() {
    }

    public Url(String originalUrl, String shortenUrl){
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
    }

    public Url(Builder builder){
        this.shortenUrl = builder.shortenUrl;
        this.originalUrl = builder.originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    //This is how we make a builder for our java classes by hand
    public static class Builder{
        private String originalUrl;
        private String shortenUrl;

        public Builder setOriginalUrl(String originalUrl){
            this.originalUrl = originalUrl;
            return this;
        }

        public Builder setShortenUrl(String shortenUrl){
            this.shortenUrl = shortenUrl;
            return this;
        }

        //We can force some variable that need to have a value like this
        public Url build() {
            if(shortenUrl == null){
                throw new IllegalStateException("Invalid. Did not have a shortened url");
            }
            return new Url(this);
        }
    }

}
