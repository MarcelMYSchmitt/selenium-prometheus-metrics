package com.schmitt.selenium.prometheus;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SeleniumPrometheusLoginTester {

	private SeleniumConfig config;
	
	private WebDriver webDriver;
	
	@Autowired
	private SeleniumPrometheusMetricDefinition seleniumPrometheusMetricDefinition;
	
	@Value("${selenium.testing.startUrl}")
	private String startUrl;

	@Value("${selenium.testing.username}")
	private String username;
	
	@Value("${selenium.testing.userpassword}")
	private String userpassword;

	@Value("${selenium.testing.landingUrl}")
	private String landingUrl;
	
	
    // we probe every 150 seconds
	@Scheduled(fixedRate = 150000)
	public void getTitle() throws InterruptedException {
		
		config = new SeleniumConfig();
		config.getDriver().get(startUrl);
		
		webDriver = config.getDriver();
		String url = this.config.getDriver().getTitle();
		System.out.println("Site Title: " + url);

		TimeUnit.SECONDS.sleep(2);

		WebElement cookieLayerCloseElement = webDriver.findElement(By.id("test-cookie-layer-close"));
		cookieLayerCloseElement.click();

		TimeUnit.SECONDS.sleep(2);

		WebElement loginButtonElement = webDriver.findElement(By.id("carousel_login_button"));
		loginButtonElement.click();

		TimeUnit.SECONDS.sleep(2);

		WebElement usernameButtonElement = webDriver.findElement(By.id("username"));
		usernameButtonElement.sendKeys(username);

		TimeUnit.SECONDS.sleep(2);

		WebElement continueButtonElement = webDriver.findElement(By.id("continue"));
		continueButtonElement.click();

		TimeUnit.SECONDS.sleep(2);

		WebElement continueLoginButtonElement = webDriver.findElement(By.id("login-with-password"));
		continueLoginButtonElement.click();

		TimeUnit.SECONDS.sleep(2);

		WebElement passwordButtonElement = webDriver.findElement(By.id("password"));
		passwordButtonElement.sendKeys(userpassword);

		TimeUnit.SECONDS.sleep(2);

		WebElement confirmButtonElement = webDriver.findElement(By.id("confirm"));
		confirmButtonElement.click();

		TimeUnit.SECONDS.sleep(5);

		String currentUrl = webDriver.getCurrentUrl();
		if(currentUrl.contains(landingUrl)){
			System.out.println("Login successful, setting Login Metric 'LOGIN_AVAILABILITY' to 1.");	
						
			TimeUnit.SECONDS.sleep(2);
			
			seleniumPrometheusMetricDefinition.setLoginAvailabilityStatus(1);
			TimeUnit.SECONDS.sleep(2);
		} else {
			System.out.println("Login seems not to be successful, setting Login Metric 'LOGIN_AVAILABILITY' to 0.");	

			seleniumPrometheusMetricDefinition.setLoginAvailabilityStatus(0);
			TimeUnit.SECONDS.sleep(2);
		}
		
		webDriver.close();
	}
}
