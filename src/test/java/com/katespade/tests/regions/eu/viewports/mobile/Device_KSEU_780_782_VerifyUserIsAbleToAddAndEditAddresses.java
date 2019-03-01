package com.katespade.tests.regions.eu.viewports.mobile;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Device_KSEU_780_782_VerifyUserIsAbleToAddAndEditAddresses extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> fname;
	Map<String, String> pageName;
	Map<String, String> shipping1;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		shipping1 = testData.get("GuestShippingDetails").get(1);
		users = testData.get("Users").get(4);
		fname = testData.get("GuestShippingDetails").get(1);
		pageName = testData.get("Page Names").get(6);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyRegisteredUserIsAbleToLoginIntoKateSpade() {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyRegisteredUserLandsOnMyAccountPage() {
		dsl.verifyUserIsInAccountPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserIsAbleToEditAccountSettings() {
		dsl.addAddressInMyAccount(shipping.get("Address"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Zip"), shipping.get("Phone Number"), shipping.get("Address"), shipping.get("City"));
		dsl.verifySavedAddressOnAccountPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyUserLandsOnAddBookPageOnClickingXBtn() {
		dsl.clickOnaddressButton();
		dsl.verifyUserLandsOnAddBookPageOnClickingXBtn();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyUserIsAbleToEditAndUpdateAddress() {
		dsl.verifyUserIsAbleToEditAddress(fname.get("First Name"));
		dsl.verifyUserIsAbleToUpdateAddress();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyUserIsAbleTocloseEditWindowOnClickingXBtn() {
		dsl.verifyUserIsAbleTocloseAddWindowOnClickingXBtn();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyUserIsAbleToEditAccountSettings() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_addAddressInAccountSettings(shipping1.get("Address"), shipping1.get("First Name"),
				shipping1.get("Last Name"), shipping1.get("Zip"), shipping1.get("Phone Number"),
				shipping1.get("Address"), shipping1.get("City"));
		dsl.verifyUserIsableToMakeNewlyAddedAddressAsDefaultAddress(shipping1.get("First Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifyUserIsAbleToDeleteAddress() {
		dsl.verifyUserIsAbleToDeleteAddress();
	}
	
}
