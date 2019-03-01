package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.PropFileHandler;

public class TopBanner extends GetPage {

	WebDriver driver;

	public TopBanner(WebDriver driver) {
		super(driver, "Katespade/TopBanner");
		this.driver = driver;
	}

	public AccountPage clickOnSignINRegister() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");
		}
		isElementDisplayed("link_signInRegister");
		element("link_signInRegister").click();
		msg.log("Clicked on 'Sign in/Register' label");

		return new AccountPage(driver);
	}

	public void navigateToNewViewAll() {
		wait.waitForPageToLoadCompletely();
	}

	public AccountPage mobile_clickOnSignINRegister() {
		wait.waitForLoad();
		isElementDisplayed("icon_hamburger");
		element("icon_hamburger").click();
		isElementDisplayed("link_signIn");
		element("link_signIn").click();
		msg.log("User clicks on sign in");
		return new AccountPage(driver);
	}

	public void utilityNavigationBar() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_signInRegister");
		msg.log("Header Icon Sign Is displayed");
		isElementDisplayed("icon_wishlist");
		msg.log("Header Icon WishList Is displayed");
		isElementDisplayed("icon_cart");
		msg.log("Header Icon Cart Is displayed");
	}

	public void mobile_utilityNavigationBar() {
		wait.waitForPageToLoadCompletely();
		openHamBurgerNavigation();
		isElementDisplayed("link_signIn");
		msg.log("sign in link is displayed");
		isElementDisplayed("icon_wishlist");
		msg.log("Header Icon WishList Is displayed");
	}

	public void navigateToWishListFromTopNavigationOnMobile() {
		wait.waitForLoad();
		scrollDown(element("icon_hamburger"));
		isElementDisplayed("icon_hamburger");
		element("icon_hamburger").click();
		isElementDisplayed("icon_wishlist");
		element("icon_wishlist").click();
		msg.log("User clicked on wish list icon from mobile top navigations");
	}

	public void verifyStoreLocatoreIcon() {
		isElementDisplayed("store_locator_icon");
		msg.log("Store Locator Icon Is displayed");
	}

	public void verifyHelpLink() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			openHamBurgerNavigation();
		}
		isElementDisplayed("need_help");
		msg.log("Need Help Icon Is displayed");
	}

	public void verifyKatespadeSpadeLogo() {
		scrollUpToPage();
		isElementDisplayed("primary_logo");
		msg.log("Primary Logo: KateSpade Is displayed");
	}

	public void verifySearchIconIsDisplayed() {
		isElementDisplayed("search_icon");
		msg.log("Search Icon Is displayed");
	}

	public void verifyTopNavigationBar() {
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			isElementDisplayed("icon_hamburger");
			executeJavascript("$('.menu-toggle').click()");
			msg.log("Click on hamburger icon");
			isElementDisplayed("link_signIn");
			isElementDisplayed("link_register");

		} else {
			isElementDisplayed("link_signInRegister");
			/*
			 * hover(element("link_signInRegister")); isElementDisplayed("link_signIn");
			 * isElementDisplayed("link_register");
			 */
			isElementDisplayed("navigation_bar_TopBanner");
			msg.log("verified to navigations");
		}
	}

	public void verifyMobileCategoryNavigations() {
		isElementDisplayed("icon_hamburger");
		executeJavascript("$('.menu-toggle').click()");
		isElementDisplayed("menuNavigationPannel");
		assertTrue(element("icon_categoryShopsExpend", "1").isDisplayed());
		msg.log(element("icon_categoryShopsExpend", "1").getText());
		assertTrue(element("icon_categoryShopsExpend", "2").isDisplayed());
		msg.log(element("icon_categoryShopsExpend", "2").getText());
		assertTrue(element("icon_categoryShopsExpend", "3").isDisplayed());
		msg.log(element("icon_categoryShopsExpend", "2").getText());
		scrollDown(element("icon_Country"));
		isElementDisplayed("icon_Country");
		msg.log("verified category navigation pannel");
	}

	public void verifySubCategoryForMobile() {
		isElementDisplayed("icon_categoryShopsExpend", "1");
		element("icon_categoryShopsExpend", "1").click();
		msg.log("user clicks on first shop");
	}

	public void openHamBurgerNavigation() {
		// Due to script in iPhone/iPad
		hardWait(3);
		isElementDisplayed("icon_hamburger");
		executeJavascript("$('.menu-toggle').click()");
		msg.log("User click on hamburger Icon");
	}

	public void navigateToMobileSignInPage() {
		wait.waitForLoad();
		isElementDisplayed("icon_hamburger");
		executeJavascript("$('.menu-toggle').click()");
		isElementDisplayed("link_signIn");
		element("link_signIn").click();
		msg.log("User navigates to my account page");
	}

	public void navigateToMobileRegisterAccountPage() {
		isElementDisplayed("icon_hamburger");
		executeJavascript("$('.menu-toggle').click()");
		isElementDisplayed("link_register");
		element("link_register").click();
		msg.log("User navigatges to my account page");
	}

	public void verifySingInRegisterLinkWhenUserLoggedIn() {
		wait.waitForLoad();
		isElementDisplayed("link_LogedInUser");
		String user = element("link_LogedInUser").getText();
		msg.log(user);
		assertEquals(user.contains("Hi,"), true, "User is not logged in");
		msg.log("User is logged in");
	}

	public void verifyTopBannerWhenUserNotLoggedIn() {
		isElementDisplayed("link_signInRegister");
		String el = executeJavascriptReturnsString("return $('.navigation-top a')[0].textContent");
		assertEquals(el.contains("Sign In / Register"), true, "User is already logged in");
		msg.log("User is not logged in");
	}

	public void verifyTopBannerWhenUserLoggedIn() {
		isElementDisplayed("link_signInRegister");
		String el = executeJavascriptReturnsString("return $('.navigation-top a')[0].textContent");
		assertEquals(el.contains("Hi,"), true, "User is not signed in");
		msg.log("User is logged in");
	}

	public void closeHamburgerMenuOptions() {
		executeJavascript("$('.menu-toggle').click()");
		msg.log("Hamburger menu closed");
	}

	public void logoutFromTheApplication() {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			scrollUpToPage();
			isElementDisplayed("lbl_username");
			isElementDisplayed("link_logout");
			element("link_logout").click();
			msg.log("User Log outs from the application");
		} else {
			isElementDisplayed("link_username");
			hover(element("link_username"));
			isElementDisplayed("link_logout");
			clickUsingJS(element("link_logout"));
			msg.log("User Log outs from the application");
		}
	}

	public void verifyAccountMenuTabOptions() {
		wait.waitForLoad();
		isElementDisplayed("link_username");
		hover(element("link_username"));
		isElementDisplayed("link_myAccount");
		isElementDisplayed("link_checkOrder");
		isElementDisplayed("link_logout");
		logMessage("Account menu links are verified");
	}

	public void navigateToMyAccountPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_username");
		hover(element("link_username"));
		isElementDisplayed("link_myAccount");
		element("link_myAccount").click();
		msg.log("User clicked on the My account link");
	}

	public void getFirstNameOfUser() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_username");
		PropFileHandler.writeProperty("user_fname", element("link_username").getText().substring(4));
	}

	public void clickOnCheckOrderLink() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_username");
		hover(element("link_username"));
		isElementDisplayed("link_checkOrder");
		clickUsingJS(element("link_checkOrder"));
		msg.log("User clicked on Check Order link");
	}

	public void closeCookieNotification() {
		wait.waitForPageToLoadCompletely();
		try {
			isElementDisplayed("icon_closeCookieNotification");
			element("icon_closeCookieNotification").click();
			msg.log("Close cookie notification icon is clicked ");
		} catch (Exception e) {
			msg.log("Exception : " + e);
		}
	}

	public int noOfItemsInCart() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}

		msg.log("Quantity in Cart: " + element("span_miniCartQuantity").getText());
		int quantity = Integer.parseInt(element("span_miniCartQuantity").getText().replaceAll("[()]+", "").trim());
		msg.log("Quantity: " + quantity);
		return quantity;
	}

	public void navigateToHomePage() {
		element("primary_logo").click();
		msg.log("Clicked on primary logo to navigate back to Home page");
	}

	public void verifyCheckboxAtLogin(String userName, String password) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			element("icon_hamburger").click();
			msg.log("Clicked on 'Hamburger' icon");
		}

		isElementDisplayed("link_signInRegister");
		element("link_signInRegister").click();
		msg.log("Clicked on 'Sign in/Register' label");

		wait.waitForLoad();
		scrollUpToPage();

		isElementDisplayed("textbox_login_emailAddress");
		sendText(element("textbox_login_emailAddress"), userName);
		msg.log("'" + userName + "' is entered");

		isElementDisplayed("textbox_login_password");
		sendText(element("textbox_login_password"), password);
		msg.log("'" + password + "' is entered");

		isElementDisplayed("checkBox_RememberMe");
		executeJavascript("document.getElementById('dwfrm_login_rememberme').click()");
		msg.log("'remember me' checkbox is selected !!!");

		// Takes time to reflect the selection of 'remember me' Check box
		hardWait(2);

		String beforeCssValueOnSelection = executeJavascriptReturnsString(
				"return window.getComputedStyle(document.getElementsByTagName("
						+ "'label')[2], ':before').getPropertyValue('background-image')");

		msg.log("ACTUAL Before CSS value of 'remember me' checkbox: '" + beforeCssValueOnSelection + "'");
		msg.log("EXPECTED Value: 'checkmark.svg'");

		Assert.assertTrue(beforeCssValueOnSelection.contains("checkmark.svg"),
				"[ASSERTION FAILED]: 'remember me' check box is NOT SELECTED!!!");
		msg.pass("'remember me' check box is selected !!!");

		executeJavascript("document.getElementById('dwfrm_login_rememberme').click()");
		msg.log("'remember me' checkbox is un-selected !!!");

		// Takes time to reflect the selection of 'remember me' Check box
		hardWait(2);

		String beforeCssValueOnUnSelection = executeJavascriptReturnsString(
				"return window.getComputedStyle(document.getElementsByTagName("
						+ "'label')[2], ':before').getPropertyValue('background-image')");
		msg.log("ACTUAL Before CSS value of 'remember me' checkbox: '" + beforeCssValueOnUnSelection + "'");
		msg.log("EXPECTED Value: 'none'");

		Assert.assertTrue(beforeCssValueOnUnSelection.contains("none"),
				"[ASSERTION FAILED]: 'remember me' check box is STILL SELECTED !!!");
		msg.pass("'remember me' check box is un-selected !!!");

		msg.pass("Remember Me check box is checkable !!!");
	}

	public void navigateToWishListPage() {
		scrollUpToPage();

		element("icon_wishlist").click();
		msg.log("WishList Link is clicked on home page!!!");
	}

}
