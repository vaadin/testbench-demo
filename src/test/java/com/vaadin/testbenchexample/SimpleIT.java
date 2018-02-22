package com.vaadin.testbenchexample;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbenchexample.pageobjectexample.CalcViewIT;

/**
 * This is an example of very simple TestBench usage and introduces the element
 * query syntax.
 * <p>
 * In the real world, you would implement this test as
 * {@link CalcViewIT#calculateOnePlusTwo()}
 */
public class SimpleIT extends AbstractIT {

    @Test
    public void calculateOnePlusTwo() {
        $(ButtonElement.class).id("button_1").click();
        $(ButtonElement.class).id("button_+").click();
        $(ButtonElement.class).id("button_2").click();
        $(ButtonElement.class).id("button_=").click();
        Assert.assertEquals("3.0",
                $(TextFieldElement.class).first().getValue());
    }

}
