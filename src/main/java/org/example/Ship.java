package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {
    private final int shipLength;
    private final Coordinates coordinates;
    private final List<Coordinate> coordinateList;
    private final List<Character> coordinateStatusList;

    public Ship(int shipLength, Coordinates coordinates) {
        if (shipLength != coordinates.getLength()) {
            throw new IllegalShipLengthException();
        }
        this.shipLength = shipLength;
        this.coordinates = coordinates;
        this.coordinateList = coordinates.getParts();
        this.coordinateStatusList = createCoordinateStatus(shipLength);
    }

    private List<Character> createCoordinateStatus(int shipLength) {
        List<Character> statuses = new ArrayList<>();
        for (int i = 0; i < shipLength; i++) {
            statuses.add('O');
        }
        return statuses;
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

    public boolean isSunk() {
        long hitCount = coordinateStatusList.stream()
                .filter(e -> e == 'X')
                .count();
        return hitCount == shipLength;
    }

    public void setCoordinateStatus(Coordinate coordinate) {
        for (int i = 0; i < coordinateList.size(); i++) {
            if (coordinateList.get(i).equals(coordinate)) {
                coordinateStatusList.set(i, 'X');
            }
        }
    }

    public boolean containsCoordinate(Coordinate coordinate) {
        return coordinateList.contains(coordinate);
    }
}

