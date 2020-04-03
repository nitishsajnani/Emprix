/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empirix.webfactory;

import static com.empirix.utilities.DataPropertFileUtil.getProperty;
import static com.empirix.utilities.YamlReader.getYamlValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class TestSessionInitiator {

	protected WebDriver driver, originalDriver;
	private WebDriverFactory wdfactory;
	Map<String, Object> chromeOptions = null;
	protected static String product;
	
	
	
	public TestSessionInitiator() {
		wdfactory = new WebDriverFactory();
	}

	protected void configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		int timeout;
			timeout = Integer.parseInt(_getSessionConfig().get("timeout"));
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
		originalDriver = driver;
	}

	private static Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "timeout",
				"operatingSystem", "wDriverpathIE", "wDriverpathChrome","wDriverpathFirefox","driverpathFirefox", "driverpathChrome", "otherFilesPath","screenshot-path"};
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public static String getEnv() {
		String tier = System.getProperty("env");
		if (tier == null)
			tier = _getSessionConfig().get("tier");
		return tier;
	}
	

	public static String getBrowser() {
		String browser = System.getProperty("browser");
		if (browser == null)
			browser = _getSessionConfig().get("browser");
		return browser;
	}

	public String getTakeScreenshot() {
		return _getSessionConfig().get("takeScreenshot");
	}
	
	public String getAutoITScriptPath() {
		return _getSessionConfig().get("autoITPath");
	}

	public static String getProduct() {
		if (System.getProperty("product") != null)
			product = System.getProperty("product");
		return product;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("app_url"));
	}

	public void launchApplication(String applicationpath) {
		Reporter.log("The application url is :- " + applicationpath, true);
		Reporter.log("The test browser is :- " + getBrowser(), true);
		driver.get(applicationpath);
	}

	public void getURL(String url) {
		//driver.manage().deleteAllCookies();
		driver.get(url);
	}

	public void closeBrowserSession() {
		driver.quit();
	}

	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP:- " + testStepName.toUpperCase()
				+ " *****", true);
		Reporter.log(" ", true);
	}

	public void waitForPageLoad()
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void navigateBack() {
		driver.navigate().back();
	}
	
	public String getCurrentWindowsURL() {
		return driver.getCurrentUrl().trim();
	}

	public void printMethodExecutionTime(long startTime, long endTime) {
		long totalExecutionTimeInSeconds = (endTime - startTime) / 1000;
		long hours = totalExecutionTimeInSeconds / 3600;
		long minutes = (totalExecutionTimeInSeconds % 3600) / 60;
		long seconds = totalExecutionTimeInSeconds % 60;

		String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		Reporter.log("\n---------- METHOD EXECUTION TIME: " + timeString + " ----------\n", true);
	}
	
	public void closeWindow() {
		driver.close();
	}

}
