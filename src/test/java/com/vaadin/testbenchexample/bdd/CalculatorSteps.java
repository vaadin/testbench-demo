package com.vaadin.testbenchexample.bdd;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;

import com.vaadin.testbenchexample.pageobjectexample.pageobjects.KeypadElement;

/**
 * This class maps steps in the calculator story files to TestBench operations
 * using the page objects from the page object example.
 * <p>
 * See <a href="http://jbehave.org">JBehave</a> for details.
 */
public class CalculatorSteps extends ChromeSteps {

    private KeypadElement calculator;

    @BeforeScenario
    public void setElements() {
        calculator = $(KeypadElement.class).first();
    }

    @When("I push $buttons")
    public void enter(String buttons) {
        calculator.calculate(buttons);
    }

    @Then("the display should show $result")
    public void displayShows(String result) {
        Assertions.assertEquals(result, calculator.getDisplayValue());
    }
}
