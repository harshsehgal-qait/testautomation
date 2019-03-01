package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_771_VerifyLoginAndRegistrationPageFlow extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> pageName;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(0);
		pageName = testData.get("Page Names").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyHeadersDisplaySigninAndRegistration() {
		dsl.navigateToMobileSignInPage();
		dsl.validateThatOnClickSignInLinkTakesUserToMyAccountLandingLoginPage();
		dsl.clickOnRegisterButton();
		dsl.validateThatOnClickRegisterLinkTakesUserToLoginCreateAccountLandingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyEmailANdPasswordAreRequiredToLogin() {
		dsl.navigateToMobileSignInPage();
		dsl.verifyEmailANdPasswordAreRequiredToLogin();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyOnEnteringWrongCredentialsErrorMessageAppears() {
		dsl.verifyOnEnteringWrongCredentialsErrorMessageAppears("abc@qa.com", users.get("Password"),
				pageName.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyForgotPasswordLink() {
		dsl.verifyForgotPasswordLink(users.get("Username"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyAfterEnteringValidCredentialsLogInDisplayesHiNameOfUserAndMyAccountPageIsOpen() {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
		dsl.expandMyAccountOptionsInMobile();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyOrderHistoryPage() {
		dsl.logoutFromApplicationMobile();
		dsl.verifyImNewHereAndClickOnCheckYourOrderLink();
		dsl.verifyOrderHistoryPage();
	}

}