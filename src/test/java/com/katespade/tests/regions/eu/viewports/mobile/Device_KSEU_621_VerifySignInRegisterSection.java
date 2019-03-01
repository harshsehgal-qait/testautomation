package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_621_VerifySignInRegisterSection
		extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> users;
	

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(1);
		
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_verifySignInTakesUserToAccountPageWhenUserNotLoggedIn() {
		dsl.navigateToMobileSignInPage();
		dsl.verifyUserIsOnSingInPage();
		
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_verifyRegisterAccountTakesUserToAccountPageWhenUserNotLoggedIn() {
		dsl.navigateToMobileRegisterAccountPage();
		dsl.veriyUserIsOnRegisterAccountPageOnMobile();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyWishlistTakesUserToAccountPageWhenUserNotLoggedIn() {
		dsl.navigateToWishListFromTopNavigationOnMobile();
		dsl.verifyUserIsOnSingInPage();
		
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifySignInRegisterLinkUpdatesToHiWhenUserLoggedIn() {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
		dsl.openHamburgerMenu();
		dsl.verifySingInRegisterLinkWhenUserLoggedIn();
		dsl.closeHamburgerMenu();
		
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyWishlistTakesUserToWishlistPageWhenUserLoggedIn() {
		dsl.navigateToWishListFromTopNavigationOnMobile();
		dsl.verifyUserLandsOnWishlistPage();
		
	}
	
	
	
	
}
