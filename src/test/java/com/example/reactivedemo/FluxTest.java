package com.example.reactivedemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    String MSG_ERR = "Mock exp";

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("A", "B", "C")
                //.concatWith(Flux.error(new RuntimeException(MSG_ERR)))
                .concatWith(Flux.just("D"))
                .log();
        stringFlux.subscribe(System.out::println,
                System.err::println,
                () -> System.out.println("Completed !!!"));
    }

    @Test
    public void fluxTest_WithoutError() {
        Flux<String> stringFlux = Flux.just("A", "B");

        StepVerifier.create(stringFlux)
                //.expectNext("A")
                //.expectNext("B")
                .expectNext("A", "B")
                //.expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void fluxTest_WithError() {

        Flux<String> stringFlux = Flux.just("A", "B")
                .concatWith(Flux.error(new RuntimeException(MSG_ERR)));

        StepVerifier.create(stringFlux)
                .expectNext("A")
                .expectNext("B")
                //.expectError(RuntimeException.class)
                .expectErrorMessage(MSG_ERR)
                .verify();
    }
}
