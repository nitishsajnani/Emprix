/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empirix.webfactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

public class WebDriverFactory {

	private static String browser;
	static Process p = null;

	private static final DesiredCapabilities capabilities = new DesiredCapabilities();

	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		browser = System.getProperty("browser");
		if (browser == null || browser.isEmpty()) {
			browser = seleniumconfig.get("browser");
		}
		System.out.println("Browser=" + browser);
		Reporter.log(browser, true);
		if (seleniumconfig.get("operatingSystem").equalsIgnoreCase("window")) {
			if (browser.equalsIgnoreCase("firefox")) {
				System.out.println(seleniumconfig.get("wDriverpathFirefox"));
				return getFirefoxDriver(seleniumconfig.get("wDriverpathFirefox"), seleniumconfig.get("otherFilesPath"));
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println(seleniumconfig.get("wDriverpathChrome"));
				return getChromeDriver(seleniumconfig.get("wDriverpathChrome"), seleniumconfig.get("otherFilesPath"));
			} else if (browser.equalsIgnoreCase("Safari")) {
				return getSafariDriver();
			} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
					|| (browser.equalsIgnoreCase("internet explorer"))) {
				return getInternetExplorerDriver(seleniumconfig.get("wDriverpathFirefox"));
			}
		} else if (seleniumconfig.get("operatingSystem").equalsIgnoreCase("linux")) {
			if (browser.equalsIgnoreCase("firefox")) {
				System.out.println(seleniumconfig.get("driverpathFirefox"));
				return getFirefoxDriver(seleniumconfig.get("driverpathFirefox"), seleniumconfig.get("otherFilesPath"));
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println(seleniumconfig.get("driverpathChrome"));
				return getChromeDriver(seleniumconfig.get("driverpathChrome"), seleniumconfig.get("otherFilesPath"));
			}
		} else if (seleniumconfig.get("operatingSystem").equalsIgnoreCase("mac")) {
			if (browser.equalsIgnoreCase("Safari"))
				return getSafariDriver();
		}

		return new FirefoxDriver();
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
		browser = selConfig.get("browser");
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
		} else if (browser.equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
		} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
		}
		String seleniuhubaddress = selConfig.get("seleniumserverhost");
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniuhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);

		return new RemoteWebDriver(selserverhost, cap);
	}

	private static WebDriver getChromeDriver(String driverpath, String downloadFilePath) {
		System.setProperty("webdriver.chrome.driver", driverpath);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	//	WebDriverManager.chromedriver().version("2.41").setup();
	/*	WebDriverManager.chromedriver().version("2.41").setup();*/
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);

		// disable flash and the PDF viewer
		chromePrefs.put("plugins.plugins_disabled",
				new String[] { "Adobe Flash Player", "Chrome PDF Viewer", "plugins.always_open_pdf_externally" });
		// ((Object)
		// options).AddUserProfilePreference("plugins.always_open_pdf_externally",
		// true);
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory",
				System.getProperty("user.dir") + File.separator + downloadFilePath);
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--disable-extensions");
		options.addArguments("test-type");
		options.addArguments("--disable-impl-side-painting");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		ChromeDriver driver = new ChromeDriver(cap);
		driver.manage().deleteAllCookies();
	//	ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort().build();
		/*ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort().build();*/
		return driver;
	}

	private static WebDriver getInternetExplorerDriver(String driverpath) {
		System.setProperty("webdriver.ie.driver", driverpath);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("ignoreZoomLevel", true);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}


	private static WebDriver getFirefoxDriver(String driverpath, String downloadFilePath) {
		FirefoxProfile profile = new FirefoxProfile();
		System.setProperty("webdriver.gecko.driver", driverpath);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		profile.setPreference("browser.cache.disk.enable", false);
		profile.setPreference("browser.download.dir",
				System.getProperty("user.dir") + File.separator + downloadFilePath);
		return new FirefoxDriver();
	}
}
