package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_747_Checkout_Verify_Guest_User_Can_Input_Address_For_Billing extends KateSpadeTest {

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
		users = testData.get("Users").get(0);
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
		dsl.verifyUseThisAddressForBillingIsByDefaultNotSelected();
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
	public void TestStep09_Verify_Fields_From_Shipping_Page_Are_Pre_Populated_Into_Billing_Page() {
		dsl.verifyFieldsFromShippingPageArePopulatedIntoRelevantFieldsOfBillingPage(shipping.get("First Name"),
				shipping.get("Last Name"), shipping.get("Zip"), shipping.get("Phone Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Remove_All_the_Address_Information_Copied_From_Shipping_Page() {
		dsl.clickSameAsShippingAddressCheckboxOnBillingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Continue_To_Place_Order_Is_Disabled_Before_Entering_Required_Fields() {
		dsl.verifyContinueToPlaceOrderButtonIsDisabledInBillingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_MakePaymentUsingCreditCard() {
		dsl.makePaymentFromMasterCard(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Verify_Guest_User_Is_Navigated_Back_To_Previously_Completed_Checkout_Steps() {
		dsl.verifyUsersCanNavigateBackToPreviouslyCompletedCheckoutSteps();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_Uncheck_Use_This_Address_For_Billing_Checkbox_And_Click_Continue_To_Billing_Btn() {
		dsl.uncheckUseThisAddressForBillingAndClickContinueToBillingBtn();
	}
	//KSEU-947
	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_Verify_Fields_Of_Billing_Address_Are_Empty() {
		dsl.verifyFieldsOfBillingPageAreEmpty();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep16_Billing_Address_Details() {
		dsl.enterBillingAddressDetails(shipping.get("First Name"), shipping.get("Last Name"), shipping.get("Address"),
				shipping.get("Zip"), shipping.get("Phone Number"), shipping.get("City"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep17_Verify_Fields_From_Shipping_Page_Are_Pre_Populated_Into_Billing_Page() {
		dsl.verifyFieldsFromShippingPageArePopulatedIntoRelevantFieldsOfBillingPage(shipping.get("First Name"),
				shipping.get("Last Name"), shipping.get("Zip"), shipping.get("Phone Number"));
	}
}
