package com.test.upwork.util;

public class Freelancer {

	private String name = "";
	private String title = "";
	private String overview = "";
	private String rate = "";
	private String[] skills;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public void printAllSkills() {
		for (String skill : skills) {
			System.out.println(skill);
		}
	}

	public Freelancer() {

	}

	public Freelancer(String name, String title, String overview, String[] skills) {
		setName(name);
		setTitle(title);
		setOverview(overview);
		setSkills(skills);
	}

	public Freelancer(String fullPage) {
		// parses all values from fullPage into a Freelancer object

	}

	public boolean verifyFreelancerOverviewContainsKeyword(String keyword) {

		boolean res = false;
		System.out.println("Overview is  " + getOverview());
		if (getOverview().isEmpty() || getOverview().toLowerCase().contains(keyword.toLowerCase())) {
			System.out.println(getName() + " has the searchkeyword " + keyword + " in his overview");
			res = true;
		}
		return res;
	}

	public boolean verifyFreelancerTitleContainsKeyword(String keyword) {
		boolean res = false;
		System.out.println("Title is  " + getTitle());
		if (getTitle().isEmpty() || getTitle().toLowerCase().contains(keyword.toLowerCase())) {
			System.out.println(getName() + " has the searchkeyword " + keyword + " in his title");
			res = true;
		}
		return res;
	}

	public boolean verifyFreelancerSkillsContainsKeyword(String keyword) {
		boolean res = false;
		for (String skill : getSkills()) {

			if (skill != null) {
				if (skill.toLowerCase().contains(keyword.toLowerCase())) {
					System.out.println(getName() + " has the searchkeyword " + keyword + " in his skills");
					res = true;
				}

			}
		}

		return res;
	}

	public boolean verifyFreelancerContainsKeyword(String keyword) {
		if (verifyFreelancerOverviewContainsKeyword(keyword) || verifyFreelancerTitleContainsKeyword(keyword)) {
			return true;
		}
		return false;
	}
}
