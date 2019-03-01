package com.katespade.tests.regions.eu.roe.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_ROE_660_VerifyContentBrowsePage_GeneralElements extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> increasingOrder;
	Map<String, String> decreasingOrder;
	Map<String, String> justAdded;
	String categoryIndex = "4";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		increasingOrder = testData.get("Page Names").get(22);
		decreasingOrder = testData.get("Page Names").get(23);
		justAdded = testData.get("Page Names").get(24);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_Click_On_The_Category_From_Header() {
		dsl.clickCategoryOnHeader(categoryIndex);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_BreadCrum_Icon_On_Shop_Grid_Page() {
		dsl.verifyBreadCrumbIcon();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_Refinement_Section_In_Shop_Grid_Page() {
		dsl.verifyRefinementSectionInShopGridPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Sorting_In_Shop_Grid_Page() {
		dsl.verifyProductSortingInShopGridPage(justAdded.get("Text Verification"));
		dsl.verifyProductSortingInShopGridPage(increasingOrder.get("Text Verification"));
		dsl.verifyProductSortingInShopGridPage(decreasingOrder.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Category_And_Product_Count_Is_Displayed_In_Shop_Grid_Page() {
		dsl.verifyCategoryHeaderAndProductCountIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Product_Count_Is_Changed_As_Per_Refinement_In_Shop_Grid_Page() {
		// dsl.verifyProductCountChangesAccordingToRefinement();
		dsl.verifyProductCountChangesAccordingToSizeRefinement();
	}
}
