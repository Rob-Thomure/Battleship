package org.example;

public class Destroyer extends Ship {
    private static final int SHIP_LENGTH = 2;

    public Destroyer(Coordinates coordinates) {
        super(SHIP_LENGTH, coordinates);
    }
}
