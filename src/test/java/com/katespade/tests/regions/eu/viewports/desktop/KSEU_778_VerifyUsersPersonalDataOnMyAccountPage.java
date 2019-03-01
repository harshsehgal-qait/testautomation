package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;
import com.qait.automation.utils.PropFileHandler;

public class KSEU_778_VerifyUsersPersonalDataOnMyAccountPage extends KateSpadeTest {

	Map<String, String> homePageURL;
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
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		pageName = testData.get("Page Names").get(12);
		errorMsg = testData.get("Page Names").get(17);
		errorMsgPassword = testData.get("Page Names").get(18);
		user1 = testData.get("Users").get(2);
		optIn = testData.get("Page Names").get(19);
		optOut = testData.get("Page Names").get(32);
		wrongPassErrMsg = testData.get("Page Names").get(21);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Navigate_To_Registration_Page() {
		dsl.verifySignInFromFooter();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Register_New_User_Into_KateSpade() {
		dsl.registerANewUser();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_User_Lands_On_Profile_Page_On_Clicking_My_Info() {
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(myInfo, pageName.get("Page Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_First_And_Last_Name_Field_Is_Present() {
		dsl.verifyFirstAndLastNameFieldIsPresent();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Email_Address_Field_Is_Present() {
		dsl.verifyEmailAddressFieldIsPresent(user1.get("Username"), invalidEmail, errorMsg.get("Text Verification"),
				PropFileHandler.readProperty("emailAddress"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Confirm_Email_Field_And_Error_If_Email_Field_Mismatch_From_Users_Email() {
		dsl.verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(invalidEmail);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Password_Field() {
		dsl.verifyPasswordField(password1, errorMsgPassword.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_Radio_Button_Field_And_Opt_In_Opt_Out_Text() {
		dsl.verifyRadioButtonFieldAndOptInOptOutText(optIn.get("Text Verification"), optOut.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_Submit_Button_Functinality() {
		dsl.verifySubmitButtonFunctinality(lastName, PropFileHandler.readProperty("emailAddress"), PropFileHandler.readProperty("password"));
		dsl.verifylastNameisUpdatedSuccessfully(lastName);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_Change_Password_Feilds_Cannot_Left_Be_Empty() {
		dsl.verifyChangePasswordFeildsCannotLeftBeEmpty();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step12_Verify_New_Password_Field_Length() {
		dsl.verifyNewPasswordFeildLength(password1, errorMsgPassword.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step13_Verify_Confirm_Password_Field_Should_Be_Same_As_New_Password() {
		dsl.verifyConfirmPasswordFeildShouldBeSameAsNewPassword(newPassword, newPassword);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step14_Verify_Error_Message_When_Confirm_Password_Field_And_New_Password_Does_Not_Match() {
		dsl.verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(newPassword, password1,
				errorMsgPassword.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step15_Verify_Error_Message_When_Wrong_Current_Password_Is_Entered() {
		dsl.verifyErrorMessageWhenWrongCurrentPasswordIsEntered(myInfo, newPassword, newPassword,
				wrongPassErrMsg.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step16_Verify_User_Is_Able_To_Change_Password_Successfully() {
		dsl.verifyUserIsAbleToChangePasswordSuccessfully(PropFileHandler.readProperty("password"), newPassword, newPassword, myAccount);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step17_Update_The_Original_User_Deatils() {
		dsl.verifySubmitButtonFunctinality(orgLastName, PropFileHandler.readProperty("emailAddress"), newPassword);
		dsl.verifylastNameisUpdatedSuccessfully(orgLastName);
		dsl.verifyUserIsAbleToChangePasswordSuccessfully(newPassword, PropFileHandler.readProperty("password"), PropFileHandler.readProperty("password"),
				myAccount);
	}

}
