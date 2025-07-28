package com.paypilot.exception;

public class InvalidPaymentException extends RuntimeException {
  public InvalidPaymentException(String message) {
    super(message);
  }
}
