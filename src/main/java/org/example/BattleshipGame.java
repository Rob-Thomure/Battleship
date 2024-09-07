package org.example;

import org.example.ships.*;

import java.io.IOException;
import java.util.Scanner;

public class BattleshipGame {
    GameField gameFieldPlayer1;
    GameField gameFieldPlayer2;
    private boolean gameOver = false;

    public BattleshipGame() {
        this.gameFieldPlayer1 = new GameField();
        this.gameFieldPlayer2 = new GameField();
    }

    public void playGame() {
        System.out.println("Player 1, place your ships on the game field\n");
        System.out.println(gameFieldPlayer1);
        addShipsPlayer1();
        System.out.println("Press Enter and pass the move to another player");
        waitForEnterKey();
        System.out.println("Player 2, place your ships to the game field\n");
        System.out.println(gameFieldPlayer2);
        addShipsPlayer2();
        System.out.println("Press Enter and pass the move to another player");
        waitForEnterKey();
        while (!gameOver) {
            takeShotPlayer1();
            if (!gameOver) {
                takeShotPlayer2();
            }
        }
    }

    private void waitForEnterKey() {
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void takeShotPlayer1() {
        System.out.print(gameFieldPlayer2.fogOfWarToString());
        System.out.println("---------------------");
        System.out.print(gameFieldPlayer1);
        System.out.println();
        System.out.println("Player 1, it's your turn:\n");
        takeShotsWhileNotValid(gameFieldPlayer2);
    }

    private void takeShotPlayer2() {
        System.out.print(gameFieldPlayer1.fogOfWarToString());
        System.out.println("---------------------");
        System.out.print(gameFieldPlayer2);
        System.out.println();
        System.out.println("Player 2, it's your turn:\n");
        takeShotsWhileNotValid(gameFieldPlayer1);
    }

    private void takeShotsWhileNotValid(GameField gameFieldPlayer) {
        boolean validShot = false;
        while (!validShot) {
            String input = new Scanner(System.in).nextLine();
            System.out.println();
            Coordinate coordinate = new Coordinate(input);
            String result = gameFieldPlayer.takeShot(coordinate);
            validShot = determineShotResultPlayer(result);
        }
    }

    private boolean determineShotResultPlayer(String result) {
        boolean validShot = true;
        switch (result) {
            case "hit" -> {
                System.out.println("You hit a ship!");
                System.out.println("Press Enter and pass the move to another player");
                waitForEnterKey();
            }
            case "missed" -> {
                System.out.println("You missed!");
                System.out.println("Press Enter and pass the move to another player");
                waitForEnterKey();
            }
            case "out of bounds" -> {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                System.out.println();
                validShot = false;
            }
            case "sank" -> {
                System.out.println("You sank a ship!");
                System.out.println("Press Enter and pass the move to another player");
                waitForEnterKey();
            }
            case "all ships sunk" -> {
                System.out.println("You sank the last ship. You won. Congratulations!");
                gameOver = true;
            }
        }
        return validShot;
    }

    private void addShipsPlayer1() {
        addShip("Aircraft Carrier", gameFieldPlayer1);
        addShip("Battleship", gameFieldPlayer1);
        addShip("Submarine", gameFieldPlayer1);
        addShip("Cruiser", gameFieldPlayer1);
        addShip("Destroyer", gameFieldPlayer1);
    }

    private void addShipsPlayer2() {
        addShip("Aircraft Carrier", gameFieldPlayer2);
        addShip("Battleship", gameFieldPlayer2);
        addShip("Submarine", gameFieldPlayer2);
        addShip("Cruiser", gameFieldPlayer2);
        addShip("Destroyer", gameFieldPlayer2);
    }

    private void addShip(String shipName, GameField gameField) {
        Scanner scanner = new Scanner(System.in);
        boolean validShip = false;
        System.out.printf("Enter the coordinates of the %s (%d cells):\n%n", shipName, getShipSize(shipName));
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
