package DelimiterChecker;

import collections.implementation.ArrayStack;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that have a balance checker method.
 */
public class DelimiterChecker {

    private static final int OPEN_DELIMITER = 0;
    private static final int CLOSE_DELIMITER = 1;
    private static final int OTHER = -1;

    /**
     * Checks if and expression if balanced, in other words, if all delimiter are correct. Delimiters supported : (, [ and {.
     * Example of a balanced expression: { ( ) [ ] }
     * Example of a unbalanced expression: public void method)( return ;
     *
     * @param exp Expression to be analyzed
     * @return True in case we have a balanced expression, false otherwise.
     * @throws NullPointerException In case the expression received is null.
     */
    public static boolean isBalanced(String exp) throws NullPointerException {
        return check(exp);
    }

    /**
     * Checks if and expression if balanced, in other words, if all delimiter are correct. Delimiters supported : (, [ and {.
     * Example of a balanced expression: { ( ) [ ] }
     * Example of a unbalanced expression: public void method)( return ;
     *
     * @param filePath Path of a File to be analyzed.
     * @return True in case we have a balanced expression, false otherwise.
     * @throws NullPointerException In case the expression received is null.
     * @throws IOException          In case the file in not found
     */
    public static boolean isBalanced(Path filePath) throws NullPointerException, IOException {
        String content = Files.readString(filePath);

        return check(content);
    }

    /**
     * Checks if the char received is an open delimiter, close delimiter or other char.
     *
     * @param c Char to be analysed
     * @return Return 0 in case of an open delimiter, return 1 in case a close delimiter, or -1 in case of other char.
     */
    private static int typeOfChar(char c) {
        if (c == '(' || c == '{' || c == '[') {
            return OPEN_DELIMITER;
        } else if (c == ')' || c == '}' || c == ']') {
            return CLOSE_DELIMITER;
        } else {
            return OTHER;
        }
    }

    /**
     * Checks if the two chars received are a pair.
     *
     * @param opening Possible opening char
     * @param closing Possible closing char
     * @return True in case of both being paired, false otherwise.
     */
    private static boolean arePair(char opening, char closing) {
        if (opening == '(' && closing == ')')
            return true;
        else if (opening == '{' && closing == '}')
            return true;
        else
            return opening == '[' && closing == ']';
    }

    /**
     * Checks if expression received is balanced.
     *
     * @param exp Expression to be analysed.
     * @return True in case of being balanced, false otherwise.
     * @throws NullPointerException If parameter is null.
     */
    private static boolean check(String exp) throws NullPointerException {
        if (exp == null)
            throw new NullPointerException("Expression received is null");

        ArrayStack<Character> stack = new ArrayStack<>();

        for (int i = 0, size = exp.length(); i < size; i++) { //Goes throw all expression, char by char.
            char c = exp.charAt(i);
            if (typeOfChar(c) == OPEN_DELIMITER) { //If we are facing an open delimiter
                stack.push(c); //Push to top of stack
            } else if (typeOfChar(c) == CLOSE_DELIMITER) { //If we found and close delimiter
                if (stack.isEmpty() || !(arePair(stack.peek(), c))) //We check if the stack is empty or the last char is not the corresponding close delimiter.
                    return false; //In case of true, we already know that we are not facing a balanced expression
                stack.pop(); //Otherwise, we just pop the last
            }
        }
        return stack.isEmpty(); //If after the algorithm the stack is empty, we have sure the expression is balanced
    }

}
