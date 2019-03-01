package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_763_Verify_Customer_Is_Able_To_Review_Order_Totals extends KateSpadeTest{

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
		creditCards = testData.get("CardDetails").get(2);
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
	public void TestStep08_Billing_Page_Details() {
		dsl.makePaymentFromMasterCard(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));  
		
		dsl.markSameAsBillingAndClickSubmit();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_user_Is_Navigated_To_Order_Review_Page() {
		dsl.verifyStep3OrderReviewIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Elements_In_Order_Summary_At_Order_Review_Page() {
		dsl.verifyElementsInOrderSummaryAtOrderReviewPage();
	} 
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Pricing_Structure_Of_Products_In_Order_Review_Page() {
		dsl.verifyThePricingStructureOfProductInOrderReviewPage();
	} 

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Verify_Sticky_Nature_Of_The_Order_Review_Box() {
		dsl.verifyTheStickyNatureOfOrderSummaryBoxInOrderReviewPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Verify_Terms_Of_Use_And_Privacy_Policy_Links_In_Order_Review_Page() {
		dsl.verifyTermsOfUseAndPrivacyPolicyLinksInOrderReviewPage();
	}
	
	
}
