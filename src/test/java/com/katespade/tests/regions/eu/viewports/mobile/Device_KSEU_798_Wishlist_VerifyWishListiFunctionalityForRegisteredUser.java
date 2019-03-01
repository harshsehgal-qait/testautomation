/*
 - KSEU-798 :Wishlist - Login - Included Elements
 - 
  * */
package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

/*KSEU-798- Wishlist - Login - Included Elements
 * 
 * */
public class Device_KSEU_798_Wishlist_VerifyWishListiFunctionalityForRegisteredUser extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> products;
	Map<String, String> pageName;
	Map<String, String> homepageURL;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		homepageURL = testData.get("BASE URL").get(2);
		products = testData.get("Products").get(0);
		users = testData.get("Users").get(4);
		pageName = testData.get("Page Names").get(0);

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyRegisteredUserIsAbleToLoginIntoKateSpade() throws InterruptedException {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyWishlistCanBeAccessdedMyAccount() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.verifyWishlistCanBeAccessdedMyAccount();
		dsl.verifyUserLandsOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserlandsOnHomePageOnClickingKateSpadeLogo() {
		dsl.verifyUserlandsOnHomePageOnClickingKateSpadeLogo(homepageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyWishlistCanBeAccessdedFromHeader() {
		dsl.openHamburgerMenu();
		dsl.verifyWishlistCanBeAccessdedFromHeader();
		dsl.verifyUserLandsOnWishlistPage();
		dsl.verfifyUserIsNotOnWishlistSignInPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyWishlistCanBeAccessdedFromFooter() {
		dsl.verifyUserlandsOnHomePageOnClickingKateSpadeLogo(homepageURL.get("URL"));
		dsl.verifyWishlistCanBeAccessdedFromFooter();
		dsl.verifyUserLandsOnWishlistPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_SelectTheFirstProductandSelectSwatch() {
		dsl.selectFirstProduct();
//		dsl.selectAndClickOnSwatch();
		String SkuNum = dsl.verifyWishlistCanBeAccessdedFromPDP();
		dsl.verifyUserLandsOnWishlistPage();
		dsl.verifyProductAddedInWishList(SkuNum);

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyProductDetailsOnWishlistPage() {
		dsl.verifyProductDetailsOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifyImageQuantityAndAddtoBagOnWishlistPage() {
		dsl.verifyImageQuantityAndAddtoBagOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_VerifyEditAndRemoveOnWishlistPage() {
		dsl.verifyRemoveBtnOnWishlistPage();
		dsl.removeTheProductAddedFromWishlist();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_VerifySideNavOnWishlistPage() {
		dsl.verifySideNavOnWishlistPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	 public void TestStep13_LogOutFromApplication() {
	  dsl.logOutFromTheApplication();
	 }

}
