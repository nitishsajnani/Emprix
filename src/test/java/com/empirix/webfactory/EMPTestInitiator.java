package com.empirix.webfactory;

import static com.empirix.utilities.YamlReader.setYamlFilePath;

import com.empirix.utilities.CustomFunctions;

import com.empirix.actions.LoginPageActions;
import com.empirix.getpageobjects.GetPage;

import com.empirix.utilities.TakeScreenshot;

public class EMPTestInitiator extends TestSessionInitiator{

	public TakeScreenshot takescreenshot;
	public CustomFunctions customFunctions;
	public LoginPageActions loginPage;
	
		private void _initPage() {
			loginPage = new LoginPageActions(driver);		
		}

	public EMPTestInitiator(String testname) {
		super();
		setProduct();
		setYamlFilePath();
		configureBrowser();
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	public void setProduct(){
		product = "EMP";
		CustomFunctions.setProduct(product);

		GetPage.setProduct(product);
	}
	
	
}

