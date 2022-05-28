package com.example.wineshop.exceptions;

public class TypeNotFoundException extends RuntimeException {

    public TypeNotFoundException(int id) {
        super("Could not find type " + id);
    }
}
