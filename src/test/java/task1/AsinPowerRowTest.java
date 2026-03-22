package task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class AsinPowerRowTest {
    private final AsinPowerRow calc = new AsinPowerRow();
    private final double DELTA = 0.01;
    private final int ITERATIONS = 100;

    //КЭ1
    @ParameterizedTest
    @ValueSource(doubles = {-1.0000001, -2.0, -100.0, Double.NEGATIVE_INFINITY})
    void testInvalidNegativeEquivalenceClass(double x) {
        assertTrue(Double.isNaN(calc.asin(x, ITERATIONS)), "Должно быть NaN для x < -1");
    }

    //КЭ2
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.99, -0.5, -0.000001})
    void testValidNegativeEquivalenceClass(double x) {
        // Для границы -1.0 ряд сходится медленнее, поэтому задаем больше итераций и погрешность
        if (x == -1.0) {
            assertEquals(Math.asin(x), calc.asin(x, 500), 0.1, "Сбой на границе x: " + x);
        } else {
            assertEquals(Math.asin(x), calc.asin(x, ITERATIONS), DELTA, "Сбой для валидного отрицательного x: " + x);
        }
    }

    //КЭ3
    @Test
    void testZeroEquivalenceClass() {
        assertEquals(Math.asin(0.0), calc.asin(0.0, ITERATIONS), 0.0, "Сбой для нуля");
        assertEquals(Math.asin(-0.0), calc.asin(-0.0, ITERATIONS), 0.0, "Сбой для отрицательного нуля");
    }

    //КЭ4
    @ParameterizedTest
    @ValueSource(doubles = {0.000001, 0.5, 0.99, 1.0})
    void testValidPositiveEquivalenceClass(double x) {
        if (x == 1.0) {
            assertEquals(Math.asin(x), calc.asin(x, 500), 0.1, "Сбой на границе x: " + x);
        } else {
            assertEquals(Math.asin(x), calc.asin(x, ITERATIONS), DELTA, "Сбой для валидного положительного x: " + x);
        }
    }

    //КЭ5
    @ParameterizedTest
    @ValueSource(doubles = {1.0000001, 2.0, 100.0, Double.POSITIVE_INFINITY})
    void testInvalidPositiveEquivalenceClass(double x) {
        assertTrue(Double.isNaN(calc.asin(x, ITERATIONS)), "Должно быть NaN для x > 1");
    }

    @Test
    void testSpecialPointsAndNaN() {
        assertTrue(Double.isNaN(calc.asin(Double.NaN, ITERATIONS)), "Сбой для ввода NaN");
        assertEquals(Math.asin(Double.MIN_VALUE), calc.asin(Double.MIN_VALUE, ITERATIONS), 1e-20, "Сбой для MIN_VALUE");
    }

    @Test
    void testRangeStepByStep() {
        for (double x = -1.0; x <= 1.0; x += 0.1) {
            assertEquals(Math.asin(x), calc.asin(x, 200), 0.1, "Сбой на шаге x: " + x);
        }
    }
}