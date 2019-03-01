package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class MainHeader extends GetPage {
	WebDriver driver;

	public MainHeader(WebDriver driver) {
		super(driver, "Katespade/MainHeader");
		this.driver = driver;
	}

	public void verifyAccountLablesOnHover() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("signedin_name_topright");
		hover(element("signedin_name_topright"));
		executeJavascript("$('.dd')[0].style.display = 'block';");
		msg.log("User vlocks the view for dropdown under account name");
		List<WebElement> allSuggestions = elements("dropdown_acountholder");
		for (WebElement dropdown : allSuggestions) {
			msg.log(dropdown.getText());
		}
	}

	public void verifyMobileAccountLablesOnHover() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_hamburger");
		element("icon_hamburger").click();
		isElementDisplayed("signedin_name_topright");
		element("signedin_name_topright").click();
	}

	public void clickOnMyAccount(String account) {
		element(("dropdown_acountholder"), account).click();
		msg.log("User clicks on : " + account);
	}

	public void navigateToHandbagFromLandingPage(String handbag) {
		wait.waitForPageToLoadCompletely();
		element("category_tab", handbag).click();
		msg.log("User clicks on:" + handbag);
	}

	public void navigateToNewViewAllShop() {
		wait.waitForPageToLoadCompletely();
		hover(element("link_handbag"));
		msg.log("User hovers on handbags");
		wait.waitForElementToBeClickable(element("subcategory_viewall", "2"));
		element("subcategory_viewall", "2").click();
		msg.log("User clicks on view all");
	}

	public void navigateToClothingViewAllShop() {
		wait.waitForPageToLoadCompletely();
		hover(element("link_ClothingShop"));
		msg.log("User hovers on clothing");
		wait.waitForElementToBeClickable(element("subcategory_viewall", "3"));
		element("subcategory_viewall", "3").click();
		msg.log("User clicks on view all");
	}

	public void navigateToHandbagFromLandingPageOnMobile(String handbag) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_hamburger");
		element("icon_hamburger").click();
		msg.log("User clicks on hamburger icon");
		wait.waitForElementToBeVisible(element("navigationPannel"));
		clickUsingJavaScriptClickEvent("//ul[@class='menu-category level-1']/li/a[contains(@href,'handbags')]");
		msg.log("User clicks on handbag button");
	}

	public void verifyAndHoverAtHeaderLinks() {
		wait.waitForPageToLoadCompletely();
		int sizeOfComponents = elements("lst_categories").size();
		for (int i = 0; i < sizeOfComponents; i++) {
			hover(elements("lst_categories").get(i));
			msg.log("Hovered on " + elements("lst_categories").get(i));
		}
	}

	public void navigateToKateSpadeHomePage() {
		wait.waitForLoad();
		element("logo_kateSpade").click();
		wait.waitForLoad();
		hardWait(3);
		msg.log("User is navigated back to home page");
	}

	public void verifySubcategoriesOfHeaderLinks() {
		HttpURLConnection huc = null;
		int respCode = 200;
		List<WebElement> countrylinks = webdriver.findElements(getLocator("subcategories_links"));
		// WebElement links = webdriver.findElement(getLocator("subcategories_links"));
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
				System.out.println("Response code for the site is " + respCode);

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
		msg.log("Verified Subcategories of header Links");
	}

	public void verifyMegaMenuIsDisplayed() {
		String location = executeJavascriptReturnsString(
				"return $('.category-nav-wrapper.nav-aligned')[0].getAttribute('style')");
		msg.log(location + " is displayed to left");
		assertEquals(location, "left: 220.828px;", "Mega Menu is not displayed to left");
		msg.log("Mega Menu is displayed to left");
	}

	public void verifyTopLevelCategoriesColorAndMegaMenu() {
		wait.waitForPageToLoadCompletely();
		int sizeOfComponents = elements("lst_categories").size();
		for (int i = 0; i < sizeOfComponents; i++) {

			hover(elements("lst_categories").get(i));
			// Wait for hover
			hardWait(2);
			// Category color on hover
			assertEquals(elements("lst_categories").get(i).getCssValue("color"), "rgba(72, 168, 66, 1)",
					"Green color not displaying");
			msg.log("Green color is didplayed");
			// Black Underline on hover
			// String Underline =
			// elements("lst_categories").get(i).getCssValue("border-bottom");
			assertEquals(elements("lst_categories").get(i).getCssValue("border-bottom"), "4px solid rgb(0, 0, 0)",
					"Underline not displaying");
			msg.log("Underline is displayed");
			// MegaMenu Location
			String location = executeJavascriptReturnsString(
					"return $('.category-nav-wrapper.nav-aligned')[" + i + "].getAttribute('style')");
			String leftLocation = location.substring(0, 4);
			msg.log(leftLocation);
			assertEquals(leftLocation, "left", "Mega Menu is not displayed towards " + leftLocation);
			msg.log("Mega Menu is displayed towards " + leftLocation);
		}
	}

	public void verifyOnClickingCategoryUserLandsOnCorrectPage() {
		int sizeOfComponents = elements("lst_categories").size();
		for (int i = 0; i < sizeOfComponents; i++) {
			elements("lst_categories").get(i).click();
			wait.waitForPageToLoadCompletely();

			isElementsDisplayed(elements("breadcrumbSubCatgry"));
			assertEquals(elements("lst_categories").get(i).getText().trim().toLowerCase(),
					element("breadcrumbSubCatgry").getText().trim().toLowerCase(),
					"[Assert Fail]: User is not in the " + elements("lst_categories").get(i).getText() + " page");
			msg.log("User is in the " + elements("lst_categories").get(i).getText() + " page");
		}
	}

	public void verifyFullWidthExperienceOfHeader() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("header_navigation");
		String property = element("header_navigation").getCssValue("width");
		msg.log(property);
		property = property.replaceAll("\\D+", "");
		int width = Integer.parseInt(property);
		msg.log("Width is " + width);
		boolean flag;
		if (width >= 1024 && width <= 1366)
			flag = true;
		else
			flag = false;
		Assert.assertEquals(flag, true, "[Assert Failed]: Width is not in the range 1024-1366px");
		msg.log("[Assert Passed]: Width is in the range of 1024-1366 px");
	}

	public void verifyKateSpadeLogoIsPresent() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("logo_kateSpade");
		msg.log("Kate Spade logo is verified");
	}

	public void verifyStoreLocatorIcon() {
		wait.waitForLoad();
		isElementDisplayed("icon_storeLocator");
		element("icon_storeLocator").click();
		msg.log("user click on store locator link");
	}

	public void verifyKateSpadeLogoIsLinkedToHomapage(String homepageURL) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("logo_kateSpade");
		element("logo_kateSpade").click();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(2);
			wait.waitForLoad();
		} else {
			wait.waitForPageToLoadCompletely();
		}
		Assert.assertEquals(getCurrentURL(), homepageURL,
				"[ASSERTION FAILED]: Current URL and homepage URL does not matched !!!");
		msg.pass("Current URL and homepage URL matched , user is in homepage !!!");
	}

	public void verifyCountryAndItsCurrencySymbol(String countryNameAndCurrency) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_country");

		Assert.assertEquals(element("link_country").getText(), countryNameAndCurrency.toUpperCase(),
				"[Assert Fail]: Country And Currency does not matching !!!");
		msg.pass("Country And Currency are matching !!!");
	}

	public void verifySignInAndRegisterLink() {
		wait.waitForPageToLoadCompletely();
		hover(element("link_signInRegister"));
		isElementDisplayed("link_signIn");
		isElementDisplayed("link_register");
	}

	public void verifyLogOutLink() {
		wait.waitForPageToLoadCompletely();
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr-fr")) {
			isElementDisplayed("txt_usernameFr");
			hover(element("txt_usernameFr"));
		} else {
			isElementDisplayed("text_username");
			hover(element("text_username"));
		}
		isElementDisplayed("link_logout");
	}

	public void verifyWishlistLinkAndIcon() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_wishlist");
		isElementDisplayed("icon_Wishlist");
	}

	public void verifySearchTextAndIconFunctionality() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_search");
		executeJavascript("$('div.search-toggle a').click()");
		// element("icon_search").click();
		isElementDisplayed("text_search");
		isElementDisplayed("btn_search");
		msg.log("Search verified");
	}

	public void verifyMaxWidthOfHeader() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("header_navigation");
		String property = element("header_navigation").getCssValue("max-width");
		property = property.replaceAll("\\D+", "");
		int maxWidth = Integer.parseInt(property);
		msg.log("Max Width is " + maxWidth);
		Assert.assertEquals(maxWidth, 1600, "[Assert Failed]: Max width is not 1600px");
		msg.log("[Assert Passed]: Max Width is 1600 px");
	}

	public void verifyWidthOfStickyHeader() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("stickyTopBanner");
		int width = Integer.parseInt(element("stickyTopBanner").getCssValue("width").replaceAll("\\D+", ""));
		msg.log("Width is " + width);
		Assert.assertEquals(width, 1600, "[Assert Failed]: Width is not 1600px");
		msg.log("[Assert Passed]: Width is 1600 px");
	}

	public void verifyStickyKSLogoAndItsLinkingToHomepage(String homepageURL) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("stickyLogo_kateSpade");
		msg.log("Sticky Kate Spade logo is verified");
		clickUsingJS(element("stickyLogo_kateSpade"));
		wait.waitForLoad();
		msg.log(getCurrentURL());
		Assert.assertEquals(getCurrentURL().equals(homepageURL), true,
				"[Assert Fail]: Current URL and homepage URL does not matched");
		msg.log("[Assert Pass]: Current URL and homepage URL matched , user is in homepage");
	}

	public void verifySignInAndRegisterIcon() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("logo_accountLink");
		hover(element("logo_accountLink"));
		msg.log("Hovered");
		wait.waitForElementToBeVisible(element("stickyLink_signIn"));
		msg.log("hoverd on logo_accountLink");
		isElementDisplayed("stickyLink_signIn");
		isElementDisplayed("stickyLink_register");
		msg.log("Sign In and Register link is verified when user is not logged in.");
	}

	public void verifyAccountLinksWhenUserIsLoggedIn() {
		wait.waitForPageToLoadCompletely();
		hover(element("logo_accountLink"));

		isElementDisplayed("link_myAccount");
		element("link_myAccount").click();
		msg.log("My Account link is clicked");
	}

	public void verifyBagIconFunctionalityInStickyHeader() {
		wait.waitForPageToLoadCompletely();
		scrollDownToBottom();
		isElementDisplayed("icon_bag");
		isElementDisplayed("stickyBagQty");
		int bagQty = Integer.parseInt(element("stickyBagQty").getText().replaceAll("\\D+", ""));
		msg.log("Sticky Bag Quantity is " + bagQty);
		hover(element("stickyBagQty"));
	}

	public void verifyBagIconForMobile() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_bag");
		String bagCount = executeJavascriptReturnsString("return $('.bag-icon-mobile')[0].textContent");
		msg.log(bagCount.replaceAll("\n", " ").trim());
		assertEquals(bagCount.replaceAll("\n", " ").contains("0"), true, "bag count not matched");
	}

	public void verifyPromotionalMessagingBarAppearsOnHeader() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("msg_headerPromotionalMessagingBar");
		msg.log("Header Promotional Mesage is :" + element("msg_headerPromotionalMessagingBar").getText());
	}

	public void verifyMegaMenuElements() {
		for (int i = 0; i < 5; i++) {
			msg.log(elements("lst_categories").get(i).getAttribute("class"));

			hover(elements("lst_categories").get(i));
			msg.log("Hovered");
			hardWait(2);
			try {
				int j = i;
				j = j + 1;
				msg.log("element displayed -" + j);
				if (driver
						.findElement(By.xpath(
								"//ul[@class='menu-category level-1']/li[" + j + "]//a[contains(text(),'view all')]"))
						.isDisplayed()) {
					driver.findElement(By.xpath(
							"//ul[@class='menu-category level-1']/li[" + j + "]//a[contains(text(),'view all')]"))
							.click();
					/*
					 * .findElement(By.xpath( "//ul[@class='menu-category level-1']/li[" + j +
					 * "]//a[contains(@href,'view-all')]")) .isDisplayed()) { driver.findElement(By
					 * .xpath("//ul[@class='menu-category level-1']/li[" + j +
					 * "]//a[contains(@href,'view-all')]")) .click();
					 */
					wait.waitForPageToLoadCompletely();
					isElementsDisplayed(elements("main_breadcrum"));
					msg.log("main_breadcrum is displayed");
					// isElementsDisplayed(elements("subcategories_title"));
					// msg.log("subcategories_title is displayed");

				} else {
					continue;
				}
			} catch (ElementNotVisibleException e) {
				msg.log("Element not found");
			}
		}
	}

	public void verifySimplifiedHeaderKSLogoAndItsLinkingToHomepage(String homepageURL) {
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(4);
		}

		isElementDisplayed("logo_kateSpade");
		msg.pass("Kate Spade Logo is present in the simplified header of checkout flow");

		element("logo_kateSpade").click();
		msg.log("Clicked on 'Kate Spade' logo");

		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(4);
			wait.waitForLoad();
		}

		if (getCurrentURL().contains("staging")) {
			msg.log("Actual URL: " + getCurrentURL());
			msg.log("Expected URL (contains): staging");

			Assert.assertTrue(getCurrentURL().contains("staging"),
					"[ASSERTION FAILED]: Current URL and homepage URL DOES NOT MATCH !!!");
		} else {
			Assert.assertEquals(getCurrentURL(), homepageURL,
					"[ASSERTION FAILED]: Current URL and homepage URL DOES NOT MATCH !!!");
		}
		msg.pass("Verified user is on HOME page !!!");
	}

	public void verifyWishlistIconIsNotDisplayedInSimplifiedHeaderAtShippingPage() {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementNotDisplayed("icon_hamburger");
			msg.log("Verified: Verified Simplified Header for iPhone/iPad");
		} else {
			isElementNotDisplayed("icon_Wishlist");
			msg.log("Verified: Wishlish icon is not displayed for simplified header");
		}
	}

	public void verifyHandbagSubcategyColorChangesToGreenOnHovering() {
		wait.waitForLoad();
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr-fr")) {
			hover(element("link_handbag"));
			msg.log("Hovered on link_handbag");
			wait.waitForElementToBeClickable(element("subcategory_viewallFr", "2"));
			hover(element("subcategory_viewallFr", "2"));
			msg.log("Hovered on sub category");
			hardWait(2);
			assertEquals(element("subcategory_viewallFr", "2").getCssValue("color"), "rgba(72, 168, 66, 1)",
					"Font does not turns green on hovering on Subcategory");
			msg.log("Font turns green on hovering on Subcategory");
		} else {
			hover(element("link_handbag"));
			msg.log("Hovered on link_handbag");
			wait.waitForElementToBeClickable(element("subcategory_viewall", "2"));
			hover(element("subcategory_viewall", "2"));
			msg.log("Hovered on sub category");
			hardWait(2);
			assertEquals(element("subcategory_viewall", "2").getCssValue("color"), "rgba(72, 168, 66, 1)",
					"Font does not turns green on hovering on Subcategory");
			msg.log("Font turns green on hovering on Subcategory");
		}
	}

	public void clickCategoryOnHeader(String categoryIndex) {
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			wait.waitForLoad();
			isElementDisplayed("icon_hamburger");
			executeJavascript("$('.menu-toggle').click()");
			msg.log("User clicked on 'Hamburger' Icon");
			selectProductCategoryForMobileUser(categoryIndex);
		} else {
			wait.waitForPageToLoadCompletely();
			isElementDisplayed("categories", categoryIndex);
			element("categories", categoryIndex).click();
			msg.log("User clicked on the category option at index : " + categoryIndex);
		}
	}

	public void clickOnTheSubCategoryLinkOnTheHeader(String categoryIndex, String subCategory, String subCategoryFr) {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("categories", categoryIndex);
		hover(element("categories", categoryIndex));

		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("en-fr")) {
			isElementDisplayed("lnk_subCategory", subCategoryFr);
			element("lnk_subCategory", subCategoryFr).click();
			msg.log("Sub category : " + subCategoryFr + " is clicked");
		} else {
			isElementDisplayed("lnk_subCategory", subCategory);
			element("lnk_subCategory", subCategory).click();
			msg.log("Sub category : " + subCategory + " is clicked");
		}
	}

	public void clickOnTheSubCategoryLinkWithIndexOnTheHeader(String categoryIndex, String subCategoryIndex) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("categories", categoryIndex);
		hover(element("categories", categoryIndex));
		isElementDisplayed("link_subcategoryWithIndex", subCategoryIndex);
		clickUsingJS(element("link_subcategoryWithIndex", subCategoryIndex));
		msg.log("Sub category : " + element("link_subcategoryWithIndex", subCategoryIndex).getText() + " is clicked");
	}

	public void navigateToAnyShopFromHeader() {
		wait.waitForPageToLoadCompletely();
		hover(element("link_ClothingShop"));
		msg.log("User hovers on clothing");
		wait.waitForElementToBeClickable(element("subcategory_dresses", "3"));
		element("subcategory_dresses", "3").click();
		msg.log("User clicks on dresses");
	}

	public void clickOnBagIcon() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_bag");
		clickUsingJS(element("icon_bag"));
		msg.log("Clicked on 'Bag' icon");
	}

	public void selectProductCategoryForMobileUser(String catIndex) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("prdctCatgry", catIndex);
		String prodCatgry = element("prdctCatgry", catIndex).getText();
		clickUsingJS(element("prdctCatgry", catIndex));
		msg.log("User is navigated to the " + prodCatgry + " category page");
	}

	public void VerifyMegaMenuElementsForFr() {
		for (int i = 0; i < 5; i++) {
			msg.log(elements("lst_categories").get(i).getAttribute("class"));

			hover(elements("lst_categories").get(i));
			msg.log("Hovered");
			hardWait(2);
			try {
				int j = i;
				j = j + 1;
				msg.log("element displayed -" + j);
				if (driver.findElement(By.xpath(
						"//ul[@class='menu-category level-1']/li[" + j + "]//a[contains(text(),'Tout Afficher')]"))
						.isDisplayed()) {
					driver.findElement(By.xpath(
							"//ul[@class='menu-category level-1']/li[" + j + "]//a[contains(text(),'Tout Afficher')]"))
							.click();
					/*
					 * .findElement(By.xpath( "//ul[@class='menu-category level-1']/li[" + j +
					 * "]//a[contains(@href,'view-all')]")) .isDisplayed()) { driver.findElement(By
					 * .xpath("//ul[@class='menu-category level-1']/li[" + j +
					 * "]//a[contains(@href,'view-all')]")) .click();
					 */
					wait.waitForPageToLoadCompletely();
					isElementsDisplayed(elements("main_breadcrum"));
					msg.log("main_breadcrum is displayed");
					// isElementsDisplayed(elements("subcategories_title"));
					// msg.log("subcategories_title is displayed");

				} else {
					continue;
				}
			} catch (ElementNotVisibleException e) {
				msg.log("Element not found");
			}

		}

	}

}