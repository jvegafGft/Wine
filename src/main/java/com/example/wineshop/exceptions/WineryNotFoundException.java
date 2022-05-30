package com.example.wineshop.exceptions;

public class WineryNotFoundException extends RuntimeException {

    public WineryNotFoundException(int id) {
        super("Could not find winery " + id);
    }
}
