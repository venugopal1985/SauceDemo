package com.saucedemo.example;

import cucumber.api.Scenario;
import cucumber.api.java8.En;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements En
{

    public static final String C_WEB_DRIVERS_CHROMEDRIVER_WIN_64_CHROMEDRIVER_EXE = "C:\\webDrivers\\chromedriver-win64\\chromedriver.exe";
    public static final String C_EDGEDRIVER_WIN_64_MSEDGEDRIVER_EXE = "C:\\webDrivers\\edgedriver_win64\\msedgedriver.exe";
    public static final String URL = "https://saucedemo.com";

    public static RemoteWebDriver getDriverInstance(TestPlatform tp, Scenario scenario)
    {

        return getDesktopDriverInstance(tp, scenario);

    }


    private static RemoteWebDriver getDesktopDriverInstance(TestPlatform tp, Scenario scenario)
    {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", tp.getBrowser().toString());
        caps.setCapability("version", tp.getBrowserVersion());
        caps.setCapability("platform", tp.getPlatformName());
        String resultsURL = "";

        // Set ACCEPT_SSL_CERTS  variable to true
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        RemoteWebDriver driver = null;
            switch (tp.getBrowser())
            {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    HashMap<String, Object> googOpts = new HashMap<String, Object>();
                    googOpts.put("w3c", true);
                    chromeOptions.setCapability("goog:chromeOptions", googOpts);
                    chromeOptions.merge(caps);

                    System.setProperty("webdriver.chrome.driver", C_WEB_DRIVERS_CHROMEDRIVER_WIN_64_CHROMEDRIVER_EXE);
                    driver = new ChromeDriver(chromeOptions);
                    driver.get(URL);
                    break;

                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.merge(caps);
                    System.setProperty("webdriver.edge.driver", C_EDGEDRIVER_WIN_64_MSEDGEDRIVER_EXE);
                    driver = new EdgeDriver(edgeOptions);
                    driver.get(URL);
                    break;
                default:
                    throw new RuntimeException("Unsupported browserName: " + tp.getBrowser());
            }
        driver.manage().window().maximize();
        return driver;
    }
}
