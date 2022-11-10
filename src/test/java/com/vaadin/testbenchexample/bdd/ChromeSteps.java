package com.vaadin.testbenchexample.bdd;

import org.jbehave.core.annotations.BeforeScenario;

import com.vaadin.testbench.AbstractBrowserDriverTestBase;
import com.vaadin.testbench.IPAddress;

/**
 * Base class for local Chrome tests
 */
public class ChromeSteps extends AbstractBrowserDriverTestBase {

    // open webpage before test
    @BeforeScenario(order = 100)
    public void beforeScenario() {
        getDriver().get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
    }

}
