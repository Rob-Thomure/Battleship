package org.example;

public abstract class Ship {
    private final int shipLength;
    private final Coordinates coordinates;

    public Ship(int shipLength, Coordinates coordinates) {
        if (shipLength != coordinates.getLength()) {
            throw new IllegalShipLength();
        }
        this.shipLength = shipLength;
        this.coordinates = coordinates;
    }

    public int getShipLength() {
        return shipLength;
    }

    public boolean isAdjacentToAnotherShip(Ship otherShip) {
        for (Coordinate coordinate : coordinates.getParts()) {
            for (Coordinate otherShipCoordinate : otherShip.getCoordinates().getParts()) {
                if (coordinate.isAdjacentToAnother(otherShipCoordinate)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}

class IllegalShipLength extends RuntimeException {
    public IllegalShipLength() {
        super("Error! Wrong length");
    }
}
