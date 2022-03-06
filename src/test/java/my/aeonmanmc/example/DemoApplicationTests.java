package my.aeonmanmc.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class DemoApplicationTests {

  @Test
  void monoTest() {
    Mono<String> stringMono = Mono.just("Hello Mono").log();
    stringMono.subscribe(System.out::println);
  }

  @Test
  void fluxTest() {
    Flux<Integer> intFlux = Flux.just(1, 2).log();
    intFlux.subscribe(System.out::println);
  }

}
