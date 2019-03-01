package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_756_758_Checkout_Registered_User__Select_Credit_Card
		extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> creditCards;
	String cardName = "Master";
	String cardOwnerName = "paymentCardTest";

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
	public void TestStep02_LoginIntoKateSpade() throws InterruptedException {
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
	public void TestStep06_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_ContinueToPaymentPage() {
		dsl.clickContinuePaymentButton();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_user_Is_Navigated_To_Billing_Step() {
		dsl.verifyStep2BillingIsActive();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_By_Default_Payment_Method_Is_Credit_Card_On_Billing_Page() {
		dsl.verifyCreditCardIsTheByDefaultPaymentMethodOnTheBillingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Payment_Method_Fields_On_Billing_Page() {
		dsl.verifyPaymentMethodFieldsOnBillingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Error_Message_Is_Displayed_For_Payment_Method_Fields_When_Left_Blank() {
		dsl.mobile_verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Verify_Previously_Saved_Cards_Are_Displayed_On_Billing_Page() {
		dsl.verifyPreviouslySavedCardsAreDisplayedOnBillingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Verify_Fields_Of_Payment_Method_Are_AutoPopulated() {
		dsl.selectFirstSavedCardInTheAccount(cardName);
		dsl.verifyFieldsOfPaymentMethodAreAutoPopulated();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_Enter_Card_Details_And_Submit_Your_Order() {
		dsl.selectFirstSavedCardInTheAccount("Select from saved cards");
		dsl.makePaymentFromMasterCard(creditCards.get("Number"), creditCards.get("Month/Year"), cardOwnerName,
				creditCards.get("CVV"));
		dsl.verifyCustomerServiceContactNoIsDisplayedOnOrderReviewPage();
		dsl.clickSubmitYourOrderButtonInOrderReviewPage();
		dsl.verifyUserIsNavigatedToOrderConfirmationPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_Navigate_To_My_Account_Page() {
		dsl.navigateToMyAccountPage();
		dsl.clickOnCreditCardLinkOnMyAccountPage();
		dsl.verifyNewlyAddedCardIsDisplayedOnAccountPage(cardOwnerName);
		dsl.deleteSavedCardByItsNameOnAccountPage(cardOwnerName);
	}

	


}
