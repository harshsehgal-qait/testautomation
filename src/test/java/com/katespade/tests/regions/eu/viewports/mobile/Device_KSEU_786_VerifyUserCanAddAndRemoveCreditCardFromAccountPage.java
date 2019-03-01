package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_786_VerifyUserCanAddAndRemoveCreditCardFromAccountPage extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> pageName;
	Map<String, String> creditcard;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(0);
		pageName = testData.get("Page Names").get(0);
		creditcard = testData.get("CardDetails").get(0);
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
	public void TestStep03_VerifyRegisteredUserLandsOnMyAccountPage() {
		dsl.verifyUserIsInAccountPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserCanAddaCreditCard() {
		dsl.goToCreditCardFromContentAssest();
		dsl.verifyUserCanAddANewAddress();
		dsl.verifyAllTheValidationChecksAreAvailable();
		dsl.enterInvalidCreditDetail();
		
//		dsl.enterTheValidCreditCardValues(creditcard.get("Type"), creditcard.get("CardName"), creditcard.get("Number"),creditcard.get("CVV"));
		 

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyOnClickingOnCancelButtonUserIsBackOnWallet() {
		 dsl.verifyOnClickingOnCancelButtonUserIsBackOnWalletPage();
	}

}
