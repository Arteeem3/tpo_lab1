package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.Mockito.*;

public class BombingTest {
    private Bombing bombing;
    private Room room;

    @BeforeEach
    public void setUp() {
        bombing = new Bombing();
        room = mock(Room.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 30, 100, 500})
    public void testBombingInteractionTable(int damage) {
        bombing.bomb(room, damage);
        verify(room, times(1)).takeDamage(damage);
    }
}