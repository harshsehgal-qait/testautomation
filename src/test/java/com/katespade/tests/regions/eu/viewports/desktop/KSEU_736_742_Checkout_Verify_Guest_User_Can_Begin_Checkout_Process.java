package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_736_742_Checkout_Verify_Guest_User_Can_Begin_Checkout_Process extends KateSpadeTest{ 
	
	Map<String, String> homePageURL;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(2);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Search_A_Product_From_Landing_Page() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Select_The_First_Product() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_That_The_Simplified_Header_Is_Present_For_Checkout_Flow(){
		dsl.verifyWishlistIconIsNotDisplayedInSimplifiedHeaderAtShippingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_That_Guest_User_Cannot_Navigate_To_Next_Step_Until_Current_Step_Is_Completed(){
		dsl.verifyContinueToPaymentButtonIsDisabledInShippingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_That_Step_Footer_Is_Displayed(){
		dsl.verifyThatStepFooterIsDisplayed();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Enter_Shipping_Details() {
		dsl.enterShipingDetails(users.get("Username"), shipping.get("First Name"), shipping.get("Last Name"), 
				shipping.get("Address"), shipping.get("Zip"), shipping.get("City"), shipping.get("Phone Number"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_user_Is_Navigated_To_Billing_Step() {
		dsl.verifyStep2BillingIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_Guest_User_Is_Navigated_Back_To_Previously_Completed_Checkout_Steps() {
		dsl.verifyUsersCanNavigateBackToPreviouslyCompletedCheckoutSteps();
	}
	
}
