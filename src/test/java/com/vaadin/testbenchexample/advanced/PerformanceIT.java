package com.vaadin.testbenchexample.advanced;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbenchexample.AbstractIT;
import com.vaadin.testbenchexample.SimpleIT;
import com.vaadin.testbenchexample.pageobjectexample.pageobjects.KeypadElement;

/**
 * This case demonstrates usage of execution time reporting.
 */
public class PerformanceIT extends AbstractIT {

    /**
     * Does the same thing as in {@link SimpleIT} and verifies server don't
     * spend too much time during the process. Also the test makes sure the time
     * spent by browser to render the UI within sane limits.
     *
     * @throws Exception
     */
    @Test
    @Ignore("Flow is currently missing forceSync")
    public void verifyServerExecutionTime() throws Exception {
        long currentSessionTime = testBench().totalTimeSpentServicingRequests();
        $(KeypadElement.class).first().calculate("1+2");

        long timeSpentByServerForSimpleCalculation = testBench()
                .totalTimeSpentServicingRequests() - currentSessionTime;

        System.out.println("Calculating 1+2 took about "
                + timeSpentByServerForSimpleCalculation
                + "ms in servlets service method.");

        if (timeSpentByServerForSimpleCalculation > 30) {
            fail("Simple calculation shouldn't take "
                    + timeSpentByServerForSimpleCalculation + "ms!");
        }

        long totalTimeSpentRendering = testBench().totalTimeSpentRendering();
        System.out
                .println("Rendering UI took " + totalTimeSpentRendering + "ms");
        if (totalTimeSpentRendering > 1000) {
            fail("Rendering UI shouldn't take " + totalTimeSpentRendering
                    + "ms!");
        }

        assertEquals("3.0",
                $(TextFieldElement.class).first().getAttribute("value"));
    }
}
