package calculator.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author ae
 */
public class CalculatorTest {

    public CalculatorTest() {
    }

    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        this.calculator = new Calculator();
    }

    @Test
    public void testAdd() throws Exception {
        int actualResult = calculator.add(5, 3);
        int expectedResult = 8;
        assertEquals("unit test for add", expectedResult, actualResult);
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals("multiply", 18, calculator.multiply(6, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivide() {
        calculator.divide(7, 0);
    }

    @Test
    @Ignore
    public void testLater() {
        // Testing code...
    }
}
