package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_680_PDPVerifyUserIsAbleToAddItemToWishlist extends KateSpadeTest {

	Map<String, String> homepageURL;
	Map<String, String> products;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(0);
		users = testData.get("Users").get(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SelectTheFirstProduct() {
		// dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserIsNeededToChooseAttributesBeforeAddingToWishlist() {
		dsl.verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyUserIsAbleToAddProductToWishlistWhenLoggedIn() {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_User_Can_Add_Product_To_Wishlist_Directly_When_Already_Logged_In() {
		dsl.verifyUserIsAbleToAddProductToWishlistDirectlyWhenAlreadyLoggedIn(products.get("Style Number"));
	}

}
