package org.example.reactive;

public class Message {
  private Message() {
    throw new IllegalStateException("Utility class");
  }

  static final String RECEIVED = "Received: ";
  static final String ERROR = "Error: ";
  static final String COMPLETED = "Completed";
  static final String ERROR_OCCURRED = "An error occurred: ";
}
