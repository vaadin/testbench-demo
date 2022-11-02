package com.vaadin.testbenchexample.junit5;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.parallel.Browser;

/**
 * This example shows how to make use of JUnit5 parallel feature.
 * <p>
 * Configuration file located at {@code resources/junit-platform.json} is required.
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-parallel-execution">JUnit5 writing tests parallel execution</a>
 */
@RunLocally(Browser.CHROME)
@Execution(ExecutionMode.SAME_THREAD)
public class ParallelExecutionIT extends AbstractJUnit5IT {

    @BrowserTest
    public void clickButton_notificationShown1() {
        calculate("2", "+", "2", "4.0");
    }

    @BrowserTest
    public void clickButton_notificationShown2() {
        calculate("2", "-", "2", "0.0");
    }

    @BrowserTest
    public void clickButton_notificationShown3() {
        calculate("2", "*", "2", "4.0");
    }

    @BrowserTest
    public void clickButton_notificationShown4() {
        calculate("2", "/", "2", "1.0");
    }

}
