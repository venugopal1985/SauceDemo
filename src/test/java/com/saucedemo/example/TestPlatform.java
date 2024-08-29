package com.saucedemo.example;

public class TestPlatform
{
    private Browser browser;
    private String browserVersion;
    private String platformName;
    private String platformVersion;

    private TestPlatform()
    {
    }
    public Browser getBrowser()
    {
        return browser;
    }

    public String getBrowserVersion()
    {
        return browserVersion;
    }

    public String getPlatformName()
    {
        return platformName;
    }

    public static class Builder
    {
        private Browser browser;
        private String browserVersion;
        private String platformName;

        public Builder()
        {

        }

        public Builder browser(Browser browser)
        {
            this.browser = browser;
            return this;
        }

        public Builder browserVersion(String browserVersion)
        {
            this.browserVersion = browserVersion;
            return this;
        }

        public Builder platformName(String platformName)
        {
            this.platformName = platformName;
            return this;
        }

        public TestPlatform build()
        {
            TestPlatform tp = new TestPlatform();
            tp.browser = browser;
            tp.browserVersion = browserVersion;
            tp.platformName = platformName;
            return tp;
        }
    }

}
