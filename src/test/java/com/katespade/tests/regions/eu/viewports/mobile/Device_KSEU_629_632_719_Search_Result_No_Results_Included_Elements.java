package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_629_632_719_Search_Result_No_Results_Included_Elements extends KateSpadeTest {

	Map<String, String> homepageURL;
	Map<String, String> products;
	Map<String, String> zeroResults;
	Map<String, String> searchBxPlaceholder;
	String invalidSearchTerm = "bagg";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(0);
		zeroResults = testData.get("Page Names").get(26);
		searchBxPlaceholder = testData.get("Page Names").get(27);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Search_A_Product_From_Landing_Page() {
		dsl.verifySearchBxFunctionality(searchBxPlaceholder.get("Text Verification"));
		dsl.searchProduct(invalidSearchTerm);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Header_For_The_No_Results_Page() {
		dsl.verifyHeaderForTheNoResultsPage(zeroResults.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_No_Result_Help_Is_Displayed() {
		dsl.verifyNoResultHelpIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Search_From_Zero_Result_Page() {
		dsl.verifySearchFrom0ResultsSearchPage(products.get("Style Number"));
	}

}
