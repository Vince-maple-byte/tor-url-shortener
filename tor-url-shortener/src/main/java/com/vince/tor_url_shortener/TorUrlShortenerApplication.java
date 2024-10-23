package com.vince.tor_url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class TorUrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorUrlShortenerApplication.class, args);
	}

}
