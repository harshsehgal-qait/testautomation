package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_45_VerifyLastCardDetailsRemovedFromSavedCards extends KateSpadeTest{
	
	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> creditcard1;
	Map<String, String> creditcard2;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(3);
		creditcard1 = testData.get("CardDetails").get(2);
		creditcard2 = testData.get("CardDetails").get(0);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_LogIn_As_Existing_User() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_User_Can_Add_Credit_Or_Debit_Card() {
		dsl.goToCreditCardFromContentAssest();
		dsl.verifyUserCanAddANewCard();
		dsl.enterTheValidCreditCardValues(creditcard1.get("Type"), creditcard1.get("CardName"), creditcard1.get("Number"),creditcard1.get("CVV"));
	}
	

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_VerifyUserCanAddSecondCreditCard() {
		dsl.verifyUserCanAddANewCard();
		dsl.verifyAllTheValidationChecksAreAvailable();
		dsl.enterTheValidCreditCardValues(creditcard2.get("Type"), creditcard2.get("CardName"), creditcard2.get("Number"),creditcard2.get("CVV"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_VerifyUserCanDeleteFirstCard() {
		dsl.verifyUserCanRemoveSavedCard(0);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_VerifyUserCanDeleteSecondCard() {
		dsl.verifyUserCanRemoveSavedCard(0);
	}
	
	
	
	
	
	

//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step04_Verify_User_Can_Add_Second_Credit_Card() {
//		dsl.verifyUserCanAddANewCard();
//		dsl.verifyAllTheValidationChecksAreAvailable();
//		dsl.enterTheValidCreditCardValues(creditcard2.get("Type"), creditcard2.get("CardName"), creditcard2.get("Number"),creditcard2.get("CVV"));
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step05_Verify_User_Can_Delete_First_Card() {
//		dsl.verifyUserCanRemoveSavedCard();
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step06_Verify_User_Can_Delete_Second_Card() {
//		dsl.verifyUserCanRemoveSavedCard();
//		dsl.verifySavedCardRemoved();
//	}

	
}
