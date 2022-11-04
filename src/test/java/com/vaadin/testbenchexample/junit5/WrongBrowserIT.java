package com.vaadin.testbenchexample.junit5;

import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.Capabilities;

import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.BrowserUtil;

/**
 * In this example you can see how to filter test to run only on selected
 * browser. {@code Capabilities} are being injected by TestBench.
 */
@RunLocally(Browser.CHROME) // the driver (could be multi-browser remote driver - Sauce)
public class WrongBrowserIT extends AbstractJUnit5IT {

    @BrowserTest // capabilities are injected by TestBench
    public void clickButton_notificationShown_safari(Capabilities capabilities) {
        Assumptions.assumeTrue(BrowserUtil.isSafari(capabilities),  // that test will be ignored
                "Safari test only, will not run on: " + getCapabilities().getBrowserName());
        calculate_resultEquals("2", "+", "2", "4.0");
    }

    @BrowserTest // use capabilities already injected into parent BrowserTestBase
    public void clickButton_notificationShown_chrome() {
        Assumptions.assumeTrue(BrowserUtil.isChrome(getCapabilities()));
        calculate_resultEquals("2", "+", "2", "4.0");
    }

}
