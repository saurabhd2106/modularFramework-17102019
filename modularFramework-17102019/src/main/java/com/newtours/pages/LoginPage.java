package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	@FindBy(name = "userName")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(name = "login")
	private WebElement signinButton;

	@FindBy(linkText = "REGISTER")
	public WebElement registerLink;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	public void login(String sUsername, String sPassword) throws Exception {

		elementControl.setText(username, sUsername);
		elementControl.setText(password, sPassword);
		elementControl.clickElement(signinButton);
	}

}
