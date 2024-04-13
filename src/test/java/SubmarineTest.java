import org.example.Coordinate;
import org.example.Coordinates;
import org.example.Ship;
import org.example.Submarine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubmarineTest {

    @Test
    public void testWrongSizeSubmarine() {
        Coordinate coordinateJ7 = new Coordinate("J7");
        Coordinate coordinateJ10 = new Coordinate("J10");
        Coordinates coordinatesJ7J10 = new Coordinates(coordinateJ7, coordinateJ10);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {Submarine submarine = new Submarine(coordinatesJ7J10);});
    }
}
