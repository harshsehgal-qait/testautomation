package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_740_Checkout_Registered_User_Order_Summary extends KateSpadeTest{
	
	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> promoCode;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		promoCode = testData.get("Promo Codes").get(0);
		users = testData.get("Users").get(2);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyRegisteredUserIsAbleToLogin()  {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Apply_Promo_Code_And_Checkout_From_Shoping_Cart_Page() {
		dsl.applyPromoCodeInShopingCartPage(promoCode.get("Promo Code"));
		dsl.checkoutFromCartPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_User_Is_In_Shipng_Page() {
		dsl.verifyUserIsOnShippingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Order_Summary_Box_In_Shipng_Page() {
		dsl.verifyOrderSummaryForDiscountedProductInShippingPg();
		dsl.verifyTheStickyNatureOfOrderSummaryBoxInProductShipngPage();
	}
}
