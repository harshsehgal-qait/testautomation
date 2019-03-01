package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_660_VerifyContentBrowsePage_GeneralElements extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> increasingOrder;
	Map<String, String> decreasingOrder;
	Map<String, String> justAdded;
	String categoryIndex = "3";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		increasingOrder = testData.get("Page Names").get(22);
		decreasingOrder = testData.get("Page Names").get(23);
		justAdded = testData.get("Page Names").get(24);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Click_On_The_Category_From_Header() {
		dsl.clickCategoryOnHeader(categoryIndex);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Bread_Crumb_Icon_On_Shop_Grid_Page() {
		dsl.verifyBreadCrumbIcon();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Refinement_Section_In_Shop_Grid_Page() {
		dsl.verifyRefinementSectionInShopGridPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Sorting_In_Shop_Grid_Page() {
		dsl.verifyProductSortingInShopGridPage(increasingOrder.get("Text Verification"));
		dsl.verifyProductSortingInShopGridPage(decreasingOrder.get("Text Verification"));
		dsl.verifyProductSortingInShopGridPage(justAdded.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Category_And_Product_Count_Is_Displayed_In_Shop_Grid_Page() {
		dsl.verifyCategoryHeaderAndProductCountIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Product_Count_Is_Changed_As_Per_Size_Refinement_In_Shop_Grid_Page() {
		dsl.verifyProductCountChangesAccordingToSizeRefinement();
	}
	
}