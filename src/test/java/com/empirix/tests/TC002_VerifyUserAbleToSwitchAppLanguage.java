package com.empirix.tests;

import static com.empirix.utilities.YamlReader.getYamlValue;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_VerifyUserAbleToSwitchAppLanguage extends BaseTest  {	
	public TC002_VerifyUserAbleToSwitchAppLanguage(String baseUrl){
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
	public void TestStep02_VerifyUserSuccessfullyChangeLanguageToJapanese() {
		EMP.loginPage.clickOnMenuButton();
		EMP.loginPage.selectLanguageJapanese();
		EMP.loginPage.verifyLanguageChangeToJapanese();
		Reporter.log("Application language has been changed to Japanese",true);

	}
	@Test
	public void TestStep03_VerifyUserSuccessfullyChangeLanguageToEnglish() {
		EMP.loginPage.clickOnMenuButton();
		EMP.loginPage.selectLanguageEnglish();
		EMP.loginPage.verifyLanguageChangeToEnglish();
		Reporter.log("Application language has been changed to Englsh",true);

	}
	@Test
	public void TestStep02_VerifyUserSuccessfullyLogoutfromtheApplication() {
		EMP.loginPage.clickOnLogout();
		Reporter.log("User is successfully logout from the application",true);
	}
}
