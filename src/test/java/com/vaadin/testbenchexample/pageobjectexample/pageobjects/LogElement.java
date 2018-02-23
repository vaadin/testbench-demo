package com.vaadin.testbenchexample.pageobjectexample.pageobjects;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.html.testbench.ParagraphElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.annotations.Attribute;
import com.vaadin.testbenchexample.Log;

/**
 * This page object maps to {@link Log} and knows how to retrieve individual log
 * lines from the calculator log.
 */
@Attribute(name = "id", value = Attribute.SIMPLE_CLASS_NAME)
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

    public CommentWindowElement openCommentWindow() {
        $(ButtonElement.class).id("add-comment").click();
        // Need "onPage()" here as the window element is not a child of this
        // calc view (it's attached to the document body)
        return $(CommentWindowElement.class).onPage().first();
    }

}
