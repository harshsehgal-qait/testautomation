package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_735_Checkout_VerifyUserCanLoginFromShippingPageAndCheckTheFlowTillBillingPage extends KateSpadeTest{

	Map<String, String> homePageURL;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shippingDetails;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		users = testData.get("Users").get(0);
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
	public void TestStep06_Click_On_The_Registered_User_Sign_In_Now_Btn_And_Verify_Fields_To_Login_Are_Present(){
		dsl.clickOnRegisterUserSignInNowButtonAndVerifyFieldsForLogin();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Clicking_Forgot_Password_Link_Opens_Forgot_Password_Modal(){
		dsl.verifyForgotPasswordModalOpensOnClickingForgotPasswordLink();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Registered_User_Is_Able_To_Login_During_Checkout_Flow(){
		dsl.loginToAccountDuringCheckoutFlow(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyShippingPageAfterSucessfulLogin(){
		dsl.verifyShippingPageAfterSucessfulLogin();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Registered_User_Is_Able_To_Click_And_Save_First_Address_Dropdown(){
		dsl.verifyThatRegisteredUserIsAbleToClickAndSelectFirstSavedAddress();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Shipping_Fields_Are_PrePopulated(){
		dsl.verifyThatShippingFieldsArePrePopulated(shippingDetails.get("First Name"), 
				shippingDetails.get("Last Name"), shippingDetails.get("Phone Number")); 
	}
	
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Click_On_The_KS_Logo(){
		dsl.navigateBackToKateSpadeHomePage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Logout_From_The_Application(){
		dsl.mobile_logoutFromApplication();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_VerifyRegisteredUserIsAbleToLogin()  {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep16_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep17_Add_Product_To_Bag() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep18_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep19_Verify_Login_Fields_Are_Not_Visible_In_Shiping_Page_When_User_Is_Already_Login() {
		dsl.verifyLoginFieldsAreNotVisibleInShippingPage();
	}
	
}
