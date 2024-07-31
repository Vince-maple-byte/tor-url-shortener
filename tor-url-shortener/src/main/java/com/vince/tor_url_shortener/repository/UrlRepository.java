package com.vince.tor_url_shortener.repository;

import com.vince.tor_url_shortener.domain.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<Url, String> {
}
