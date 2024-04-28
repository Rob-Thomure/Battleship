package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameField {
    List<List<Character>> fieldPositions;
    List<Ship> ships;

    public GameField() {
        createFieldPositions();
        this.ships = new ArrayList<>();
    }

    private void createFieldPositions() {
        fieldPositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add('~');
            }
            fieldPositions.add(row);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder("  1 2 3 4 5 6 7 8 9 10\n");
        List<Character> rowNames = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
        for (int i = 0; i < 10; i++) {
            String rowString = fieldPositions.get(i).stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));
            stringBuffer.append(rowNames.get(i))
                    .append(" ")
                    .append(rowString)
                    .append("\n");
        }
        return stringBuffer.toString();
    }

    public void addShip(Ship ship) throws IllegalArgumentException {
        for (Ship existingShip : ships) {
            if (existingShip.isAdjacentToAnotherShip(ship)) {
                throw new TooCloseToAnotherShipException();
            }
        }
        List<Coordinate> coordinates = ship.getCoordinates().getParts();
        coordinates.forEach(coordinate -> {
            int rowIndex = convertRowToIndex(coordinate);
            int colIndex = convertColToIndex(coordinate);
            fieldPositions.get(rowIndex).set(colIndex, 'O');
        });
        ships.add(ship);
    }

    private int convertRowToIndex(Coordinate coordinate) {
        return ((byte) coordinate.getRow()) - 65;
    }

    private int convertColToIndex(Coordinate coordinate) {
        return coordinate.getColumn() - 1;
    }


}