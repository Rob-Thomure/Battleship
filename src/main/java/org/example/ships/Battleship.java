package org.example.ships;

import org.example.Coordinates;

public class Battleship extends Ship {
    private static final int SHIP_Length = 4;

    public Battleship(Coordinates coordinates) {
        super(SHIP_Length, coordinates);
    }
}
