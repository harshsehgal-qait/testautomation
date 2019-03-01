
package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_721_VerifyShoppingCartIncludedElements extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> emptyWishlist;
	Map<String, String> youHaveNoSavedItems;
	String youMayAlsoLike = "you may also like";
	String youMayAlsoLikeFr = "vous aimerez aussi";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(4);
		users = testData.get("Users").get(4);
		emptyWishlist = testData.get("Page Names").get(10);
		youHaveNoSavedItems = testData.get("Page Names").get(11);

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_User_Is_Able_To_Add_Product_To_Bag_and_Goes_To_Bag_Page() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Recommendations_On_Cart_Page() {
		dsl.verifyRecommendationsOnCartPage(youMayAlsoLike, youMayAlsoLikeFr);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Maximum_4_Recommendations_Displayed_On_Cart_Page() {
		// Pending
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Recommendations_Tile_Navigates_User_To_PDP_When_Clicked() {
		// Pending
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyUserIsAbleToViewEmptyWishlist() {
		dsl.verifyUserIsAbleToViewEmptyWishlist();
		dsl.loginWithRegisteredUser(users.get("Username"), users.get("Password"));

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyUserWishlistIsEmpty() {
		dsl.verifyWishlistIsEmpty((emptyWishlist.get("Text Verification")));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifyYouHaveNoSavedItemsOnCartPageWhenUserWishlistIsEmpty() {
		dsl.verifyYouHaveNoSavedItemsOnCartPageWhenUserWishlistIsEmpty((youHaveNoSavedItems.get("Text Verification")));
	}

}
