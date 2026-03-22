package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room(100);
        new People(List.of(room));
    }

    @ParameterizedTest
    @CsvSource({
        "0,   100, CALM,   MEDIUM",
        "40,  60,  CALM,   MEDIUM",
        "50,  50,  MEDIUM, MEDIUM",
        "99,  1,   MEDIUM, MEDIUM",
        "100, 0,   LOUD,   HOT"
    })
    void testRoomStateTransitions(int damage, int expectedHealth, NoiseLevel expectedNoise, Temperature expectedTemp) {
        room.takeDamage(damage);
        assertAll(
            () -> assertEquals(expectedHealth, room.getHealth()),
            () -> assertEquals(expectedNoise, room.getNoiseLevel()),
            () -> assertEquals(expectedTemp, room.getTemperature())
        );
    }
}