package com.vaadin.testbenchexample.bdd;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbenchexample.pageobjectexample.pageobjects.CalculatorPageObject;

/**
 * This class maps steps in the calculator story files to TestBench
 * operations using the page objects from the page object example.
 *
 * See http://jbehave.org for details.
 */
public class CalculatorSteps extends TestBenchTestCase {

    private WebDriver driver;
    private CalculatorPageObject calculator;

    @BeforeScenario
    public void setUpWebDriver() {
        driver = new ChromeDriver();
        calculator = PageFactory.initElements(driver, CalculatorPageObject.class);
    }

    @AfterScenario
    public void tearDownWebDriver() {
        driver.quit();
    }

    @Given("I have the calculator open")
    public void theCalculatorIsOpen() {
        calculator.open();
    }

    @When("I push $buttons")
    public void enter(String buttons) {
        calculator.enter(buttons);
    }

    @Then("the display should show $result")
    public void displayShows(String result) {
        assertEquals(result, calculator.getResult());
    }
}
