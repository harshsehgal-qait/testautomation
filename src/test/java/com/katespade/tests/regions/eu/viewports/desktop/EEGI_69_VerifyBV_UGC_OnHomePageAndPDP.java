package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_69_VerifyBV_UGC_OnHomePageAndPDP extends KateSpadeTest{

	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> searchProduct;
	Map<String, String> titleUGC;
	Map<String, String> descriptionUGC;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(3);
		searchProduct = testData.get("Products").get(4);
		titleUGC = testData.get("Page Names").get(29);
		descriptionUGC = testData.get("Page Names").get(30);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_BV_UGC_Content_Slot_On_HomePage() {
		dsl.verifyUGCContentSlotAboveFooter();
		dsl.verifyUGCContentSlotByTitle(titleUGC.get("Text Verification"));
		dsl.verifyUGCContentSlotByDescription(descriptionUGC.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_BV_UGC_Content_Slot_On_PDP() {
		dsl.searchProduct(searchProduct.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyUGCContentSlotAboveRatingsAndReviewSection();
		dsl.verifyUGCContentSlotByTitle(titleUGC.get("Text Verification"));
		dsl.verifyUGCContentSlotByDescription(descriptionUGC.get("Text Verification"));
		dsl.confirmNavigationOnClickingViewGalleryButton();
		dsl.verifyUserlandsOnHomePageOnClickingKateSpadeLogo(homePageURL.get("URL"));
		dsl.confirmNavigationOnClickingSubmitYourPhotoButton();
	}
	
}
