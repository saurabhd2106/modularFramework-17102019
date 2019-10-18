package com.newtours.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.newtours.utils.TestDataProvider;

public class LoginTestsDataDriven extends Basetests{
	
	@Test(priority=0)
	public void verifyTitleOfThePageBeforeLogin() throws Exception{
		
		extentTest = extentReporter.createTest("TC - 12303 - Verify Title of the page before login");
		
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = cmnDriver.getTitle();
		
		extentTest.log(Status.INFO, "Expected and actual Title are - "+ expectedTitle + " and "+ actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	

	@Test(priority=1000, groups ={"Sanity"}, dataProvider = "getData", dataProviderClass=TestDataProvider.class)
	public void verifyLoginToNewTours(String sUsername, String sPassword) throws Exception{
		extentTest = extentReporter.createTest("TC - 12303 - Verify login");
		
		extentTest.log(Status.INFO, "Username - "+ sUsername + "Password - "+ sPassword);
		loginPage.login(sUsername, sPassword);
		String expectedTitle = "Find a Flight: Mercury Tours:";
		String actualTitle = cmnDriver.getTitle();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualTitle, expectedTitle);
		softAssert.assertTrue(homepage.verifySingOffLinkIsPresent());;
		softAssert.assertAll();
		
		homepage.signOff();
		
		cmnDriver.navigateToUrl(configProperty.getProperty("baseUrl"));
		}

}
