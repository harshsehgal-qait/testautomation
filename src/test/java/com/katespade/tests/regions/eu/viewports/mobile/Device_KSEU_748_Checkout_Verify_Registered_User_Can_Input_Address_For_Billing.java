package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_748_Checkout_Verify_Registered_User_Can_Input_Address_For_Billing extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> homepageURL;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> shipping2;
	Map<String, String> creditCards;
	String cardName="visa";
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		homepageURL = testData.get("BASE URL").get(2);
		products = testData.get("Products").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		shipping2 = testData.get("GuestShippingDetails").get(2);
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
//		dsl.selectFirstProduct();
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
	public void TestStep07_Click_Use_This_Address_For_Billing_Checkbox_In_Shipping_Page() throws InterruptedException {
		dsl.clickUseThisAddressForBillingCheckboxInShippingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Enter_Shipping_Details() {
		dsl.clickContinuePaymentButton();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_user_Is_Navigated_To_Billing_Step() {
		dsl.verifyStep2BillingIsActive();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Select_First_Saved_Card_In_The_Account() {
		dsl.selectFirstSavedCardInTheAccount(cardName);
		dsl.verifyErrorMessageIsNotDisplayed();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Guest_User_Is_Navigated_Back_To_Previously_Completed_Checkout_Steps() {
		dsl.verifyUsersCanNavigateBackToPreviouslyCompletedCheckoutSteps();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Uncheck_Use_This_Address_For_Billing_Checkbox_And_Click_Continue_To_Billing_Btn() {
		dsl.uncheckUseThisAddressForBillingAndClickContinueToBillingBtn();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Verify_Address_Fields_Are_Pre_Populated() {
		dsl.verifyFieldsOfBillingPageAreNotEmpty();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_Billing_Address_Details() {
		dsl.clickAddToAddressBookCheckbox();
		dsl.enterBillingAddressDetails(shipping2.get("First Name"), shipping2.get("Last Name"), shipping2.get("Address"),
				shipping2.get("Zip"), shipping2.get("Phone Number"), shipping.get("City"));
		dsl.makePaymentFromMasterCard(creditCards.get("Number"), creditCards.get("Month/Year"), creditCards.get("Type"),
				creditCards.get("CVV"));                           // Remove it after bug resolve
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_Verify_Added_Address_Is_InAddress_Book() {
		dsl.verifySimplifiedHeaderKSLogoAndItsLinkingToHomepage(homepageURL.get("URL"));
		
	}

}
