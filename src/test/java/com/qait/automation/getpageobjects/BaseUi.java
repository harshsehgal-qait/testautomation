package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getPageTitleFromFile;
import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.Msg;
import com.qait.automation.utils.SeleniumWait;

/**
 *
 * @author prashantshukla
 */
public class BaseUi {

	WebDriver driver;
	protected SeleniumWait wait;
	private String pageName;
	public Msg msg;

	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty("Config.properties", "timeout")));
		this.msg = new Msg();
	}

	protected Actions returnActionsObject() {
		return new Actions(driver);
	}

	protected JavascriptExecutor returnJSExecutor() {
		return (JavascriptExecutor) driver;
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected String logMessage(String message) {
		Reporter.log(message, true);
		return message;
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected void launchURL(String url) {
		driver.get(url);
	}

	protected void verifyPageTitleExact() {
		String pageTitle = getPageTitleFromFile(pageName);
		verifyPageTitleExact(pageTitle);
	}

	protected void backButton() {
		driver.navigate().back();
		msg.log("'Back' button is clicked");
	}

	public void wait_for_The_loading_Red_Grey_Spinner_To_Vanish(By loc) {
		hardWait(1);
		changeWindow(1);
		wait.waitForElementToBeInVisible(loc);
		logMessage("[INFO]: Red-Grey spinner vanished, application loaded! ");
	}
	
	public void clickUsingJavaScriptClickEvent(String locatorValue) {
		String path1 = locatorValue;

		String js = "var targetElement = document.evaluate(\"" + path1
				+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue;" +
				"targetElement.click();";
		((JavascriptExecutor) driver).executeScript(js);
	}

	protected void verifyPageTitleExact(String expectedPagetitle) {
		if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))
				&& (getProperty("browser").equalsIgnoreCase("chrome"))) {
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToBeExact(expectedPagetitle.toString());
			msg.log("[ASSERT PASSED]: PageTitle for " + pageName + " is exactly: '" + expectedPagetitle + "'");
		} catch (TimeoutException ex) {
			msg.log("[ASSERT FAILED]: PageTitle for " + pageName + " is not exactly: '" + expectedPagetitle
					+ "'!!!\n instead it is :- " + driver.getTitle());
		}
	}

	/**
	 * Verification of the page title with the title text provided in the page
	 * object repository
	 */
	protected void verifyPageTitleContains() {
		waitForLoad();
		String url = driver.getCurrentUrl();
		msg.log(url);
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(5);
		}
		String expectedPagetitle = getPageTitleFromFile(pageName).trim();
		verifyPageTitleContains(expectedPagetitle);
	}

	protected void verifyPageTitleCorrect(String expected_title) {
		Assert.assertTrue(getPageTitle().trim().contains(expected_title.trim()),
				"[ASSERT FAILED]: Page title is not correct ");
		msg.log("[ASSERT FAILED]: Correct page title is displayed on launching eReader");
	}

	/**
	 * this method will get page title of current window and match it partially with
	 * the param provided
	 *
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	protected void verifyPageTitleContains(String expectedPagetitle) {
		String url = driver.getCurrentUrl();
		msg.log(url);
		msg.log("CONTACT US PAGE");
		if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))
				&& (getProperty("browser").equalsIgnoreCase("chrome"))) {
			expectedPagetitle = getPageTitle();
		}
		try {
			wait.waitForPageTitleToContain(expectedPagetitle.toString());
			msg.log("[ASSERT PASSED]: PageTitle for " + pageName + " contains: '" + expectedPagetitle + "'.");
		} catch (TimeoutException exp) {
			msg.log("[ASSERT FAILED]: As actual Page Title for '" + pageName
					+ "' does not contain expected Page Title : '" + expectedPagetitle + "'.");
		}
	}

	/**
	 * this method will get page url of current window and match it partially with
	 * the param provided
	 *
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	protected void verifyPageUrlContains(String expectedPageUrl) {
		wait.waitForPageToLoadCompletely();
		String currenturl = getCurrentURL();
		Assert.assertTrue(currenturl.toLowerCase().trim().contains(expectedPageUrl.toLowerCase()),
				msg.log("[INFO]: verifying: URL - " + currenturl + " of the page '" + pageName + "' contains: "
						+ expectedPageUrl));
		msg.log("[ASSERT PASSED]: URL of the page " + pageName + " contains:- " + expectedPageUrl);
	}

	protected WebElement getElementByIndex(List<WebElement> elementlist, int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist, String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// exception
		if (element == null) {
		}
		return element;
	}

	protected WebElement getElementByContainsText(List<WebElement> elementlist, String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}

	public void clickOnKateSpadeLogo() {
		executeJavascript("$('#top-banner-interior .primary-logo img').click()");
		msg.log("Click on Katespade logo");
	}

	protected void switchToFrame(WebElement element) {
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
	}

	public void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}

	public void switchToFrame(String name) {
		msg.log("switch frame");
		hardWait(2);
		if ((ConfigPropertyReader.getProperty("browser").equals("safari"))
				|| (ConfigPropertyReader.getProperty("browser").equals("iosMobile"))) {
			hardWait(3);
		}
		wait.waitForFrameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@name='" + name + "']"));
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
		System.out.println("switched back to default content ");
	}

	protected void executeJavascript(String script) {
		hardWait(1);
		waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").equals("safari")
				|| ConfigPropertyReader.getProperty("browser").contains("iosMobile")) {
			hardWait(1);
		}
		try {
			((JavascriptExecutor) driver).executeScript(script);
		} catch (WebDriverException e) {
			System.out.println("There was some exception while executing JS");
		}
	}

	protected void executeJavaScriptUsingXpath(String xpath) {
		executeJavascript("document.evaluate(\"" + xpath
				+ "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()");
	}

	protected Object executeJavascriptWithReturn(String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}

	protected void executeJavascript(String script, WebElement e) {
		((JavascriptExecutor) driver).executeScript(script, e);
	}

	protected String executeJavascriptReturnsString(String script) {
		return (String) ((JavascriptExecutor) driver).executeScript(script);
	}

	protected boolean executeJavascriptReturnsBoolean(String script) {
		return (boolean) ((JavascriptExecutor) driver).executeScript(script);
	}

	@SuppressWarnings("unchecked")
	protected List<WebElement> executeJavascriptReturnsWebElements(String script) {
		return (List<WebElement>) ((JavascriptExecutor) driver).executeScript(script);
	}

	protected void clickUsingJS(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	protected Long executeJavascriptReturnsLong(String script) {
		return (Long) ((JavascriptExecutor) driver).executeScript(script);
	}

	protected void hover(WebElement element) {
		if ((ConfigPropertyReader.getProperty("browser").contains("firefox"))
				|| (ConfigPropertyReader.getProperty("browser").contains("ie"))
				|| (ConfigPropertyReader.getProperty("browser").contains("chrome"))
				|| (ConfigPropertyReader.getProperty("browser").equals("mobile"))) {
			Actions hoverOver = new Actions(driver);
			hoverOver.moveToElement(element).build().perform();
		} else if (ConfigPropertyReader.getProperty("browser").contains("iosMobile")) {
			click(element);
		} else if (ConfigPropertyReader.getProperty("browser").contains("safari")) {

		}
	}
	
	protected void pressEnter() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	protected void handleAlert() {
		try {
			switchToAlert().accept();
			msg.log("Alert handled..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}

	Set<String> windows;
	String wins[];

	static String parentWindow;

	public void changeWindow(int i) {
		hardWait(1);
		parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		if (i > 0) {
			for (int j = 0; j < 9; j++) {
				System.out.println("Windows: " + windows.size());
				hardWait(1);
				if (windows.size() >= 2) {
					try {
						Thread.sleep(5000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
				windows = driver.getWindowHandles();
			}
		}
		String wins[] = windows.toArray(new String[windows.size()]);
		driver.switchTo().window(wins[i]);
		hardWait(1);
		msg.log("Title: " + driver.switchTo().window(wins[i]).getTitle());
	}

	public void switchWindow() {
		for (String current : driver.getWindowHandles()) {
			driver.switchTo().window(current);
		}
		msg.log("Window switched");
	}

	public void scrollWindow(int x, int y) {
		hardWait(1);
		waitForLoad();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + x + "," + y + ")", "");
	}

	protected void closeWindowAndSwitchBackToOriginalWindow(int i) {
		driver.close();
		driver.switchTo().window(wins[i]);
	}

	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected void selectProvidedTextFromDropDown(WebElement el, String text) {
		wait.waitForElementToBeVisible(el);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}
	
	protected void selectIndexFromDropDown(WebElement el, int index) {
		wait.waitForElementToBeVisible(el);
		Select sel = new Select(el);
		sel.selectByIndex(index);
	}
	
	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	protected void click(WebElement element) {
		try {
			waitForLoad();
			wait.waitForElementToBeClickable(element);
			wait.waitForElementToBeVisible(element);
			// scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			hardWait(2);
			wait.waitForElementToBeVisible(element);
			wait.waitForElementToBeClickable(element);
			element.click();
			msg.log("Clicked Element " + element + " after catching Stale Element Exception");
		} catch (WebDriverException ex2) {
			wait.waitForElementToBeClickable(element);
			scrollDown(element);
			element.click();
			msg.log("Clicked Element " + element + " after catching Webdriver Exception");
		} catch (Exception ex3) {
			msg.log("Element " + element + " could not be clicked! " + ex3.getMessage());
		}
	}

	protected void sendText(WebElement element, String text) {
		try {
			waitForLoad();
			wait.waitForElementToBeVisible(element);
			element.clear();
			hardWait(1);
			try{
				element.click();
			}catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			}
			element.sendKeys(text);
			msg.log("Entered '" + text + "' in textbox");
		} catch (StaleElementReferenceException ex1) {
			hardWait(2);
			wait.waitForElementToBeVisible(element);
			element.sendKeys(text);
			msg.log("Clicked Element " + element + " after catching Stale Element Exception");
		} catch (Exception ex2) {
			msg.log("Element " + element + " could not be clicked! " + ex2.getMessage());
		}
	}

	public void launchSpecificUrl(String url) {
		driver.get(url);
		hardWait(1);
		msg.log("Application Url is: " + url);
		executeJavascript("document.getElementsByClassName('cookie-notifier ui-helper-hidden')[0]."
				+ "getElementsByTagName('button')[0].click()");
		msg.log("Clicked on 'Close' icon of Cookie notification");
		
		// executeJavascript("sessionStorage.setItem('pagevisit',4);");
		// driver.manage().deleteAllCookies();
		// executeJavascript("PhishingAlertController.ignoreWarningSelected();");
		// Cookie ck = new Cookie("emailoverlay", "value");
		// driver.manage().addCookie(ck);
		//
		// Cookie ck2 = new Cookie("fsr.s",
		// "{'v2':2,'v1':1,'rid':'d0367025370225726458fd6921ea','to':3,'c':'http://dwdevelopment.coach.com/','pv':1,'lc':{'d0':{'v':1,'s':true}},'cd':0,'sd':0,'l':'en','i':0}");
		// driver.manage().addCookie(ck2);
		//
		// Cookie ck1 = new Cookie("fsr.r",
		// "{'d':90,'i':'1292956831475_954940','e':1293563385893,'s':1}");
		// driver.manage().addCookie(ck1);
	}

	public void launch_Core_Curriculum_Url(String url) {
		String target = url.replace("$", "#!");
		msg.log("Target url is: " + target);
		driver.get(target);
	}

	public void clickByJavascript(WebElement element) {
		wait.waitForElementToBeVisible(element);
		scrollDown(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void waitUntilElementIsEnabled(WebElement el) {
		int i = 1;
		wait: while (!el.isEnabled()) {
			if (i > 15) {
				break wait;
			}
			try {
				i++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void waitUntilWebElementListIsLoaded(List<WebElement> li) {
		int timer = 1;
		wait: while (!(li.size() > 0)) {
			if (timer > 10) {
				break wait;
			}
			try {
				timer++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printPageURl() {
		msg.log("Page URL: " + driver.getCurrentUrl());
	}

	public void refreshThePage() {
		driver.navigate().refresh();
		hardWait(5);
		msg.log("Refresh the current web page");
	}
	
	public String switchOnWindow() {
		try {
			String currentWindow = driver.getWindowHandle();
			msg.log("Windows:" + driver.getWindowHandles().size());
			for (String s : driver.getWindowHandles())
				driver.switchTo().window(s);
			msg.log("Window Switched." + driver.getWindowHandle() + " and title: " + driver.getTitle());
			return currentWindow;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void switchToNewWindow() {
		hardWait(2);
		if ((ConfigPropertyReader.getProperty("browser").equals("safari"))
				|| (ConfigPropertyReader.getProperty("browser").equals("iosMobile"))) {
			hardWait(3);
		}
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		msg.log(driver.getTitle());
		msg.log(driver.getCurrentUrl());
	}

	public void waitForLoaderSpinnerToDissapear(WebElement element) {
		int i = 0;
		try {
			while (element.isDisplayed() && i <= SeleniumWait.timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		}
	}

	public void waitForLoad() {
		try {
			new WebDriverWait(driver, 90).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
					.executeScript("return document.readyState").equals("complete"));
		} catch (NullPointerException e) {
			msg.log("Null Pointer Exception Caught");
			hardWait(3);
		}
	}

}