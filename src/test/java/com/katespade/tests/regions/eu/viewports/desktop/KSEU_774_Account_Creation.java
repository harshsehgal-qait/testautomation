package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_774_Account_Creation extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> alredayRegisteredEmail;
	Map<String, String> shipping;
	Map<String, String> pageName;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		alredayRegisteredEmail = testData.get("Users").get(0);
		pageName = testData.get("Page Names").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_NaviagateToAccountRegistrationPage() {
		dsl.verifyHeadersDisplaySigninAndRegistration();
		dsl.clickOnSignInLink();
		dsl.validateThatOnClickSignInLinkTakesUserToMyAccountLandingLoginPage();
		dsl.clickOnRegisterButton();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_Error_Msg_Displayed_If_Already_Registered_Email_Is_Used_To_Create_A_New_Account() {
		dsl.verifyErrorMsgIfAlreadyRegisteredEmailIsUsedForAccontCreation(alredayRegisteredEmail.get("Username"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_Add_To_Mailing_List_RadioBtn_Is_Not_Checked_By_Default() {
		dsl.verifyAddToMailingListRadioBtnIsNotChecked();
	}
		
}
