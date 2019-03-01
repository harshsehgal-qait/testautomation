package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;
import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.LayoutValidation;
import com.qait.automation.utils.PropFileHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class GetPage extends BaseUi {

	protected WebDriver webdriver;
	String pageName;
	LayoutValidation layouttest;
	public static String environment = "";

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
		layouttest = new LayoutValidation(driver, pageName);
	}

	public void selectOptionFromDropDownList(String el, String text) {
		boolean found = false;
		List<WebElement> list = elements(el);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().trim().contains(text)) {
				found = true;
				list.get(i).click();
				msg.pass("Text has found in list and clicked");
				list = elements(el);
				break;
			}
		}
		if (found == false)
			msg.fail("Text is not found in the list");
	}

	public void switchToFrameElement(int i) {
		driver.switchTo().frame(element("particular_frame_element", i + ""));
	}

	public void switchToDefaultContent() {
		hardWait(1);
		if ((ConfigPropertyReader.getProperty("browser").equals("safari"))
				|| (ConfigPropertyReader.getProperty("browser").equals("iosMobile"))) {
			hardWait(3);
		}
		driver.switchTo().defaultContent();
		System.out.println("switched back to default content ");
	}

	public void switchToDefaultWindow1(String currentwindow) {
		hardWait(3);
		driver.switchTo().window(currentwindow);
	}

	public void perform_full_screen() {
		element("body_element").sendKeys(Keys.F11);
		hardWait(4);
	}

	public String check_changing_text_Of_Element(String token, String initial_incorrect_text) {
		for (int i = 1; i <= 300; i++) {
			if (element(token).getText().trim().equals(initial_incorrect_text))
				continue;
			else
				break;
		}
		return element(token).getText().trim();
	}

	public String check_changing_text_Of_Invisible_Element(String token, String initial_incorrect_text) {
		for (int i = 1; i <= 300; i++) {
			if (elementPresent(token).getText().trim().equals(initial_incorrect_text))
				continue;
			else
				break;
		}
		return elementPresent(token).getText().trim();
	}

	public String check_changing_classname_Of_Element(String token, String initial_class) {
		for (int i = 1; i <= 300; i++) {
			if (element(token).getAttribute("class").trim().equals(initial_class))
				continue;
			else
				break;
		}
		return element(token).getAttribute("class").trim();
	}

	public String check_changing_classname_Of_Element_not_Visible(String token, String initial_class) {
		for (int i = 1; i <= 300; i++) {
			if (elementPresent(token).getAttribute("class").trim().equals(initial_class))
				continue;
			else
				break;
		}
		return elementPresent(token).getAttribute("class").trim();
	}

	public void bring_Header_Footer_In_Focus_Click_Element(String token, String clickElementToken) {
		String path1 = getLocatorTwo(token, "");
		String clickElementXpath = getLocatorTwo(clickElementToken, "");

		String js = "var targetElement = document.evaluate(\"" + path1
				+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue;" +

				"var targetElement2 = document.evaluate(\"" + clickElementXpath
				+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue;"
				+ "var evt=new Event('mouseover');" + "var evt1=new Event('click');" +

				"targetElement.dispatchEvent(evt);" + "targetElement2.click();";
		
		((JavascriptExecutor) driver).executeScript(js);
		hardWait(1);
	}

	public void bring_Header_Footer_In_Focus_Hover_Element(String token, String hoverElementToken) {
		hardWait(2);
		String path1 = getLocatorTwo(token, "");
		String clickElementXpath = getLocatorTwo(hoverElementToken, "");

		String js = "var targetElement = document.evaluate(\"" + path1
				+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue;" +

				"var targetElement2 = document.evaluate(\"" + clickElementXpath
				+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue;"
				+ "var evt=new Event('mouseover');" + "var evt1=new Event('mouseover');" +

				"targetElement.dispatchEvent(evt);" + "targetElement2.dispatchEvent(evt1);";

		((JavascriptExecutor) driver).executeScript(js);
	}

	@SuppressWarnings("unused")
	public void dragScrubber(int current_page_no, String scrubber, String footer_id, int pages) {
		int new_diff = 0;
		int current_x_position = 0, new_x_postion = 0;
		int diff = Integer.parseInt(PropFileHandler.readProperty("pixel_diff_scrubber"));

		current_x_position = current_page_no * diff;

		WebElement el = driver.findElement(By.id("hbpFooter"));

		new_diff = diff * pages;
		new_x_postion = current_x_position + new_diff;

		String str = "document.getElementById('" + footer_id + "').className='footer hbp-footer'";
		executeJavascript(str);

		hardWait(4);

		Actions ac = returnActionsObject();
		ac.moveToElement(el).dragAndDropBy(element("scrubber"), new_diff, 0).build().perform();

		logMessage("[INFO]: Dragged scrubber " + pages + " pages ahead");
	}

	protected void make_navigation_bar_visible_by_changing_classname(String nav_bar_id) {
		String str = "document.getElementById('" + nav_bar_id + "').className='reading-mode'";
		executeJavascript(str);
		hardWait(3);
	}

	public boolean testPageLayout(List<String> tagsToBeTested) {
		return layouttest.checklayout(tagsToBeTested);
	}

	public void testPageLayout(List<String> browserSizes, List<String> tagsToBeTested) {
		layouttest.checklayout(browserSizes, tagsToBeTested);
	}

	public void testPageLayout(String[] browserSizes, String[] tagToBeTested) {
		testPageLayout(Arrays.asList(browserSizes), Arrays.asList(tagToBeTested));
	}

	public boolean testPageLayout(String... tagToBeTested) {
		return testPageLayout(Arrays.asList(tagToBeTested));
	}

	public void testPageLayout() {
		testPageLayout(Arrays.asList(getProperty("./Config.properties", "browser")));
	}

	// TODO: put this in right place, create dedicated class for frame and
	// window handlers
	protected void switchToNestedFrames(String frameNames) {
		switchToDefaultContent();
		String[] frameIdentifiers = frameNames.split(":");
		for (String frameId : frameIdentifiers) {
			wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId.trim()));
		}
	}

	protected WebElement element(String elementToken) {
		return element(elementToken, "");
	}

	protected WebElement elementPresent(String token) {
		return elementPresent(token, "");
	}

	protected List<WebElement> elementsPresent(String token) {
		return elementsPresent(token, "");
	}

	protected WebElement elementPresent(String elementToken, String replacement) throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.checkPresenceOfElementInDom(getLocator(elementToken, replacement));
		} catch (NoSuchElementException excp) {
			fail(logMessage(
					"[ASSERT FAILED]: Element " + elementToken + " not found on the " + this.pageName + " !!!"));
			switchToDefaultContent();
		} catch (NullPointerException npe) {

		}
		return elem;
	}

	protected List<WebElement> elementsPresent(String elementToken, String replacement) throws NoSuchElementException {
		List<WebElement> elem = null;
		try {
			elem = wait.checkPresenceOfElementsInDom(getLocator(elementToken, replacement));
		} catch (NoSuchElementException excp) {
			fail(logMessage(
					"[ASSERT FAILED]: Element " + elementToken + " not found on the " + this.pageName + " !!!"));
			switchToDefaultContent();
		} catch (NullPointerException npe) {

		}
		return elem;
	}

	protected WebElement element(String elementToken, String replacement) throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail(logMessage(
					"[ASSERTION FAILED]: Element '" + elementToken + "' not found on the " + this.pageName + " !!!"));
			switchToDefaultContent();
		} catch (TimeoutException time) {
			fail(logMessage("[ASSERTION FAILED]: Element '" + elementToken + "' not found on the " + this.pageName
					+ " !!! due to Timeout Exception"));
			switchToDefaultContent();
		} catch (StaleElementReferenceException excp) {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement)));
		}
		return elem;
	}

	protected WebElement element(String elementToken, String replacement1, String replacement2)
			throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(
					webdriver.findElement(getLocator(elementToken, replacement1, replacement2)));
		} catch (NoSuchElementException excp) {
			fail(logMessage(
					"[ASSERTION FAILED]: Element " + elementToken + " not found on the " + this.pageName + " !!!"));
			switchToDefaultContent();
		} catch (NullPointerException npe) {
			switchToDefaultContent();
		} catch (StaleElementReferenceException excp) {
			elem = wait.waitForElementToBeVisible(
					webdriver.findElement(getLocator(elementToken, replacement1, replacement2)));
			switchToDefaultContent();
		}

		return elem;
	}

	protected WebElement childOfElement(WebElement el, String elementToken, String replacement)
			throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(el.findElement(getLocator(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail(logMessage(
					"[ASSERTION FAILED]: Element " + elementToken + " not found on the " + this.pageName + " !!!"));
			switchToDefaultContent();
		} catch (NullPointerException npe) {
			
		}
		return elem;
	}

	@SuppressWarnings("unused")
	protected void checkAndWaitForLoadingSpinnerToDisappear(String elementToken) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement elem = null;

		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, "")));
			for (int i = 1;; i++) {
				try {
					if (elem.isDisplayed())
						hardWait(1);
				} catch (Exception e) {
					logMessage("[INFO]: Content loaded! ");
					break;
				}
			}
		} catch (NoSuchElementException excp) {
			logMessage("[INFO]: Loading spinner not found");
		} catch (NullPointerException npe) {
			logMessage("[INFO]: Loading spinner not found");
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
	}

	protected WebElement childOfElement(WebElement el, String elementToken) throws NoSuchElementException {
		return childOfElement(el, elementToken, "");
	}
	
	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken, replacement)));
	}
	
	protected List<WebElement> elementsFromElement(WebElement el, String elementToken, String replacement) {
		wait.waitForElementsToBeVisible(el.findElements(getLocator(elementToken, replacement)));
		return el.findElements(getLocator(elementToken, replacement));
	}

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	protected List<WebElement> elementsFromElement(WebElement el, String elementToken) {
		return elementsFromElement(el, elementToken, "");
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		assertTrue(result, "[ASSERTION FAILED]: element '" + elementName + "' with text '"
				+ elementTextReplace + "' is not displayed");
		logMessage("[ASSERTION PASSED]: element '" + elementName + "' with text '" 
				+ elementTextReplace + "' is displayed");
		return result;
	}
	
	protected void verifyElementText(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertEquals(element(elementName).getText().trim(), expectedText,
				"[ASSERTION FAILED]: Text of the page element '" + elementName + "' is not as expected");
		logMessage("[ASSERTION PASSED]: element '" + elementName + "' is visible and Text is '" 
				+ expectedText + "'");
	}

	protected boolean verifyElementTextContains(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).getText().trim().contains(expectedText.trim());
		assertTrue(result, "[ASSERTION FAILED]: Text of the page element '" + elementName + "' is not as expected: ");
		logMessage("[ASSERTION PASSED]: element '" + elementName + "' is visible and Text is '" + expectedText + "'");
		return result;
	}

	protected boolean isElementDisplayed(String elementName) {
		waitForLoad();
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).isDisplayed();
		assertTrue(result, "[ASSERTION FAILED]: element '" + elementName + "' is NOT DISPLAYING !!!");
		logMessage("[ASSERTION PASSED]: element " + elementName + " is displayed");
		return result;
	}

	protected void isElementsDisplayed(List<WebElement> elementName) {
		for (WebElement el : elementName) {
			wait.waitForElementToBeVisible(el);
			boolean result = el.isDisplayed();
			assertTrue(result, "[ASSERTION FAILED]: element '" + elementName + "' is NOT DISPLAYING !!!");
			logMessage("[ASSERTION PASSED]: element " + elementName + " is displayed.");
		}
	}

	public boolean verifyFormat(String str, String pattern) {
		boolean matches;
		Pattern pat = Pattern.compile(pattern);
		Matcher m = pat.matcher(str);
		matches = m.find();
		return matches;
	}

	protected boolean isElementEnabled(String elementName, boolean expected) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = expected && element(elementName).isEnabled();
		assertTrue(result, "[ASSERTION FAILED]: element '" + elementName + "' is  ENABLED :- " + !expected);
		logMessage("[ASSERTION PASSED]: element " + elementName + " is enabled :- " + expected);
		return result;
	}

	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected String getLocatorTwo(String token, String repl) {
		String[] locator = getELementFromFile(this.pageName, token);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", repl);
		return locator[2];
	}

	protected WebElement elemconstructed_dynamically(String elementToken, String replacement)
			throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait
					.waitForElementToBeVisible(webdriver.findElement(getLocatorByReplacing(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail(logMessage(
					"[ASSERTION FAILED]: Element " + elementToken + " not found on the " + this.pageName + " !!!"));
			switchToDefaultContent();
		} catch (NullPointerException npe) {
			switchToDefaultContent();
		} catch (StaleElementReferenceException exe) {
			System.out.println("Inside catch ....");
			hardWait(1);
			elem = wait
					.waitForElementToBeVisible(webdriver.findElement(getLocatorByReplacing(elementToken, replacement)));
		}
		return elem;
	}

	protected WebElement elementPresentconstructed_dynamically(String elementToken, String replacement)
			throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.checkPresenceOfElementInDom(getLocatorByReplacing(elementToken, replacement));
		} catch (NoSuchElementException excp) {
			fail(logMessage(
					"[ASSERTION FAILED]: Element '" + elementToken + "' not found on the " + this.pageName + " !!!"));
			switchToDefaultContent();
		} catch (NullPointerException npe) {
			switchToDefaultContent();
		} catch (StaleElementReferenceException exe) {
			System.out.println("Inside catch ....");
			hardWait(1);
			elem = wait
					.waitForElementToBeVisible(webdriver.findElement(getLocatorByReplacing(elementToken, replacement)));
		}
		return elem;
	}

	protected By getLocatorByReplacing(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		// locator[2] = StringUtils.replace(locator[2], "%", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = StringUtils.replace(locator[2], "$", replacement1);
		locator[2] = StringUtils.replace(locator[2], "%", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}
	
	public static void copyUsingKeyboard(WebElement e) {
		e.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.chord(Keys.CONTROL, "c"));
	}

	public static String getTextFromClipboard() throws UnsupportedFlavorException, IOException {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		return result;
	}

	@SuppressWarnings("unused")
	public void mobileSwipe(WebElement element, double startX, double startY, double endX, double endY,
			double duration) {
		Point location = element.getLocation();
		Dimension size = element.getSize();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		logMessage("Starting swiping element with the coordinate ");
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", startX);
		swipeObject.put("startY", startY);
		swipeObject.put("endX", endX);
		swipeObject.put("endY", endY);
		swipeObject.put("duration", duration);
		js.executeScript("mobile: swipe", swipeObject);
	}

	@SuppressWarnings("rawtypes")
	public void swipeLeft(String elementToken, String replacement) {
		By locator = getLocator(elementToken, replacement);
		MobileElement element = (MobileElement) driver.findElement(locator);
		int offset = 1;
		Point p = element.getCenter();
		Point location = element.getLocation();
		Dimension size = element.getSize();
		((AppiumDriver) driver).swipe(location.getX() + size.getWidth() - offset, p.getY(), location.getX() + offset,
				p.getY(), 1000);
	}

	@SuppressWarnings("rawtypes")
	public void swipeRight(String elementToken, String replacement) {
		By locator = getLocator(elementToken, replacement);
		MobileElement element = (MobileElement) driver.findElement(locator);
		int offset = 1;
		Point p = element.getCenter();
		Point location = element.getLocation();
		Dimension size = element.getSize();
		((AppiumDriver) driver).swipe(location.getX() + offset + 20, p.getY(),
				location.getX() + size.getWidth() - offset, p.getY(), 1000);
	}

	@SuppressWarnings("rawtypes")
	public void orintation() {
		// System.out.println(((AppiumDriver) webdriver).
		System.out.println("starting ..........................");
		wait.hardWait(4);
		((AppiumDriver) webdriver).hideKeyboard();
		System.out.println("HideKeyboard  completion");
		// webdriver.navigate().back();
		wait.hardWait(4);
		webdriver.navigate().back();
		System.out.println("Back completion");

		// ((AppiumDriver) webdriver).rotate(ScreenOrientation.LANDSCAPE);
		((AppiumDriver) webdriver).zoom(200, 300);
		System.out.println("Zoom completion");
		// ((AppiumDriver) webdriver).hideKeyboard();
		// ((AppiumDriver) webdriver).scrollTo("About");

		TouchAction touch = new TouchAction((AppiumDriver) webdriver);
		touch.longPress(200, 200).perform();
		System.out.println("long press worked");
	}

	public void scrollDownToBottom() {
		wait.waitForPageToLoadCompletely();
		scrollWindow(0, 10000);
		msg.log("User scrolled to bottom");
	}

	public void scrollUpToPage() {
		wait.waitForPageToLoadCompletely();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)");
		msg.log("User scrolled Up");
	}

	protected boolean checkIfElementIsNotThere(String eleString) {
		boolean flag = false;
		try {
			if (webdriver.findElement(getLocator(eleString)).isDisplayed()) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (NoSuchElementException ex) {
			flag = true;
		}
		return flag;
	}

	protected boolean isElementNotDisplayed(String elementName) {
		boolean result;
		result = checkIfElementIsNotThere(elementName);
		assertTrue(result, "[ASSERTION FAILED]: element '" + elementName + "' is displayed which should not be there");
		logMessage("[ASSERTION PASSED]: element '" + elementName
				+ "' is NOT displayed after waiting for 1 minute on the page as expected!!!");
		return result;
	}

	protected boolean isElementNotDisplayed(String elementName, String elementTextReplace) {
		boolean result;
		try {
			driver.findElement(getLocator(elementName, elementTextReplace));
			result = false;
		} catch (NoSuchElementException excp) {
			result = true;
		}
		assertTrue(result, "[ASSERTION FAILED]: element '" + elementName + "' with text '" + elementTextReplace
				+ "' is displayed as expected");
		logMessage("[ASSERTION PASSED]: element '" + elementName + "' with text '" + elementTextReplace
				+ "' is NOT displayed as expected!!!");
		return result;
	}

	protected boolean checkIfElementIsThere(String eleString) {
		boolean flag = false;
		try {
			if (webdriver.findElement(getLocator(eleString)).isDisplayed()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException ex) {
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings({ "rawtypes" })
	public void swipe_screen_to_left() {
		// ((AppiumDriver) webdriver).swipe(50,600,600,600,2000);
		// JavascriptExecutor js = (JavascriptExecutor) webdriver;
		// HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		// swipeObject.put("startX", 0.95);
		// swipeObject.put("startY", 0.5);
		// swipeObject.put("endX", 0.05);
		// swipeObject.put("endY", 0.5);
		// swipeObject.put("duration", 1.8);
		// js.executeScript("mobile: swipe", swipeObject);
		((AppiumDriver) webdriver).context("NATIVE_APP");
		Dimension size = ((AppiumDriver) webdriver).manage().window().getSize();
		int startx = (int) (size.width * 0.8);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		((AppiumDriver) webdriver).swipe(startx, starty, endx, starty, 1000);
		System.out.println("swipe completion");

		// ((AppiumDriver) webdriver).
	}

	// Sugandha Mathur

	public void scrollToElement(String locator) {
		WebElement element = driver.findElement(getLocator(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		hardWait(1);
	}

	public int getElementSize(String string) {
		return driver.findElements(getLocator(string)).size();
	}

	public int getElementSize(String elementToken, String replacement) {
		By locator = getLocator(elementToken, replacement);
		return driver.findElements(locator).size();
	}

	public void scrollToElement(WebElement locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locator);
		hardWait(1);
	}
	
	public void checkLazyLoading() {
		int flag = 1;
		Object reachedbottom = (executeJavascriptWithReturn(
				"return $(document).height() == ($(window).height() + $(window).scrollTop());"));
		while (reachedbottom.toString() == "false") {
			executeJavascript("window.scrollBy(0,1000)");
			flag++;
			if (flag == 5) {
				break;
			}
			reachedbottom = (executeJavascriptWithReturn(
					"return $(document).height() == ($(window).height() + $(window).scrollTop());"));
		}
		msg.log("Verified: Lazy Loading");
	}

}
