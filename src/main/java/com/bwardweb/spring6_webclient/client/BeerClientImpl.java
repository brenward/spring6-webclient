package com.bwardweb.spring6_webclient.client;

import com.bwardweb.spring6_webclient.model.BeerDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {
    public static final String BEER_PATH = "/api/v3/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    private final WebClient webClient;

    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Flux<String> listBeers() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(String.class);
    }

    @Override
    public Flux<Map> listBeersMap() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(Map.class);
    }

    @Override
    public Flux<JsonNode> listBeersJsonNode() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<BeerDTO> listBeersDTO() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(BeerDTO.class);
    }

    @Override
    public Mono<BeerDTO> getBeerById(String beerId) {
        return webClient.get().uri(uriBuilder ->
            uriBuilder.path(BEER_PATH_ID).build(beerId)
        ).retrieve().bodyToMono(BeerDTO.class);
    }

    @Override
    public Flux<BeerDTO> getBeerByBeerStyle(String beerStyle) {
        return webClient.get().uri(uriBuilder ->
                uriBuilder.path(BEER_PATH).queryParam("beerStyle", beerStyle).build()
        ).exchangeToFlux(clientResponse -> {
            if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new RuntimeException("Beer style not found");
            } else {
                return clientResponse.bodyToFlux(BeerDTO.class);
            }
        });
    }

}
