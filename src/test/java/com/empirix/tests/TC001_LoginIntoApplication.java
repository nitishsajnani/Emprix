package com.empirix.tests;

import com.empirix.tests.BaseTest;

import static com.empirix.utilities.YamlReader.getYamlValue;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_LoginIntoApplication extends BaseTest {

	public TC001_LoginIntoApplication(String baseUrl) {
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
	public void TestStep02_VerifyUserSuccessfullyLogin() {
		EMP.loginPage.clickOnMenuButton();
		EMP.loginPage.verifyLogoutButton();
		Reporter.log("User is successfully login into the application",true);
		
	
	}
	

	}