package com.example.reactivedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWebTestClient
class SampleHandlerTest {

    String MSG_ERR = "Mock exp";

    @Autowired
    WebTestClient webTestClient;

    @Test
    void flux_approach1(){
        Flux<Integer> integerFlux = webTestClient.get()
                .uri("/functional/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void mono_approach1(){
        webTestClient.get()
                .uri("/functional/mono")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(response -> assertEquals(100, response.getResponseBody()));
    }
}