package com.example.infrastructure.exception;


public abstract class InfrastructureException extends RuntimeException {

    protected InfrastructureException(String message) {
        super(message);
    }

}
