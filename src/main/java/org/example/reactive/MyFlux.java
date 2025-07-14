package org.example.reactive;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class MyFlux {

  private static final Logger logger = Logger.getLogger(MyFlux.class.getName());

  public static void main(String[] args) throws InterruptedException {
    MyFlux myFlux = new MyFlux();
    myFlux.getEmptyFlux().subscribe(value -> logger.info(String.valueOf(value)));
    myFlux.getFluxFromValues("A", "B", "C").subscribe(logger::info);
    myFlux.getFluxFromIterable(List.of("X", "Y", "Z")).subscribe(logger::info);
    myFlux
        .errorFlux()
        .subscribe(
            value -> logger.info(String.valueOf(value)),
            error -> logger.severe("Error: " + error.getMessage()));
    myFlux.counter().subscribe(value -> logger.info(String.valueOf(value)));
    Thread.sleep(2000);
  }

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
