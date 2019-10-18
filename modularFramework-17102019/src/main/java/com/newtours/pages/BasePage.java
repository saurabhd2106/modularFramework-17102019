package com.newtours.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;
import commonLibs.implementation.MouseControl;

public class BasePage {
	
	ElementControl elementControl;
	MouseControl mouseControl;
	DropdownControl dropdownControl;
	
	public BasePage(WebDriver driver) {
		elementControl = new ElementControl();
		mouseControl = new MouseControl(driver);
		dropdownControl = new DropdownControl();

	}

}
