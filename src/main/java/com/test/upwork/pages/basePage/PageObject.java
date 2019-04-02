package com.test.upwork.pages.basePage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

	public WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver = driver;
		waitForPageToLoad();
		PageFactory.initElements(driver, this);
	}

	public void explicitWait(int timeout) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	 public void waitForPageToLoad() {
	        ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	                    }
	                };
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            wait.until(expectation);
	        } catch (Throwable error) {
	            //to add error logging here
	        }
	    }
	
	
	public boolean isElementPresent(final String elementxPath) {

		return (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath(elementxPath)) != null;
			}
		});
	}

}
