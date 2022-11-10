package com.vaadin.testbenchexample.bdd;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JupiterStories;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import com.vaadin.testbench.AbstractBrowserDriverTestBase;
import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.browser.BrowserTestInfo;
import com.vaadin.testbench.parallel.Browser;

import static java.util.Arrays.asList;

/**
 * Parent class for local Chrome JBehave tests. {@link WebDriver} is managed by
 * TestBench.
 */
@RunLocally(Browser.CHROME)
public abstract class ChromeStory extends JupiterStories {

    protected WebDriver driver;

    @BeforeEach
    public void setupDriver(BrowserTestInfo browserTestInfo) {
        driver = browserTestInfo.driver();
    }

    @AfterEach
    public void closeDriver() {
        driver.close();
    }

    @Override
    @BrowserTest
    public void run() {
        super.run();
    }

    // get story path from test class
    @Override
    public List<String> storyPaths() {
        StoryPathResolver pathResolver = configuredEmbedder().configuration().storyPathResolver();
        String storyPath = pathResolver.resolve(this.getClass());
        return asList(storyPath);
    }

    // extension to InstanceStepsFactory that sets driver in steps instances
    public class InstanceChromeStepsFactory extends InstanceStepsFactory {
        public InstanceChromeStepsFactory(Configuration configuration, Object... stepsInstances) {
            super(configuration, stepsInstances);
            Arrays.stream(stepsInstances)
                    .filter(AbstractBrowserDriverTestBase.class::isInstance)
                    .map(AbstractBrowserDriverTestBase.class::cast)
                    .forEach(s -> s.setDriver(driver));
        }
    }

}
