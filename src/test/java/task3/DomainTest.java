package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {
    private Room room1, room2;
    private People people;
    private Bombing bombing;

    @BeforeEach
    public void setUp() {
        room1 = new Room(100);
        room2 = new Room(100);
        people = new People(List.of(room1, room2));
        room1.setPeople(people);
        bombing = new Bombing();
    }

    @ParameterizedTest
    @CsvSource({
        "50, SOLID, MEDIUM, MEDIUM, false",
        "200, LIQUID, LOUD, HOT, false",
        "300, LIQUID, LOUD, HOT, false"
    })
    void testFullDomainScenario(int damage, MetalState mState, NoiseLevel noise, Temperature temp, boolean isDead) {
        bombing.bomb(room1, damage);

        assertAll(
            () -> assertEquals(mState, room1.getComputerBank().getFrontSide().getState()),
            () -> assertEquals(noise, room1.getNoiseLevel()),
            () -> assertEquals(temp, room1.getTemperature()),
            () -> assertEquals(isDead, people.isDead())
        );

        if (damage >= 300) {
            assertEquals(room2, people.getCurrentRoom());
        } else {
            assertEquals(room1, people.getCurrentRoom());
        }
    }
}