package com.hopik.task2.exception;

public class TextParseException extends RuntimeException {
    public TextParseException(String message) {
        super(message);
    }

    public TextParseException(String message, Throwable reason) {
        super(message, reason);
    }
}