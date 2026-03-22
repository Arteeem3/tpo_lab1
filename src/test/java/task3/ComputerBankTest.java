package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerBankTest {
    private ComputerBank computerBank;
    private Metal frontSide;

    @BeforeEach
    public void setUp() {
        Room room = new Room(100);
        computerBank = room.getComputerBank();
        frontSide = computerBank.getFrontSide();
    }

    @ParameterizedTest
    @CsvSource({
        "0,   100, 100",
        "30,  70,  100",
        "100, 0,   100",
        "150, 0,   50"
    })
    void testComputerBankTransitionTable(int damage, int expectedBankHealth, int expectedMetalHealth) {
        computerBank.takeDamage(damage);
        assertAll(
            () -> assertEquals(expectedBankHealth, computerBank.getHealth(), "Health of ComputerBank is wrong"),
            () -> assertEquals(expectedMetalHealth, frontSide.getHealth(), "Overflow damage to Metal is wrong")
        );
    }
}