package com.katespade.tests.regions.eu.roe.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_ROE_754_Checkout_Billing_Payment_Method_Klarna_Guest_User extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> shipping;
	Map<String, String> klarna;
	String guestUserEmail= "test@katespade.com";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		klarna = testData.get("Klarna").get(0);
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
//		dsl.selectFirstProduct();
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
	public void TestStep06_Enter_Shipping_Details_For_Guest_User() {
		dsl.enterShipingDetails(guestUserEmail, shipping.get("First Name"), shipping.get("Last Name"),
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
	public void TestStep09_Select_Klarna_payment_Method() {
		dsl.selectKlarnaRadioBtnForPaymentInBillingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Enter_Klarna_Payment_Details(){
		dsl.enterKlarnaPaymentDetails(klarna.get("DOB"),klarna.get("House Number"),klarna.get("House Extension"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_place_Order() {
		dsl.clickSameAsShippingAddressCheckboxOnBillingPage();
		dsl.clickContinueToPlaceOrderButton();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Verify_user_Is_Navigated_To_Order_Review_Step() {
		dsl.verifyStep3OrderReviewIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Submit_Order_() {
		dsl.clickSubmitYourOrderButtonInOrderReviewPage();
		dsl.verifyUserIsNavigatedToOrderConfirmationPage();

	}
	
}
