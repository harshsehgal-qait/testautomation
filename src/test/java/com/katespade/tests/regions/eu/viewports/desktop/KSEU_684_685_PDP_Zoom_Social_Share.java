package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_684_685_PDP_Zoom_Social_Share extends KateSpadeTest {
	
	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		users = testData.get("Users").get(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_User_Has_The_Ability_To_Zoom_The_Product() {
		dsl.verifyUserHasTheAbilityToZoomTheProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Zoom_Image_And_Primary_Image_Occupy_Same_Space() {
		dsl.verifyMainImageAndZoomImageOccupyTheSameSpace();
		dsl.verifySocialShareLinks();
	}
	
}
