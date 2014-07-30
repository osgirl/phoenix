package com.phoenix.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /**
     * Entry point for the program
     *
     * @param args Any command line arguments that were passed in - these are ignored
     */
    public static void main(String[] args) {

        Main main = new Main();

        // ask the user for input
        main.printHeader();
        int a = main.getInputFromUser("enter the first value - \"a\"");
        int b = main.getInputFromUser("enter the second value - \"b\"");
        int c = main.getInputFromUser("enter the third value - \"c\"");

        // TODO - validate the user input more than this.
        if (a == 0) {
            System.out.println("If the first value is 0, this is a linear equation!");
            return;
        }
        // calculate roots
        Quadratic quadratic = new Quadratic(a, b, c);
        if (quadratic.calculateRoots()) {
            System.out.format("The roots for (%d, %d, %d) are %f and %f",
                    a, b, c, quadratic.getPositiveRoot(), quadratic.getNegativeRoot());
        } else {
            System.out.format("There are no Real roots for (%d, %d, %d)", a, b, c);
        }
    }

    private void printHeader() {
        System.out.println("This program will output the positive and negative factors");
        System.out.println(" for a quadradic formula - ax^2 + bx + c == 0).");
        System.out.println("You supply a, b and c.  And I'll print out x.");
        System.out.println(" NOTE: a cannot be zero");
    }

    /**
     * Prompt the user for an input value.
     */
    private int getInputFromUser(String message) {
        String input;
        boolean isInt;
        do {
            System.out.print(message);
            System.out.print(": ");

            input = readLine();
            isInt = isInteger(input);
            if (!isInt) {
                System.out.println("You must supply a valid integer value.");
                System.out.println("Please try again. \n");
            }
        }
        while (!isInt);

        return Integer.parseInt(input);
    }

    /**
     * Determine if the given string is a valid integer
     */
    private boolean isInteger(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException nfe) {
        }

        return false;
    }

    /**
     * Read a line of input from System.in
     *
     * @return All the text that the user inputted before they hit return.
     */
    private String readLine() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            return s;
        } catch (IOException e) {
            System.err.println("Sorry, I could not read the input.");
        }
        return "";
    }
}
