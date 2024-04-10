package org.example;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        GameField gameField = new GameField();
        System.out.println(gameField);
        System.out.println("Enter the coordinates of the ship:");
        String[] input = new Scanner(System.in).nextLine().split(" ");
        Coordinate coordinate1 = new Coordinate(input[0]);
        Coordinate coordinate2 = new Coordinate(input[1]);
        Coordinates coordinates = new Coordinates(coordinate1, coordinate2);
        if (coordinate1.isWithinBounds() && coordinate2.isWithinBounds()
                && coordinates.isNotDiagonal()) {
            System.out.println("Length: " + coordinates.getLength());
            String parts = coordinates.getParts().stream()
                    .map(Coordinate::toString)
                    .collect(Collectors.joining(" "));
            System.out.println("Parts: " + parts);
        } else {
            System.out.println("Error!");
        }


    }
}