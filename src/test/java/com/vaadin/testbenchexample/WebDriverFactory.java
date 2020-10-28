package com.vaadin.testbenchexample;

import com.vaadin.testbench.TestBench;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class WebDriverFactory {
    public final static String TEST_PROPS_FILENAME = "src/test/resources/testing.properties";
    public final static String PROP_WEBDRIVER = "selenium.webdriver";
    public final static String DEFAULT_WEBDRIVER = "Firefox";

    private final static Logger LOGGER = Logger.getAnonymousLogger();

    private final static WebDriverFactory theInstance = new WebDriverFactory();

    public static WebDriverFactory getInstance(){
        return WebDriverFactory.theInstance;
    }

    private final String webDriverName;

    private WebDriverFactory(){
        Properties prop = new Properties();
        File f = new File(TEST_PROPS_FILENAME);
        try {
            InputStream input = new FileInputStream(f);
            prop.load(input);
            LOGGER.info("Properties file '"+f.getAbsolutePath()+"' loaded okay.");
            Set<Object> keys = prop.keySet();
            for (Object key : keys.toArray()){
                LOGGER.info("Properties file '"+f.getAbsolutePath()+"', found key '" + key.toString() + "'.");
            }
        }
        catch (IOException | NullPointerException exc) {
            LOGGER.warning("Could not load properties file '"+f.getAbsolutePath()+"'.");
        }

        this.webDriverName = prop.getProperty(PROP_WEBDRIVER, DEFAULT_WEBDRIVER);
        LOGGER.info("WebDriver for '"+this.webDriverName+"' chosen through property '"+PROP_WEBDRIVER+"'.");
    }

    public WebDriver createDriver(){
        if (webDriverName.equalsIgnoreCase("chrome")){
            return TestBench.createDriver(new ChromeDriver());
        }
        else if (webDriverName.equalsIgnoreCase("firefox")) {
            return TestBench.createDriver(new FirefoxDriver());
        }
        else if (webDriverName.equalsIgnoreCase("edge")){
            return TestBench.createDriver(new EdgeDriver());
        }
        else if (webDriverName.equalsIgnoreCase("opera")){
            return TestBench.createDriver(new OperaDriver());
        }
        else if (webDriverName.equalsIgnoreCase("internetexplorer")){
            return TestBench.createDriver(new InternetExplorerDriver());
        }
        else if (webDriverName.equalsIgnoreCase("safari")){
            return TestBench.createDriver(new SafariDriver());
        }
        throw new IllegalArgumentException("At this time I do not understand '"+webDriverName+"', extend me.");
    }
}
