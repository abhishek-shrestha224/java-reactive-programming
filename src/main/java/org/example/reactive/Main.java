package org.example.reactive;

import java.util.logging.Logger;
import reactor.core.publisher.Flux;

public class Main {
  private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public static void main(String[] args) throws InterruptedException {
    MyFlux fluxCreator = new MyFlux();
    Flux<Long> flux;

    //    flux = fluxCreator.getEmptyFlux();
    //    flux = fluxCreator.getFluxFromValues("Hello", "World", "from", "Reactor");
    //    flux = fluxCreator.getFluxFromIterable(Arrays.asList("Hello", "World", "from",
    // "Reactor"));
    flux = fluxCreator.counter();
    flux.subscribe(
        i -> logger.info("onNext Triggered: " + i),
        error -> logger.severe("onError Triggered: " + error.getMessage()),
        () -> logger.info("onComplete Triggered"));

    Thread.sleep(2000);
  }
}
