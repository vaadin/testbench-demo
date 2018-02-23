package com.vaadin.testbenchexample.pageobjectexample.pageobjects;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.annotations.Attribute;

/**
 * A page object for the KeyPad class.
 * <p>
 * Knows how to enter digits and operands in the calculator and read back the
 * display.
 */
@Attribute(name = "id", value = Attribute.SIMPLE_CLASS_NAME)
public class KeypadElement extends VerticalLayoutElement {

    /**
     * Pushes the specified button.
     *
     * @param symbol
     *            The character representation of the button to push.
     */
    public void press(char symbol) {
        getButton(symbol).click();
    }

    protected ButtonElement getButton(char symbol) {
        return $(ButtonElement.class).id("button_" + symbol);
    }

    /**
     * Performs the given calculation.
     * <p>
     * Presses the given symbols (<code>0-9,+,-,*,/</code> etc) in the string in
     * the given order and ends with a "=".
     * <p>
     * Returns the result of the calculation, as shown in the calculator
     * display.
     *
     * @param symbols
     *            the symbols to press
     * @return the result of the calculation
     */
    public String calculate(String symbols) {
        for (char symbol : symbols.toCharArray()) {
            press(symbol);
        }
        if (!symbols.endsWith("=")) {
            getButton('=').click();
        }
        return getDisplayValue();
    }

    /**
     * Gets the value currently shown in the display.
     *
     * @return the value shown in the display
     */
    public String getDisplayValue() {
        return $(TextFieldElement.class).id("display").getValue();
    }

}
