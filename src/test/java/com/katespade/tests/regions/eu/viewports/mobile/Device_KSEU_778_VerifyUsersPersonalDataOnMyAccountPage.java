package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;
import com.qait.automation.utils.PropFileHandler;

public class Device_KSEU_778_VerifyUsersPersonalDataOnMyAccountPage extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> user1;
	Map<String, String> products;
	Map<String, String> pageName;
	Map<String, String> errorMsg;
	Map<String, String> errorMsgPassword;
	Map<String, String> optIn;
	Map<String, String> optOut;
	Map<String, String> wrongPassErrMsg;
	String myInfo = "profile";
	String myAccount = "account";
	String invalidEmail = "1234";
	String password1 = "1111"; // 4 characters
	String newPassword = "12345678"; // valid password
	String lastName = "testuser";
	String orgLastName = "automation";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		pageName = testData.get("Page Names").get(12);
		errorMsg = testData.get("Page Names").get(17);
		errorMsgPassword = testData.get("Page Names").get(18);
		user1 = testData.get("Users").get(0);
		optIn = testData.get("Page Names").get(19);
		optOut = testData.get("Page Names").get(20);
		wrongPassErrMsg = testData.get("Page Names").get(21);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_NavigateToRegistrationPage() {
		dsl.verifySignInFromFooter();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_RegisterNewUserIntoKateSpade() {
		dsl.registerANewUser();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserLandsOnProfilePageOnClickingMyInfo() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(myInfo, pageName.get("Page Name"));

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyFirstAndLastNameFieldIsPresent() {
		dsl.mobile_verifyFirstAndLastNameFieldIsPresent();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyEmailAddressFieldIsPresent() {
		dsl.mobile_verifyEmailAddressFieldIsPresent(user1.get("Username"), invalidEmail, errorMsg.get("Text Verification"),
				PropFileHandler.readProperty("emailAddress"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail() {
		dsl.mobile_verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(invalidEmail);

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyPasswordField() {
		dsl.mobile_verifyPasswordField(password1, errorMsgPassword.get("Text Verification"));

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyRadioButtonFieldAndOptInOptOutText() {
		dsl.verifyRadioButtonFieldAndOptInOptOutText(optIn.get("Text Verification"), optOut.get("Text Verification"));

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifySubmitButtonFunctionality() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.verifySubmitButtonFunctinality(lastName, PropFileHandler.readProperty("emailAddress"), PropFileHandler.readProperty("password"));
		dsl.verifylastNameisUpdatedSuccessfully(lastName);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_VerifyChangePasswordFeildsCannotLeftBeEmpty() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_verifyChangePasswordFeildsCannotLeftBeEmpty();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_VerifyNewPasswordFeildLength() {
		dsl.verifyNewPasswordFeildLength(password1, errorMsgPassword.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_VerifyConfirmPasswordFeildShouldBeSameAsNewPassword() {
		dsl.verifyConfirmPasswordFeildShouldBeSameAsNewPassword(newPassword, newPassword);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_VerifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch() {
		dsl.mobile_verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(newPassword, password1,
				errorMsgPassword.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_VerifyErrorMessageWhenWrongCurrentPasswordIsEntered() {
		dsl.verifyErrorMessageWhenWrongCurrentPasswordIsEntered(myInfo, newPassword, newPassword,
				wrongPassErrMsg.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep16_VerifyUserIsAbleToChangePasswordSuccessfully() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.verifyUserIsAbleToChangePasswordSuccessfully(PropFileHandler.readProperty("password"), newPassword, newPassword, myAccount);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep17_UpdateTheOriginalUserDetails() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.verifySubmitButtonFunctinality(orgLastName, PropFileHandler.readProperty("emailAddress"), newPassword);
		dsl.verifylastNameisUpdatedSuccessfully(orgLastName);
		dsl.expandMyAccountOptionsInMobile();
		dsl.verifyUserIsAbleToChangePasswordSuccessfully(newPassword, PropFileHandler.readProperty("password"), PropFileHandler.readProperty("password"),
				myAccount);
	}

}
