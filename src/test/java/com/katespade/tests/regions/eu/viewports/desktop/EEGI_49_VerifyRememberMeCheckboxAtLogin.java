package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_49_VerifyRememberMeCheckboxAtLogin extends KateSpadeTest{
	
	Map<String, String> homePageURL;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(3);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_RememberMe_Checkbox_At_Login_Page() {
		dsl.verifyCheckboxAtLogin(users.get("Username"), users.get("Password"));
	}
	
	 @Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_RememberMe_Checkbox_At_WishList_Login() {
		dsl.navigateToWishListPage();
		dsl.verifyUserLandsOnWishlistPage();
		dsl.verifyCheckBoxAtWishListLogin(users.get("Username"), users.get("Password"));
	}
	
}
