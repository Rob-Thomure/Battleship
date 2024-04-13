import org.example.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    @Test
    public void testCoordinateIsAdjacentToAnotherCoordinate() {
        Coordinate coordinateE6 = new Coordinate("E6");
        Coordinate coordinateF6 = new Coordinate("F6");
        boolean actual = coordinateE6.isAdjacentToAnother(coordinateF6);
        assertTrue(actual);
    }

    @Test
    public void testCoordinateIsNotAdjacentToAnotherCoordinate() {
        Coordinate coordinateE6 = new Coordinate("E6");
        Coordinate coordinateG6 = new Coordinate("G6");
        boolean actual = coordinateE6.isAdjacentToAnother(coordinateG6);
        assertFalse(actual);

    }
}
