import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    @Test
    public void testIsAdjacentToAnotherShip() {
        Coordinate coordinateF3 = new Coordinate("F3");
        Coordinate coordinateF7 = new Coordinate("F7");
        Coordinates coordinatesF3F7 = new Coordinates(coordinateF3, coordinateF7);
        Ship airCraftCarrier = new AirCraftCarrier(coordinatesF3F7);
        Coordinate coordinateE6 = new Coordinate("E6");
        Coordinate coordinateD6 = new Coordinate("D6");
        Coordinates coordinatesE6D6 = new Coordinates(coordinateE6, coordinateD6);
        Ship destroyer = new Destroyer(coordinatesE6D6);
        boolean result = airCraftCarrier.isAdjacentToAnotherShip(destroyer);
        assertTrue(result);
    }

    @Test
    public void testIsNotAdjacentToAnotherShip() {
        Coordinate coordinateF3 = new Coordinate("F3");
        Coordinate coordinateF7 = new Coordinate("F7");
        Coordinates coordinatesF3F7 = new Coordinates(coordinateF3, coordinateF7);
        Ship airCraftCarrier = new AirCraftCarrier(coordinatesF3F7);
        Coordinate coordinateC6 = new Coordinate("C6");
        Coordinate coordinateD6 = new Coordinate("D6");
        Coordinates coordinatesE6D6 = new Coordinates(coordinateC6, coordinateD6);
        Ship destroyer = new Destroyer(coordinatesE6D6);
        boolean result = airCraftCarrier.isAdjacentToAnotherShip(destroyer);
        assertFalse(result);
    }
}
