package com.katespade.tests.regions.eu.viewports.mobile;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Device_KSEU_783_UK_VerifyAddAndEditAddressesPostalCodeLookUp extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> pageName;
	Map<String, String> postalCode;
	String errMessage = "Please enter a valid UK postcode or enter manually below";
	String enterManuallyMsg = "address not found. please try again or enter manually";
	

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);		
		users = testData.get("Users").get(4);	
		pageName = testData.get("Page Names").get(6);
		postalCode = testData.get("GuestShippingDetails").get(0);
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
	public void TestStep03_VerifyUserIsAbleToEditAccountSettings() {
		dsl.openHamburgerMenu();
		dsl.mobile_verifyAddressBookFeilds();		
	}
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyErrorMsgWhenInvalidPostalCodeIsEntered() {
		dsl.verifyErrorMsgWhenInvalidPostalCodeIsEntered(errMessage,enterManuallyMsg);		
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyWeHaveFoundMsgWhenValidPostalCodeIsEntered() {
		dsl.verifyWeHaveFoundMsgWhenValidPostalCodeIsEntered(postalCode.get("Zip"));		
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyUserIsAbleToEnterAddressManually() {
		dsl.verifyUserIsAbleToEnterAddressManually();		
	}

	
}
