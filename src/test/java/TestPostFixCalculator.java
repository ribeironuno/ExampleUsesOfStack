import PostFixCalculator.PostFixCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPostFixCalculator {

    PostFixCalculator calc = new PostFixCalculator("arrayStack");

    @Test
    @DisplayName("Tests calculate")
    public void testPostFixCalculator() {
        String exp = "6 5 + 3 * ";
        Assertions.assertEquals(33, calc.calculate(exp));
        exp = "6 5 2 3 + 8 * + 3 + * ";
        Assertions.assertEquals(288, calc.calculate(exp));
        exp = "-3.0 -2.0 + 2 * ";
        Assertions.assertEquals(-10, calc.calculate(exp));

    }

    @Test
    @DisplayName("Test the throws")
    public void testPostFixCalculatorThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.calculate("6523+ sss*+3+*"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.calculate(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.calculate("adsadsadsadsads"));
    }
}
