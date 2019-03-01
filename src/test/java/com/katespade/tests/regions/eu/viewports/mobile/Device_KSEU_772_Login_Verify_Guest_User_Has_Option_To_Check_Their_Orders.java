package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_772_Login_Verify_Guest_User_Has_Option_To_Check_Their_Orders extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_Navigate_To_Check_Your_Order_Page() {
		dsl.navigateToCheckYourOrderPageInMobile();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_Sign_In_Section_Of_Guest_Order_Check_Page() {
		dsl.mobile_verifyEmailAndPwdAreMandatoryFieldsInGuestOrderCheckPage();
		dsl.verifyRememberMeChkbxIsUncheckedByDefault();
		dsl.verifyForgotPasswordLinkInOrderHistoryPage(users.get("Username"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_Check_Field_Cant_Be_Left_Empty() {
		dsl.mobile_verifyCheckOrderFieldsCannotBeLeftEmpty();
	}

	
}
