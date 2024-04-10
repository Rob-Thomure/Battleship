package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Coordinates {
    private Coordinate coordinate1;
    private Coordinate coordinate2;

    public Coordinates(Coordinate coordinate1, Coordinate coordinate2) throws IllegalArgumentException {
        if (isDiagonal(coordinate1, coordinate2) || !coordinate1.isWithinBounds() || !coordinate2.isWithinBounds()) {
            throw new IllegalArgumentException("Error diagonal coordinates");
        }
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
    }

    private boolean isDiagonal(Coordinate coordinate1, Coordinate coordinate2) {
        boolean sameRow = coordinate1.getRow() == coordinate2.getRow();
        boolean sameColumn = coordinate1.getColumn() == coordinate2.getColumn();
        return !sameRow && !sameColumn;
    }

    public boolean isNotDiagonal() {
        boolean sameRow = coordinate1.getRow() == coordinate2.getRow();
        boolean sameColumn = coordinate1.getColumn() == coordinate2.getColumn();
        return sameRow ^ sameColumn;
    }

    public int getLength() {
        int rowLength = Math.abs(coordinate1.getRow() - coordinate2.getRow());
        int columnLength = Math.abs(coordinate1.getColumn() - coordinate2.getColumn());
        return rowLength == 0 ? columnLength + 1 : rowLength + 1;
    }

    public List<Coordinate> getParts() {
        List<Coordinate> coordinates;
        int rowLength = Math.abs(coordinate1.getRow() - coordinate2.getRow());
        int columnLength = Math.abs(coordinate1.getColumn() - coordinate2.getColumn());
        if (rowLength != 0) {
            coordinates = createCoordinatesInRow(rowLength);
        } else {
            coordinates = createCoordinatesInColumn(columnLength);
        }
        if (coordinate1.getRow() > coordinate2.getRow()
                || coordinate1.getColumn() > coordinate2.getColumn()) {
            Collections.reverse(coordinates);
        }
        return coordinates;
    }

    public String getPartsString() {
        return getParts().stream()
                .map(Coordinate::toString)
                .collect(Collectors.joining(" "));
    }

    private List<Coordinate> createCoordinatesInRow(int rowLength) {
        List<Coordinate> coordinates = new ArrayList<>();
        char startingRow = coordinate1.getRow() < coordinate2.getRow()
                ? coordinate1.getRow() : coordinate2.getRow();
        for (int i = 0; i <= rowLength; i++) {
            char row = (char) (startingRow + i);
            int column = coordinate1.getColumn();
            Coordinate coordinate = new Coordinate(row, column);
            coordinates.add(coordinate);
        }
        return coordinates;
    }

    private List<Coordinate> createCoordinatesInColumn(int columnLength) {
        List<Coordinate> coordinates = new ArrayList<>();
        int startingColumn = coordinate1.getColumn() < coordinate2.getColumn()
                ? coordinate1.getColumn() : coordinate2.getColumn();
        for (int i = 0; i <= columnLength; i++) {
            char row = coordinate1.getRow();
            int column = startingColumn + i;
            Coordinate coordinate = new Coordinate(row, column);
            coordinates.add(coordinate);
        }
        return coordinates;
    }

}