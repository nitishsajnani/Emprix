package com.empirix.tests;

import static com.empirix.utilities.YamlReader.getYamlValue;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_VerifyUserAbleToAccessAllTabsInEnglishAndJapanse extends BaseTest  {	
	public TC003_VerifyUserAbleToAccessAllTabsInEnglishAndJapanse(String baseUrl){
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
	public void TestStep02_VerifyUserNavigateToAllTabsEnglish() {
		EMP.loginPage.verifyTab("tab_dashboard","Dashboard");
		EMP.loginPage.verifyTab("tab_alerts","alerts");
		EMP.loginPage.verifyTab("tab_tests","tests");
		EMP.loginPage.verifyTab("tab_variable","variables");
		EMP.loginPage.verifyTab("tab_notifications","notifi");
		Reporter.log("verified all the tabs are accessible to user in English",true);
	}
	@Test
	public void TestStep03_VerifyUserNavigateToAllTabsJapanese() {
		EMP.loginPage.clickOnMenuButton();
		EMP.loginPage.selectLanguageJapanese();
		EMP.loginPage.verifyTab("tab_dashboard","Dashboard");
		EMP.loginPage.verifyTab("tab_alerts","alerts");
		EMP.loginPage.verifyTab("tab_tests","tests");
		EMP.loginPage.verifyTab("tab_variable","variables");
		EMP.loginPage.verifyTab("tab_notifications","notifi");
		Reporter.log("verified all the tabs are accessible to user in Japanese",true);
		EMP.loginPage.clickOnMenuButton();
		EMP.loginPage.selectLanguageEnglish();
	}
	@Test
	public void TestStep02_VerifyUserSuccessfullyLogoutfromtheApplication() {
		EMP.loginPage.clickOnLogout();
		Reporter.log("User is successfully logout from the application",true);
	}
}
