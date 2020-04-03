package com.empirix.getpageobjects;

import static com.empirix.utilities.DataPropertFileUtil.getProperty;

import org.openqa.selenium.Alert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.empirix.utilities.SeleniumWait;

public class BaseUi {

	WebDriver driver, driverToUploadImage;
	protected SeleniumWait wait;


	protected String browser;

	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		//this.pageName = pageName;
		int timeout;
		timeout = Integer.parseInt(getProperty("Config.properties", "timeout"));
		this.wait = new SeleniumWait(driver, timeout);
	}
	
	protected void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	protected String handleAlert() {
		String alertBoxText = null;
		try {
			alertBoxText = switchToAlert().getText();
			switchToAlert().accept();
			logMessage("Alert handled...");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
		return alertBoxText;
	}
	protected String getPageTitle() {
		return driver.getTitle().trim();
	}

	public void logMessage(String message) {
		Reporter.setEscapeHtml(true);
		Reporter.log(message, true);
	}
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void enterText(WebElement element, String text) {
		try {
			wait.waitForElementToBeVisible(element);
			element.clear();
			element.sendKeys(text);
		} catch (StaleElementReferenceException ex1) {
			element.clear();
			element.sendKeys(text);
			logMessage("Entered Text '" + text + "' in Element " + element + " after catching Stale Element Exception");
		} catch (UnhandledAlertException u) {
			element.clear();
			element.sendKeys(text);
		}
	}

}