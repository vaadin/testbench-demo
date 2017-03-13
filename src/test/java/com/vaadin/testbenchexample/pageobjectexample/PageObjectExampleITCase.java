package com.vaadin.testbenchexample.pageobjectexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;
import com.vaadin.testbenchexample.TestBase;
import com.vaadin.testbenchexample.pageobjectexample.pageobjects.AddCommentPageObject;
import com.vaadin.testbenchexample.pageobjectexample.pageobjects.CalculatorPageObject;
import com.vaadin.testbenchexample.pageobjectexample.pageobjects.LogPageObject;

/**
 * A simple test case using page objects.
 */
public class PageObjectExampleITCase extends TestBase {

    public static final String COMMENT = "That was a simple calculation";
    private CalculatorPageObject calculator;
    private LogPageObject log;

    /**
     * Creates a WebDriver instance and the page objects used.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        // Use the PageFactory to automatically initialize fields.
        calculator = PageFactory.initElements(driver, CalculatorPageObject.class);
        log = PageFactory.initElements(driver, LogPageObject.class);
    }

    /**
     * @see com.vaadin.testbenchexample.SimpleCalculatorITCase#testOnePlusTwo()
     */
    @Test
    public void testOnePlusTwo() throws Exception {
        calculator.open();
        // Enter 1+2 and verify the result
        assertEquals("3.0", calculator.enter("1").add("2").getResult());
        // Verify the log
        assertEquals("1.0 + 2.0 = 3.0", log.getRow(0));
    }
    @Test
    public void testCalculateWithLongNumbers() throws Exception {
        calculator.open();
        assertEquals("1337.0", calculator.enter("1337").multiplyBy("5").divideBy("5").getResult());
        assertEquals("-4958.0", calculator.enter("42").subtract("5000").getResult());
    }

    @Test
    public void testAlternateAPI() throws Exception {
        calculator.open();
        assertEquals("1337.0", calculator.enter("1337*5/5").getResult());
        assertEquals("-4958.0", calculator.enter("42-5000").getResult());
    }

    /**
     * @see com.vaadin.testbenchexample.AdvancedCommandsITCase#useContextMenuToAddCommentRow()
     */
    @Test
    public void testAddCommentRowToLog() throws Exception {
        calculator.open();
        // just do some math first
        calculator.enter("1+2");
        // Verify the result of the calculation
        assertEquals("3.0", calculator.getResult());

        // Add a comment
        AddCommentPageObject addComment = log.openAddCommentWindow();
        $(WindowElement.class).$(TextFieldElement.class).first().setValue(COMMENT);
        $(WindowElement.class).$(TextFieldElement.class).first().sendKeys(Keys.RETURN);

        // Ensure the comment window is closed
        assertFalse(addComment.isOpen());

        // Verify that the log contains our comment
//        assertTrue(log.getRow(1).contains(COMMENT));
    }
}
