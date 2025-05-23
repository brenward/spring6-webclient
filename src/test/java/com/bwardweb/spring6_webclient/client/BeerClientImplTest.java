package com.bwardweb.spring6_webclient.client;

import com.bwardweb.spring6_webclient.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.awaitility.Awaitility.await;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient client;

    @Test
    void listBeer(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeers().subscribe(response -> {
           System.out.println(response);
           atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetMap(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerJson(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerDto(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersDTO().subscribe(beerDto -> {
            System.out.println(beerDto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerById(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersDTO().flatMap(dto -> client.getBeerById(dto.getId()))
                .subscribe(beerDto -> {
                    System.out.println(beerDto.getBeerName());
                    atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByBeerStyle(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.getBeerByBeerStyle("Lager")
                .subscribe(beerDto -> {
                    System.out.println(beerDto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByBeerStyle_NotFound(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.getBeerByBeerStyle("XXX")
                .subscribe(beerDto -> {
                    System.out.println(beerDto.toString());
                    atomicBoolean.set(true);
                },error -> {
                    atomicBoolean.set(true);
                    throw new RuntimeException("My Error1: " + error.getMessage());
                });

        await().untilTrue(atomicBoolean);
    }
}