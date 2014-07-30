package com.phoenix.interview;

public class Quadratic {

    private int a, b, c;
    private double posRoot;
    private double negRoot;

    /**
     * create an instance of the class.
     *
     * @param a This is the first factor of the equation
     * @param b This is the second factor of the equation
     * @param c This is the third factor of the equation
     */
    public Quadratic(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;

    }

    public int getA() {

        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public double getPositiveRoot() {
        return posRoot;
    }

    public double getNegativeRoot() {
        return negRoot;
    }


    /**
     * Calculates the quadratic roots for 3 given integers
     *
     * @return an array with the 2 roots
     */
    public boolean calculateRoots() {
        if (a == 0) {
            throw new IllegalArgumentException("The first factor cannot be zero.");
        }

        int discriminant = (b * b) - (4 * a * c);
        if (discriminant > 0) {
            double squareFactor = (Math.sqrt(discriminant));
            double negDenom = -b - squareFactor;
            double posDenom = -b + squareFactor;

            negRoot = negDenom / (2 * a);
            posRoot = posDenom / (2 * a);
            return true;
        } else {
            // There are no real roots
            return false;
        }

    }
}