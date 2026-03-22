package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class MetalTest {
    private Metal metal;

    @BeforeEach
    public void setUp() {
        Room room = new Room(100);
        metal = room.getComputerBank().getFrontSide();
    }

    @ParameterizedTest
    @CsvSource({
        "0,   100, SOLID",
        "50,  50,  SOLID",
        "100, 0,   LIQUID"
    })
    void testMetalStateTransitions(int damage, int expectedHealth, MetalState expectedState) {
        metal.takeDamage(damage);
        assertEquals(expectedHealth, metal.getHealth());
        assertEquals(expectedState, metal.getState());
    }
}