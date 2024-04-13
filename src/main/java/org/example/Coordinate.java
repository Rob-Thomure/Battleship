package org.example;

public class Coordinate {
    private char row;
    private int column;

    public Coordinate(String coordinatePosition) {
        row = coordinatePosition.charAt(0);
        column = Integer.parseInt(coordinatePosition.substring(1));
    }

    public Coordinate(char row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isWithinBounds() {
        return row > '@' && row < 'K' && column > 0 && column < 11;
    }

    public boolean isAdjacentToAnother(Coordinate otherCoordinate) {
        int rowDifference = Math.abs(row - otherCoordinate.row);
        int columnDifference = Math.abs(column - otherCoordinate.column);
        return (rowDifference == 0 && columnDifference < 2)
                || (columnDifference == 0 && rowDifference < 2);
    }

    public char getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return Character.toString(row) + column;
    }

}