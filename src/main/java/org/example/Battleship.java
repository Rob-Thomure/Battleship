package org.example;

public class Battleship extends Ship {
    private static final int SHIP_Length = 4;

    public Battleship(Coordinates coordinates) {
        super(SHIP_Length, coordinates);
    }
}
