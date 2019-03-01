package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_630_633_Verify_SearchNavigation extends KateSpadeTest {

	Map<String, String> homepageURL;
	Map<String, String> products;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(22);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Search_A_Product_From_Landing_Page() {
		dsl.verifyThePredictiveResults();
		dsl.verifyCategoryNameAndParentAssociation();
		dsl.clickOnFirstElementFromPredectiveResult();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_PDP() {
		dsl.verifyPrimaryImageAndProductThumbnailInMobile();
		dsl.verifyDetailsOfProductInPDPPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Search_A_Product() {
		dsl.searchProduct(products.get("Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Search_Result_Page() {
		dsl.verifySearchResultPage(products.get("Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_On_Clicking_On_Category_User_Lands_On_Category_Browse_Page() {
		dsl.verifyOnClickingOnCategoryUserLandsOnCategoryBrowsePageForMobile();
	}

}
