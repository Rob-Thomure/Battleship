package org.example.ships;

import org.example.Coordinates;

public class Submarine extends Ship {
    private static final int SHIP_LENGTH = 3;

    public Submarine(Coordinates coordinates) {
        super(SHIP_LENGTH, coordinates);
    }
}
