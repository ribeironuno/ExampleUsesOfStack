package PostFixCalculator;


import collections.implementation.ArrayStack;
import collections.implementation.LinkedStack;
import collections.interfaces.IStack;

import java.security.InvalidParameterException;

/**
 * Class that implements a post fix calculator
 */
public class PostFixCalculator {

    private final IStack<Double> stack;

    /**
     * Creates a post fix calculator.
     *
     * @param implementationType "arrayStack" for array Stack or "linkedStack" for linked stack; In case of wrong input by default stack will be implemented in arraystack.
     */
    public PostFixCalculator(String implementationType) {
        if (implementationType.equals("arrayStack")) {
            stack = new ArrayStack<>();
        } else {
            stack = new LinkedStack<>();
        }
    }

    /**
     * Says if a char received is a operator (* or + or - or /)
     *
     * @param c Chat o analyze.
     * @return True if it is an operator, false otherwise.
     */
    private boolean isOperator(char c) {
        return c == '*' || c == '/' || c == '-' || c == '+';
    }

    /**
     * Makes the operation between two doubles.
     *
     * @param v1       First value.
     * @param v2       Second value.
     * @param operator Operator to execute. Must be -> + or / or * or -
     * @return Value of return
     */
    private double makeOperation(double v1, double v2, char operator) {
        switch (operator) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            case '/':
                return v1 / v2;
            default:
                return v1 * v2;
        }
    }

    /***
     * Method that will calculate the result of a post fix expression.
     * @param exp Expression to be analyzed. Expression must contain only numbers and operators (+ / * -) and the space will work like a separator.
     *            Valid expressions-> "2 3 * ". In last is necessary space too.
     * @return Result of expression.
     * @throws IllegalArgumentException Throws an exception in case if argument received is wrong, ex: g2+3* -> letter are not valid.
     */
    public double calculate(String exp) throws IllegalArgumentException {
        if (exp == null)
            throw new IllegalArgumentException("Null exp");

        for (int i = 0, posLastSpace = 0, size = exp.length(); i < size; i++) {

            if (exp.charAt(i) == ' ') {
                String sub = exp.substring(posLastSpace, i);
                if (sub.length() == 1) {
                    char c = sub.charAt(0);
                    if (isOperator(c)) {
                        stack.push(makeOperation(stack.pop(), stack.pop(), c));
                    } else if (Character.isDigit(c)) {
                        stack.push(Double.parseDouble(sub));
                    } else {
                        throw new IllegalArgumentException("Invalid string");
                    }
                } else {
                    try {
                        Double value = Double.parseDouble(sub);
                        stack.push(value);
                    } catch (NumberFormatException ex) {
                        throw new InvalidParameterException("Invalid string");
                    }
                }
                posLastSpace = i + 1;
            }
        }
        return stack.peek();
    }
}