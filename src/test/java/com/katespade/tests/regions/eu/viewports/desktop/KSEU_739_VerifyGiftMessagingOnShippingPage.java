package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class KSEU_739_VerifyGiftMessagingOnShippingPage extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> giftMsg;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(2);
		giftMsg = testData.get("Page Names").get(33);
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
	public void TestStep04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep06_EnterShippingDetailsAndDoNotContinue() {
//		dsl.enterShippingDetailsAndDoNotContinue(users.get("Username"), shipping.get("First Name"),
//				shipping.get("Last Name"), shipping.get("Address"), shipping.get("Zip"), shipping.get("City"),
//				shipping.get("Phone Number"));
//	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyGiftMessagingOnShippingPage() {
		dsl.clickOnGiftMessageAndGiftBox();
		dsl.verifyGiftBoxModal();
		dsl.verifyAllCheckboxesInGiftBoxModel();
		dsl.verifyFreeGiftMessageFunctionaity(giftMsg.get("Text Verification"));
	}
}
