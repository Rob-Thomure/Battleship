package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameField {
    List<List<Character>> fieldPositions;
    List<Ship> ships;

    public GameField() {
        this.fieldPositions = createGrid();
        this.ships = new ArrayList<>();
    }

    private List<List<Character>> createGrid() {
        List<List<Character>> grid = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add('~');
            }
            grid.add(row);
        }
        return grid;
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


    public String takeShot(Coordinate coordinate) {
        int rowIndex = convertRowToIndex(coordinate);
        int colIndex = convertColToIndex(coordinate);
        if (rowIndex < 0 || rowIndex > 9 || colIndex < 0 || colIndex > 9) {
            return  "out of bounds";
        } else if (fieldPositions.get(rowIndex).get(colIndex) == 'O' || fieldPositions.get(rowIndex).get(colIndex) == 'X') {
            fieldPositions.get(rowIndex).set(colIndex, 'X');
            Ship ship = getShipThatWasShot(coordinate);
            ship.setCoordinateStatus(coordinate);
            if (ships.stream().filter(Ship::isSunk).count() == 5) {
                return "all ships sunk";
            } else if (ship.isSunk()) {
                return "sank";
            } else {
                return "hit";
            }
        } else {
            fieldPositions.get(rowIndex).set(colIndex, 'M');
            return  "missed";
        }
    }

    private Ship getShipThatWasShot(Coordinate coordinate) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).containsCoordinate(coordinate)) {
                return ships.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("  1 2 3 4 5 6 7 8 9 10\n");
        List<Character> rowNames = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
        for (int i = 0; i < 10; i++) {
            String rowString = fieldPositions.get(i).stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));
            stringBuilder.append(rowNames.get(i))
                    .append(" ")
                    .append(rowString)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public String fogOfWarToString() {
        StringBuilder stringBuilder = new StringBuilder("  1 2 3 4 5 6 7 8 9 10\n");
        List<Character> rowNames = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
        for (int i = 0; i < 10; i++) {
            String rowString = fieldPositions.get(i).stream()
                    .map(this::createFog)
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));
            stringBuilder.append(rowNames.get(i))
                    .append(" ")
                    .append(rowString)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private Character createFog(Character character) {
        return character.equals('O') ? '~' : character;
    }

}