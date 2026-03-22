package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class PeopleTest {
    private Room room1, room2, room3;
    private People people;

    @BeforeEach
    public void setUp() {
        room1 = new Room(100);
        room2 = new Room(100);
        room3 = new Room(100);
        people = new People(List.of(room1, room2, room3));
    }

    @ParameterizedTest
    @MethodSource("provideMovementScenarios")
    void testPeopleMovementTransitions(int hits, int expectedRoomIdx, boolean expectedDead) {
        Room[] rooms = {room1, room2, room3};

        for (int i = 0; i < hits; i++) {
            people.getCurrentRoom().getCorner().takeDamage(100);
        }

        assertEquals(expectedDead, people.isDead());
        if (!expectedDead) {
            assertEquals(rooms[expectedRoomIdx], people.getCurrentRoom());
        }
    }

    private static Stream<Arguments> provideMovementScenarios() {
        return Stream.of(
            Arguments.of(0, 0, false),
            Arguments.of(1, 1, false),
            Arguments.of(2, 2, false),
            Arguments.of(3, -1, true)
        );
    }
}