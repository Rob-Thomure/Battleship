package org.example;

public class TooCloseToAnotherShipException extends RuntimeException {
    public TooCloseToAnotherShipException() {
        super("Error! You placed it too close to another one.");
    }
}
