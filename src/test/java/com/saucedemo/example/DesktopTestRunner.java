package com.saucedemo.example;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import gherkin.events.PickleEvent;
import gherkin.pickles.Pickle;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.*;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
// @formatter:off
@CucumberOptions(
    // Features
    features = "src/test/resources/features",

    // Glue
    glue = {"com/saucedemo/example/stepdefs"},
    snippets = SnippetType.CAMELCASE,

    // Plugins
    plugin = {
        // Cucumber report location
        "json:target/json-reports/cucumber-desktop.json",
        "usage:target/usage-reports/cucumber-usage-desktop.json"
    }
)
// @formatter:on
public class DesktopTestRunner
{
    protected TestNGCucumberRunner testNGCucumberRunner;

    @AfterClass(alwaysRun = true)
    public void tearDownClass()
    {
        if (testNGCucumberRunner == null)
        {
            return;
        }

        testNGCucumberRunner.finish();
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void feature(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper cucumberFeatureWrapper)
            throws Throwable
    {
        PickleEvent event = pickleWrapper.getPickleEvent();
        Pickle pickle = event.pickle;
        System.out.printf("Running scenario: %s\n", pickle.getName());

        // the 'featureWrapper' parameter solely exists to display the feature file in a test report
        testNGCucumberRunner.runScenario(event);
    }

    @DataProvider
    public Object[][] scenarios()
    {
        if (testNGCucumberRunner == null)
        {
            return null;
        }

        return testNGCucumberRunner.provideScenarios();
    }

    @Parameters({"browser", "version", "platformName"})
    @BeforeClass(alwaysRun = false)
    public void setUpDesktopProfile(String browser, String version, String platformName
    )
    {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

        TestPlatform.Builder builder = new TestPlatform.Builder();

        // @formatter:off
        TestPlatform tp = builder
                .browser(Browser.valueOf(browser))
                .browserVersion(version)
                .platformName(platformName)
                .build();
        // @formatter:on

    }
}
