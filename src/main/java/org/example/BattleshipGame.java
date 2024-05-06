package org.example;

import java.util.Scanner;

public class BattleshipGame {
    GameField gameField;
    private boolean gameOver = false;

    public BattleshipGame() {
        this.gameField = new GameField();
    }

    public void playGame() {
        System.out.println(gameField);
        addShips();
        System.out.println("The game starts!\n");
        System.out.println(gameField.fogOfWarToString());

        takeShots();
    }

    private void takeShots() {
        System.out.println("Take a shot!\n");
        while (!gameOver) {
            String input = new Scanner(System.in).nextLine();
            System.out.println();
            Coordinate coordinate = new Coordinate(input);
            String result = gameField.takeShot(coordinate);
            determineShotResult(result);
        }
    }

    private void takeShot() {
        boolean validShot = false;
        while (!validShot) {
            String input = new Scanner(System.in).nextLine();
            System.out.println();
            Coordinate coordinate = new Coordinate(input);
            String result = gameField.takeShot(coordinate);
            validShot = determineShotResult(result);
        }
    }

    private boolean determineShotResult(String result) {
        boolean validShot = true;
        if (result.equals("hit")) {
            System.out.println(gameField.fogOfWarToString());
            System.out.println("You hit a ship! Try again:");
            System.out.println();
        } else if (result.equals("missed")) {
            System.out.println(gameField.fogOfWarToString());
            System.out.println("You missed. Try again:");
            System.out.println();
        } else if (result.equals("out of bounds")) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            System.out.println();
            validShot = false;
        } else if (result.equals("sank")) {
            System.out.println(gameField.fogOfWarToString());
            System.out.println("You sank a ship! Specify a new target:");
            System.out.println();
        } else if (result.equals("all ships sunk")) {
            System.out.println(gameField.fogOfWarToString());
            System.out.println("You sank the last ship. You won. Congratulations!");
            gameOver = true;
        }
        return validShot;
    }

    private void addShips() {
        addShip("Aircraft Carrier");
        addShip("Battleship");
        addShip("Submarine");
        addShip("Cruiser");
        addShip("Destroyer");
    }

    private void addShip(String shipName) {
        Scanner scanner = new Scanner(System.in);
        boolean validShip = false;
        System.out.println("Enter the coordinates of the %s (%d cells):\n".formatted(shipName, getShipSize(shipName)));
        while (!validShip) {
            try {
                String[] input = scanner.nextLine().split(" ");
                System.out.println();
                Coordinate coordinate1 = new Coordinate(input[0]);
                Coordinate coordinate2 = new Coordinate(input[1]);
                Coordinates coordinates = new Coordinates(coordinate1, coordinate2);
                Ship ship = shipFactory(shipName, coordinates);
                gameField.addShip(ship);
                System.out.println(gameField);
                validShip = true;
            } catch (IllegalShipLengthException e) {
                System.out.println(e.getMessage() + " of the %s! Try again:\n".formatted(shipName));
            } catch (TooCloseToAnotherShipException e) {
                System.out.println(e.getMessage() + " Try again:\n");
            } catch (DiagonalCoordinates e) {
                System.out.println("Error! Wrong ship location! Try again:\n");
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
