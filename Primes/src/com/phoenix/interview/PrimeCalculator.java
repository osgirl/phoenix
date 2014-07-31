package com.phoenix.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * This class will calculate prime numbers until you tell it to stop.
 * 
 * When a new prime number is found, the listener will be notified.
 */
public class PrimeCalculator implements Observer {
    PrimeGenerator generator;
    Thread t;
    PrimeCalculatorListener listener;


    public PrimeCalculator(PrimeCalculatorListener listener) {
        this.listener = listener;
        generator = new PrimeGenerator();
        generator.addObserver(this);
    }

    public void Start() {
        t = new Thread(generator);
        t.start();
    }

    public void Stop() {
        if (t != null)
        {
            generator.quit();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        List<Integer> list = (List<Integer>)arg;
        listener.onNewNumber(list);
    }

    /**
     * This class will do the actual prime list generation
     */
    private static class PrimeGenerator extends Observable implements Runnable {

        private List<Integer> primes = new ArrayList<>();
        private int lastPrime = 0;
        private boolean stop = false;

        public void quit() {
            stop = true;
        }

        @Override
        public void run() {
            stop = false;
            primes.add(2);  // need to add the first prime
            int x = 3;
            while (!stop) {
                 if (isPrime(x)) {
                     primes.add(x);
                     lastPrime = x;
                     
                     // let the observer know there was a change
                     synchronized (this) {
                         setChanged();
                         notifyObservers(primes);
                     }
                 }
                x++;
            }
        }

        /**
         * check if a given integer is prime
         * 
         * To find primeness, you see if it's devisable by any of the 
         * prime numbers below it.  If it's not evenly dividable by
         * any of the prime numbers lower than it, then its prime
         * 
         * @param x The number to check for primeness.
         * @return True if the number is a prime number, else false.
         */
        private boolean isPrime(int x) {

            // the highest value you ever need to test against
            // is the square of the number you are testing (rounded up)
            double max = Math.ceil(Math.min(Math.sqrt(x), new Double(lastPrime)));

            for (int n : primes) {
                // if it is divisible by this prime, it isn't prime.
                if (x % n == 0) {
                    return false;
                }

                if (n > max) {
                    break;
                }
            }

            // if we got this far, we are prime!
            return true;
        }
    }
}

