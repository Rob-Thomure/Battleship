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

    public void addShip(Ship ship) {
        List<Coordinate> coordinates = ship.getCoordinates().getParts();
        coordinates.forEach(coordinate -> {
            int rowIndex = convertRowToIndex(coordinate);
            int colIndex = convertColToIndex(coordinate);
            fieldPositions.get(rowIndex).set(colIndex, 'O');
        });
        ships.add(ship);
    }

    public void addAirCraftCarrier(Coordinates coordinates) {
        List<Coordinate> coordinatesParts = coordinates.getParts();
        coordinatesParts.forEach(coordinate -> {
            int rowIndex = convertRowToIndex(coordinate);
            int colIndex = convertColToIndex(coordinate);
            fieldPositions.get(rowIndex).set(colIndex, 'O');
        });

    }

    public void addBattleship(Coordinates coordinates) {
        List<Coordinate> coordinatesParts = coordinates.getParts();
        coordinatesParts.forEach(coordinate -> {
            int rowIndex = convertRowToIndex(coordinate);
            int colIndex = convertColToIndex(coordinate);
            fieldPositions.get(rowIndex).set(colIndex, 'O');
        });
    }

    private int convertRowToIndex(Coordinate coordinate) {
        return ((byte) coordinate.getRow()) - 65;
    }

    private int convertColToIndex(Coordinate coordinate) {
        return coordinate.getColumn() - 1;
    }


}