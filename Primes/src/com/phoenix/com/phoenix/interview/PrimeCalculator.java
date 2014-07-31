package com.phoenix.com.phoenix.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



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


    public void update(Observable o, Object arg) {
        List<Integer> list = (List<Integer>)arg;
        listener.onNewNumber(list);
    }


    private static class PrimeGenerator extends Observable implements Runnable  {

        private List<Integer> primes = new ArrayList<Integer>();
        private int lastPrime = 0;
        private boolean stop = false;

        public void quit() {
            stop = true;
        }


        public void run() {
            stop = false;
            primes.add(2);  // need to add the first prime
            int x = 3;
            while (!stop) {
                 if (isPrime(x)) {
                     primes.add(x);
                     lastPrime = x;
                     synchronized (this) {
                         setChanged();
                         notifyObservers(primes);
                     }
                 }
                x++;
            }
        }

        private boolean isPrime(int x) {

            double max = Math.ceil(Math.min(Math.sqrt(x), new Double(lastPrime)));

            for (int n : primes) {
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

