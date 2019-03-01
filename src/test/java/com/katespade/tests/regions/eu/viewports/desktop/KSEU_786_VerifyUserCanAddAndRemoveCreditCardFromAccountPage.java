package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_786_VerifyUserCanAddAndRemoveCreditCardFromAccountPage extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> pageName;
	Map<String, String> creditcard;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(0);
		pageName = testData.get("Page Names").get(0);
		creditcard = testData.get("CardDetails").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Login_Into_KateSpade() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Registered_User_Lands_On_My_Account_Page() {
		dsl.verifyAfterEnteringValidCredentialsLogInDisplayesHiNameOfUserAndMyAccountPageIsOpen();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_User_Can_Add_Credit_Card() {
		dsl.goToCreditCardFromContentAssest();
		dsl.verifyUserCanAddANewAddress();
		dsl.verifyAllTheValidationChecksAreAvailable();
		dsl.enterInvalidCreditDetail();
		dsl.enterTheValidCreditCardValues(creditcard.get("Type"), creditcard.get("CardName"), 
				creditcard.get("Number"), creditcard.get("CVV"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_On_Clicking_On_Cancel_Button_User_Is_Back_On_Wallet() {
		dsl.verifyUserCanAddANewCard();
		dsl.verifyOnClickingOnCancelButtonUserIsBackOnWalletPage();
	}

}
