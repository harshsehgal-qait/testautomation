package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_749_Checkout_Verify_Billing_Address_Required_Input_Form_Field_Are_Validated extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> creditCards;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(2);
		creditCards = testData.get("CardDetails").get(0);
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

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Click_Use_This_Address_For_Billing_Checkbox_In_Shipping_Page() throws InterruptedException {
		dsl.clickUseThisAddressForBillingCheckboxInShippingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Enter_Shipping_Details() {
		dsl.enterShipingDetails(users.get("Username"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Address"), shipping.get("Zip"), shipping.get("City"), shipping.get("Phone Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_user_Is_Navigated_To_Billing_Step() {
		dsl.verifyStep2BillingIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_Error_Message_Is_Dispalyed_For_required_Fields() {
		dsl.verifyErrorMessageDisplayedForRequiredFields();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Continue_To_Place_Order_Is_Disabled_Before_Entering_Required_Fields() {
		dsl.verifyContinueToPlaceOrderButtonIsDisabledInBillingPage();
	}
	
	
	
}
