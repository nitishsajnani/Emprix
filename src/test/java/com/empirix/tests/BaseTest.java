package com.empirix.tests;

import static com.empirix.utilities.YamlReader.getYamlValue;
import java.io.IOException;
import java.lang.reflect.Method;
import com.empirix.webfactory.EMPTestInitiator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners()
public class BaseTest {
	protected boolean isTestRunCreated = false;
	protected EMPTestInitiator EMP;
	protected String product;
	protected int counterForTests;
	protected int failCount;
	protected String baseUrl;

	public BaseTest(String baseUrl) {
		this.baseUrl = baseUrl;
		System.out.println("baseURL::"+baseUrl);
	}

	@BeforeClass
	public void beforeMethod() {
		EMP = new EMPTestInitiator(this.getClass().getSimpleName());
		EMP.launchApplication(getYamlValue(baseUrl));
		counterForTests = 0;
		failCount = 0;
		// createFolder(System.getProperty("user.dir")+File.separator+"ALMLog");
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		EMP.stepStartMessage(method.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result, Method method) {
		EMP.takescreenshot.takeScreenShotOnException(result, counterForTests, method.getName());
	}

@AfterClass(alwaysRun=true)
	public void close_Test_Session() throws IOException {
		// EMP.closeBrowserSession();
	}


}
