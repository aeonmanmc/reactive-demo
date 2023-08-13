package com.example.reactivedemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    String MSG_ERR = "Mock exp";

    @Test
    public void monoTest(){
        Mono<String> stringMono = Mono.just("A")
                .log();

        StepVerifier.create(stringMono)
                .expectNext("A")
                .verifyComplete();
    }

    @Test
    public void monoTest_Error(){
        StepVerifier.create(Mono.error(new RuntimeException(MSG_ERR)).log())
                .expectError()
                .verify();
    }
}
