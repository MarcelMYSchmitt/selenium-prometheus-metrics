package com.schmitt.selenium.prometheus;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumConfig {

    private WebDriver driver;

    @SuppressWarnings("deprecation")
	public SeleniumConfig() {
        Capabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver(capabilities);
        driver.manage()
            .timeouts()
            .implicitlyWait(5, TimeUnit.SECONDS);
    }

    static {
    	// windows setting -> in docker we use linux as OS, so we have to install geckodriver and set the right PATH
        System.setProperty("webdriver.gecko.driver",  "geckodriver.exe");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
