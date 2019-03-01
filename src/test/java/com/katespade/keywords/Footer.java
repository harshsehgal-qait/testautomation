package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class Footer extends GetPage {

	WebDriver driver;
	private static String[] links = null;
	private static int linksCount = 0;

	public Footer(WebDriver driver) {
		super(driver, "Katespade/Footer");
		this.driver = driver;
	}

	public void verifyFooterSection() {
		isElementDisplayed("section_Footer");
		isElementDisplayed("textbox_emailAddress");
		isElementDisplayed("button_join");
		isElementDisplayed("icon_socialIcons");
	}

	public void verifySignInFromFooter() {
		wait.waitForLoad();
		scrollDown(element("signin_register_link"));
		clickUsingJS(element("signin_register_link"));
		msg.log("User is navigated to the Sign In / Register Page");
	}

	public void verifyAboutUsFooterLinks() {
		wait.waitForPageToLoadCompletely();

		int respCode = 200;
		HttpURLConnection huc = null;
		List<WebElement> countrylinks = webdriver.findElements(getLocator("about_us_link"));
		Iterator<WebElement> it = countrylinks.iterator();

		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			msg.log(url);
			if (url == null || url.isEmpty()) {
				msg.log("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();

				respCode = huc.getResponseCode();
				msg.log("Response Code: " + respCode);
				if (respCode >= 400) {
					msg.log(url + " is a broken link");
				} else {
					msg.log(url + " is a valid link");
				}
			} catch (MalformedURLException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		msg.log("Verified About Us Footer Links");
	}

	public void verifyCustomerCareFooterLinks() {
		wait.waitForPageToLoadCompletely();
		HttpURLConnection huc = null;
		int respCode = 200;
		List<WebElement> countrylinks = webdriver.findElements(getLocator("customer_care_link"));
		// WebElement links = webdriver.findElement(getLocator("customer_care_link"));
		Iterator<WebElement> it = countrylinks.iterator();
		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			msg.log(url);
			if (url == null || url.isEmpty()) {
				msg.log("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();
				System.out.println(respCode);
				if (respCode >= 400) {
					msg.log(url + " is a broken link");
				} else {
					msg.log(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		msg.log("Verified Customer Care Footer Links");

	}

	public void verifyCareersFooterLinks() {
		wait.waitForPageToLoadCompletely();
		HttpURLConnection huc = null;
		int respCode = 200;
		List<WebElement> countrylinks = webdriver.findElements(getLocator("careers_link"));
		// WebElement links = webdriver.findElement(getLocator("careers_link"));
		Iterator<WebElement> it = countrylinks.iterator();
		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			msg.log(url);
			if (url == null || url.isEmpty()) {
				msg.log("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();
				System.out.println(respCode);
				if (respCode >= 400) {
					msg.log(url + " is a broken link");
				} else {
					msg.log(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		msg.log("Verified Careers Footer Links");

	}

	public void verifyMyAccountFooterLinks() {
		wait.waitForPageToLoadCompletely();

		int respCode = 200;
		HttpURLConnection huc = null;
		List<WebElement> countrylinks = webdriver.findElements(getLocator("account_link"));
		Iterator<WebElement> it = countrylinks.iterator();

		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			msg.log(url);
			if (url == null || url.isEmpty()) {
				msg.log("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();

				respCode = huc.getResponseCode();
				System.out.println("Response Code is: " + respCode);
				if (respCode >= 400) {
					msg.log(url + " is a broken link");
				} else {
					msg.log(url + " is a valid link");
				}
				assertEquals(respCode, 200);

			} catch (MalformedURLException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		msg.log("Verified My Account Footer Links");
	}

	public void goToYourOrderFromFooter() {
		msg.fail("CURRENTLY 'your order' footer link IS NOT APPEARING !!!");
		msg.log("Refer: 'https://5thandpacific.atlassian.net/browse/KSEU-27' Story for more details");

		/*
		 * wait.waitForPageToLoadCompletely(); scrollDownToBottom();
		 * 
		 * isElementDisplayed("your_order"); clickUsingJS(element("your_order"));
		 * msg.log("User clicked on your order from footer");
		 * 
		 * wait.waitForPageToLoadCompletely();
		 * 
		 * String url = getCurrentURL(); assertEquals(url.contains("your-order"), true,
		 * "User is not on your order page"); msg.log("User is on your order page");
		 * 
		 * isElementDisplayed("order_history_page");
		 * msg.log(element("order_history_page").getText());
		 */
	}

	public void verifyWishlistCanBeAccessdedFromFooter() {
		scrollDownToBottom();
		isElementDisplayed("wishlist_footer");
		msg.log("Wishlist Icon is displayed");
		click(element("wishlist_footer"));
		msg.log("User clicked on Wishlist Icon on Footer");
	}

	public void verifyComapnyLinksAndThePageThatTheyNavigateTo() {
		List<WebElement> linksize = elements("companyLinks_footer");
		linksCount = linksize.size();
		msg.log("Total no of links Available: " + linksCount);
		links = new String[linksCount];
		msg.log("List of links Available: ");
		// print all the links from webpage
		for (int i = 0; i < linksCount; i++) {
			links[i] = linksize.get(i).getAttribute("href");
		}
		// navigate to each Link on the webpage
		for (int i = 0; i < linksCount; i++) {
			driver.navigate().to(links[i]);
			isElementDisplayed("page_heading");
			scrollDownToBottom();

		}

	}

	public void verifyComapnyLinkNameInFooter() {
		List<WebElement> companyname = elements("companytext_footer");
		for (WebElement name : companyname) {
			isElementsDisplayed(companyname);
			msg.log(name.getText());
		}

	}

	public void verifySocialMediaIcons() {
		scrollDownToBottom();
		HttpURLConnection huc = null;
		int respCode = 200;
		List<WebElement> socialmedialinks = webdriver.findElements(getLocator("socialMedia_icons"));
		Iterator<WebElement> it = socialmedialinks.iterator();
		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			msg.log(url);
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();
				System.out.println(respCode);

				if (respCode >= 400) {
					// assertTrue(url + " is a broken link");
					msg.log(url + " is a broken link");
				} else {
					msg.log(url + " is a valid link");
				}
				assertEquals(respCode, 200, "Broken URL");
				msg.log("All social media links are working");

			} catch (MalformedURLException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		msg.log("Verified Social Media links in Footer");
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			scrollDownToBottom();
		}
		int iconsSize = elements("socialMedia_icons").size();
		for (int i = 0; i < iconsSize; i++) {
			elements("socialMedia_icons").get(i).isDisplayed();
			elements("socialMedia_icons").get(i).click();
			msg.log("User clicks on Social media icon : " + i);
			changeWindow(i + 1);
			assertEquals(getCurrentURL().contains("katespade"), true, "Page didn't open");
			wait.waitForPageToLoadCompletely();
			changeWindow(0);
			scrollDownToBottom();
		}
	}

	public void verifyEmailSignUpElementsInFooter() {
		wait.waitForPageToLoadCompletely();
		scrollDownToBottom();
		isElementDisplayed("textbox_emailAddress");
		isElementDisplayed("button_join");
		isElementDisplayed("text_joinMailinglist");
	}

	public void clickOrderHistoryLinkOnFooter() {
		wait.waitForPageToLoadCompletely();
		scrollDownToBottom();
		isElementDisplayed("lnk_orderHistory");
		element("lnk_orderHistory").click();
		msg.log("Clicked on the order history link");
	}

	public void goToCustomerCarePageFromFooter() {
		scrollDownToBottom();
		isElementDisplayed("contact_us_link");
		clickUsingJS(element("contact_us_link"));
		msg.log("User clicks on contact us link");
	}

	public void verifySocialMediaIconsFR() {
		scrollDownToBottom();

		int respCode = 200;
		HttpURLConnection huc = null;
		List<WebElement> socialmedialinks = webdriver.findElements(getLocator("socialMedia_icons"));
		Iterator<WebElement> it = socialmedialinks.iterator();

		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			msg.log(url);

			if (!url.contains("instagram")) {
				try {
					huc = (HttpURLConnection) (new URL(url).openConnection());
					huc.setRequestMethod("HEAD");
					huc.connect();

					respCode = huc.getResponseCode();
					msg.log("Response Code: " + respCode);

					if (respCode >= 400) {
						msg.log("'" + url + "' is a BROKEN link");
					} else {
						msg.log("'" + url + "' is a VALID link");
					}
					assertEquals(respCode, 200, "[Assertion Failed]: Broken URL");
					assertTrue(url.contains("katespade"), "[Assertion Failed]: Page didn't open !!!");
				} catch (MalformedURLException e) {
					System.out.println(e);
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		msg.log("All social media links are working in Footer");
	}

	public void verifyTheValidationsInJoinMailingList() {
		verifyErrorInEnteringInvalidEmailAddress();
		verifyMessageForAlreadySubscribedEmailAddress();
	}

	public void verifyErrorInEnteringInvalidEmailAddress() {
		isElementDisplayed("textbox_emailAddress");
		clickUsingJS(element("textbox_emailAddress"));
		element("textbox_emailAddress").sendKeys("xyz.com");
		msg.log("Invalid email address is entered");
		isElementDisplayed("button_join");
		clickUsingJS(element("button_join"));
		msg.log("Join button is clicked");
		isElementDisplayed("emailSignUpError");
		msg.log("Error is displayed for the wrong email address");
	}

	public void verifyMessageForAlreadySubscribedEmailAddress() {
		isElementDisplayed("textbox_emailAddress");
		clickUsingJS(element("textbox_emailAddress"));
		element("textbox_emailAddress").sendKeys("autotest@katespade.com");
		msg.log("Valid email address is entered");
		isElementDisplayed("button_join");
		clickUsingJS(element("button_join"));
		msg.log("Join button is clicked");
		isElementDisplayed("successfullJoinMsg");
		msg.log(element("successfullJoinMsg").getText() + " is displayed for the valid email address subscription");
		refreshThePage();
		scrollDownToBottom();
		isElementDisplayed("textbox_emailAddress");
		clickUsingJS(element("textbox_emailAddress"));
		element("textbox_emailAddress").sendKeys("autotest@katespade.com");
		msg.log("Already subscribed email address is entered");
		isElementDisplayed("button_join");
		clickUsingJS(element("button_join"));
		msg.log("Join button is clicked");
		isElementDisplayed("successfullJoinMsg");
		msg.log(element("successfullJoinMsg").getText() + " is displayed for the already subscribed email ");
	}

}
