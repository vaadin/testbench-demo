# TestBench Demo

This project is a collection of samples on how to use Vaadin TestBench.
If you are looking for more information on TestBench, see https://vaadin.com/testbench

|         Vaadin | TestBench demo                                     |
|---------------:|----------------------------------------------------|
|             24 | Current version                                    |
|             23 | https://github.com/vaadin/testbench-demo/tree/23.0 |
|             14 | https://github.com/vaadin/testbench-demo/tree/14.0 |
|             10 | https://github.com/vaadin/testbench-demo/tree/10.0 |
|              8 | https://github.com/vaadin/testbench-demo/tree/8.0  |
|              7 | https://github.com/vaadin/testbench-demo/tree/7.7  |

## Running the Example

The project is built using Maven. A similar setup can be done using other build systems as well.
The Maven project can easily be imported into any IDE supporting
Maven or used via the command line interface. Maven can be downloaded from:
http://maven.apache.org.

The project produces a standard WAR file containing a simple calculator application written with Vaadin.
During the build process TestBench tests are automatically run against the final
war file using a Jetty server. The tests can be run from the
command line by issuing the following command:

	mvn verify

If you are running specific tests directly via an IDE, you might need to deploy the
project beforehand to http://localhost:8080/. Any method will do, but the easiest
one is probably Maven and the jetty-maven-plugin:

	mvn jetty:run

### Sample code classes explained

The source code for the application to be tested, a desktop calculator
application, is given in the `src/main/java` subfolder.

The TestBench tests for the application are located under the
`src/test/java` subfolder.

### Screenshot comparison in the example project

The screenshot comparison example in `src/test/java/com/vaadin/testbenchexample/advanced/ScreenshotIT.java` grabs a screenshot and compares it to a reference file in `src/test/screenshots`. The first time it is run, it will automatically generate a reference screenshot in that folder, e.g. `src/test/screenshots/oneplustwo_mac_chrome_107.png` (exact file name depends on your operating system and browser). Normally the reference file generation would be handled by hand so you can verify that the screenshots look correct at the same time.

### JUnit5 support
JUnit5 is supported since TestBench 9, examples can be found at `src/test/java/com/vaadin/testbenchexample/junit5`.

### TestBench together with Selenium-Jupiter
It is possible to use TestBench together with <a href="https://bonigarcia.dev/selenium-jupiter/">Selenium-Jupiter JUnit5 extension</a>, see example test at `src/test/java/com/vaadin/testbenchexample/junit5/SimpleCaseSeleniumIT`.