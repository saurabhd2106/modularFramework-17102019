package com.newtours.tests;

import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.newtours.pages.HomePage;
import com.newtours.pages.LoginPage;
import com.newtours.pages.RegisterPage;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import commonLibs.utils.ConfigReadUtils;

public class Basetests {

	CommonDriver cmnDriver;

	String url; 

	HomePage homepage;
	LoginPage loginPage;
	RegisterPage registerPage;
	ScreenshotControl screenshot;
	
	static Properties configProperty;
	static String configFilename;

	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extentReporter;
	static ExtentTest extentTest;

	static String htmlReportFilename;
	static String currentWorkingDirectory;
	static String testExecutionStartTime;

	static {

		Date date = new Date();

		currentWorkingDirectory = System.getProperty("user.dir");
		testExecutionStartTime = String.valueOf(date.getTime());
		
		configFilename = String.format("%s/config/config.properties", currentWorkingDirectory);

		htmlReportFilename = String.format("%s/reports/newtoursTestReport_%s.html", currentWorkingDirectory,
				testExecutionStartTime);

		htmlReporter = new ExtentHtmlReporter(htmlReportFilename);
		extentReporter = new ExtentReports();

		extentReporter.attachReporter(htmlReporter);
		
		

	}

	WebDriver driver;

	@BeforeSuite
	public void preSetup() throws Exception{
		configProperty = ConfigReadUtils.readProperties(configFilename);
	}
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {

		extentTest = extentReporter.createTest("Test Setup - Setting the environment");

		invokeBrowser();

		pageinitialization();

		screenshot = new ScreenshotControl(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterEveryTestcase(ITestResult testResult) throws Exception {
		String testcasename = testResult.getName();

		String screenshotFileName = String.format("%s/screenshots/%s_%s.jpeg", currentWorkingDirectory, testcasename,
				testExecutionStartTime);

		if (testResult.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, testcasename);
		} else if (testResult.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, testcasename);
			screenshot.captureAndSaveScreenshot(screenshotFileName);

			extentTest.addScreenCaptureFromPath(screenshotFileName);

		} else {
			extentTest.log(Status.SKIP, testcasename);
		}

	}

	@AfterClass(alwaysRun = true)
	public void cleanUp() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

	@AfterSuite(alwaysRun = true)
	public void postCleanup() {
		extentReporter.flush();

	}

	private void pageinitialization() {
		loginPage = new LoginPage(driver);
		homepage = new HomePage(driver);
		registerPage = new RegisterPage(driver);

	}

	private void invokeBrowser() throws Exception {
		String browserType = configProperty.getProperty("browserType");
		extentTest.log(Status.INFO, "invoking Browser - " + browserType);

		cmnDriver = new CommonDriver(browserType);

		int pageloadtimeout = Integer.parseInt(configProperty.getProperty("pageloadTimeout"));
		extentTest.log(Status.INFO, "Page loadtime set as" + pageloadtimeout);
		cmnDriver.setPageloadTimeout(pageloadtimeout);

		int elementDetectionTimeout = Integer.parseInt(configProperty.getProperty("implicitWait"));;
		extentTest.log(Status.INFO, "Implicit wait set as" + elementDetectionTimeout);
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeout);

		url = configProperty.getProperty("baseUrl");
		extentTest.log(Status.INFO, "Navigating to a url - " + url);
		cmnDriver.navigateToFirstUrl(url);

		driver = cmnDriver.getDriver();

	}

}
