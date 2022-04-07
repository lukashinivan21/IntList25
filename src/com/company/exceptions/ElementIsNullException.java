package com.company.exceptions;

public class ElementIsNullException extends RuntimeException {

    public ElementIsNullException() {

    }

    public ElementIsNullException(String message) {
        super(message);
    }
}
