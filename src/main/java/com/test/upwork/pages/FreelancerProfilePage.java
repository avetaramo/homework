package com.test.upwork.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.test.upwork.pages.basePage.PageObject;

public class FreelancerProfilePage extends PageObject {

	public FreelancerProfilePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(@data-ng-class, 'active-context-title')]/span")
	WebElement freelancerName;

	@FindBy(xpath = "//*[contains(@class, 'active-context-title')]/span")
	WebElement freelancerTitle;

	private final String OVERVIEW_XPATH ="//*[contains(@class, 'active-context-title')]/span[contains(@data-ng-bind-html, 'getProfileTitle')]";
	@FindBy(xpath = OVERVIEW_XPATH )
	WebElement freelancerOverview;

	@FindBy(xpath = "//a[contains(@data-ng-repeat, 'skill in items')]")
	WebElement[] freelancerSkills;

	public String getTitle() {
		return freelancerTitle.getText().trim();
	}

	public String getOverview() {
		return freelancerOverview.getText().trim();
	}

	public String[] getSkills() {
		String skills[] = new String[freelancerSkills.length];
		int j = 0;
		for (WebElement freelancerSkill : freelancerSkills) {
			skills[j++] = freelancerSkill.getText().trim();
		}
		return skills;
	}
	
	public void waitForOveriviewPresent() {
		isElementPresent(OVERVIEW_XPATH);
	}

}
