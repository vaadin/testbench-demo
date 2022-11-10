package com.vaadin.testbenchexample.junit5;

import org.junit.jupiter.api.Nested;
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
public class ParallelExecutionIT extends AbstractJUnit5IT {

    /**
     * This tests will run concurrently according to configuration.
     */
    @Nested
    @Execution(ExecutionMode.CONCURRENT)
    @RunLocally(Browser.CHROME)
    public class NestedConcurrent {
        @BrowserTest
        public void clickButton_notificationShown1() {
            calculate_resultEquals("3", "+", "3", "6.0");
        }

        @BrowserTest
        public void clickButton_notificationShown2() {
            calculate_resultEquals("2", "-", "2", "0.0");
        }

        @BrowserTest
        public void clickButton_notificationShown3() {
            calculate_resultEquals("2", "*", "2", "4.0");
        }

        @BrowserTest
        public void clickButton_notificationShown4() {
            calculate_resultEquals("2", "/", "2", "1.0");
        }
    }

    /**
     * This tests will be run by the same thread.
     */
    @Nested
    @Execution(ExecutionMode.SAME_THREAD)
    @RunLocally(Browser.CHROME)
    public class NestedSameThread {
        @BrowserTest
        public void clickButton_notificationShown1() {
            calculate_resultEquals("2", "+", "2", "4.0");
        }

        @BrowserTest
        public void clickButton_notificationShown2() {
            calculate_resultEquals("2", "-", "2", "0.0");
        }

        @BrowserTest
        public void clickButton_notificationShown3() {
            calculate_resultEquals("2", "*", "2", "4.0");
        }

        @BrowserTest
        public void clickButton_notificationShown4() {
            calculate_resultEquals("2", "/", "2", "1.0");
        }
    }

}
