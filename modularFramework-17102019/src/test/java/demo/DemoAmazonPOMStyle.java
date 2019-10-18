package demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import designPattern.AmazonPOMStyle;

public class DemoAmazonPOMStyle {

	CommonDriver cmnDriver;

	String url = "https://www.amazon.in/";

	AmazonPOMStyle amazonHomepage;

	WebDriver driver;

	@BeforeClass
	public void invokeBrowser() throws Exception {

		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(60);

		cmnDriver.setElementDetectionTimeout(60);

		cmnDriver.navigateToFirstUrl(url);

		driver = cmnDriver.getDriver();

		amazonHomepage = new AmazonPOMStyle(driver);

	}

	@Test(priority = 0)
	public void verifySearchOnHomepage() throws Exception {

		String product = "Apple Watch";

		String category = "Electronics";

		amazonHomepage.searchProduct(product, category);

	}

	@AfterClass(enabled = false)
	public void closeBrowser() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

}
