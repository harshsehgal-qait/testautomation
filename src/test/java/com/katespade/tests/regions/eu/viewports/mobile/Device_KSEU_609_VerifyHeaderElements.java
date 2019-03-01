package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_609_VerifyHeaderElements extends KateSpadeTest {
	
	Map<String, String> homepageURL;
	Map<String, String> products;
	Map<String, String> users;
	String textInPlaceholder = "Enter Address, City, Post Code or country";
	String textInPlaceholderFr= "Entrez une adresse, une ville, un code postal ou un pays";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(0);
		users = testData.get("Users").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Company_Logo_Link() {
		dsl.verifyTheCompanyLogoIsDisplayedAndLinkToHeader(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Promotional_Messaging_Bar() {
		dsl.verifyPromotionalMessagingBarAppearsOnHeader();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Store_Locator_Icon_And_Page() {
		dsl.verifyStoreLocatorIcon();
		dsl.verifyDetailsOfStoreLocatorPage(textInPlaceholder, textInPlaceholderFr);
		dsl.navigateBackToKateSpadeHomePage();
		
	}
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Shopping_Bag() {
		dsl.verifyBagIconForMobile();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Search_Text_And_Icon() {
		dsl.verifySearchtextAndIcon();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Mobile_Category_Navigation_And_Top_Headers() {
		dsl.verifyMobileCategoryNavigation();
	}
	
	
	
	
}
