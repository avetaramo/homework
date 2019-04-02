package com.test.upwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.upwork.pages.basePage.PageObject;

public class SearchPage extends PageObject {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(@class,'icon-search')]")
	WebElement searchIcon;

	@FindBy(xpath = "//a[@data-qa='freelancer_value']")
	WebElement findFreelancers;

	@FindBy(xpath = "//*[@class='navbar-collapse d-none d-lg-flex']//input[@name='q']")
	WebElement searchField;

	public void selectFreelancers() {
		searchIcon.click();
		findFreelancers.click();
	}

	public void typeSearch(String keyword) {
		System.out.println("typeSearch");
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='navbar-collapse d-none d-lg-flex']//input[@name='q']")));
		searchField.sendKeys(keyword);

	}

	public SearchResultPage searchFreelancers(String keyword) {
		typeSearch(keyword);
		searchField.submit();
		return new SearchResultPage(driver);
	}
}
