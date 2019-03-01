package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_681_697_PDP_Verify_User_Has_The_Ability_To_See_Recommendations extends KateSpadeTest{
	
	Map<String, String> homePageURL;
	Map<String, String> product1;
	Map<String, String> product2;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		product1 = testData.get("Products").get(0);
		product2 = testData.get("Products").get(1);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Search_A_Product_From_Landing_Page() {
		dsl.searchProduct(product1.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_The_Header_Of_Recommendation_Section() {
		dsl.verifyTheHeaderOfTheRecommendationSection();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_The_Max_Limit_Of_Recommended_Items_Its_Product_Tiles() {
		dsl.verifyTheMaxLimitOfRecommendedItemsAndProductTiles();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Quick_View_Shop_Button_Visibility() {
		dsl.verifyQuickViewShopButtonShouldNotVisibleOnPDPPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Color_Change_On_Hover_Of_The_Recommended_Product_Name() {
		dsl.verifyColorChangeOfTheRecommendedProductName();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_The_Alignment_Of_Recommended_Product_Image() {
		dsl.scrollUpToPage();
		dsl.searchProduct(product2.get("Style Number"));
		dsl.verifyTheAlignmentOfTheRecommendedProductImage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Select_Product_From_Recommended_List_And_Verify_In_PDP_Page() {
		dsl.selectProductFromRecommendedListAndVerifyInPDPPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Select_Product_Image_From_Recommended_List_And_Verify_In_PDP_Page() {
		dsl.selectProductImageRecommendedListAndVerifyInPDPPage();
	}
}
