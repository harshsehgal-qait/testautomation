package com.katespade.tests.regions.eu.ie.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_17_VerifyCheckoutAndAccountCreation extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> countryDetails;
	Map<String, String> shippingLabel;
	Map<String, String> users;
	Map<String, String> products;
	Map<String, String> countrySelectorPage;
	Map<String, String> shipping;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		countryDetails = testData.get("Country And Currency").get(0);
		shippingLabel = testData.get("Page Names").get(3);
		users = testData.get("Users").get(0);
		products = testData.get("Products").get(2);
		countrySelectorPage = testData.get("Page Names").get(4);
		shipping = testData.get("GuestShippingDetails").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Navigate_To_Registration_Page() {
		dsl.verifySignInFromFooter();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Register_New_User_Into_KateSpade() {
		dsl.registerANewUser();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Add_A_Product_To_Cart() {
		dsl.searchProduct(products.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Proceed_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Proceed_To_Billing_Page() {
		dsl.enterShipingDetails(users.get("Username"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Address"), shipping.get("Zip"), shipping.get("City"), shipping.get("Phone Number"));
	}

}
