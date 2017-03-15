package com.vaadin.testbenchexample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.GridLayoutElement;
import com.vaadin.testbench.elements.PanelElement;
import com.vaadin.testbench.elements.TextFieldElement;

/**
 * This example demonstrates usage of various element selection methods.
 */
public class SelectorExamplesITCase extends TestBase {

    /**
     * This example calculates 1+2 using different finding alternatives. It is a
     * demonstration of some of the ways of using TestBench4 element finders.
     * 
     * @throws Exception
     */
    @Test
    public void testButtonFindingAlternatives() throws Exception {

        // When IDs are not available, this is the simplest and generally
        // preferred way of finding a Vaadin widget in your application when it
        // can be uniquely identified by its caption.
        $(ButtonElement.class).caption("1").first().click();

        // ElementQueries allow you to define a search hierarchy. Searching for
        // a button with given caption in a specific container can be done with
        // .in() or .childOf() function. We are looking for a button with
        // caption "+" somewhere inside a Panel with caption "Calculator".
        $(PanelElement.class).caption("Calculator").$(ButtonElement.class)
                .caption("+").first().click();

        // You can also get all the ButtonElements with .all(). You get a list
        // of Elements that can be used in many ways. Filtering with
        // ElementQuery features can be used to limit the results. This will
        // find all direct children of Keypad (which is a GridLayout).
        for (ButtonElement button : $(GridLayoutElement.class).$$(
                ButtonElement.class).all()) {
            if ("2".equals(button.getText())) {
                button.click();
            }
        }

        // If $() doesn't work for you, or you know exactly what kind of
        // selector string you want to enter, you can do so manually using
        // Selenium like By.vaadin(String vaadinSelector).
        getDriver().findElement(By.vaadin("//VButton[caption='=']")).click();

        // Finally, test that we actually got the right answer from our clicking
        // with the different ways of selecting buttons. Here, we get the first
        // TextField Element we come across, and read its "value" attribute.
        assertEquals("3.0",
                $(TextFieldElement.class).first().getAttribute("value"));
    }
}
