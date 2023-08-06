package com.vaadin.testbenchexample.junit5;

import io.github.bonigarcia.seljup.Arguments;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.HasElementQuery;
import com.vaadin.testbench.IPAddress;
import com.vaadin.testbench.TestBench;


/**
 * This example shows how to use SeleniumJupiter together with TestBench.
 * Developer should remember to wrap driver for enabling TestBench features.
 */
@ExtendWith(SeleniumJupiter.class)
public class SimpleCaseSeleniumIT implements HasElementQuery {

    private WebDriver driver;

    @BeforeEach

    public void beforeEach(@Arguments("--headless") ChromeDriver driver) { // driver injection by Selenium
        this.driver = TestBench.createDriver(driver); // TestBench driver proxy
        this.driver.get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
    }

    @Test
    public void calculateOnePlusTwo() {
        $(ButtonElement.class).id("button_1").click();
        $(ButtonElement.class).id("button_+").click();
        $(ButtonElement.class).id("button_2").click();
        $(ButtonElement.class).id("button_=").click();
        Assertions.assertEquals("3.0",
                $(TextFieldElement.class).first().getValue());
    }

    @Override
    public SearchContext getContext() {
        return driver;
    }
}
