package com.vaadin.testbenchexample.bdd;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;

/**
 * This class runs the scenarios listed in the simple_b_d_d_calculation_i_t.story
 * file using TestBench JUnit 5. See <a href="http://jbehave.org">JBehave</a> for details.
 */
public class SimpleBDDCalculationIT extends ChromeStory {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder().withDefaultFormats()
                                .withFormats(Format.CONSOLE, Format.TXT));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceChromeStepsFactory(configuration(), new CalculatorSteps());
    }
}
