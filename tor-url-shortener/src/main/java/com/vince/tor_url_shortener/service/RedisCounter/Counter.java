package com.vince.tor_url_shortener.service.RedisCounter;

public interface Counter {
    public long getCounter();
    public long getCounterAndIncrement();
}
