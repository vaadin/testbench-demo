package com.vaadin.testbenchexample.pageobjectexample.pageobjects;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.orderedlayout.testbench.HorizontalLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbenchexample.CalcView;

/**
 * The CalculatorElement is a page object which knows how to enter digits and
 * operands in the calculator and read back the display.
 * <p>
 * This element corresponds to the {@link CalcView} class and the parent class
 * defines the tag name for the class.
 */
public class CalcViewElement extends HorizontalLayoutElement {

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

    public CommentWindowElement openCommentWindow() {
        $(ButtonElement.class).id("add-comment").click();

        // Need "onPage()" here as the window element is not a child of this
        // calc view (it's attached to the document body)
        return $(CommentWindowElement.class).onPage().first();
    }
}
