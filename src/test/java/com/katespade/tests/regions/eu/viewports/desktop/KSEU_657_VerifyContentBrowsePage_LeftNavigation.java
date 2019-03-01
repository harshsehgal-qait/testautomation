package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_657_VerifyContentBrowsePage_LeftNavigation extends KateSpadeTest{

	Map<String, String> url;
	String categoryIndex="2";
	String subCategoryWithSubOptions = "the perfect black bag";
	String subCategoryWithSubOptionsFr = "the perfect black bag";
	String subCategoryWithNoSubOption = "satchels";
	String subCategoryWithNoSubOptionFr = "satchels";
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
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
	public void TestStep03_Verify_SubCategories_Are_Displayed_After_Main_Category_Selection_In_Header() {
		dsl.verifySubCategoriesAreDisplayedInSideBar();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_SubCategories_Are_Displayed_After_SubCategory_Selection_In_Header() {
		dsl.clickOnTheSubCategoryLinkOnTheHeader(categoryIndex, subCategoryWithNoSubOption,subCategoryWithNoSubOptionFr);
		dsl.verifySubCategoriesAreDisplayedInSideBar();
		dsl.verifyTheGreenColorOfSelectedSubCategory();
		dsl.clickOnTheSubCategoryLinkOnTheHeader(categoryIndex, subCategoryWithSubOptions,subCategoryWithSubOptionsFr);
		dsl.verifyTheGreenColorOfSelectedSubCategory();
		dsl.verifySubcategoriesAreExpandedInSideBar();
		
	}
	
}
