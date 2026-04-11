import ShoppingCartApp.Calculator.CalculatorModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorModelTest {

    private final InputStream originalIn = System.in;

    @AfterEach
    void tearDownStreams() {
        System.setIn(originalIn);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void testGetCurrentItemPrice_WithNumber() {
        setInput("5\n");
        CalculatorModel calculator = new CalculatorModel();

        CalculatorModel.PriceResult result = calculator.getCurrentItemPrice("5");

        assertTrue(result.isValid());
        assertFalse(result.isZero());
//        assertEquals(0.0, calculator.getTotal());
    }

    @Test
    void testGetCurrentItemQuantity_UsesPriceAndQuantity() {
        setInput("5\n2\n");
        CalculatorModel calculator = new CalculatorModel();

        CalculatorModel.PriceResult priceResult = calculator.getCurrentItemPrice("5");
        CalculatorModel.QuantityResult quantityResult = calculator.getCurrentItemQuantity("2");

        assertTrue(priceResult.isValid());
        assertFalse(priceResult.isZero());
        assertTrue(quantityResult.isValid());
        assertFalse(quantityResult.isZero());
//        assertEquals(10.0, calculator.getTotal());
    }
}
