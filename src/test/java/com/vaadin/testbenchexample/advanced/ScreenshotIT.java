package com.vaadin.testbenchexample.advanced;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.HasCapabilities;

import com.vaadin.testbench.Parameters;
import com.vaadin.testbench.commands.TestBenchCommandExecutor;
import com.vaadin.testbenchexample.AbstractIT;
import com.vaadin.testbenchexample.pageobjectexample.pageobjects.CalcViewElement;

/**
 * This example contains usage examples of screenshot comparison feature.
 * <p>
 */
public class ScreenshotIT extends AbstractIT {

    /**
     * We'll want to perform some additional setup functions, so we override the
     * setUp() method.
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();

        // Set a fixed viewport size so the screenshot is always the same
        // resolution
        testBench().resizeViewPortTo(500, 400);

        // Define the directory for reference screenshots and for error files
        Parameters.setScreenshotReferenceDirectory("src/test/screenshots");
        Parameters.setScreenshotErrorDirectory("target/screenshot_errors");
    }

    @Test
    public void testOnePlusTwo() throws Exception {
        CalcViewElement calcView = $(CalcViewElement.class).first();
        // Change this calculation after running the test once to see how errors
        // in screenshots are verified.
        // The output is placed in target/screenshot_errors
        calcView.calculate("1+2");

        generateReferenceIfNotFound("oneplustwo");

        // Compare screen with reference image with id "oneplustwo" from the
        // reference image directory. Reference image filenames also contain
        // browser, version and platform.
        assertTrue(
                "Screenshot comparison for 'oneplustwo' failed, see "
                        + Parameters.getScreenshotErrorDirectory()
                        + " for error images",
                testBench().compareScreen("oneplustwo"));
    }

    /**
     * Generates a reference screenshot if no reference exists.
     * <p>
     * This method only exists for demonstration purposes. Normally you should
     * perform this task manually after verifying that the screenshots look
     * correct.
     *
     * @param referenceId
     *            the id of the reference image
     * @throws IOException
     */
    private void generateReferenceIfNotFound(String referenceId)
            throws IOException {
        String refName = ((TestBenchCommandExecutor) testBench())
                .getReferenceNameGenerator().generateName(referenceId,
                        ((HasCapabilities) getDriver()).getCapabilities());
        File referenceFile = new File(
                Parameters.getScreenshotReferenceDirectory(), refName + ".png");
        if (referenceFile.exists()) {
            return;
        }

        if (!referenceFile.getParentFile().exists()) {
            referenceFile.getParentFile().mkdirs();
        }

        File errorFile = new File(Parameters.getScreenshotErrorDirectory(),
                referenceFile.getName());

        // Take a screenshot and move it to the reference location
        testBench().compareScreen(referenceId);
        errorFile.renameTo(referenceFile);

        System.out.println("Created new reference file in " + referenceFile);

    }

}
