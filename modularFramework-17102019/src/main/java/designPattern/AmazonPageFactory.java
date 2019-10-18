package designPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPageFactory {

	@CacheLookup
	@FindBy(id = "searchDropdownBox")
	private WebElement searchCategory;

	@CacheLookup
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;

	@CacheLookup
	@FindBy(xpath = "//input[@value='Go']")
	private WebElement searchButton;

	@FindBy(xpath = "//span[@data-component-type='s-result-info-bar']")
	private WebElement searchResult;

	DropdownControl dropdownControl;

	ElementControl elementControl;

	public AmazonPageFactory(WebDriver driver) {

		PageFactory.initElements(driver, this);

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
