package com.phoenix.com.phoenix.interview;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class PrimesUi extends JDialog implements PrimeCalculatorListener {
    private JPanel contentPane;
    private JButton buttonStop;
    private JButton buttonCancel;
    private JButton buttonStart;
    private JTextField textField1;
    private PrimeCalculator calculator;

    public PrimesUi() {
        calculator = new PrimeCalculator(this);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonStop);

        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStart();
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStop();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onStart() {
        calculator.Start();
    }

    private void onStop() {
        calculator.Stop();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void onNewNumber(List<Integer> numbers) {
        Integer lastPrime = numbers.get(numbers.size() - 1);

        textField1.setText(lastPrime.toString());
    }


}
