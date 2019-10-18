package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPOMStyle {

	private WebElement searchCategory;

	private WebElement searchBox;

	private WebElement searchButton;
	
	private WebElement searchResult;
	

	DropdownControl dropdownControl;

	ElementControl elementControl;

	public AmazonPOMStyle(WebDriver driver) {

		searchCategory = driver.findElement(By.id("searchDropdownBox"));

		searchBox = driver.findElement(By.id("twotabsearchtextbox"));

		searchButton = driver.findElement(By.xpath("//input[@value='Go']"));
		
		searchResult = driver.findElement(By.xpath("//span[@data-component-type='s-result-info-bar']"));

		elementControl = new ElementControl();

		dropdownControl = new DropdownControl();

	}

	public void searchProduct(String product, String category) throws Exception {

		elementControl.setText(searchBox, product);

		dropdownControl.selectViaVisibleText(searchCategory, category);

		elementControl.clickElement(searchButton);

		System.out.println(elementControl.getText(searchResult));
	}

}
