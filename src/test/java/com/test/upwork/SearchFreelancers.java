package com.test.upwork;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.upwork.pages.FilterPage;
import com.test.upwork.pages.FreelancerProfilePage;
import com.test.upwork.pages.SearchPage;
import com.test.upwork.pages.SearchResultPage;
import com.test.upwork.util.Freelancer;
import com.test.upwork.util.FunctionalTestBase;

public class SearchFreelancers extends FunctionalTestBase {

	@Parameters(value = "keyword")
	@Test
	public void searchByKeyword(String searchKeyword) throws InterruptedException {

		System.out.println("Starting a test to search for keyword " + searchKeyword);

		System.out.println("Go to www.upwork.com ");
		// open searchPage
		SearchPage search = new SearchPage(driver);
		// srach freelancer by keyword
		System.out.println("Enter " + searchKeyword + " into the search field and submit it");
		SearchResultPage results = search.searchFreelancers(searchKeyword);
		// SearchResultPage results = new SearchResultPage(driver);

		// filter freelancers' list
//		FilterPage filter = results.clickFilter();
//		filter.filterByHourlyRate("0-10");
//		filter.filterByWebDevelopmentCategory();
//
//		// sleep until the filter runs
//		Thread.sleep(3000);
//		filter.clickFilter();

		// sleep to pass the captcha
		Thread.sleep(30000);
		results.waitForPageToLoad();
		results.waitForNextLinkPresent();
		// parse all freelancers into a list of Freelancer object
		System.out.println(
				"Parse the 1st page with search results: store info given on the 1st page of search results as structured data of any chosen by you type (i.e. hash of hashes or array of hashes, whatever structure handy to be parsed).");
		Freelancer[] freelancers = results.parseFreelancersNames();

		freelancers = results.parseFreelancersOverview(freelancers);
		freelancers = results.parseFreelancersTitles(freelancers);
		freelancers = results.parseFreelancersSkills(freelancers);
		System.out.println(
				"Make sure at least one attribute (title, overview, skills, etc) of each item (found freelancer) from parsed search results contains <keyword> Log in stdout which freelancers and attributes contain "
						+ searchKeyword + " and which do not.");
		// iterate through all freelancers and get which area contains the keyword
		for (Freelancer freelancer : freelancers) {
			freelancer.verifyFreelancerContainsKeyword(searchKeyword);
		}

		// navigate to 2nd freelancer's profile
		System.out.println("Click on 2nd freelancer's title\r\n" + "Get into that freelancer's profile");
		FreelancerProfilePage profile = results.navigateToFreelancer(freelancers[1]);
		profile.waitForPageToLoad();
		profile.waitForOveriviewPresent();

		System.out.println(
				"Check that each attribute value is equal to one of those stored in the structure created in #6");
		// verify at least one of the attributes in the
		Thread.sleep(30000);
		assertTrue((freelancers[1]).getOverview().equals(profile.getOverview())
				|| freelancers[1].getTitle().equals(profile.getTitle())
				|| freelancers[1].getSkills().equals(profile.getSkills()));

		assertTrue(freelancers[1].verifyFreelancerContainsKeyword(searchKeyword));
	}

}
