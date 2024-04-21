package org.example;

public class IllegalShipLengthException extends RuntimeException {
    public IllegalShipLengthException() {
        super("Error! Wrong length");
    }
}
