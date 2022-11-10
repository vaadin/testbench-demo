package com.vaadin.testbenchexample.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.HasElementQuery;
import com.vaadin.testbench.IPAddress;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.browser.BrowserTestInfo;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbenchexample.pageobjectexample.CalcViewIT;

/**
 * This is an example of very simple TestBench JUnit5 usage and introduces the element
 * query syntax.
 * <p>
 * In the real world, you would implement this test similar to
 * {@link CalcViewIT#calculateOnePlusTwo()}
 * </p>
 * <p>
 * This example does not extend any TestBench class!
 * </p>
 */
@RunLocally(Browser.CHROME)
public class SimpleCaseIT implements HasElementQuery { // not extending any TestBench classes!

    private WebDriver driver;

    @BeforeEach
    public void beforeEach(BrowserTestInfo browserTestInfo) { // driver injection
        this.driver = browserTestInfo.driver();
        this.driver.get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
    }

    @BrowserTest // test with TestBench 9 annotation
    public void calculateOnePlusTwo() {
        $(ButtonElement.class).id("button_1").click();
        $(ButtonElement.class).id("button_+").click();
        $(ButtonElement.class).id("button_2").click();
        $(ButtonElement.class).id("button_=").click();
        Assertions.assertEquals("3.0",
                $(TextFieldElement.class).first().getValue());
    }

    @Override
    public SearchContext getContext() { // required for $( )
        return driver;
    }
}
