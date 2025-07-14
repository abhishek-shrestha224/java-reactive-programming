package org.example.reactive;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class MyFlux {

  Flux<String> getEmptyFlux() {
    return Flux.empty();
  }

  Flux<String> getFluxFromValues(String... values) {
    return Flux.just(values);
  }

  Flux<String> getFluxFromIterable(Iterable<String> iterable) {
    return Flux.fromIterable(iterable);
  }

  Flux<String> errorFlux() {
    return Flux.error(new IllegalStateException("Something went wrong!"));
  }

  Flux<Long> counter() {
    return Flux.interval(Duration.ofMillis(100)).take(10);
  }
}
