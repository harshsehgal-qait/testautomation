package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_752_Checkout_Verify_Guest_User_Have_Ability_To_Select_Credit_Card_Payment_Method extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> creditCards;
	String[] availableCards = {"Visa" , "American Express", "MasterCard"};

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
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
	public void TestStep06_Enter_Shipping_Details() {
		dsl.enterShipingDetails(users.get("Username"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Address"), shipping.get("Zip"), shipping.get("City"), shipping.get("Phone Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_user_Is_Navigated_To_Billing_Step() {
		dsl.verifyStep2BillingIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_By_Default_Payment_Method_Is_Credit_Card_On_Billing_Page() {
		dsl.verifyCreditCardIsTheByDefaultPaymentMethodOnTheBillingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Payment_Method_Fields_On_Billing_Page() {
		dsl.verifyPaymentMethodFieldsOnBillingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_Error_Message_Is_Displayed_For_Payment_Method_Fields_When_Left_Blank() {
		dsl.verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Tool_Tip_For_CVN_Is_Displayed() {
		// Pending issue KSEU- 973
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Available_Payment_Method() {
		dsl.verifyCardTypeAvailableForPayment(availableCards);  
	}
	
}
