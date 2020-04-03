package com.empirix.tests;

import static com.empirix.utilities.YamlReader.getYamlValue;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC004_VerifyClientDetails extends BaseTest  {	
	public TC004_VerifyClientDetails(String baseUrl){
	super("profile.baseUrl");
	}

	String username, password;
	@BeforeClass
	public void init() {
		username = getYamlValue("HomePage.LoginDetails.username");
		password = getYamlValue("HomePage.LoginDetails.password");
	}

	@Test
	public void TestStep01_UserSubmitLoginDetails() {
		EMP.loginPage.enterSignInDetails(username, password);
		EMP.loginPage.clickOnSubmitButton();
	}
	@Test
	public void TestStep02_VerifyUserSuccessfullyPrintClientDetails() {
		EMP.loginPage.clickOnMenuButton();
		EMP.loginPage.clickOnclient();
		EMP.loginPage.clientDetailsPrint();
		Reporter.log("User is successfully printed the client's details",true);
	
	}
	@Test
	public void TestStep02_VerifyUserSuccessfullyLogoutfromtheApplication() {
		EMP.loginPage.clickOnLogout();
		Reporter.log("User is successfully logout from the application",true);
	}
}
