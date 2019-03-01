package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_677_Product_Set_Recommendations extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> product1;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		product1 = testData.get("Products").get(6);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(product1.get("Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_The_Header_Of_Recommedation_Section() {
		dsl.verifyTheHeaderOfTheRecommendationSection();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_The_Max_Limit_Of_Recommended_Items_Its_Product_Tiles() {
		dsl.verifyTheMaxLimitOfRecommendedItemsAndProductTiles();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Product_Images_Are_Loaded() throws InterruptedException {
		dsl.verifyProductImagesAreLoadedUsingAdobeScene7InPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Alternate_Images_Are_Displayed_On_Mouse_Over() {
		dsl.verifyAlternateImagesAreDisplayedOnMouseOverInPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Sales_Price_For_Recommended_Product() {
		dsl.verifySalesPriceForRecommendedProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Select_Product_From_Recommended_List_And_Verify_In_PDP_Page() {
		dsl.selectProductFromRecommendedListAndVerifyInPDPPage();
	}
}
