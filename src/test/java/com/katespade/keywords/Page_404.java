package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class Page_404 extends GetPage {

	WebDriver driver;

	public Page_404(WebDriver driver) {
		super(driver, "Katespade/Page404");
		this.driver = driver;
	}

	public void verify_404_page_Customer_Service(String customerServiceMessage) {
		isElementDisplayed("customerServiceMsg");
		String message = element("customerServiceMsg").getText();
		msg.log("" + message);
		assertEquals(message.contains(customerServiceMessage), true, "Customer Service message is not present");
		msg.log("Customer Service message is present");
	}

	public void verify_404_page_Try_New_Search(String searchText) {
		isElementDisplayed("try_new_search");
		String search_msg = element("try_new_search").getText();
		msg.log("" + search_msg);
		assertEquals(search_msg.contains(searchText), true, "Try New Search is not Displayed");
		msg.log("Try New Search is not Displayed");

	}

	public void verify_404_Page_Search_Functionality(String product) {
		isElementDisplayed("input_Search");
		sendText(element("input_Search"), product);
		msg.log(product + " is entered");
		isElementDisplayed("btn_search");
		element("btn_search").click();
		msg.log("User clicks on GO button");
	}

	public void verify_404_page(String title, String titleFr) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("page_header");
		String header = element("page_header").getText();
		msg.log("" + header);
		if (getCurrentURL().contains("ksEuFr")) {
			assertEquals(header.contains(titleFr), true, "User is not on 404 Page");
		} else {
			assertEquals(header.contains(title), true, "User is not on 404 Page");
		}
		msg.log("User is on 404 Page");

	}

	public void verify404PageIsDisplayedWhenProductIDNotFoundInUrl(String productId) {
		String url = getCurrentURL();
		msg.log(url);
		String ProductIdInUpperCase = productId.toUpperCase() + ".html";
		String urlWithoutId = url.replace(ProductIdInUpperCase, ".html");
		msg.log(urlWithoutId);
		launchSpecificUrl(urlWithoutId);

	}

	public void verify404PageIsDisplayedWhenProductCategoryNotFoundInUrl() {
		String url = getCurrentURL();
		msg.log(url);
		int lengthOfURL = url.length();
		msg.log("" + lengthOfURL);
		String newURL = url.substring(0, lengthOfURL - 2);
		msg.log(newURL);
		launchSpecificUrl(newURL);

	}

	public void verifyContentAssetIn404Page() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("contentAsset404");
		isElementDisplayed("btn_home");
		isElementDisplayed("btn_customerCare");
		isElementDisplayed("lbl_tryNewSearch");
		isElementDisplayed("input_Search");
		isElementDisplayed("btn_search");
		msg.log("Verified: Content Asset In 404 Page");
	}

	public void verifyCustomerCareFunctionalityIn404Page() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_customerCare");
		clickUsingJS(element("btn_customerCare"));
		wait.waitForPageToLoadCompletely();
		assertEquals(getCurrentURL().contains("customer-care"), true,
				"[Assert Fail]: User is not navigated to the customer care page");
		msg.log("User is navigated to the customer care page");
		backButton();
		assertEquals(getCurrentURL().contains("404"), true, "[Assert Fail]: User is not navigated back to 404 page");
		msg.log("User is navigated to the 404 page");
	}

	public void verifySearchFunctionalityIn404Page() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("input_Search");
		element("input_Search").click();
		element("input_Search").sendKeys("bags");
		clickUsingJS(element("btn_search"));
		wait.waitForPageToLoadCompletely();
		assertEquals(getCurrentURL().contains("bags"), true, "[Assert Fail]: User is not navigated to the search page");
		assertEquals(executeJavascriptReturnsString("return $('h1.cat-name-title').text()").contains("bags"), true,
				"[Assert Fail]: User is not navigated to the search page");
		msg.log("User is navigated to the search page");
		backButton();
		assertEquals(getCurrentURL().contains("404"), true, "[Assert Fail]: User is not navigated back to 404 page");
		msg.log("User is navigated to the 404 page");
	}

	public void verifyHomeBtnFunctionalityIn404Page(String homepageURL) {
		wait.waitForPageToLoadCompletely();
		Assert.assertEquals(getCurrentURL(), homepageURL, 
				"[ASSERTION FAILED]: User is not navigated to the homepage");
		msg.pass("User is navigated to the homepage !!!");
		
		isElementDisplayed("btn_home");
		clickUsingJS(element("btn_home"));
		msg.pass("Home button functionality is working on 404 page !!!");
		
		backButton();
		
		Assert.assertTrue(getCurrentURL().contains("404"), 
				"[ASSERTION FAILED]: User is not navigated back to 404 page");
		msg.pass("User is navigated to the 404 page !!!");
	}

}
