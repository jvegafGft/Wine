package com.example.wineshop.exceptions;

public class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException(int id) {
        super("Could not find region " + id);
    }
}
