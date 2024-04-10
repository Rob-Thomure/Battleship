import org.example.Coordinate;
import org.example.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {
    private final Coordinate coordinateA1 = new Coordinate("A1");
    private final Coordinate coordinateA4 = new Coordinate("A4");
    private final Coordinates coordinatesA1A4 = new Coordinates(coordinateA1, coordinateA4);
    private final Coordinate coordinateF7 = new Coordinate("F7");
    private final Coordinate coordinateF3 = new Coordinate("F3");
    private final Coordinates coordinatesF7F3 = new Coordinates(coordinateF7, coordinateF3);

    @Test
    public void testCoordinatesLengthA1A4() {
        int expectedLength = 4;
        int actualLength = coordinatesA1A4.getLength();
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testCoordinatesPartsA1A4() {
        String expectedParts = "A1 A2 A3 A4";
        String actualParts = coordinatesA1A4.getPartsString();
        assertEquals(expectedParts, actualParts);
    }

    @Test
    public void testCoordinatesLengthF7F3() {
        int expectedLength = 5;
        int actualLength = coordinatesF7F3.getLength();
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testCoordinatesPartsF7F3() {
        String expectedParts = "F7 F6 F5 F4 F3";
        String actualParts = coordinatesF7F3.getPartsString();
        assertEquals(expectedParts, actualParts);
    }

    @Test
    public void testCoordinatesG2E3ThrowsIllegalArgumentException() {
        Coordinate coordinateG2 = new Coordinate("G2");
        Coordinate coordinateE3 = new Coordinate("E3");
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(coordinateG2, coordinateE3));
    }

    @Test
    public void testCoordinatesA11A14ThrowsIllegalArgumentException() {
        Coordinate coordinateA11 = new Coordinate("A11");
        Coordinate coordinateA14 = new Coordinate("A14");
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(coordinateA11, coordinateA14));
    }

    @Test
    public void testCoordinatesL2M2ThrowsIllegalArgumentException() {
        Coordinate coordinateL2 = new Coordinate("L2");
        Coordinate coordinateM2 = new Coordinate("M2");
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(coordinateL2, coordinateM2));
    }
}
