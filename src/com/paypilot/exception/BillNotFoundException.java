package com.paypilot.exception;

public class BillNotFoundException extends Exception {
    public BillNotFoundException(String message) {
        super(message);
    }
}