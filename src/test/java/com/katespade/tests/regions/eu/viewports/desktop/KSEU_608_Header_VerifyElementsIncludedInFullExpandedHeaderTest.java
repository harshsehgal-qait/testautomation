package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_608_Header_VerifyElementsIncludedInFullExpandedHeaderTest extends KateSpadeTest{

	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> countryAndCurrencyDeatils;
	Map<String, String> product;
	Map<String, String> homepageURL;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		homepageURL = testData.get("BASE URL").get(0);
		countryAndCurrencyDeatils = testData.get("Country And Currency").get(0);
		users = testData.get("Users").get(2);
		product = testData.get("Products").get(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_The_Company_Logo_In_Header_And_Verify_It_is_Link_To_Homepage() {
		dsl.verifyTheCompanyLogoIsDisplayedAndLinkToHeader(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_VerifyCountryNameAndItsCurrencySymbol() {
		dsl.verifyCountryAndItsCurrencySymbol(countryAndCurrencyDeatils.get("Country")+" "+countryAndCurrencyDeatils.get("Currency"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_VerifySignInAndRegisterLink() {
		dsl.verifySignInAndRegisterLink(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Store_Locator_Icon_HomePage() {
		dsl.verifyStoreLocatorIconInHomepage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Wishlist_Link_And_Icon() {
		dsl.verifyWishlistLinkAndIcon();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Mini_Cart_Window() {
		dsl.verifyMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Search_Text_And_Icon() {
		dsl.verifySearchtextAndIcon();
	}
}
