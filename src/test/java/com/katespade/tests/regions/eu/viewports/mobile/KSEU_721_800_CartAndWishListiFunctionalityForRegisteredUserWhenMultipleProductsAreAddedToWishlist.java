package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

/*KSEU-800- Wishlist - Detail - Included Elements
 * 
 * */
public class KSEU_721_800_CartAndWishListiFunctionalityForRegisteredUserWhenMultipleProductsAreAddedToWishlist
		extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> product;
	Map<String, String> pageName;
	Map<String, String> product1;
	Map<String, String> product2;
	Map<String, String> product3;
	Map<String, String> product4;
	Map<String, String> product5;
	Map<String, String> product6;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		product = testData.get("Products").get(0);
		product1 = testData.get("Products").get(1);
		product2 = testData.get("Products").get(2);
		product3 = testData.get("Products").get(3);
		product4 = testData.get("Products").get(4);
		product5 = testData.get("Products").get(8);
		product6 = testData.get("Products").get(9);
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

	// Add 1st Product to Wishlist
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SearchAProductFromLandingPage() {
		dsl.searchProduct(product.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_SelectTheFirstProduct() {
//		dsl.selectFirstProduct();
		dsl.addProductToWishlist();
		dsl.verifyUserLandsOnWishlistPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyProductIsAddedToWishlistRecommendationOnCartPage() {
		dsl.verifyProductIsAddedToWishlistRecommendationOnCartPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyCustomerServiceOnCartPage() {
		dsl.verifyCustomerServiceOnCartPage();
	}

	// Add 2nd Product to Wishlist
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_SearchAProductFromLandingPage() {
		dsl.searchProduct(product1.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_SelectTheFirstProduct() {
//		dsl.selectFirstProduct();
		dsl.addProductToWishlist();
		dsl.verifyUserLandsOnWishlistPage();
	}

	// Add 3rd Product to Wishlist
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_SearchAProductFromLandingPage() {
		dsl.searchProduct(product2.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
		dsl.addProductToWishlist();
		dsl.verifyUserLandsOnWishlistPage();
	}

	// Add 4th Product to Wishlist
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_SearchAProductFromLandingPage() {
		dsl.searchProduct(product3.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_SelectTheFirstProduct() {
//		dsl.selectFirstProduct();
		dsl.addProductToWishlist();
		dsl.verifyUserLandsOnWishlistPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_VerifyTheMaxLimitOfWishlistRecommendedItemsOnCartPage() {
		dsl.verifyTheMaxLimitOfWishlistRecommendedItemsOnCartPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_VerifyFirstProductAddedToWishlistIsNotPresentInWishlistRecommendedItemsOnCartPage() {
		dsl.verifyFirstProductAddedToWishlistIsNotPresentInWishlistRecommendedItemsOnCartPage();
	}

	// Add 5th Product to Wishlist
	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_SearchAProductFromLandingPage() {
		dsl.searchProduct(product4.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep16_SelectTheFirstProduct() {
//		dsl.selectFirstProduct();
		dsl.addProductToWishlist();
		dsl.verifyUserLandsOnWishlistPage();
	}

	// Add 6th Product to Wishlist
	@Test(groups = { "desktop", "mobile" })
	public void TestStep17_SearchAProductFromLandingPage() {
		dsl.searchProduct(product5.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep18_SelectTheFirstProduct() {
//		dsl.selectFirstProduct();
		dsl.addProductToWishlist();
		dsl.verifyUserLandsOnWishlistPage();
	}

//	// Add 6th Product to Wishlist
//		@Test(groups = { "desktop", "mobile" })
//		public void TestStep19_SearchAProductFromLandingPage() {
//			dsl.searchProduct(product6.get("Style Number"));
//		}
//
//		@Test(groups = { "desktop", "mobile" })
//		public void TestStep20_SelectTheFirstProduct() {
//			dsl.selectFirstProduct();
//			dsl.addProductToWishlist();
//			dsl.verifyUserLandsOnWishlistPage();
//		}

	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep21_VerifyProductThumbnailOnWishlistPage() {
		dsl.verifyProductThumbnailOnWishlistPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep22_VerifyProductNameOnWishlistPage() {
		dsl.verifyProductNameForMultipleItemsOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep23_VerifyStyleNumberOnWishlistPage() {
		dsl.verifyStyleNumberOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep24_VerifySizeAttributeOnWishlistPage() {
		dsl.verifySizeAttributeOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep25_VerifyColorttributeOnWishlistPage() {
		dsl.verifyColorAttributeOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep26_VerifyPriceOnWishlistPage() {
		dsl.verifyPriceOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep27_VerifyInventoryOnWishlistPage() {
		dsl.verifyInventoryOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep28_VerifyInventoryOnWishlistPage() {
		dsl.verifyDateOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep29_VerifyQuantityIsSetToOneByDefaultOnWishlistPage() {
		dsl.verifyQuantityIsSetToOneByDefaultOnWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep30_VerifyAddedItemsAreRemovedFromWishlistPage() {
		dsl.verifyAddedItemsAreRemovedFromWishlistPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep31_LogOutFromApplication() {
		dsl.mobile_logoutFromApplication();
	}

}
