package com.bwardweb.spring6_webclient.client;

import com.bwardweb.spring6_webclient.model.BeerDTO;
import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {
    Flux<String> listBeers();

    Flux<Map> listBeersMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeersDTO();

    Mono<BeerDTO> getBeerById(String beerId);

    Flux<BeerDTO> getBeerByBeerStyle(String beerStyle);

    Mono<BeerDTO> createBeer(BeerDTO beerDto);

    Mono<BeerDTO> updateBeer(BeerDTO beerDto);

    Mono<BeerDTO> patchBeer(BeerDTO beerDto);

    Mono<Void> deleteBeer(String beerId);
}
