package com.vaadin.testbenchexample.junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.BrowserTestBase;
import com.vaadin.testbench.DriverSupplier;
import com.vaadin.testbench.IPAddress;
import com.vaadin.testbench.TestBench;

/**
 * Base class for all our JUnit5 tests, allowing us to change the applicable driver,
 * test URL or other configurations in one place.
 */
public abstract class AbstractJUnit5IT extends BrowserTestBase implements DriverSupplier {

    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("com.vaadin.testbench.Parameters.headless")) {
            options.addArguments("--headless=new");
        }
        return TestBench.createDriver(new ChromeDriver(options));
    }

    @BeforeEach
    public void setUp() {
        getDriver().get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
    }

    @AfterEach
    public void afterEach() {
        getDriver().close();
    }

    // IT test for demo purposes
    protected void calculate_resultEquals(String a, String op, String b, String result) {
        $(ButtonElement.class).id("button_" + a).click();
        $(ButtonElement.class).id("button_" + op).click();
        $(ButtonElement.class).id("button_" + b).click();
        $(ButtonElement.class).id("button_=").click();
        Assertions.assertEquals(result,
                $(TextFieldElement.class).first().getValue());
    }

}
