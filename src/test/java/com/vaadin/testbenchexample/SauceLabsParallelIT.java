package com.vaadin.testbenchexample;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.IPAddress;
import com.vaadin.testbench.parallel.ParallelTest;

/**
 * Test to be run parallel on multiple browsers using Sauce Labs.
 *
 */
public class SauceLabsParallelIT extends ParallelTest {
    @Test
    public void calculateOnePlusTwo() {
        getDriver().get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
        $(ButtonElement.class).id("button_1").click();
        $(ButtonElement.class).id("button_+").click();
        $(ButtonElement.class).id("button_2").click();
        $(ButtonElement.class).id("button_=").click();
        Assert.assertEquals("3.0",
                $(TextFieldElement.class).first().getValue());
    }
}
