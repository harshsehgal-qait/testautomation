package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KSEU_783_UK_VerifyAddAndEditAddressesPostalCodeLookUp extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> pageName;
	Map<String, String> postalCode;
	String errMessage = "Please enter a valid UK postcode or enter manually below";
	String enterManuallyMsg = "address not found. please try again or enter manually";
	

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);		
		users = testData.get("Users").get(2);	
		pageName = testData.get("Page Names").get(6);
		postalCode = testData.get("GuestShippingDetails").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyRegisteredUserIsAbleToLoginIntoKateSpade() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyRegisteredUserLandsOnMyAccountPage() {
		dsl.verifyAfterEnteringValidCredentialsLogInDisplayesHiNameOfUserAndMyAccountPageIsOpen();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserIsAbleToEditAccountSettings() {
		dsl.verifyAddressBookFeilds();		
	}
	
	/*
	 * Find Address Functionality Changed
	 */
	
	/*@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyErrorMsgWhenInvalidPostalCodeIsEntered() {
		dsl.verifyErrorMsgWhenInvalidPostalCodeIsEntered(errMessage,enterManuallyMsg);		
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyWeHaveFoundMsgWhenValidPostalCodeIsEntered() {
		dsl.verifyWeHaveFoundMsgWhenValidPostalCodeIsEntered(postalCode.get("Zip"));		
	}*/
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyUserIsAbleToEnterAddressManually() {
		dsl.verifyUserIsAbleToEnterAddressManually();		
	}

	
}
