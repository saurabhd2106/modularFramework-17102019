package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "SIGN-OFF")
	private WebElement signOffLink;

	public boolean verifySingOffLinkIsPresent() throws Exception {

		return elementControl.isElementVisible(signOffLink);

	}

	public void signOff() throws Exception {

		elementControl.clickElement(signOffLink);

	}

}
