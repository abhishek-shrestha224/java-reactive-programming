package org.example.reactive;

import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class MyMono {
  private static final Logger logger = Logger.getLogger(MyMono.class.getName());

  public static void main(String[] args) {
    // Example usage of the Mono methods
    getEmptyMono()
        .subscribe(
            value -> logger.info(Message.RECEIVED + value),
            error -> logger.severe(Message.ERROR + error),
            () -> logger.info(Message.COMPLETED));

    getMonoFromValue("Hello, Mono!").subscribe(value -> logger.info(Message.RECEIVED + value));

    getErrorMono()
        .subscribe(
            value -> logger.info(Message.RECEIVED + value),
            error -> logger.severe(Message.ERROR + error));

    getSignallessMono()
        .subscribe(
            value -> logger.info(Message.RECEIVED + value),
            error -> logger.severe(Message.ERROR + error));
  }

  static Mono<String> getEmptyMono() {
    return Mono.empty();
  }

  static Mono<String> getMonoFromValue(String value) {
    return Mono.just(value);
  }

  static Mono<String> getErrorMono() {
    return Mono.error(new IllegalStateException(Message.ERROR_OCCURRED));
  }

  static Mono<String> getSignallessMono() {
    return Mono.never();
  }
}
