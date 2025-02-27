package com.vince.tor_url_shortener.domain;

import jakarta.persistence.*;

@Entity
@Table(name="url")
public class Url {

    @Column(name = "originalurl")
    private String originalUrl;

    @Id
    @Column(name = "shortenurl", nullable = false)
    private String shortenUrl;

    public Url() {
    }

    public Url(String originalUrl, String shortenUrl){
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
    }

    //This is just to create an java object with the builder pattern
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

            if (originalUrl == null){
                throw new IllegalStateException("Invalid. Did not have an original url");
            }
            return new Url(this);
        }
    }

}
