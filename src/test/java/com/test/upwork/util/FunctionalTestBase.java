package com.test.upwork.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class FunctionalTestBase {
	
	public WebDriver driver ;
	final String URL = "https://www.upwork.com";
	@Parameters(value={"driver.prop", "driver.path"} )
	@BeforeClass
	public void setup(String driverProp, String driverPath) {
		System.setProperty(driverProp , driverPath );
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().window().fullscreen();
		driver.get(URL);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
