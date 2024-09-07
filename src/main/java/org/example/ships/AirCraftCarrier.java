package org.example.ships;

import org.example.Coordinates;

public class AirCraftCarrier extends Ship {
    private static final int SHIP_Length = 5;

    public AirCraftCarrier(Coordinates coordinates) {
        super(SHIP_Length, coordinates);
    }

}
