package com.vaadin.testbenchexample.pageobjectexample.pageobjects;

import com.vaadin.flow.component.html.testbench.ParagraphElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbenchexample.Log;

/**
 * This page object maps to {@link Log} and knows how to retrieve individual log
 * lines from the calculator log.
 */
public class LogElement extends VerticalLayoutElement {

    /**
     * Returns the text contents of the specified log row.
     *
     * @param row
     *            the row index
     * @return the text at the specified row.
     */
    public String getRow(int row) {
        return findRowElement(row).getText();
    }

    /**
     * Finds the Nth row element
     *
     * @param row
     *            the index of the row element
     * @return the Nth row element.
     */
    private TestBenchElement findRowElement(int row) {
        return $(TestBenchElement.class).id("messages")
                .$(ParagraphElement.class).get(row);
    }

}
