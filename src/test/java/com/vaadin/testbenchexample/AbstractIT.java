package com.vaadin.testbenchexample;

import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.vaadin.testbench.IPAddress;
import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Base class for all our tests, allowing us to change the applicable driver,
 * test URL or other configurations in one place.
 */
public abstract class AbstractIT extends TestBenchTestCase {

    @Rule
    public ScreenshotOnFailureRule rule = new ScreenshotOnFailureRule(this,
            true);

    @Before
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("com.vaadin.testbench.Parameters.headless")) {
            options.addArguments("--headless");
        }
        setDriver(TestBench.createDriver(new ChromeDriver(options)));
        getDriver().get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
        getCommandExecutor().waitForVaadin();
    }

}
