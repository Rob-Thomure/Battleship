package org.example;

import java.util.Scanner;

public class BattleshipGame {
    GameField gameField;

    public BattleshipGame() {
        this.gameField = new GameField();
    }

    public void playGame() {
        System.out.println(gameField);
        addAircraftCarrier();
        addBattleship();
        addSubmarine();
        addCruiser();
        addDestroyer();
    }

    private void addAircraftCarrier() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
        String[] input = scanner.nextLine().split(" ");
        System.out.println();
        Coordinate coordinate1 = new Coordinate(input[0]);
        Coordinate coordinate2 = new Coordinate(input[1]);
        Coordinates coordinates = new Coordinates(coordinate1, coordinate2);
        Ship ship = new AirCraftCarrier(coordinates);
        gameField.addShip(ship);
        System.out.println(gameField);
    }

    private void addBattleship() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");
        String[] input = scanner.nextLine().split(" ");
        System.out.println();
        Coordinate coordinate1 = new Coordinate(input[0]);
        Coordinate coordinate2 = new Coordinate(input[1]);
        Coordinates coordinates = new Coordinates(coordinate1, coordinate2);
        Ship ship = new Battleship(coordinates);
        gameField.addShip(ship);
        System.out.println(gameField);
    }

    private void addSubmarine() {
        Scanner scanner = new Scanner(System.in);
        boolean validShipLength = false;
        System.out.println("Enter the coordinates of the Submarine (3 cells):\n");
        while (!validShipLength) {
            try {
                String[] input = scanner.nextLine().split(" ");
                System.out.println();
                Coordinate coordinate1 = new Coordinate(input[0]);
                Coordinate coordinate2 = new Coordinate(input[1]);
                Coordinates coordinates = new Coordinates(coordinate1, coordinate2);
                Ship ship = shipFactory("Submarine", coordinates);
                gameField.addShip(ship);
                System.out.println(gameField);
                validShipLength = true;
            } catch (IllegalShipLength e) {
                System.out.println(e.getMessage() + " of the Submarine (3 cells)\n");
            } catch (ToCloseToAnotherShipException e) {
                System.out.println(e.getMessage() + " Try again:\n");
            } catch (DiagonalCoordinatesException e) {
                System.out.println("Error! Wrong ship location! Try again:\n");
            }
        }
    }



    private void addCruiser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");
        String[] input = scanner.nextLine().split(" ");
        System.out.println();
        Coordinate coordinate1 = new Coordinate(input[0]);
        Coordinate coordinate2 = new Coordinate(input[1]);
        Coordinates coordinates = new Coordinates(coordinate1, coordinate2);
        Ship ship = new Cruiser(coordinates);
        gameField.addShip(ship);
        System.out.println(gameField);
    }

    private void addDestroyer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the Destroyer (2 cells):\n");
        String[] input = scanner.nextLine().split(" ");
        System.out.println();
        Coordinate coordinate1 = new Coordinate(input[0]);
        Coordinate coordinate2 = new Coordinate(input[1]);
        Coordinates coordinates = new Coordinates(coordinate1, coordinate2);
        Ship ship = new Destroyer(coordinates);
        gameField.addShip(ship);
        System.out.println(gameField);
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
            try {
                Scanner scanner = new Scanner(System.in);
                String[] input =  scanner.nextLine().split(" ");
                Coordinates coordinates = new Coordinates(new Coordinate(input[0]) , new Coordinate(input[1]));
                gameField.addShip(shipFactory(shipName, coordinates));
                validShip = true;
                System.out.println();
                System.out.println(gameField);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }


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

    private Ship shipFactory(String shipName, Coordinates coordinates) {
        return switch (shipName) {
            case "Aircraft Carrier" -> new AirCraftCarrier(coordinates);
            case "Battleship" -> new Battleship(coordinates);
            case "Submarine" -> new Submarine(coordinates);
            case "Cruiser" -> new Cruiser(coordinates);
            case "Destroyer" -> new Destroyer(coordinates);
            default -> throw new IllegalStateException("Unexpected value: " + shipName);
        };
    }







}
