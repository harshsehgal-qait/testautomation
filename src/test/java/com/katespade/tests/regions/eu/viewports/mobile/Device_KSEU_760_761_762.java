package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_760_761_762 extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> creditCards;
	Map<String, String> shippingDetails;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(2);
		creditCards = testData.get("CardDetails").get(0);
		shippingDetails = testData.get("GuestShippingDetails").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
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
	public void TestStep08_Billing_Page_Details() {
		dsl.makePaymentFromMasterCard(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));                           
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_user_Is_Navigated_To_Order_Review_Page() {
		dsl.verifyStep3OrderReviewIsActive();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Customer_Is_Unable_To_Edit_Items_Within_Review_Page() {
		dsl.verifyCustomerIsUnableToEditItemsWithinCartInOrderReviewPage();                           
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_User_Is_Navigated_to_Shipng_Page_After_Clicking_Shpng_Address_Edit_Btn_In_Order_Review_Page() {
		dsl.clickShipngAddressEditBtnInOrderReviewPage();
		dsl.verifyUserIsOnShippingPage();
		dsl.verifyThatShippingFieldsArePrePopulated(shippingDetails.get("First Name"), 
				shippingDetails.get("Last Name"), shippingDetails.get("Phone Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Click_Continue_Payment_Button() {
		dsl.clickContinuePaymentButton();
	}
		
	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Fill_Details_And_Navigate_To_Order_Review_Page() {
		dsl.verifyStep2BillingIsActive();
		dsl.enterPaymentInformation(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));
		dsl.clickContinueToPlaceOrderButton();
		dsl.verifyStep3OrderReviewIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_Verify_User_Is_Navigated_to_Shipng_Page_After_Clicking_Shipng_Method_Edit_Btn_In_Order_Review_Page() {
		dsl.clickShipngMethodEditBtnInOrderReviewPage();
		dsl.verifyUserIsOnShippingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_Fill_Details_And_Navigate_To_Order_Review_Page() {
		dsl.clickContinuePaymentButton();
		dsl.verifyStep2BillingIsActive();
		dsl.enterPaymentInformation(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));
		dsl.clickContinueToPlaceOrderButton();
		dsl.verifyStep3OrderReviewIsActive();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep16_Verify_User_Is_Navigated_to_Billing_Page_After_Clicking_Billing_Address_Edit_Btn_In_Order_Review_Page() {
		dsl.clickBillingAddressEditBtnInOrderReviewPage();
		dsl.verifyStep2BillingIsActive();
		dsl.verifyFieldsOfBillingPageAreNotEmpty(); 
	}
}
