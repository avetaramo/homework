package com.test.upwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterPage extends SearchResultPage {

	public FilterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = SearchPageConstants.CATEGORY_WEB_DEVELOPMENT_XPATH)
	WebElement webDevelopment;

	public FilterPage filterByHourlyRate(String rate) {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( SearchPageConstants.CATEGORY_WEB_DEVELOPMENT_XPATH)));
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xpath = String.format(SearchPageConstants.HOURLY_RATE_XPATH, rate);
		driver.findElement(By.xpath(xpath)).click();
		return new FilterPage(driver);
	}

	public FilterPage filterByWebDevelopmentCategory() {
			webDevelopment.click();
		return new FilterPage(driver);
	}
}
