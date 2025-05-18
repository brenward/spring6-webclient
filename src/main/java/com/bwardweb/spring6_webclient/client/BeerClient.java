package com.bwardweb.spring6_webclient.client;

import reactor.core.publisher.Flux;

public interface BeerClient {
    Flux<String> listBeers();
}
