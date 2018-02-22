package com.vaadin.testbenchexample.bdd;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.IPAddress;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbenchexample.pageobjectexample.pageobjects.CalcViewElement;

/**
 * This class maps steps in the calculator story files to TestBench operations
 * using the page objects from the page object example.
 *
 * See http://jbehave.org for details.
 */
public class CalculatorSteps extends TestBenchTestCase {

    private CalcViewElement calculator;

    @BeforeScenario
    public void setUpWebDriver() throws Exception {
        setDriver(new ChromeDriver());
        getDriver().get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
        calculator = $(CalcViewElement.class).first();
    }

    @AfterScenario
    public void tearDownWebDriver() {
        getDriver().quit();
    }

    @When("I push $buttons")
    public void enter(String buttons) {
        calculator.calculate(buttons);
    }

    @Then("the display should show $result")
    public void displayShows(String result) {
        assertEquals(result, calculator.getDisplayValue());
    }
}
