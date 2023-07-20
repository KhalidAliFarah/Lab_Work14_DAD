package solution_Part1_Exercise_5_controller;

/**
 * 
 * @Solution  Part1 Exercise_5 
 * 
 * This class represents a NumberCalculator that performs basic arithmetic operations.
 * It can calculate the sum and multiplication of two or three numbers.
 * 
 * @author Khalid
 */
public class NumberCalculator {

    /**
     * Calculates the sum of two numbers.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     * @return The sum of the two numbers.
     */
    public int getSum(int number1, int number2) {
        return number1 + number2;
    }

    /**
     * Calculates the multiplication of two numbers.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     * @return The multiplication of the two numbers.
     */
    public int getMultiplication(int number1, int number2) {
        return number1 * number2;
    }

    /**
     * Calculates the sum of three numbers.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     * @param number3 The third number.
     * @return The sum of the three numbers.
     */
    public int getSum(int number1, int number2, int number3) {
        return number1 + number2 + number3;
    }

    /**
     * Calculates the multiplication of three numbers.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     * @param number3 The third number.
     * @return The multiplication of the three numbers.
     */
    public int getMultiplication(int number1, int number2, int number3) {
        return number1 * number2 * number3;
    }
}
