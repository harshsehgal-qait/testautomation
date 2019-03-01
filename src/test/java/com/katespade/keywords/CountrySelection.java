package com.katespade.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class CountrySelection extends GetPage {

	WebDriver driver;

	public CountrySelection(WebDriver driver) {
		super(driver, "Katespade/CountrySelection");
		this.driver = driver;
	}

	public void clickOnCountryToggle() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		isElementDisplayed("breadcrumb_country_selector");
		msg.log("Country Selector Bread Crumb: '" + element("breadcrumb_country_selector").getText() + "'");
		isElementDisplayed("country_and_lang_selector");
		msg.log("Country & Language Selector: '" + element("country_and_lang_selector").getText() + "'");
		msg.log("User lands on country selector page");
	}

	public void verifyRegionHeading(String region) {
		isElementDisplayed("region_heading");
		assertEquals(region, element("region_heading").getText(), "Region is different");
		msg.log(region + " is displayed");
	}

	public void selectACountryFromCountrySelectorPage(String country) {
		if (getCurrentURL().contains("ksEuRoe") || getCurrentURL().contains("en-de")
				|| getCurrentURL().contains("en_GB")) {
			scrollDown(element("lnk_country", country));
			isElementDisplayed("lnk_country", country);
			element("lnk_country", country).click();
			msg.log("User clicked on '" + country + "' country");
		} else {
			isElementDisplayed("lnk_country", country);
			scrollDown(element("lnk_country", country));
			clickByJavascript(element("lnk_country", country));
			msg.log("Clicked on '" + country + "' country");
		}
	}

	public void verifyCountrySwitchDialogIsDisplayed() {
		wait.waitForElementToBeVisible(element("countrySwitchDialog"));
		isElementDisplayed("countrySwitchDialog");
		isElementDisplayed("countrySwitchMessage");
		msg.log(element("countrySwitchMessage").getText());
		isElementDisplayed("countrySwitch_continue_button");
		isElementDisplayed("countrySwitch_cancel_button");
	}

	public void clickOnContinueButtonFromCountrySwitchDialog() {
		element("countrySwitch_continue_button").click();
		msg.log("Clicked on 'Continue' button");
	}

	public void clickOnCancelButtonFromCountrySwitchDialog() {
		isElementDisplayed("countrySwitch_cancel_button");
		element("countrySwitch_cancel_button").click();
		msg.log("Clicked on 'Cancel' button");
	}

	public void verifyOnCancellingTheCountrySwitchTheMyBagRetainsTheProductThatIsAddedToTheBag() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);

			isElementDisplayed("link_bagQuantity");
			assertEquals(element("link_bagQuantity").getText(), "1",
					"[ASSERTION FAILED]: The added product didn't retain");
			msg.pass("Added product is retained");
		} else {
			isElementDisplayed("label_bag");
			isElementDisplayed("link_bagQuantity");
			assertEquals(element("link_bagQuantity").getText(), "(1)", "The added product didn't retain");
			msg.pass("Added product is retained");
		}
	}

	public void verifyCountryLinks() {
		wait.waitForPageToLoadCompletely();
		HttpURLConnection huc = null;
		int respCode = 200;
		List<WebElement> countrylinks = webdriver.findElements(getLocator("lnk_country"));

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
	}

	public void verifyCountryIsAddedInCountrySelectionPage(String country) {
		isElementDisplayed("lnk_country", country);
		msg.pass("'" + country + "' country link is added in Country Selection Page !!!");

		if (country.equals("France")) {
			assertTrue(element("img_countryFlag", country).getAttribute("src").contains(country.toLowerCase()),
					"[ASSERTION FAILED]: '" + country
							+ "' flag is NOT along country link in Country Selection Page !!!");
		} else {
			assertEquals(element("img_countryFlag", country).getAttribute("atl").toLowerCase(), country.toLowerCase(),
					"[ASSERTION FAILED]: '" + country
							+ "' flag is NOT along country link in Country Selection Page !!!");
		}
		msg.pass("'" + country + "' country flag is shown along with link in Country Selection Page !!!");
	}

	public void clickOnCountryLink(String country) {
		element("lnk_country", country).click();
		msg.log("'" + country + "' link is clicked !!!");
	}

	public void verifyUserHasNavigatedToHomePageOfSelectedCountry(String homePageURL) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}

		assertEquals(getCurrentURL(), homePageURL, "[ASSERTION FAILED]: User is not on selected country home page !!!");
		msg.pass("User is on selected country home page !!!");
	}

	public void verifyLocaleInKateSpadeRoESiteHomePage(String countryLocale) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
			wait.waitForPageToLoadCompletely();
		}
		assertTrue(getCurrentURL().contains(countryLocale),
				"[ASSERTION FAILED]: User is not having '" + countryLocale + "' appended to the URL");
		msg.pass("User is redirected to the homepage with the '" + countryLocale
				+ "' locale selected and appended to the end of the URL !!!");
	}

	public void verifyOrderOfCountriesInListOnCountrySelectorPage(String listOfCountries) {
		int i = 0;
		boolean flag = true;
		String[] countries = listOfCountries.split(",");

		List<String> actualListOfCountries = new ArrayList<String>();
		for (WebElement e : elements("list_countries")) {
			actualListOfCountries.add(e.getText().trim());
		}

		List<String> expectedListOfCountries = Arrays.asList(countries);
		for (String s : expectedListOfCountries) {
			if (s.equals(actualListOfCountries.get(i))) {
				i++;
			} else {
				flag = false;
				break;
			}
		}
		assertTrue(flag, "[ASSERTION FAILED]: List of countries is NOT as expected !!!");
		msg.pass("List of countries on Country Selector page is as expected !!!");
	}

	public void clickOnCountryLinkOnCountrySelectionPage(String homePageURL) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		}
		assertEquals(getCurrentURL(), homePageURL, "[ASSERTION FAILED]: User is not on selected country home page");
		msg.pass("User is on selected country home page !!!");
	}

	public void clickOnCountry(String country) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		}
		clickByJavascript(element("lnk_country", country));
		msg.log("Clicked on '" + country + "' country !!!");
	}

	public void verifyMessageOfItemRemoveFromShoppingBag(String message) {
		Assert.assertEquals(element("message_div").getText(), message,
				"[ASSERTION FAILED]: Required message is not displayed");
		msg.pass("Message appears informing the customer their cart may be removed !!!");
	}

	public void clickOnNavigationOverlay() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("icon_closeNavigationOverlay");
			element("icon_closeNavigationOverlay").click();
			msg.log("Clicked on 'Navigation Overlay' icon");
		}
	}

	public void verifyCountryLinkForFranceHasBeenModifiedInCountrySelectorPage(String countryLinkFranceFrench,
			String countryLinkFranceEnglish) {
		for (WebElement elem : elements("list_countries")) {
			if (elem.getText().trim().contains(countryLinkFranceFrench)) {
				Assert.fail("[ASSERTION FAILED]: '" + countryLinkFranceFrench + "' country link is appearing !!!");
			} else if (elem.getText().trim().contains(countryLinkFranceEnglish)) {
				Assert.fail("[ASSERTION FAILED]: '" + countryLinkFranceEnglish + "' country link is appearing !!!");
			} else {
				continue;
			}
		}
		msg.pass("'" + countryLinkFranceFrench + "' country link is NOT appearing !!!");
		msg.pass("'" + countryLinkFranceEnglish + "' country link is NOT appearing !!!");
	}

	public void verifyHeaderTextInCountrySelectorPage(String shippingToLabel, String countryFlag, String country,
			String countryCurrency) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);

			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");

			scrollDownToBottom();
		}
		Assert.assertEquals(element("label_shippingTo").getText(), shippingToLabel,
				"[ASSERTION FAILED]: '" + shippingToLabel + "' shipping to text is NOT appearing !!!");
		msg.pass("'" + shippingToLabel + "' shipping to text is appearing !!!");

		String[] flagIcon = countryFlag.split("_");
		Assert.assertTrue(element("icon_countryFlag").getAttribute("class").contains(flagIcon[1].toLowerCase()),
				"[ASSERTION FAILED]: Country flag icon is NOT " + country + "' !!!");
		msg.pass("Country flag icon is " + country + "' !!!");

		Assert.assertTrue(element("lnk_countrySelection").getText().toUpperCase().contains(country.toUpperCase()),
				"[ASSERTION FAILED]: Country selected is NOT " + country + "' !!!");
		msg.pass("Country selected is " + country + "' !!!");

		Assert.assertTrue(element("lnk_countrySelection").getText().contains(countryCurrency),
				"[ASSERTION FAILED]: Currency of '" + country + "'  is NOT appearing !!!");
		msg.pass("Currency of Germany is appearing !!!");
		msg.pass("Verified Header text of '" + country + "' in Country Selector page !!!");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_closeNavigationOverlay").click();
			msg.log("Clicked on 'Close' icon on navigation overlay");
		}
	}

	public void verifyCountrySwitchDialogBoxDoesNotAppearWhenCartIsEmpty() {
		Assert.assertTrue(isElementNotDisplayed("dialog_countrySwitchBox"),
				"[ASSERTION FAILED]: Country switch Dialog box is APPEARING even when CART is EMPTY !!!");
		msg.pass("Verified Country Switch Dialog box is NOT APPEARING when CART is EMPTY !!!");

		Assert.assertTrue(getCurrentURL().contains("Home-Show"),
				"[ASSERTION FAILED]: User is still on COUNTRY SELECTOR page !!!");
		msg.pass("Verified user has NAVIGATED to KateSpade HOME page !!!");
	}

	public void clickOnCountrySelectorIcon() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");
		}
		element("lnk_countrySelector").click();
		msg.log("Clicked on 'Country Selector' link");
	}

	public void verifyCountrySelectorLogoInHeader(String shippingLabel, String countryName, String currency) {
		int size = element("lnk_countrySelector").getText().length();
		String[] country = element("lnk_countrySelector").getText().split(" ");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("icon_hamburger");
			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");
		}

		Assert.assertTrue(element("label_shippingTo").getText().trim().contains(shippingLabel.toUpperCase()),
				"[ASSERTION FAILED]:Shipping Label is not shown");
		msg.pass("Shipping Label is shown for '" + countryName + "' country");

		Assert.assertEquals(country[0], countryName.toUpperCase(),
				"[ASSERTION FAILED]: Shipping Label is does not show right country name");
		msg.pass("Shipping Label showns country name as "
				+ element("lnk_countrySelector").getText().substring(0, size - 2));

		Assert.assertTrue(element("lnk_countrySelector").getText().contains(currency),
				"[ASSERTION FAILED]:Shipping Label is does not show right country name");
		msg.pass("Shipping Label showns currency as " + element("lnk_countrySelector").getText().charAt(size - 1));

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_closeNavigationOverlay").click();
			msg.log("Clicked on 'Close' icon on navigation overlay");
		}
	}

	public void verifyCartWillNotBeDeletedOnSwitchingWithinCountriesInRoESite(int cartQuantity,
			String changeShippingDestinationMsg, String shippingLabel, String countryCurrency) {
		int cartQuantityOnSwitchingCountry = 0;
		String[] countriesRoESite = { "Ireland", "Italy", "Netherlands", "Spain", "France" };

		for (int index = 0; index < countriesRoESite.length; index++) {
			clickOnCountrySelectorIcon();

			clickOnCountry(countriesRoESite[index]);

			verifyMessageOfItemRemoveFromShoppingBag(changeShippingDestinationMsg);

			clickOnContinueButtonFromCountrySwitchDialog();

			if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
				wait.waitForLoad();
				hardWait(5);
			} else {
				wait.waitForPageToLoadCompletely();
			}

			verifyCountrySelectorLogoInHeader(shippingLabel, countriesRoESite[index], countryCurrency);

			cartQuantityOnSwitchingCountry = Integer
					.parseInt(element("span_miniCartQuantity").getText().replaceAll("[()]+", "").trim());
			Assert.assertEquals(cartQuantityOnSwitchingCountry, cartQuantity,
					"[ASSERTION FAILED]: CART has BEEN EMPTIED on switching country within RoE site !!!");
			msg.pass("CART has NOT BEEN EMPTIED when switched to '" + countriesRoESite[index] + "' country");
		}
	}

}
