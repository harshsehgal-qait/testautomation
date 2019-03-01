package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.testng.Reporter;
import org.yaml.snakeyaml.Yaml;

import com.katespade.keywords.*;
import com.qait.automation.WebDriverFactory;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.TakeScreenshot;
import com.qait.automation.utils.TestDataHandler;
import com.qait.automation.utils.WikiPageData;

public class TestSessionInitiator {
	
	private String testname;
	public static String tier;
	
	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	
	public Map<?, ?> config;
	public Map<String, WikiPageData> testData;
	
	public TakeScreenshot takescreenshot;
	
	/**
	 * Initiating the page objects
	 */
	public TopBanner topBanner;
	public MainHeader homeheader;
	public ProductDetailsPage productdetails;
	public ShoppingCartPage shoppingCart;
	public Search search;
	public ProductShippingPage shipngpage;
	public ProductPaymentPage paymentpage;
	public OrderReviewPage ordereviewpage;
	public AccountPage accountPage;
	public HomeLandingPage landingPage;
	public Footer footer;
	public StoreLocator storeLocator;
	public MiniCartPage minicartpage;
	public ShopGridPage shopGridPage;
	public WishList wishlistpage;
	public OrderHistoryPage orderHistoryPage;
	public OrderConfirmationPage orderConfirmationPage;
	public OrderDetailsPage orderDetailsPage;
	public CustomerCarePage customercare;
	public CountrySelection countrySelection;
	public Page_404 page404;
	public PayPalPage paypal;

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
		homeheader = new MainHeader(driver);
		productdetails = new ProductDetailsPage(driver);
		shoppingCart = new ShoppingCartPage(driver);
		search = new Search(driver);
		shipngpage = new ProductShippingPage(driver);
		paymentpage = new ProductPaymentPage(driver);
		ordereviewpage = new OrderReviewPage(driver);
		topBanner = new TopBanner(driver);
		accountPage = new AccountPage(driver);
		landingPage = new HomeLandingPage(driver);
		footer = new Footer(driver);
		storeLocator = new StoreLocator(driver);
		minicartpage = new MiniCartPage(driver);
		shopGridPage = new ShopGridPage(driver);
		wishlistpage = new WishList(driver);
		orderHistoryPage = new OrderHistoryPage(driver);
		orderConfirmationPage = new OrderConfirmationPage(driver);
		orderDetailsPage = new OrderDetailsPage(driver);
		customercare = new CustomerCarePage(driver);
		countrySelection = new CountrySelection(driver);
		page404 = new Page_404(driver);
		paypal = new PayPalPage(driver);
	}
	
	/**
	 * Page object Initiation done
	 *
	 * @param testname
	 */
	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
		this.testname = testname;
	}
	
	public TestSessionInitiator(String testname, String browserName) {
		if (System.getProperties().containsKey("browser")) {
			browserName = System.getProperty("browser");
		}
		try {
			testConfigutationPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		wdfactory = new WebDriverFactory(browserName);
		try {
			initTestData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		testInitiator(testname);
		this.testname = testname;
	}

	@SuppressWarnings("rawtypes")
	private void testConfigutationPath() throws FileNotFoundException {
		config = new HashMap();
		Yaml yaml = new Yaml();
		String tierFromConfigFile = getProperty("Config.properties", "tier").trim();
		tier = System.getProperty("environment", tierFromConfigFile);
		if (tier.equalsIgnoreCase("dev")) {
			String yamlpath = "src/test/resources/testdata/DEV_TestData.yml";
			config = (Map<?, ?>) yaml.load(new FileInputStream(new File(yamlpath)));
		} else if (tier.equalsIgnoreCase("dev2")) {
			String yamlpath = "src/test/resources/testdata/DEV2_TestData.yml";
			config = (Map<?, ?>) yaml.load(new FileInputStream(new File(yamlpath)));
		} else if (tier.equalsIgnoreCase("stage")) {
			String yamlpath = "src/test/resources/testdata/STAGE_TestData.yml";
			config = (Map<?, ?>) yaml.load(new FileInputStream(new File(yamlpath)));
		}

		else if (tier.equalsIgnoreCase("stage2")) {
			String yamlpath = "src/test/resources/testdata/STAGE2_TestData.yml";
			config = (Map<?, ?>) yaml.load(new FileInputStream(new File(yamlpath)));
		} else if (tier.equalsIgnoreCase("dwlp2")) {
			String yamlpath = "src/test/resources/testdata/DWLP2_TestData.yml";
			config = (Map<?, ?>) yaml.load(new FileInputStream(new File(yamlpath)));
		} else if (tier.equalsIgnoreCase("prod") || tier.equalsIgnoreCase("production")) {
			// File file = new
			// File("src/test/resources/testdata/PROD_TestData.yml");
			// String yamlpath= file.getAbsolutePath();
			String yamlpath = "src/test/resources/testdata/PROD_TestData.yml";
			config = (Map<?, ?>) yaml.load(new FileInputStream(new File(yamlpath)));
		} else {
			Reporter.log("YOU HAVE PROVIDED WRONG TIER IN CONFIG!!! using dev test data", true);
		}
	}

	private void initTestData() throws Exception {
		TestDataHandler testDataHandler = new TestDataHandler(config);
		testData = testDataHandler.testDataMap();
	}

	private void testInitiator(String testname) {
		setYamlFilePath();
		_configureBrowser();
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		if (!(_getSessionConfig().get("browser").toLowerCase().contains("ios"))) {
			driver.manage().deleteAllCookies();
//			driver.manage().window().setSize(new Dimension(375,812));
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver", "seleniumserverhost", "timeout", "driverpath",
				"appiumServer", "mobileDevice" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public void launchApplication(String base_url) {
		Reporter.log("\n[INFO]: The application url is :- " + base_url, true);
		driver.manage().deleteAllCookies();
		driver.get(base_url);
	}
	
	public void openUrl(String url) {
		driver.get(url);
	}
	
	public void closeBrowserSession() {
		Reporter.log("[INFO]: The Test: " + this.testname.toUpperCase() + " COMPLETED!" + "\n", true);
		try {
			driver.quit();
		} catch (RuntimeException e) {
			System.out.println("Exception caught while closing the browser");
		}
	}

	public void closeTestSession() {
		closeBrowserSession();
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP:- " + testStepName + " *****", true);
		Reporter.log(" ", true);
	}
	
	public void modify_java_file() {
		File log = new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\qait\\hbp\\ereader\\keywords\\AnnotationHighlightActions.java");
		String search = "WebDriver driver";
		String replace = "AndroidDriver driver";
		String search1 = "AndroidDriver driver";
		String replace1 = "WebDriver driver";
		List<String> list1 = new ArrayList<String>();

		try {
			FileInputStream fr = new FileInputStream(log);
			String s;
			try (BufferedReader br = new BufferedReader(new InputStreamReader(fr))) {

				while ((s = br.readLine()) != null) {
					if (ConfigPropertyReader.getProperty("browser").equals("mobile"))
						s = s.replaceAll(search, replace);
					else
						s = s.replaceAll(search1, replace1);
					list1.add(s);
				}
				// totalStr = totalStr.;
				FileWriter fw = new FileWriter(log);
				BufferedWriter bw = new BufferedWriter(fw);
				for (String s1 : list1) {
					bw.write(s1);
					bw.newLine();
				}
				br.close();
				bw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
