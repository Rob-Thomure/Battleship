package org.example;

import java.util.Scanner;

public class BattleshipGame {
    GameField gameField;

    public BattleshipGame() {
        this.gameField = new GameField();
    }

    public void playGame() {
        addShips();
    }

    private void addShips() {
        addShip("Aircraft Carrier");
        addShip("Battleship");
        addShip("Submarine");
        addShip("Cruiser");
        addShip("Destroyer");
    }

    private void addShip(String shipName) {
        boolean validShip = false;
        while (!validShip) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n\n", shipName, getShipSize(shipName));
            Scanner scanner = new Scanner(System.in);
            String[] input =  scanner.nextLine().split(" ");
            Coordinates coordinates = new Coordinates(new Coordinate(input[0]) , new Coordinate(input[1]));
            shipFactory(shipName, coordinates);
            validShip = true;
        }

    }

    private int getShipSize(String ship) {
        return switch (ship) {
            case "Aircraft Carrier" -> 5;
            case "Battleship" -> 4;
            case "Submarine", "Cruiser" -> 3;
            case "Destroyer" -> 2;
            default -> 0;
        };
    }

    private void shipFactory(String shipName, Coordinates coordinates) {
        switch (shipName) {
            case "Aircraft Carrier" -> new AirCraftCarrier(coordinates);
            case "Battleship" -> new Battleship(coordinates);
            case "Submarine" -> new Submarine(coordinates);
            case "Cruiser" -> new Cruiser(coordinates);
            case "Destroyer" -> new Destroyer(coordinates);
        }
    }







}
