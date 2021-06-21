package com.schmitt.selenium.prometheus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumConfig {

    private WebDriver driver;

	public SeleniumConfig() {       
		// for Windows OS we can set following exe to be executed
		//System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		
		// for Linux OS / docker container we set following binary to be executed
		System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
		
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        
        driver = new FirefoxDriver(options);        
    }

    public WebDriver getDriver() {
        return driver;
    }
}
