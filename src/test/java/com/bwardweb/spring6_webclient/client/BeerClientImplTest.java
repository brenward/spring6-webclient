package com.bwardweb.spring6_webclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}