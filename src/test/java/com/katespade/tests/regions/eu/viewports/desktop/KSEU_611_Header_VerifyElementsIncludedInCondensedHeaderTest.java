package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_611_Header_VerifyElementsIncludedInCondensedHeaderTest extends KateSpadeTest{

	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> homepageURL;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		homepageURL = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_KS_Logo_In_Sticky_Header() {
		dsl.verifyStickyKSLogoItsLinkingToHomepage(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_SignIn_And_Register_Link_In_Sticky_Header()  {
		dsl.verifySignInAndRegisterLinkInStickyHeader(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Sticky_Bag_Icon_Functionality()  {
		dsl.verifyBagIconFunctionalityInStickyHeader();
	}
	
}