package com.Bank_EffectiveMobile.Bank_service.exception;

public class BadRequestParametersException extends RuntimeException{
    public BadRequestParametersException(String message) {
        super(message);
    }
}
