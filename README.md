# Testbench demo

This project is a comprehensive collection of samples on how to use Vaadin TestBench.
If you are looking for a getting started type tutorial,
go to https://github.com/vaadin/testbench-tutorial.

## Running the maven example

The project is built using Maven. A similar setup can be done using other build systems as well.
The maven project can easily be imported into any IDE supporting
Maven or used via the command line interface. Maven can be downloaded from:
http://maven.apache.org.

The project produces a standard WAR file containing a simple calculator application written with Vaadin.
During the build process TestBench tests are automatically run against the final
war file using a Jetty server. Java classes (JUnit tests in this project) ending
in "ITCase" are considered integration tests. The tests can be run from the
command line by issuing the following command:

	mvn verify

If you are running specific tests directly via an IDE, you might need to deploy the
project beforehand to http://localhost:8080/. Any method will do, but the easiest
one is probably Maven and the jetty-maven-plugin:

	mvn jetty:run


### How the project was created

The project is based on the vaadin-archetype-application archetype and originally
created like this:

	mvn archetype:generate \
	-DarchetypeGroupId=com.vaadin \
	-DarchetypeArtifactId=vaadin-archetype-application \
	-DarchetypeVersion=LATEST \
	-DgroupId=your.company \
	-DartifactId=project-name \
	-Dversion=1.0 \
	-Dpackaging=war

After the project was generated, the pom.xml was edited and the following
dependencies were added:

	<dependency>
		<groupId>com.vaadin</groupId>
		<artifactId>vaadin-testbench</artifactId>
		<version>5.2.0</version>
		<scope>test</scope>
	</dependency>
	<dependency>
	    <groupId>com.vaadin</groupId>
	    <artifactId>vaadin-testbench-api</artifactId>
	    <version>${vaadin.version}</version>
	    <scope>test</scope>
	</dependency>
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.11</version>
		<scope>test</scope>
	</dependency>

And optionally for BDD with JBehave:

    <dependency>
        <groupId>org.jbehave</groupId>
        <artifactId>jbehave-core</artifactId>
        <version>3.7.5</version>
        <scope>test</scope>
    </dependency>

Additionally jetty-maven-plugin is used to automatically deploy the war to a Jetty server
during the integration-test phase and maven-failsafe-plugin is configured to run tests
named using the \*ITCase convention as well as all BDD tests in the com.vaadin.testbenchexample.bdd
package from the src/test/java directory. Check their setup in the pom.xml file.

That's it.


### Screenshot comparison in the example project

The screenshot comparison example tests are disabled by default. See the "Screenshot\_Comparison\_Tests.pdf"
document for instructions on how to enable them and, if necessary, update the reference images.

### Sample code classes explained

The source code for the application to be tested, a desktop calculator
application, is given in the `src/main/java` subfolder.

The TestBench tests for the application are located under the
`src/test/java` subfolder, in the
`com/vaadin/testbenchexample` package subfolder. They are as follows:

#### [SimpleCalculatorITCase.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/SimpleCalculatorITCase.java)
Demonstrates the basic use of WebDriver. Interacts with the buttons in the user
interface by clicking them and checks the resulting value. Uses the ElementQuery
API to access the elements.

#### [LoopingCalculatorITCase.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/LoopingCalculatorITCase.java)
Otherwise as the simple example, but shows how to use looping to produce
programmatic repetition to create a complex use case.

#### [ScreenshotITCase.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/ScreenshotITCase.java)
Shows how to compare screenshots, as described in
[Taking and Comparing Screenshots](https://vaadin.com/docs/-/part/testbench/testbench-screenshots.html). Some of the test cases include random input, so
they require masked screenshot comparison to mask the random areas out.

The example is ignored by default with an `@Ignore` annotation,
because the included images were taken with a specific browser on a specific
platform, so if you use another environment, they will fail. If you enable the
test, you will need to run the tests, copy the error images to the reference
screenshot folder, and mask out the areas with the alpha channel. Please see the
`example/Screenshot_Comparison_Tests.pdf` for details about how to
enable the example and how to create the masked reference images.

#### [SelectorExamplesITCase.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/SelectorExamplesITCase.java)
This example shows how to find elements in different ways; by using the
high-level ElementQuery API as well as low-level `By.xpath()`
selectors.

#### [VerifyExecutionTimeITCase.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/VerifyExecutionTimeITCase.java)
Shows how to time the execution of a test case and how to report it.

#### [AdvancedCommandsITCase.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/AdvancedCommandsITCase.java)
Demonstrates how to test context menus and tooltips (see
[Special Testing Topics](https://vaadin.com/docs/-/part/testbench/testbench-special.html)). It also shows how to send keypresses to a component and how to read values of table cells.

#### [pageobjectexample/PageObjectExampleITCase.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/pageobjectexample/PageObjectExampleITCase.java)
Shows how to create maintanable tests using the __Page Object Pattern__ that
separates the low-level page structure from the business logic, as described in
[Creating
Maintainable Tests](https://vaadin.com/docs/-/part/testbench/testbench-maintainable.html). The page object classes that handle low-level interaction
with the application views are in the `pageobjects` subpackage.

#### [bdd/CalculatorSteps.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/bdd/CalculatorSteps.java) and [bdd/SimpleCalculation.java](https://github.com/vaadin/testbench-demo/blob/master/src/test/java/com/vaadin/testbenchexample/bdd/SimpleCalculation.java)
Shows how to develop tests following the __behaviour-driven development__ (BDD)
model, by using the [JBehave framework](http://jbehave.org).
`SimpleCalculation.java` defines a JUnit-based user story with one
scenario, which is defined in `CalculatorSteps.java`. The scenario
reuses the page objects defined in the page object example (see above) for
low-level application view access and control. The example is described in
[Behaviour-Driven
Development](https://vaadin.com/docs/-/part/testbench/testbench-bdd.html).
