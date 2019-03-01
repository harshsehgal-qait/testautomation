package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import com.qait.automation.utils.PropFileHandler;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase_VerifyUserIsAbleToRegisterAndLoginIntoKateSpade extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> shipping;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(2);
		shipping = testData.get("GuestShippingDetails").get(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Landing_Page() {
		dsl.verifyTopNavigationBar();
		dsl.verifySearchDisplayed();
		dsl.verifyFooterSection();
		dsl.scrollDownToBottom();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Navigate_To_Registration_Page() {
		dsl.verifySignInFromFooter();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Register_New_User_Into_KateSpade() {
		dsl.registerANewUser();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_LogOut_From_The_Application() {
		dsl.logOutFromTheApplication();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Registered_User_Is_Able_To_Login() throws InterruptedException {
		dsl.loginAsRegisteredUser(PropFileHandler.readProperty("emailAddress"),
				PropFileHandler.readProperty("password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Registered_User_Is_Able_To_See_Account_Menu_Tab_Options() {
		dsl.verifyUserAccountMenuTabOptions();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_User_Is_Able_To_Edit_Account_Settings() {
		dsl.userGoesToEditAccountPage();
		dsl.editNameinAccountSetting(PropFileHandler.readProperty("password"));
		dsl.addAddressInMyAccount(shipping.get("Address"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Zip"), shipping.get("Phone Number"),shipping.get("Address"),shipping.get("City"));
		dsl.verifySavedAddressOnAccountPage();
	}
	
}
