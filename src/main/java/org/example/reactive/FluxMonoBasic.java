package org.example.reactive;

import java.util.logging.Logger;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class FluxMonoBasic {
  private static final Logger logger = Logger.getLogger(FluxMonoBasic.class.getName());

  public static void main(String[] args) {
    logger.info("Starting FluxMonoBasic...");
    consumeDemo();
  }

  static void consumeDemo() {
    Flux<Integer> ints = Flux.range(0, 6);

    // ! Subscribe to a Flux with no handlers
    logger.info("Subscribing with no handlers...");
    ints.subscribe();

    // ! Subscribe to a Flux with onNext Signal
    logger.info("Subscribing with onNext handler...");
    ints.subscribe(i -> logger.info(Message.RECEIVED + i));

    // ! Subscribe to a Flux with onError Signal
    logger.info("Subscribing with onError handler...");
    ints.subscribe(
        i -> {
          logger.info(Message.RECEIVED + i);
          if (5 == i) {
            throw new IllegalStateException("Error at 5");
          }
        },
        e -> logger.severe(Message.ERROR + e.getMessage()));
    // ! Subscribe to a Flux with onComplete Signal
    logger.info("Subscribing with onComplete handler...");
    ints.subscribe(
        i -> logger.info(Message.RECEIVED + i),
        e -> logger.severe(Message.ERROR + e.getMessage()),
        () -> logger.info(Message.COMPLETED));

    ints.subscribe(
        new BaseSubscriber<>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(1);
          }

          @Override
          protected void hookOnNext(Integer value) {
            logger.info(Message.RECEIVED + value);
            request(1);
          }

          @Override
          protected void hookOnError(Throwable throwable) {
            logger.severe(Message.ERROR + throwable.getMessage());
          }

          @Override
          protected void hookOnComplete() {
            logger.info(Message.COMPLETED);
          }
        });
  }
}
