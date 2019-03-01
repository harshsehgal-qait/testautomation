package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.testng.Assert.*;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class CustomerCarePage extends GetPage {

	WebDriver driver;

	public CustomerCarePage(WebDriver driver) {
		super(driver, "Katespade/CustomerCarePage");
		this.driver = driver;
	}

	public void verfifyUserlandsOnContactUsPage(String pageName) {
		wait.waitForPageToLoadCompletely();
		String url = getCurrentURL();
		assertEquals(url.contains(pageName), true, "User is not on contact us page");
		msg.log("User is on contact us page");
		isElementDisplayed("page_header");
		if (!ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("customer_care_header");
			msg.log("User is on " + element("customer_care_header").getText() + " " + element("page_header").getText()
					+ " Page");
		}
	}

	public void verifyCustomerCareLeftHandNavigationLibraryFoldersAreDisplayed() {
		int sizeOfComponents = elements("lst_customer_care_library").size();
		for (int i = 0; i < sizeOfComponents; i++) {
			elements("lst_customer_care_library").get(i).isDisplayed();
			msg.log(elements("lst_customer_care_library").get(i).getText() + " is displayed");

			// Verify Library Folder
			clickUsingJS(elements("lst_customer_care_library").get(i));
			msg.log(elements("lst_customer_care_library").get(i).getText() + " is clicked");
			String style = executeJavascriptReturnsString(
					"return $('.cs-nav-wrapper ul')[" + i + "].getAttribute('style')");
			assertTrue(style.contains("display: block"),
					elements("lst_customer_care_library").get(i).getText() + " is not a folder");
			msg.log(elements("lst_customer_care_library").get(i).getText() + " is a folder");
			executeJavascript("$('.cs-nav-wrapper span')[" + i + "].click()");
			msg.log(elements("lst_customer_care_library").get(i).getText() + "is clicked and closed");
		}
	}

	public void verifyCustomerCareContentInLeftHandNavigationAndColor(String customerCarePage) {
		int sizeOfContent = elements("lst_customer_content").size();
		for (int i = 0; i < sizeOfContent; i++) {
			elements("lst_customer_content").get(i).isDisplayed();
			msg.log(elements("lst_customer_content").get(i).getText() + " is displayed");
			clickUsingJS(elements("lst_customer_content").get(i));
			if (!ConfigPropertyReader.getProperty("browser").contains("ios")) {
				verifyUserLandsOnCorrectCustomerCarePage(customerCarePage.toLowerCase(),
						elements("lst_customer_content").get(i).getText());
				// Verification of green color of lest navigation- KSEU-1041
				msg.log("Color of active page is : " + elements("lst_customer_content").get(i).getCssValue("color"));
				Assert.assertEquals(elements("lst_customer_content").get(i).getCssValue("color"), "rgba(72, 168, 66, 1)",
						"Green color not displaying");
				msg.pass("Green color is displayed");
			}
		}
	}

	public void verifyUserLandsOnCorrectCustomerCarePage(String customerCarePage, String content) {
		wait.waitForPageToLoadCompletely();
		
		isElementDisplayed("customer_care_header");
		String customerCare = element("customer_care_header").getText();
		
		msg.log("Actual Customer Care" + customerCare);
		msg.log("Expected Result" + customerCarePage);
		
		Assert.assertTrue(customerCare.contains(customerCarePage), 
				"[ASSERTION FAILED]: User is not on customer care page");
		msg.pass("User is on Customer Care page");
		
		// Page Title verification
		// isElementDisplayed("page_header");
		// String pageHeader = element("page_header").getText();
		// msg.log("Page header :" + pageHeader);
		// String pageTitle = getPageTitle();
		// msg.log("Page Title :" + pageTitle);
		// assertEquals(pageTitle.toLowerCase().contains(pageHeader.toLowerCase()),
		// true, "User is not on " + pageHeader + "page");
		// msg.log("User is on " + pageHeader + "page");
		// isElementDisplayed("breadcrumb");
		// msg.log("User lands On Breadcrum: " + element("breadcrumb").getText() +
		// "PageHeader: "
		// + element("customer_care_header").getText() + " " +
		// element("page_header").getText());

		isElementDisplayed("breadcrumb");
		String breadCrumbText = element("breadcrumb").getText();
		msg.log("Breadcrumb of page :" + breadCrumbText);
		// String pageTitle = getPageTitle();
		msg.log("Content :" + content);
		assertEquals(content.toLowerCase().contains(breadCrumbText.toLowerCase()), true,
				"User is not on " + breadCrumbText + "page");
		msg.log("User is on " + breadCrumbText + "page");
		msg.log("User lands On Breadcrum: " + element("breadcrumb").getText() + "PageHeader: "
				+ element("customer_care_header").getText() + " " + element("breadcrumb").getText());
	}

	// Customer care is with h1 label but should be with H2 label-Issue -KSEU-1042
	public void verifyCustomerCareHasHeaderH2() {
		if (!ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("customer_care_header");
			String style = element("customer_care_header").getTagName();
			msg.log("*************** Style: " + style);
			assertEquals(style.contains("h2"), true,
					element("customer_care_header").getText() + " is not with H2 label");
			msg.log(element("customer_care_header").getText() + " is with H2 label");
		}
	}

	public void verifyPageNameHasHeaderH1() {
		isElementDisplayed("page_header");
		String style = element("page_header").getTagName();
		msg.log("*************** Style: " + style);
		assertEquals(style.contains("h1"), true, element("page_header").getText() + " is not with H1 label");
		msg.log(element("page_header").getText() + " is with H1 label");
	}

	public void verifyPageDescriptionTextOnContactUsPage(String content) {
		isElementDisplayed("contact_us_content");
		msg.log("contact_us_content is Displayed");
		String textcontent = element("contact_us_content").getText();
		assertEquals(textcontent.contains(content), true, content + " is not present in content on Contact Us Page");
		msg.log(content + " is present in content on Contact Us Page");
	}

	public void verifyNeedHelpSubHeader() {
		int sizeOfComponents = elements("need_help_content").size();
		for (int i = 0; i < sizeOfComponents; i++) {
			elements("need_help_content").get(i).isDisplayed();
			msg.log(elements("need_help_content").get(i).getText() + " is displayed");

		}
		isElementDisplayed("give_us_call");
		msg.log(element("give_us_call").getText() + " is displayed");
	}

	public void verfiyChatWithUSAndEmailUs() {
		elements("chat_and_email").get(0).isDisplayed();
		// elements("chat_and_email").get(0).click();
		msg.log("User clicks on chat with us");
		// elements("chat_and_email").get(1).isDisplayed();
		// msg.log("email " + elements("chat_and_email").get(1).getText() + "is
		// displayed");
	}

	public void device_verifyUserlandsOnContactUsPage(String pageName) {
		// Avoid "Appium error: An unknown server-side error occurred while processing
		// the command" error
		wait.hardWait(5);

		String url = getCurrentURL();
		assertEquals(url.contains(pageName), true, "[ASSERTION FAILED]: User is not on contact us page");
		msg.pass("User is on 'Contact Us' page");
		isElementDisplayed("page_header");
	}

	public void verifyContactUsPage() {
		isElementDisplayed("contact_us_content");
		msg.log(element("contact_us_content").getText());
		isElementDisplayed("need_help");
		msg.log(element("need_help").getText());
		isElementDisplayed("need_help_content");
		isElementDisplayed("chat_and_email");
		isElementDisplayed("give_us_call");
	}

	public void verifyContactUsDropdown() {
		isElementDisplayed("dropdown_contactus");
		hardWait(2);
		executeJavascript("$('.cs-nav-wrapper')[0].style='display: block';");
		msg.log("User clicks on contact us dropdown");
		int sizeoflist = elements("list_contactus_category").size();
		System.out.println(sizeoflist);
		for (int i = 0; i < sizeoflist; i++) {
			elements("list_contactus_category").get(i).isDisplayed();
			msg.log("'" + elements("list_contactus_category").get(i).getText() + "' is displayed");

			// Verify Library Folder
			elements("list_contactus_category").get(i).click();
			msg.log("'" + elements("list_contactus_category").get(i).getText() + "' is clicked");
			String style = executeJavascriptReturnsString(
					"return $('.cs-nav-wrapper ul')[" + i + "].getAttribute('style')");
			assertTrue(style.contains("display: block"),
					elements("list_contactus_category").get(i).getText() + " is not a folder");
			msg.log(elements("list_contactus_category").get(i).getText() + " is a folder");
			/*
			 * int sizeOfsubcategory = elements("subcategoryUndercontactusCategory").size();
			 * for (int j = 0; j < sizeOfsubcategory-1; j++) {
			 * System.out.println(sizeOfsubcategory); if (sizeOfsubcategory != 0) {
			 * elements("subcategoryUndercontactusCategory").get(j).isDisplayed();
			 * msg.log(elements("subcategoryUndercontactusCategory").get(j).getText()); }
			 * else msg.log("Folder is empty"); }
			 */
			executeJavascript("$('.cs-nav-wrapper span')[" + i + "].click()");
			msg.log("'" + elements("list_contactus_category").get(i).getText() + "' is clicked and closed");
		}
	}

	public void expandContactUsOptions() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("toolTipContactUs");
			clickUsingJS(element("toolTipContactUs"));
			msg.log("Contact Us Option is expanded");
		}
	}

}
