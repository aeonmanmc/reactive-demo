package com.example.reactivedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebFluxTest
class MainControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void flux_approach1(){
        Flux<Integer> integerFlux = webTestClient.get()
                .uri("/flux")
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
    void flux_approach2(){
        webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .hasSize(3);
    }

    @Test
    void flux_approach3() {
        List<Integer> expectedList = Arrays.asList(1,2,3);

        EntityExchangeResult<List<Integer>> result = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();

        assertEquals(expectedList, result.getResponseBody());
    }

    @Test
    void flux_approach4() {
        List<Integer> expectedList = Arrays.asList(1,2,3);

        webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith(response -> assertEquals(expectedList, response.getResponseBody()));
    }

    @Test
    void flux_approach5(){
        Flux<Integer> integerFlux = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    void mono_approach1(){
        webTestClient.get()
                .uri("/mono")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(response -> assertEquals(100, response.getResponseBody()));
    }
}