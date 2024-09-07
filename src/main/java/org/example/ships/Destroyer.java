package org.example.ships;

import org.example.Coordinates;

public class Destroyer extends Ship {
    private static final int SHIP_LENGTH = 2;

    public Destroyer(Coordinates coordinates) {
        super(SHIP_LENGTH, coordinates);
    }
}
