package com.katespade.tests.regions.eu.viewports.mobile;

import com.katespade.tests.KateSpadeTest;
import com.qait.automation.utils.PropFileHandler;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Device_TestCase_VerifyUserIsAbleToRegisterAndLoginIntoKateSpade extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> giftRegistryDetails;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(0);
		giftRegistryDetails = testData.get("Gift Registry Details").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyLandingPage() {
		dsl.verifyTopNavigationBar();
		dsl.closeHamburgerMenu();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_NavigateToRegistrationPage() {
		dsl.verifySignInFromFooter();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_RegisterNewUserIntoKateSpade() {
		dsl.registerANewUser();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_LogOutFromApplication() {
		dsl.logOutFromTheApplication();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyRegisteredUserIsAbleToLogin() {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(PropFileHandler.readProperty("emailAddress"),
				PropFileHandler.readProperty("password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyRegisteredUserIsAbleToSeeAccountMenuTabOptions() {
		dsl.verifyUserAccountMenuTabOptions();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyUserIsAbleToEditAccountSettings() {
		dsl.userGoesToEditAccountPage();
		dsl.editNameinAccountSetting(PropFileHandler.readProperty("password"));
		dsl.addAddressInAccountSettings(shipping.get("Address"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Zip"), shipping.get("Phone Number"), giftRegistryDetails.get("Address City"),
				giftRegistryDetails.get("Address"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_LogOutFromApplication() {
		dsl.logoutFromApplicationMobile();
	}
}
