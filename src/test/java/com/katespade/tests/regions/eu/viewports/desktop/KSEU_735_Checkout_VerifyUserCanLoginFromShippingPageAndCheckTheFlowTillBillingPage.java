package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_735_Checkout_VerifyUserCanLoginFromShippingPageAndCheckTheFlowTillBillingPage extends KateSpadeTest{

	Map<String, String> homePageURL;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shippingDetails;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		users = testData.get("Users").get(0);
		shippingDetails = testData.get("GuestShippingDetails").get(3);
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
	public void Test_Step06_Click_On_The_Registered_User_Sign_In_Now_Btn_And_Verify_Fields_To_Login_Are_Present(){
		dsl.clickOnRegisterUserSignInNowButtonAndVerifyFieldsForLogin();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Clicking_Forgot_Password_Link_Opens_Forgot_Password_Modal(){
		dsl.verifyForgotPasswordModalOpensOnClickingForgotPasswordLink();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Registered_User_Is_Able_To_Login_During_Checkout_Flow(){
		dsl.loginToAccountDuringCheckoutFlow(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_Shipping_Page_After_Sucessful_Login(){
		dsl.verifyShippingPageAfterSucessfulLogin();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_Registered_User_Is_Able_To_Click_And_Save_First_Address_Dropdown(){
		dsl.verifyThatRegisteredUserIsAbleToClickAndSelectFirstSavedAddress();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_Shipping_Fields_Are_PrePopulated(){
		dsl.verifyThatShippingFieldsArePrePopulated(shippingDetails.get("First Name"), 
				shippingDetails.get("Last Name"), shippingDetails.get("Phone Number"));    
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step12_Click_On_The_KS_Logo(){
		dsl.navigateBackToKateSpadeHomePage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step13_Logout_From_The_Application(){
		dsl.logOutFromTheApplication();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step14_Verify_Registered_User_Is_Able_To_Login()  {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step15_Search_A_Product_From_Landing_Page() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step16_Select_The_First_Product() {
		dsl.selectFirstProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step17_Add_Product_To_Bag() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step18_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step19_Verify_Login_Fields_Are_Not_Visible_In_Shiping_Page_When_User_Is_Already_Login() {
		dsl.verifyLoginFieldsAreNotVisibleInShippingPage();
	}
	
}
