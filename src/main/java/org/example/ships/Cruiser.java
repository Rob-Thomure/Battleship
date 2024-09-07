package org.example.ships;

import org.example.Coordinates;

public class Cruiser extends Ship {
    private static final int SHIP_LENGTH = 3;

    public Cruiser(Coordinates coordinates) {
        super(SHIP_LENGTH, coordinates);
    }
}
