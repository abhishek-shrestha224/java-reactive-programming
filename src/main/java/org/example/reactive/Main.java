package org.example;

import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class Main {
  private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public static void main(String[] args) {
    final Flux<String> flux = Flux.just("Hello", "World");

    flux.subscribe(
        logger::info,
        error -> logger.severe("onError Triggered: " + error.getMessage()),
        () -> logger.info("onComplete Triggered"));
  }
}
