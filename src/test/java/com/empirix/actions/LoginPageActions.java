package com.empirix.actions;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.empirix.getpageobjects.GetPage;

public class LoginPageActions extends GetPage {

	WebDriver driver;

	public LoginPageActions(WebDriver driver) {
		super(driver, "LoginPage");
		this.driver = driver;
	}

	public void clickOnMenuButton()
	{
		element("dd_userMenu").click();
		logMessage("User clicks on Menu button");
	}
	
	public void verifyLogoutButton()
	{
		isElementDisplayed("btn_logout");
		assertEquals(element("btn_logout").getText(),"Logout");
		logMessage("Logout button is present for the user");

	}
	
	public void clickOnLogout()
	{
		element("btn_logout").click();
		logMessage("User gets logout from the application");

	}
	
	public void clickOnSubmitButton() {
		isElementDisplayed("btn_submit");
		element("btn_submit").click();
		logMessage("User clicks on submit button");
	}

	public void enterSignInDetails(String username, String password) {
		element("inp_username").clear();
		element("inp_username").sendKeys(username);
		logMessage("[Action]:User enters username " + username);
		element("inp_password").sendKeys(password);
		logMessage("[Action]:User enters password " + password);
	}
	
	public void selectLanguageJapanese()
	{
		element("language_changeJS").click();
		logMessage("[Action]:User selects the language japanese");
		handleAlert();
	}
	public void selectLanguageEnglish()
	{
		element("language_changeJS").click();
		logMessage("[Action]:User selects the language english");
		handleAlert();
	}
	public void clientDetailsPrint()
	{
		List<WebElement> detailsHeading=elements("txt_clientDetailsHeading");
		List<WebElement> details = elements("txt_clientDetails");
	
		for(int i=0;i<detailsHeading.size();i++)
		{
			for(int j=i;j==i;j++)
			{
				
				logMessage(detailsHeading.get(i).getText()+":"+details.get(j).getText());
			}			
		}
		}
	
	public void clickOnclient()
	{    hardWait(2);
		element("btn_client").click();
		logMessage("[Action]:User clicks on client");

	}

	public void verifyLanguageChangeToJapanese() {
   assertEquals(element("language_changeJS").getAttribute("color"), "#337ab7");
   logMessage("[Assertion Passed]:Application language has been changed");
	}
	
	public void verifyLanguageChangeToEnglish() {
		   assertEquals(element("language_changeES").getAttribute("color"), "#337ab7");
		   logMessage("[Assertion Passed]:Application language has been changed");
			}

	public void verifyTab(String tabElement,String TabName) {
		element(tabElement).click();
		hardWait(2);
		String url=driver.getCurrentUrl();			
	Assert.assertTrue(url.contains(TabName));
	logMessage("User able to access tab:"+TabName);
	}
}
	
