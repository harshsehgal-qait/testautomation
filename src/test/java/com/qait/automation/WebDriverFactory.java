package com.qait.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

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
import org.yaml.snakeyaml.Yaml;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class WebDriverFactory {

	private String browser = "";
	static Process p = null;
	Set<String> contextNames;
	static FirefoxProfile firefoxProfile;
	private static final DesiredCapabilities capabilities = new DesiredCapabilities();
	
	public WebDriverFactory() {
	}

	public WebDriverFactory(String browserName) {
		browser = browserName;
	}

	@SuppressWarnings("unchecked")
	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		if (browser == null || browser.isEmpty()) {
			browser = seleniumconfig.get("browser").trim();
		}
		try {
			Yaml yaml = new Yaml();
			Map<String, Object> registry = (Map<String, Object>) yaml
					.load(this.getClass().getResourceAsStream("/TestConfigurationsRegistry.yaml"));
			Map<String, String> regConfig = (Map<String, String>) registry.get(browser);
			seleniumconfig.put("browser", (String) regConfig.get("browser"));
			seleniumconfig.put("seleniumserver", (String) regConfig.get("seleniumserver"));
			if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("remote")) {
				seleniumconfig.put("seleniumserverhost", (String) regConfig.get("seleniumserverhost"));
			}
			browser = seleniumconfig.get("browser");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(seleniumconfig);
		Reporter.log("[INFO]: The test Browser is " + browser.toUpperCase() + " !!!", true);

		if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("local")) {
			if (browser.equalsIgnoreCase("firefox")) {
				return getFirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				// return getChromeDriver(seleniumconfig.get("driverpath"));
				return getChromeDriver();
			} else if (browser.equalsIgnoreCase("Safari")) {
				return getSafariDriver();
			} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
					|| (browser.equalsIgnoreCase("internet explorer"))) {
				return getInternetExplorerDriver();
			}
			// TODO: treat mobile browser and separate instance on lines of remote driver
			else if (browser.equalsIgnoreCase("mobile")) {
				// return setMobileDriver(seleniumconfig);
				return setAndroidDriver(seleniumconfig);
			}
			else if (browser.equalsIgnoreCase("iosMobile")) {
				return setIOSMobileDriver(seleniumconfig);
			}
		}
		if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("remote")) {
			return setRemoteDriver(seleniumconfig);
		}
		return getFirefoxDriver();
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
			cap.setCapability("acceptSslCerts", true);
			cap.setCapability("browserstack.debug'", true);
			cap.setCapability("browser", "Firefox");
			cap.setCapability("browser_version", "46.0");
			cap.setCapability("os", "Windows");
			cap.setCapability("os_version", "8.1");
			cap.setCapability("resolution", "1366x768");
			cap.setCapability("browserstack.video", "false");
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
			cap.setCapability("acceptSslCerts", true);
			cap.setCapability("browser", "Chrome");
			cap.setCapability("browser_version", "50.0");
			cap.setCapability("os", "Windows");
			cap.setCapability("os_version", "8.1");
			cap.setCapability("resolution", "1366x768");
			cap.setCapability("browserstack.video", "false");
			cap.setCapability("browserstack.debug", "true");
		} else if (browser.equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
			cap.setCapability("acceptSslCerts", true);
			cap.setCapability("browserstack.safari.enablePopups", "true");
			cap.setCapability("browserstack.debug'", true);
			cap.setCapability("browser", "Safari");
			cap.setCapability("browser_version", "10.3");
			cap.setCapability("os", "OS X");
			cap.setCapability("os_version", "El Capitan");
			cap.setCapability("resolution", "1280x1024");
		} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability("acceptSslCerts", true);
			cap.setCapability("browser", "IE");
			cap.setCapability("browser_version", "11.0");
			cap.setCapability("os", "Windows");
			cap.setCapability("os_version", "7");
			cap.setCapability("resolution", "1366x768");
			// System.setProperty("webdriver.ie.driver", "C:\\NEJM\\"
			// + "IEDriverServer.exe");
		} else if (browser.equalsIgnoreCase("iosMobile")) {
			cap = DesiredCapabilities.iphone();
			cap.setCapability("acceptSslCerts", true);
			// cap.setCapability("emulator", true);
			cap.setCapability("autoDismissAlerts", true);
			cap.setCapability("locationServicesEnabled", false);
			cap.setCapability("locationServicesAuthorized", false);
			cap.setCapability("autoAcceptAlerts", false);
			cap.setCapability("browserName", "iPhone");
			cap.setCapability("device", "iPhone 8 Plus");
			cap.setCapability("realMobile", "true");
			cap.setCapability("os_version", "11.0");
		}
		else if (browser.equalsIgnoreCase("iosIpad")) {
			cap = DesiredCapabilities.ipad();
			cap.setCapability("browserName", "iPad");
			cap.setCapability("device", "iPad Pro");
			cap.setCapability("realMobile", "true");
			cap.setCapability("os_version", "11.2");
		}
		else if (browser.equalsIgnoreCase("mobile")) {
			cap = DesiredCapabilities.android();
			cap.setCapability("acceptSslCerts", true);
			// cap.setCapability("realMobile", true);
			// cap.setCapability("device", "Motorola Moto X 2nd Gen");
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

	// private static WebDriver getChromeDriver(String driverpath) {
	//// if (driverpath.endsWith(".exe")) {
	//// System.setProperty("webdriver.chrome.driver", driverpath);
	//// } else {
	//// System.setProperty("webdriver.chrome.driver", driverpath
	//// + "chromedriver.exe");
	//// }
	// ChromeOptions options = new ChromeOptions();
	// DesiredCapabilities cap = DesiredCapabilities.chrome();
	// cap.setCapability(ChromeOptions.CAPABILITY, options);
	//
	// return new ChromeDriver(cap);
	// }

	private static WebDriver getChromeDriver() {
		/**
		 * Ramandeep: making ChomeDriver path dynamic Pick it from ENV is "CHROME_PATH"
		 * exists ELSE pick assume its set in System PATH
		 **/
		String CHROME_PATH = "src/test/resources/drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", CHROME_PATH);
		System.out.println("Path of Chrome Driver -> " + CHROME_PATH);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		cap.setCapability("chrome.switches", Arrays.asList("--incognito"));
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(cap);
	}

	private static WebDriver getInternetExplorerDriver() {
		capabilities.setCapability("acceptSslCerts", true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("nativeEvents", false);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver getFirefoxDriver() {
		// firefoxProfile = new FirefoxProfile();
		// ProfilesIni profile = new ProfilesIni();
		// firefoxProfile = profile.getProfile("default");
		// return new FirefoxDriver(firefoxProfile);
		return new FirefoxDriver();
	}

	// private WebDriver setMobileDriver(Map<String, String> selConfig) {
	// DesiredCapabilities cap = new DesiredCapabilities();
	// String [] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");
	//
	// cap.setCapability("deviceName", appiumDeviceConfig[0]);
	// cap.setCapability("device", appiumDeviceConfig[1] );
	// cap.setCapability("platformName", appiumDeviceConfig[1]);
	// cap.setCapability("app", appiumDeviceConfig[2]);
	// cap.setCapability(CapabilityType.VERSION, "5.0.2");
	// cap.setCapability(CapabilityType.PLATFORM, "Windows");
	// capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
	// cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"120");
	//
	// String appiumServerHostUrl = selConfig.get("appiumServer");
	// URL appiumServerHost = null;
	// try {
	// appiumServerHost = new URL(appiumServerHostUrl);
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// cap.setJavascriptEnabled(true);
	// System.out.println(appiumServerHostUrl);
	//
	// AndroidDriver driverAndroid =new AndroidDriver(appiumServerHost, cap);
	//
	// Set<String> contextNames = driverAndroid.getContextHandles();
	// for (String contextName : contextNames) {
	// System.out.println(contextName);
	// if (contextName.contains("WEBVIEW")) {
	// driverAndroid.context(contextName);
	// }
	// }
	//
	// WebDriver driver = driverAndroid;
	//
	// return driver;
	//
	// //return new RemoteWebDriver(appiumServerHost, cap);
	//
	// }

	// private WebDriver setMobileDriver(Map<String, String> selConfig) {
	// DesiredCapabilities cap = new DesiredCapabilities();
	//
	// String
	// apkpath="C:/Users/abhishekpalsingh/Downloads/presentation-dev-debug.apk";
	// File app=new File(apkpath);
	//
	// String [] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");
	//
	// cap.setCapability("deviceName", appiumDeviceConfig[0]);
	// cap.setCapability("device", appiumDeviceConfig[1] );
	// cap.setCapability("platformName", appiumDeviceConfig[1]);
	// //cap.setCapability("app-package", "com.flipkart.android");
	// //cap.setCapability("app-activity",
	// "com.flipkart.android.activity.SplashActivity");
	// cap.setCapability("app", app.getAbsolutePath());
	//
	// String appiumServerHostUrl = selConfig.get("appiumServer");
	// URL appiumServerHost = null;
	// try {
	// appiumServerHost = new URL(appiumServerHostUrl);
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// cap.setJavascriptEnabled(true);
	// System.out.println(appiumServerHostUrl);
	//
	// AndroidDriver driverAndroid =new AndroidDriver(appiumServerHost, cap);
	//
	// Set<String> contextNames = driverAndroid.getContextHandles();
	//// for (String contextName : contextNames) {
	//// System.out.println(contextName);
	//// if (contextName.contains("NATIVE")) {
	//// driverAndroid.context(contextName);
	//// }
	//// }
	//
	// WebDriver driver = driverAndroid;
	//
	// return driver;
	//
	// //return new RemoteWebDriver(appiumServerHost, cap);
	//
	// }
	//
	// private WebDriver setMobileDriver_IOS(Map<String, String> selConfig) {
	// DesiredCapabilities cap = new DesiredCapabilities();
	// String [] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");
	//
	// cap.setCapability("deviceName", appiumDeviceConfig[0]);
	// cap.setCapability("device", appiumDeviceConfig[1] );
	// cap.setCapability("platformName", appiumDeviceConfig[1]);
	// cap.setCapability("app", appiumDeviceConfig[2]);
	// cap.setCapability(CapabilityType.VERSION, "5.0.2");
	// cap.setCapability(CapabilityType.PLATFORM, "Windows");
	// capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
	//
	// String appiumServerHostUrl = selConfig.get("appiumServer");
	// URL appiumServerHost = null;
	// try {
	// appiumServerHost = new URL(appiumServerHostUrl);
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// cap.setJavascriptEnabled(true);
	// System.out.println(appiumServerHostUrl);
	// return new RemoteWebDriver(appiumServerHost, cap);
	//
	// }

	@SuppressWarnings("rawtypes")
	private WebDriver setAndroidDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = new DesiredCapabilities();
		String[] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");

		cap.setCapability("deviceName", appiumDeviceConfig[0]);
		cap.setCapability("device", appiumDeviceConfig[1]);
		cap.setCapability("platformName", appiumDeviceConfig[1]);
		cap.setCapability("app", appiumDeviceConfig[2]);
		cap.setCapability(CapabilityType.VERSION, "6.0.1");
		cap.setCapability(CapabilityType.PLATFORM, "Windows");
		String appiumServerHostUrl = selConfig.get("appiumServer");
		URL appiumServerHost = null;
		try {
			appiumServerHost = new URL(appiumServerHostUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		System.out.println(appiumServerHostUrl);
		return new AndroidDriver(appiumServerHost, cap);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebDriver setIOSMobileDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = new DesiredCapabilities();
		String[] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");

		cap.setCapability("deviceName", appiumDeviceConfig[0]);
		cap.setCapability("platformName", appiumDeviceConfig[1]);
		capabilities.setCapability("browserName", appiumDeviceConfig[2]);

		String appiumServerHostUrl = selConfig.get("appiumServer");
		URL appiumServerHost = null;
		try {
			appiumServerHost = new URL(appiumServerHostUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		// cap.setCapability("safariAllowPopups", true);
		// cap.setCapability("autoAcceptAlerts", true);

		cap.setCapability("safariIgnoreFraudWarning", true);
		// cap.setCapability("nativeWebTap", true);
		cap.setCapability("autoDismissAlerts", true);
		capabilities.setCapability("unexpectedAlertBehaviour", "accept");
		// cap.setCapability("safariOpenLinksInBackground", true);
		// cap.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		// cap.setCapability("unexpectedAlertBehaviour", "accept");

		System.out.println(appiumServerHostUrl);
		IOSDriver driverIOS = new IOSDriver(appiumServerHost, cap);

		System.out.println("context ::" + driverIOS.getContextHandles());
		contextNames = driverIOS.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName);
			if (contextName.contains("WEBVIEW")) {
				driverIOS.context(contextName);
			}
		}
		cap.setJavascriptEnabled(true);
		WebDriver driver = driverIOS;

		return driver;
	}

}
