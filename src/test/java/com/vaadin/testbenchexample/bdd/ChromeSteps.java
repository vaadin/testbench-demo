package com.vaadin.testbenchexample.bdd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.AbstractBrowserDriverTestBase;
import com.vaadin.testbench.IPAddress;

/**
 * Base class for local Chrome tests
 */
public class ChromeSteps extends AbstractBrowserDriverTestBase {

    // setting Chrome driver before test
    @BeforeScenario(order = 100)
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        setDriver(new ChromeDriver());
        getDriver().get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
    }

    // closing Chrome driver after test
    @AfterScenario
    public void tearDownWebDriver() {
        getDriver().quit();
    }

}
