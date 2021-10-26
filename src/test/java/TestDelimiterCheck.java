import DelimiterChecker.DelimiterChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class TestDelimiterCheck {

    @Test
    @DisplayName("Test balanced expressions -> on String parameter")
    void testTrueExpressionsOnString() {
        String exp = "{[]}";
        Assertions.assertTrue(DelimiterChecker.isBalanced(exp));
        exp = "public void() { return; }";
        Assertions.assertTrue(DelimiterChecker.isBalanced(exp));
        exp = "";
        Assertions.assertTrue(DelimiterChecker.isBalanced(exp));
    }

    @Test
    @DisplayName("Test unbalanced on expressions -> on String parameter")
    void testFalseExpressionsOnString() {
        String exp = "{[]}}";
        Assertions.assertFalse(DelimiterChecker.isBalanced(exp));
        exp = ")(";
        Assertions.assertFalse(DelimiterChecker.isBalanced(exp));
        exp = "(}{]";
        Assertions.assertFalse(DelimiterChecker.isBalanced(exp));
        exp = "public void method() {  ;";
        Assertions.assertFalse(DelimiterChecker.isBalanced(exp));

    }

    @Test
    @DisplayName("Test exceptions throws on expressions -> on String parameter")
    void testExceptionsThrowsExpressionsOnString() {
        String exp = null;
        Assertions.assertThrows(NullPointerException.class, () -> DelimiterChecker.isBalanced(exp));
    }

    @Test
    @DisplayName("Test balanced expressions -> on Path parameter")
    void testTrueExpressionsOnPath() throws IOException {
        Path path = Path.of("files/correct-file.txt");
        Assertions.assertTrue(DelimiterChecker.isBalanced(path));
    }

    @Test
    @DisplayName("Test unbalanced on expressions -> on Path parameter")
    void testFalseExpressionsOnPath() throws IOException {
        Path path = Path.of("files/incorrect-file.txt");
        Assertions.assertFalse(DelimiterChecker.isBalanced(path));
    }

    @Test
    @DisplayName("Test exceptions throws on expressions -> on Path parameter")
    void testExceptionsThrowsExpressionsOnPath() {
        Path path = Path.of("non-existing-file");
        Assertions.assertThrows(NoSuchFileException.class, () -> DelimiterChecker.isBalanced(path));
    }
}
