import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameFieldTest {
    private final GameField gameField = new GameField();

    @Test
    public void testGameFieldCreation() {
        String expectedGameFieldString = """
                  1 2 3 4 5 6 7 8 9 10
                A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                """;
        String actualGameFieldString = gameField.toString();
        assertEquals(expectedGameFieldString, actualGameFieldString);
    }

    @Test
    public void testAddAircraftCarrierF3F7() {
        addAircraftCarrierF3F7();
        String expectedGameFieldString = """
                  1 2 3 4 5 6 7 8 9 10
                A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ O O O O O ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                """;
        String actualGameFieldString = gameField.toString();
        assertEquals(expectedGameFieldString, actualGameFieldString);
    }

    @Test
    public void testThenAddBattleshipA1D1() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        String expectedGameFieldString = """
                  1 2 3 4 5 6 7 8 9 10
                A O ~ ~ ~ ~ ~ ~ ~ ~ ~
                B O ~ ~ ~ ~ ~ ~ ~ ~ ~
                C O ~ ~ ~ ~ ~ ~ ~ ~ ~
                D O ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ O O O O O ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                """;
        String actualGameFieldString = gameField.toString();
        assertEquals(expectedGameFieldString, actualGameFieldString);
    }

    @Test
    public void testThenAddSubmarineJ10J8() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        String expectedGameFieldString = """
                  1 2 3 4 5 6 7 8 9 10
                A O ~ ~ ~ ~ ~ ~ ~ ~ ~
                B O ~ ~ ~ ~ ~ ~ ~ ~ ~
                C O ~ ~ ~ ~ ~ ~ ~ ~ ~
                D O ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ O O O O O ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ O O O
                """;
        String actualGameFieldString = gameField.toString();
        assertEquals(expectedGameFieldString, actualGameFieldString);
    }

    @Test
    public void testThenAddCruiserB9D9() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        String expectedGameFieldString = """
                  1 2 3 4 5 6 7 8 9 10
                A O ~ ~ ~ ~ ~ ~ ~ ~ ~
                B O ~ ~ ~ ~ ~ ~ ~ O ~
                C O ~ ~ ~ ~ ~ ~ ~ O ~
                D O ~ ~ ~ ~ ~ ~ ~ O ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ O O O O O ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ O O O
                """;
        String actualGameFieldString = gameField.toString();
        assertEquals(expectedGameFieldString, actualGameFieldString);
    }

    @Test
    public void testThenAddDestroyerE6D6AdjacentToAircraftCarrierF3F7() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        Coordinate coordinateE6 = new Coordinate("E6");
        Coordinate coordinateD6 = new Coordinate("D6");
        Coordinates coordinatesE6D6 = new Coordinates(coordinateE6, coordinateD6);
        Ship destroyer = new Destroyer(coordinatesE6D6);
        assertThrows(TooCloseToAnotherShipException.class, () -> gameField.addShip(destroyer));
    }

    @Test
    public void testThenAddDestroyerI2J2() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        addDestroyerI2J2();
        String expecpectedGameFieldString = """
                  1 2 3 4 5 6 7 8 9 10
                A O ~ ~ ~ ~ ~ ~ ~ ~ ~
                B O ~ ~ ~ ~ ~ ~ ~ O ~
                C O ~ ~ ~ ~ ~ ~ ~ O ~
                D O ~ ~ ~ ~ ~ ~ ~ O ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ O O O O O ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ O ~ ~ ~ ~ ~ ~ ~ ~
                J ~ O ~ ~ ~ ~ ~ O O O
                """;
        String actualGameFieldString = gameField.toString();
        assertEquals(expecpectedGameFieldString, actualGameFieldString);
    }

    @Test
    public void testPositiveHitAtA1() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        addDestroyerI2J2();
        Coordinate coordinateA1 = new Coordinate("A1");
        String expected = "hit";
        String result = gameField.takeShot(coordinateA1);
        assertEquals(expected, result);
    }

    @Test
    public void testGameFieldToStringAfterPositiveHitAtA1() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        addDestroyerI2J2();
        Coordinate coordinateA1 = new Coordinate("A1");
        gameField.takeShot(coordinateA1);
        String expected = """
                  1 2 3 4 5 6 7 8 9 10
                A X ~ ~ ~ ~ ~ ~ ~ ~ ~
                B O ~ ~ ~ ~ ~ ~ ~ O ~
                C O ~ ~ ~ ~ ~ ~ ~ O ~
                D O ~ ~ ~ ~ ~ ~ ~ O ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ O O O O O ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ O ~ ~ ~ ~ ~ ~ ~ ~
                J ~ O ~ ~ ~ ~ ~ O O O
                """;
        String result = gameField.toString();
        assertEquals(expected, result);
    }

    @Test
    public void testTakeHitInvalidCoordinates() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        addDestroyerI2J2();
        Coordinate coordinateZ1 = new Coordinate("Z1");
        String expected = "out of bounds";
        String result = gameField.takeShot(coordinateZ1);
        assertEquals(expected, result);
    }

    @Test
    public void testTakeHitAtA2AndMiss() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        addDestroyerI2J2();
        Coordinate coordinateA2 = new Coordinate("A2");
        String expected = "missed";
        String result = gameField.takeShot(coordinateA2);
        assertEquals(expected, result);
    }

    @Test
    public void testFogOfWarAfterHitAtA1() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        addDestroyerI2J2();
        Coordinate coordinateA1 = new Coordinate("A1");
        gameField.takeShot(coordinateA1);
        String expected = """
                  1 2 3 4 5 6 7 8 9 10
                A X ~ ~ ~ ~ ~ ~ ~ ~ ~
                B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                """;
        String result = gameField.fogOfWarToString();
        assertEquals(expected, result);
    }

    @Test
    public void testFogOfWarAfterMissAtA2() {
        addAircraftCarrierF3F7();
        addBattleshipA1D1();
        addSubmarineJ10J8();
        addCruiserB9D9();
        addDestroyerI2J2();
        Coordinate coordinateA2 = new Coordinate("A2");
        gameField.takeShot(coordinateA2);
        String expected = """
                  1 2 3 4 5 6 7 8 9 10
                A ~ M ~ ~ ~ ~ ~ ~ ~ ~
                B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                """;
        String result = gameField.fogOfWarToString();
        assertEquals(expected, result);
    }


    private void addAircraftCarrierF3F7() {
        Coordinate coordinateF3 = new Coordinate("F3");
        Coordinate coordinateF7 = new Coordinate("F7");
        Coordinates coordinatesF3F7 = new Coordinates(coordinateF3, coordinateF7);
        Ship airCraftCarrier = new AirCraftCarrier(coordinatesF3F7);
        gameField.addShip(airCraftCarrier);
    }

    private void addBattleshipA1D1() {
        Coordinate coordinateA1 = new Coordinate("A1");
        Coordinate coordinateD1 = new Coordinate("D1");
        Coordinates coordinatesA1D1 = new Coordinates(coordinateA1, coordinateD1);
        Ship battleship = new Battleship(coordinatesA1D1);
        gameField.addShip(battleship);
    }

    private void addSubmarineJ10J8() {
        Coordinate coordinateJ10 = new Coordinate("J10");
        Coordinate coordinateJ8 = new Coordinate("J8");
        Coordinates coordinatesJ10J8 = new Coordinates(coordinateJ10, coordinateJ8);
        Ship submarine = new Submarine(coordinatesJ10J8);
        gameField.addShip(submarine);
    }

    private void addCruiserB9D9() {
        Coordinate coordinateB9 = new Coordinate("B9");
        Coordinate coordinateD9 = new Coordinate("D9");
        Coordinates coordinatesB9D9 = new Coordinates(coordinateB9, coordinateD9);
        Ship cruiser = new Cruiser(coordinatesB9D9);
        gameField.addShip(cruiser);
    }

    private void addDestroyerI2J2() {
        Coordinate coordinateI2 = new Coordinate("I2");
        Coordinate coordinateJ2 = new Coordinate("J2");
        Coordinates coordinatesI2J2 = new Coordinates(coordinateI2, coordinateJ2);
        Ship destroyer = new Destroyer(coordinatesI2J2);
        gameField.addShip(destroyer);
    }



}
