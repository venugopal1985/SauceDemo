package com.saucedemo.example;

public enum Browser
{
    CHROME("chrome"), EDGE("MicrosoftEdge");

    private final String browserName;

    Browser(String browserName)
    {
        this.browserName = browserName;
    }

    public String toString()
    {
        return browserName;
    }
}
