package com.test.upwork.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.test.upwork.util.Freelancer;

public class SearchResultPage extends SearchPage {
	public static final String NEXT_ELEMENT_XPATH = "//li[@ng-if='directionLinks']/a";

	@FindBy(xpath = "//div[contains(@class, 'filters-button')]")
	WebElement filter;
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	public void waitForNextLinkPresent() {
		isElementPresent(NEXT_ELEMENT_XPATH);
	}

	 public FilterPage clickFilter() {
		 filter.click();
		 return new FilterPage(driver);
	 }
	public Freelancer[] parseFreelancersNames() {

		List<WebElement> allLinks = driver.findElements(By.xpath(FreelancerProfileConstants.FREELANCER_NAME_XPATH));

		System.out.println(allLinks.size());
		Freelancer[] freelancers = new Freelancer[allLinks.size()];
		for (int i = 0; i < freelancers.length; i++) {
			String name = allLinks.get(i).getAttribute("innerHTML");
			Freelancer freelancer = new Freelancer();
			freelancer.setName(name.trim());
			freelancers[i] = freelancer;
		}

		return freelancers;
	}

	public Freelancer[] parseFreelancersTitles(Freelancer[] freelancers) {
		for (int i = 0; i < freelancers.length; i++) {
			String xpath = String.format(FreelancerProfileConstants.FREELANCER_TITLE_XPATH, freelancers[i].getName());
			String title = "";
			
			try {
				title = driver.findElement(By.xpath(xpath)).getAttribute("innerHTML");
			} catch (Exception e) {
				System.out.println("frelancer title cannot be detected -- shall be an agency contractor");
			}
			
		
			freelancers[i].setTitle(title.trim());
		}
		return freelancers;
	}

	public Freelancer[] parseFreelancersOverview(Freelancer[] freelancers) {
		for (int i = 0; i < freelancers.length; i++) {
			String xpath = String.format(FreelancerProfileConstants.FREELANCER_OVERVIEW_XPATH,
				
					freelancers[i].getName());
			String overview = "";
			try{
				overview = driver.findElement(By.xpath(xpath)).getAttribute("data-profile-description");
			} catch (Exception e) {
				System.out.println("a freelancer did not have overview -- shall be agency contractor");
			}
			freelancers[i].setOverview(overview);
		}
		return freelancers;
	}

	public Freelancer[] parseFreelancersSkills(Freelancer[] freelancers) {
		for (int i = 0; i < freelancers.length; i++) {
			String xpath = String.format(FreelancerProfileConstants.FREELANCER_SKILLS_XPATH, freelancers[i].getName());
			List<WebElement> allLinks = driver.findElements(By.xpath(xpath));

			String[] skills = new String[allLinks.size()];
			int j = 0;
			for (WebElement skillElement : allLinks) {
				while (j < 4) {
					if (skillElement!= null  && skillElement.getText().trim() != "") {
						skills[j++] = skillElement.getText().trim();
					}
				}
				freelancers[i].setSkills(skills);
			}

		}

		return freelancers;
	}

	public FreelancerProfilePage navigateToFreelancer(Freelancer freelancer) {
		String xpath = String.format(FreelancerProfileConstants.FREELANCER_PROFILE_LINK_XPATH, freelancer.getName());
		driver.findElement(By.xpath(xpath)).click();
		return new FreelancerProfilePage(driver);
	}

}
