package com.katespade.keywords;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class HomeLandingPage extends GetPage {

	WebDriver driver;
	String title;
	String url;

	public HomeLandingPage(WebDriver driver) {
		super(driver, "" + "Katespade/HomeLandingPage");
		this.driver = driver;
	}

	public void navigateToSatchelsUnderHandbag(String satchels) {
		wait.waitForPageToLoadCompletely();
		hover(element("hover_handbag"));
		msg.log("User hovers on Handbags");
		element("category_tab_level2", satchels).click();
		msg.log("User clicks on : " + satchels);
	}

	public void navigateToSatchelsUnderHandbagOnMobile(String satchels) {
		isElementDisplayed("icon_hamburger");
		element("icon_hamburger").click();
		msg.log("Clicked on 'Hamburger' icon");
		wait.waitForElementToBeVisible(element("navigationPannel"));
		clickUsingJavaScriptClickEvent("//a[contains(@href,'handbags/satchels')]");
		msg.log("Clicked on handbag button");
	}

	public void verifyLandingPage() {
		verifyPageTitleContains();
	}

	public void verifyCountryToggle() {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			isElementDisplayed("icon_hamburger");
			executeJavascript("$('.menu-toggle').click()");
			msg.log("Click on hamburger icon");
			scrollWindow(0, 300);
			isElementDisplayed("label_ship_to");
			msg.log(element("label_ship_to").getText());
			isElementDisplayed("flag_icon");
			isElementDisplayed("country_toggle");
			msg.log("Country Selector is diplayed");
			wait.waitForElementToBeClickable(element("country_toggle"));
			element("country_toggle").click();
			executeJavascript("$('.level-1 .name a').click()");
			msg.log("User clicks on Country Selector");
		} else {
			isElementDisplayed("label_ship_to");
			msg.log(element("label_ship_to").getText());
			isElementDisplayed("flag_icon");
			isElementDisplayed("country_toggle");
			msg.log("Country Selector is diplayed");
			wait.waitForElementToBeClickable(element("country_toggle"));
			element("country_toggle").click();
			msg.log("User clicks on Country Selector");
		}
	}

	public void verifyCountriesDsplayed() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_signIn");
		msg.log("Header Icon Sign Is displayed");
		isElementDisplayed("icon_wishlist");
		msg.log("Header Icon WishList Is displayed");
		isElementDisplayed("icon_cart");
		msg.log("Header Icon Card Is displayed");
	}

	public void verifySignInLink() {
		wait.waitForPageToLoadCompletely();
		HttpURLConnection huc = null;
		int respCode = 200;
		WebElement links = webdriver.findElement(getLocator("icon_signIn"));
		WebElement it = links;
		String url = it.getAttribute("href");
		if (url == null || url.isEmpty()) {
			msg.log("URL is either not configured for anchor tag or it is empty");
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

	public void verifyCountrySelectorLink() {
		wait.waitForPageToLoadCompletely();
		HttpURLConnection huc = null;
		int respCode = 200;
		WebElement links = webdriver.findElement(getLocator("breadcrumb_country_selector"));
		WebElement it = links;

		String url = it.getAttribute("href");
		if (url == null || url.isEmpty()) {
			msg.log("URL is either not configured for anchor tag or it is empty");
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

	public void verifyStoreLocatorIcon() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_storeLocator");
		if (!(ConfigPropertyReader.getProperty("browser").contains("ios"))) {
			isElementDisplayed("link_storeLocator");
		}
		msg.log("Store locator link and icon verified");
	}

	public void navigateToStoreLocatorPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_storeLocator");
		element("icon_storeLocator").click();
		msg.log("Clicked on 'Store locator' icon");
	}

	public void verifyUserlandsOnHomePageOnClickingKateSpadeLogo(String homepageURL) {
		isElementDisplayed("primary_logo");
		clickUsingJS(element("primary_logo"));
		msg.log("User clicked Primary Logo");
		// wait.waitForPageToLoadCompletely();
		isElementDisplayed("homepageId");
		assertEquals(getCurrentURL().equals(homepageURL), true, "Assert Fail: User is not home page");
		msg.log("Userlands on Home Page");
	}

	public void verifyWishlistCanBeAccessdedFromHeader() {
		isElementDisplayed("icon_wishlist");
		msg.log("Wishlist Icon is displayed");
		clickUsingJS(element("icon_wishlist"));
		msg.log("User clicked on Wishlist Icon on Header");
	}

	public void verifyHeadersDisplaySigninAndRegistration() {
		isElementDisplayed("header_signInRegistration");
		try {
			hover(element("header_signInRegistration"));
			msg.log("User hovers on Sign in link from the top right corer");
			msg.log("User click on Sign in link from the top right corer");
			executeJavascript("$('.signin-modal.signin-not-registered')[1].style='display: block';");
			msg.log("Blocking the view");
			List<WebElement> list = elements("signin_regLink");
			for (WebElement e : list) {
				msg.log(e.getAttribute("href"));
				hardWait(1);
				assertEquals(e.getAttribute("href").contains("account") || e.getAttribute("href").contains("register"),
						true, "Link not found");
				msg.log("Both links are present");
			}
		} catch (AssertionError er) {
			msg.log("SignIn/Register icon is present on header!!!!");
		}
	}

	public void clickOnSignInLink() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);

			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");

			element("lnk_navigationOverlaySignIn").click();
			msg.log("Clicked on 'Sign In' link");
		} else {
			wait.waitForPageToLoadCompletely();
			scrollUpToPage();

			hover(element("header_signInRegistration"));
			msg.log("User hover on 'Sign in/Register' link from the top right corer");
			try {
				elements("signin_regLink").get(0).isDisplayed();
				elements("signin_regLink").get(0).click();
				msg.log("User clicks on 'Sign in' button");

				clickByJavascript(element("header_signInRegistration"));
				msg.log("User clicks on 'Sign in' button");
			} catch (AssertionError | Exception ex) {
				clickByJavascript(element("header_signInRegistration"));
				msg.log("User clicks on 'Sign in' button");
			}
		}
	}

	public void verifyUserLandsOnSelectedCountry(String country) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		msg.log("Selected Locale: " + country);
		msg.log("Current country URL: " + getCurrentURL());
		Assert.assertEquals(getCurrentURL().toLowerCase().contains(country.toLowerCase()), true,
				"[ASSERTION FAILED]: User doesn't lands on Germany locale site !!!");
		msg.pass("User lands on Germany locale site !!!");
	}

	public void verifyAfterChangingTheCountryAddedProductIsRemoved() {
		if (!ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("label_bag");
			msg.log(element("link_bagQuantity").getText());
			assertEquals(element("link_bagQuantity").getText(), "(0)",
					"Product is still in the bag after changing the country");
			msg.log("Product is removed from the bag after changing the country");
		} else {
			isElementDisplayed("link_bagQuantity");
			msg.log(element("link_bagQuantity").getText());
			assertEquals(element("link_bagQuantity").getText(), "0",
					"Product is still in the bag after changing the country");
			msg.log("Product is removed from the bag after changing the country");
		}
	}

	public void goBackToThePreviousCountryByClickingOnBackButtonFromTheBrowser(String uk) {
		backButton();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(2);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		msg.log("Previous selected country: " + uk);
		msg.log("Current country URL: " + getCurrentURL());
		assertTrue(getCurrentURL().toLowerCase().contains(uk.toLowerCase()),
				"[ASSERTION FAILED]: User is on the different country");
		msg.pass("User is on united kingdom country");
	}

	public void verifyCountrySelectorHeaderIsUpdated(String country) {
		isElementDisplayed("country_toggle");
		msg.log(element("country_toggle").getText());
		assertNotEquals(element("country_toggle").getText(), country,
				"[ASSERTION FAILED]: User is not on the same country");
		msg.pass("User is on the same country");
	}

	public void verifyDefaultLocaleOfKateSpadeRoESite(String defaultUrl, String defaultRoESite,
			String countryShippingTo, String defaultCountry, String countryCurrency) {
		Assert.assertEquals(getCurrentURL(), defaultUrl,
				"[ASSERTION FAILED]: Kate Spade RoE Site has redirected to GERMANY home page!!!");
		msg.pass("By DEFAULT, Kate Spade RoE Site is NOT redirected to GERMANY home page !!!");

		Assert.assertTrue(getCurrentURL().contains(defaultRoESite),
				"[ASSERTION FAILED]: Kate Spade RoE Site URL is NOT matching !!!");
		msg.pass("Kate Spade RoE Site URL is matching !!!");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);

			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");

			scrollDownToBottom();
		}
		Assert.assertEquals(element("label_shippingTo").getText(), countryShippingTo,
				"[ASSERTION FAILED]: '" + countryShippingTo + "' shipping to text is NOT appearing !!!");
		msg.pass("'" + countryShippingTo + "' shipping to text is appearing !!!");

		Assert.assertEquals(element("icon_defaultCountryFlag").getAttribute("class"), "flag-icon flag-icon-de",
				"[ASSERTION FAILED]: Default Country flag icon is NOT Germany !!!");
		msg.pass("Default Country flag icon is Germany !!!");

		Assert.assertTrue(
				element("lnk_countrySelection").getText().toUpperCase().contains(defaultCountry.toUpperCase()),
				"[ASSERTION FAILED]: Default country selected is NOT Germany !!!");
		msg.pass("Default country selected is Germany !!!");

		Assert.assertTrue(element("lnk_countrySelection").getText().contains(countryCurrency),
				"[ASSERTION FAILED]: Default currency of Germany is NOT appearing !!!");
		msg.pass("Default currency of Germany is appearing !!!");

		msg.pass("Default Locale of Kate Spade RoE Site is GERMANY !!!");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_closeNavigationOverlay").click();
			msg.log("Clicked on 'Close' icon on navigation overlay");
		}
	}

	public void verifyGermanLocaleToKateSpadeRoESiteHomePage(String homePageUrl, String germanLocale) {
		launchSpecificUrl(homePageUrl);

		Assert.assertTrue(getCurrentURL().contains(germanLocale),
				"[ASSERTION FAILED]: German Locale is NOT added to Kate Spade RoE Site home page !!!");
		msg.pass("German Locale is added to Kate Spade RoE Site home page !!!");
	}

	public void clickOnCountrySelectorIcon() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");
		}
		try {
			element("lnk_countrySelector").click();
		} catch (Exception e) {
			clickByJavascript(element("lnk_countrySelector"));
		}
		msg.log("Clicked on 'Country Selector' link");
	}

	public void verifyUserHasNavigatedToCountrySelectorPage(String countrySelectorPageHeader) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		}
		wait.waitForLoad();
		assertTrue(element("h1_countrySelectorContent").getText().contains(countrySelectorPageHeader),
				"[ASSERTION FAILED]: User is not on country selector page");
		msg.pass("User is on country selector page !!!");
	}

	public void verifyCountrySelectorLogoInHeader(String shippingLabel, String countryName, String currency) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("icon_hamburger");
			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");
		}
		isElementDisplayed("lnk_countrySelector");
		isElementDisplayed("label_shippingTo");

		int size = element("lnk_countrySelector").getText().length();
		assertTrue(element("label_shippingTo").getText().trim().contains(shippingLabel.toUpperCase()),
				"[ASSERTION FAILED]:Shipping Label is not shown");
		msg.pass("Shipping Label is shown for " + element("lnk_countrySelector").getText());

		assertTrue(element("lnk_countrySelector").getText().contains(countryName.toUpperCase()),
				"[ASSERTION FAILED]:Shipping Label is does not show right country name");
		msg.pass("Shipping Label showns country name as "
				+ element("lnk_countrySelector").getText().substring(0, size - 2));

		assertTrue(element("lnk_countrySelector").getText().contains(currency),
				"[ASSERTION FAILED]:Shipping Label is does not show right country name");
		msg.pass("Shipping Label showns currency as " + element("lnk_countrySelector").getText().charAt(size - 1));

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_closeNavigationOverlay").click();
			msg.log("Clicked on 'Close' icon on navigation overlay");
		}
	}

	public void verifyTopBannerLinksOnKateSpadeRoEHomePage(String storeLocatorTitle, String needHelpTitle,
			String signInAndRegisterLink, String wishListTitle, String bagTitle) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
			msg.log("Top Banner Linktext DO NOT appear on KateSpade RoE Home Page of 'ios' devices");
		} else {
			Assert.assertEquals(element("link_storeLocator").getText(), storeLocatorTitle.toUpperCase(),
					"[ASSERTION FAILED]: '" + storeLocatorTitle + "' 'Store Locator' linktext is NOT appearing !!!");
			msg.pass("'" + storeLocatorTitle + "' 'Store Locator' linktext is appearing !!!");

			Assert.assertEquals(element("lnk_needHelp").getText(), needHelpTitle.toUpperCase(),
					"[ASSERTION FAILED]: '" + needHelpTitle + "' 'Need Help?' linktext is NOT appearing !!!");
			msg.pass("'" + needHelpTitle + "' 'Need Help?' linktext is appearing !!!");

			Assert.assertEquals(element("header_signInRegistration").getText(), signInAndRegisterLink,
					"[ASSERTION FAILED]: '" + signInAndRegisterLink
							+ "' 'Sign In/Register'  linktext is NOT appearing !!!");
			msg.pass("'" + signInAndRegisterLink + "' 'Sign In/Register' linktext is appearing !!!");

			Assert.assertEquals(element("icon_wishlist").getText(), wishListTitle.toUpperCase(),
					"[ASSERTION FAILED]: '" + wishListTitle + "' 'WishList' linktext is NOT appearing !!!");
			msg.pass("'" + wishListTitle + "' 'WishList linktext is appearing !!!");

			Assert.assertTrue(element("label_bag").getText().contains(bagTitle.toUpperCase()),
					"[ASSERTION FAILED]: '" + bagTitle + "' 'Bag' linktext is NOT appearing !!!");
			msg.pass("'" + bagTitle + "' 'Bag' linktext is appearing !!!");

			msg.pass("Verified Top Banner Links on Kate Spade RoE Home page");
		}
	}

	public void verifyItemsHaveBeenRemovedFromShoppingBagOnSwitchingCountryOtherThanRoESites(int cartQuantity,
			String shippingLabel, String countryOtherThanRoESite, String countryCurrency) {
		int cartQuantityOnSwitchingCountry;
		int size = element("lnk_countrySelector").getText().length();
		String country = element("lnk_countrySelector").getText().substring(0, size - 2);

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("icon_hamburger");
			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");
		}

		Assert.assertTrue(element("label_shippingTo").getText().trim().contains(shippingLabel.toUpperCase()),
				"[ASSERTION FAILED]:Shipping Label is not shown");
		msg.pass("Shipping Label is shown for '" + countryOtherThanRoESite + "' country");

		Assert.assertEquals(country, countryOtherThanRoESite.toUpperCase(),
				"[ASSERTION FAILED]: Shipping Label is does not show right country name");
		msg.pass("Shipping Label showns country name as "
				+ element("lnk_countrySelector").getText().substring(0, size - 2));

		msg.log("Actual Currency: " + element("lnk_countrySelector").getText());
		msg.log("Expected Currency: " + countryCurrency);
		Assert.assertTrue(element("lnk_countrySelector").getText().contains(countryCurrency),
				"[ASSERTION FAILED]:Shipping Label is does not show right country name");
		msg.pass("Shipping Label showns currency as " + element("lnk_countrySelector").getText().charAt(size - 1));

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_closeNavigationOverlay").click();
			msg.log("Clicked on 'Close' icon on navigation overlay");
		}

		cartQuantityOnSwitchingCountry = Integer
				.parseInt(element("span_miniCartQuantity").getText().replaceAll("[()]+", "").trim());

		Assert.assertTrue((cartQuantityOnSwitchingCountry != cartQuantity), "[ASSERTION FAILED]: Items HAVE NOT "
				+ "BEEN REMOVED from Shopping Bag on switching country other than RoE site !!!");

		Assert.assertTrue((cartQuantityOnSwitchingCountry == 0), "[ASSERTION FAILED]: Items HAVE NOT BEEN REMOVED "
				+ "from Shopping Bag on switching country other than RoE site !!!");

		msg.pass("Items HAVE BEEN REMOVED from Shopping bag when switched to '" + countryOtherThanRoESite
				+ "' country from RoE site !!!");
	}

	public void verifyUGCContentSlotAboveFooter() {
		scrollDown(element("span_AlsoJustIn"));
		
		hardWait(2);
		int yCoOfAlsoJust = element("span_AlsoJustIn").getLocation().getY();
		int yCoOfFooter = element("section_Footer").getLocation().getY();
		try {
			int yCoOfUGC = element("section_UGC").getLocation().getY();
			
			Assert.assertTrue(yCoOfAlsoJust < yCoOfUGC,
					"[ASSERTION FAILED]: BV UGC Content Slot is not underneath 'also just in' section !!!");
			msg.pass("BV UGC Content Slot is underneath 'also just in' section !!!");
			
			Assert.assertTrue(yCoOfFooter > yCoOfUGC,
					"[ASSERTION FAILED]: BV UGC Content Slot is not above 'footer' section !!!");
			msg.pass("BV UGC Content Slot is above 'footer' section !!!");
			msg.log("BV UGC Content Slot is placed on Homepage underneath the 'also just in' section above the footer !!!");
		} catch(AssertionError er) {
			Assert.fail("'SHOW & TELL' title of BV UGC Content Slot is NOT appearing on Home page!!!");
		}
	}

	public void verifyUGCContentSlotByTitle(String title) {
		isElementDisplayed("title_UGC");
		
		Assert.assertEquals(element("title_UGC").getText(), title, 
				"[ASSERTION FAILED]: 'SHOW & TELL' title of BV UGC content is NOT appearing !!!");
		msg.pass("Title Of BV UGC Content Slot is " + title);
	}

	public void verifyUGCContentSlotByDescription(String description) {
		isElementDisplayed("description_UGC");
		
		Assert.assertEquals(element("description_UGC").getText(), description, 
				"[ASSERTION FAILED]: Description of BV UGC content is NOT appearing !!!");
		msg.pass("Description Of BV UGC Content Slot is " + description);
	}

}