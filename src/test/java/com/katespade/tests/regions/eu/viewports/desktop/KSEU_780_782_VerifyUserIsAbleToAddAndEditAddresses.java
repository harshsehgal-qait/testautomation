package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KSEU_780_782_VerifyUserIsAbleToAddAndEditAddresses extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> fname;
	Map<String, String> shipping1;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		shipping1 = testData.get("GuestShippingDetails").get(1);
		users = testData.get("Users").get(2);
		fname = testData.get("GuestShippingDetails").get(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Registered_User_Is_Able_To_Login_Into_KateSpade() {
		dsl.clickOnSignInLink();
		dsl.loginWithRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Registered_User_Lands_On_My_Account_Page() {
		dsl.verifyAfterEnteringValidCredentialsLogInDisplayesHiNameOfUserAndMyAccountPageIsOpen();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_User_Is_Able_To_Edit_Account_Settings() {
		dsl.addAddressInMyAccount(shipping.get("Address"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Zip"), shipping.get("Phone Number"), shipping.get("Address"), shipping.get("City"));
		dsl.verifySavedAddressOnAccountPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_User_Lands_On_Add_Book_Page_On_Clicking_Cancel_Button() {
		dsl.clickOnaddressButton();
		dsl.verifyUserLandsOnAddBookPageOnClickingCancleBtn();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_User_Lands_On_Add_Book_Page_On_Clicking_X_Button() {
		dsl.clickOnaddressButton();
		dsl.verifyUserLandsOnAddBookPageOnClickingXBtn();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_User_Is_Able_To_Edit_And_Update_Address() {
		dsl.verifyUserIsAbleToEditAddress(fname.get("First Name"));
		dsl.verifyUserIsAbleToUpdateAddress();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_User_Is_Able_To_Close_Edit_Window_On_Clicking_X_Button() {
		dsl.verifyUserIsAbleTocloseAddWindowOnClickingXBtn();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_User_Is_Able_To_Edit_Account_Settings() {
		dsl.addAddressInAccountSettings(shipping1.get("Address"), shipping1.get("First Name"),
				shipping1.get("Last Name"), shipping1.get("Zip"), shipping1.get("Phone Number"),
				shipping1.get("Address"), shipping1.get("City"));
		dsl.verifyUserIsableToMakeNewlyAddedAddressAsDefaultAddress(shipping1.get("First Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_User_Is_Able_To_Delete_Address() {
		dsl.verifyUserIsAbleToDeleteAddress();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_Side_Navigation() {
		dsl.verifySideNavOnWishlistPage();
	}
	
}
