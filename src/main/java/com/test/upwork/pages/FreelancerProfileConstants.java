package com.test.upwork.pages;

public class FreelancerProfileConstants {

	public static final String FREELANCER_NAME_XPATH= "//a[@class='freelancer-tile-name']/span";
	public static final String FREELANCER_TITLE_XPATH= "//a[@title='%s']/../../..//h4[@data-qa='tile_title']";
	public static final String FREELANCER_OVERVIEW_XPATH="//a[@title='%s']/../..//p[@data-qa='tile_description']";
	public static final String FREELANCER_SKILLS_XPATH= "//a[contains(@class, 'tag-skill') and contains(@data-ng-click, '%s')]/span";
	public static final String FREELANCER_PROFILE_LINK_XPATH=FREELANCER_NAME_XPATH+"[contains(@title, '%s')]";
	public static final String FREELANCERS_XPATH = "//section[@data-qa='freelancer']";
}
