package com.tpfinal.stockmanager.exceptions;

public class entityAlreadyExists extends RuntimeException {
    public entityAlreadyExists(String message) {
        super(message);
    }
}
