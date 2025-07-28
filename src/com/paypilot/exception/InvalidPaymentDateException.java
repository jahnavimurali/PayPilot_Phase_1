package com.paypilot.exception;

public class InvalidPaymentDateException extends Exception {
    public InvalidPaymentDateException(String message) {
        super(message);
    }
}
