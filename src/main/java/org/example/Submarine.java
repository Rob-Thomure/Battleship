package org.example;

public class Submarine extends Ship {
    private static final int SHIP_LENGTH = 3;

    public Submarine(Coordinates coordinates) {
        super(SHIP_LENGTH, coordinates);
    }
}
