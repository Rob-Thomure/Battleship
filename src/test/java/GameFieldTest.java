import org.example.Coordinate;
import org.example.Coordinates;
import org.example.GameField;
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
        Coordinate coordinateF3 = new Coordinate("F3");
        Coordinate coordinateF7 = new Coordinate("F7");
        Coordinates coordinatesF3F7 = new Coordinates(coordinateF3, coordinateF7);
        gameField.addAirCraftCarrier(coordinatesF3F7);
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
    public void testAddBattleshipA1D1() {
        // TODO refactor
        Coordinate coordinateF3 = new Coordinate("F3");
        Coordinate coordinateF7 = new Coordinate("F7");
        Coordinates coordinatesF3F7 = new Coordinates(coordinateF3, coordinateF7);
        gameField.addAirCraftCarrier(coordinatesF3F7);

        Coordinate coordinateA1 = new Coordinate("A1");
        Coordinate coordinateD1 = new Coordinate("D1");
        Coordinates coordinatesA1D1 = new Coordinates(coordinateA1, coordinateD1);
        gameField.addBattleship(coordinatesA1D1);
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



}
