package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_767_Order_Receipt_Verify_Guest_User_Can_Create_Account_From_Order_Receipt extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> creditCards;
	long Random = System.currentTimeMillis();
	String email = "AT" + Random + "@katespade.com";
	String pwd = "auto1234";

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
	public void TestStep06_Enter_Shipping_Details() {
		dsl.enterShipingDetails(users.get("Username"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Address"), shipping.get("Zip"), shipping.get("City"), shipping.get("Phone Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_user_Is_Navigated_To_Billing_Step() {
		dsl.verifyStep2BillingIsActive();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Make_Payment_And_Place_Order_() {
		dsl.makePaymentFromMasterCard(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));
		dsl.clickSubmitYourOrderButtonInOrderReviewPage();
		dsl.verifyUserIsNavigatedToOrderConfirmationPage();
		dsl.verifyOrderNumberIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_User_Is_Unable_To_Create_New_Account_If_Previously_Registered_Email_Is_Used() {
		dsl.verifyEnteredEmailIsAutopopulatedInEmailFieldWhileCreatingAcc(users.get("Username"));
		dsl.createAnAccountFromOrderReceipt(users.get("Username"), pwd);
		dsl.verifyErrorMessageIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_Enter_Shipping_Details() {
		dsl.enterShipingDetails(users.get("Username"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Address"), shipping.get("Zip"), shipping.get("City"), shipping.get("Phone Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_Verify_user_Is_Navigated_To_Billing_Step() {
		dsl.verifyStep2BillingIsActive();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep16_Make_Payment_And_Place_Order_() {
		dsl.makePaymentFromMasterCard(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));
		dsl.clickSubmitYourOrderButtonInOrderReviewPage();
		dsl.verifyUserIsNavigatedToOrderConfirmationPage();
		dsl.verifyOrderNumberIsDisplayed();
		dsl.verifyContactUsContentAssetInOrderConfirmationPage();
		dsl.verifyUserIsAbleToClickPlusBtnToExpandAndMinusBtnToCloseCreateAnAccountDrawer();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep17_Verify_user_Is_Able_To_Create_Account_From_Order_Receipt() {
		dsl.createAnAccountFromOrderReceipt(email, pwd);
		dsl.verifyUserIsInAccountPage();
	}
}
