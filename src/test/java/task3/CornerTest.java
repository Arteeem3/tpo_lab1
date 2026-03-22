package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CornerTest {
    private Corner corner;
    private Room room;
    private People people;

    @BeforeEach
    public void setUp() {
        room = new Room(100);
        corner = room.getCorner();
        people = new People(List.of(room, new Room(100)));
    }

    @ParameterizedTest
    @CsvSource({
        "0,   100, false",
        "50,  50,  false",
        "100, 0,   true",
        "120, 0,   true"
    })
    void testCornerTransitionTable(int damage, int expectedHealth, boolean expectedPeopleMoved) {
        Room initialRoom = people.getCurrentRoom();
        corner.takeDamage(damage);
        assertEquals(expectedHealth, corner.getHealth());
        if (expectedPeopleMoved) {
            assertNotEquals(initialRoom, people.getCurrentRoom(), "People should have moved from the corner");
        } else {
            assertEquals(initialRoom, people.getCurrentRoom(), "People should stay in the same room");
        }
    }
}