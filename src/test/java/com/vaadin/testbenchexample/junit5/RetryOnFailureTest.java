package com.vaadin.testbenchexample.junit5;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * That test is expected to run multiple times before passing, acting similarly to flaky test.
 * It uses Surefire (or Failsafe) maven plugins to performs multiple runs.
 * @see <a href="https://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html#rerunFailingTestsCount">surefire:test documentation</a>
 */
public class RetryOnFailureTest {

    private static AtomicInteger counter = new AtomicInteger(0);

    @Test // flaky test that requires multiple runs
    public void increment_untilValue() {
        Assertions.assertEquals(3, counter.incrementAndGet());
    }

}