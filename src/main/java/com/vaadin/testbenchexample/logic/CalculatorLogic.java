package com.vaadin.testbenchexample.logic;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.function.SerializableRunnable;

/**
 * This class contains internal calculator logic which is quite uninteresting
 * from the point of testing.
 */
public class CalculatorLogic {

    private Queue<String> currentCalculation = new ArrayDeque<>();

    private Double currentValue = null;
    private double storedValue = 0.0;
    private char lastOperation;

    private Consumer<String> onResult;

    private SerializableRunnable onClear;

    public CalculatorLogic(SerializableConsumer<String> onResult,
            SerializableRunnable onClear) {
        this.onResult = onResult;
        this.onClear = onClear;
    }

    /**
     * Perform the given calculation operation.
     *
     * @param operation
     *            the operation to perform
     * @return the current calculation value
     */
    public double performOperation(char operation) {

        // Check if user clicked a number button
        if ('0' <= operation && operation <= '9') {

            // By setting the current value to null, we state that we're
            // starting from a clean slate.
            // If this is the case here, we re-init the current value to 0.
            if (currentValue == null) {
                currentValue = 0.0;
            }

            // Multiply current value by 10 (shifting the decimal point up)
            currentValue *= 10;

            // Parse an integer value out of the 'operation' character and
            // append it to the current value
            currentValue += Integer.parseInt("" + operation);

            // We do not want to do any additional processing when we're just
            // appending values..
            return currentValue;
        }

        // If we're "starting from a clean slate" and are not inputting a new
        // number, we'll want to set the current value to the last calculated
        // value
        if (currentValue == null) {
            currentValue = storedValue;
        }

        // Since we're here, we'll need to perform the last requested operation;
        // this would be one of +, -, * or /. We do this here to have the
        // correct values written into the log
        switch (lastOperation) {
        case '+':
            storedValue += currentValue;
            break;
        case '-':
            storedValue -= currentValue;
            break;
        case '*':
            storedValue *= currentValue;
            break;
        case '/':
            storedValue /= currentValue;
            break;
        default:
            storedValue = currentValue;
            break;
        }

        // We'll want to log the operation that we wish to perform, as well as
        // handling a 'clear' request.
        switch (operation) {
        case '+':
            currentCalculation.add(currentValue + " + ");
            break;

        case '-':
            currentCalculation.add(currentValue + " - ");
            break;

        case '*':
            currentCalculation.add(currentValue + " * ");
            break;

        case '/':
            currentCalculation.add(currentValue + " / ");
            break;

        case '=':
            // Set the stored value to the current value, and log
            currentCalculation.add(currentValue + " = " + storedValue);
            StringBuilder logValue = new StringBuilder();
            while (!currentCalculation.isEmpty()) {
                logValue.append(currentCalculation.poll());
            }

            onResult.accept(logValue.toString());
            break;

        case 'C':
            onClear.run();
            storedValue = 0;
            break;
        }

        // Update the last operation to the one we just logged, so that it gets
        // run on the entered value the next time this method is run
        lastOperation = operation;

        // Regardless of what we just did, we'll want to re-set the current
        // value to null, to indicate that the next time we're in this function,
        // we're starting from a clean slate, without existing input
        currentValue = null;

        // When an operation has been performed, return the stored value in
        // order to correctly update the display
        return storedValue;
    }
}
