package commonLibs.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import commonLibs.contracts.IMouseAction;

public class MouseControl implements IMouseAction {

	Actions action;

	public MouseControl(WebDriver driver) {

		action = new Actions(driver);
	}

	@Override
	public void dragAndDrop(WebElement source, WebElement target) throws Exception {

		action.dragAndDrop(source, target).build().perform();

	}

	@Override
	public void moveToElement(WebElement element) throws Exception {
		action.moveToElement(element).build().perform();

	}

	@Override
	public void rightClick(WebElement element) throws Exception {
		action.contextClick(element).build().perform();

	}

	@Override
	public void doubleClick(WebElement element) throws Exception {
		action.doubleClick(element).build().perform();

	}

	@Override
	public void moveToElementAndClick(WebElement element) throws Exception {
		action.moveToElement(element).click().build().perform();

	}

}
