package com.trip.common.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("password incorrect");
    }
}
