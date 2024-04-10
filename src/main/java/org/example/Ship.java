package org.example;

public abstract class Ship {
    private int shipLength;
    private Coordinates coordinates;

    public Ship(int shipLength, Coordinates coordinates) {
        this.shipLength = shipLength;
        this.coordinates = coordinates;
    }

    public int getShipLength() {
        return shipLength;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
