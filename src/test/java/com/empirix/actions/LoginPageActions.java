package com.empirix.actions;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
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
		logMessage("User enters password " + password);
	}
}