package org.example;

public class Cruiser extends Ship {
    private static final int SHIP_LENGTH = 3;

    public Cruiser(Coordinates coordinates) {
        super(SHIP_LENGTH, coordinates);
    }
}
