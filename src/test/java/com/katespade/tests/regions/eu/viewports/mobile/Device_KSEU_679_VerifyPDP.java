package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_679_VerifyPDP extends KateSpadeTest {

	Map<String, String> homepageURL;
	Map<String, String> products;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
		users = testData.get("Users").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Search_A_Product_From_Landing_Page() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Select_The_First_Product() {
		dsl.selectFirstProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Primary_Image_On_PDP_And_Thumbnails() {
		dsl.verifyPrimaryImageAndProductThumbnailInMobile();
		dsl.verifyBlackBottomBorderOfTheSelectedThumbnailImage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Description_Of_Product_In_PDP_Page() {
		dsl.verifyDetailsOfProductInPDPPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Social_Sites_Icons_In_PDP_Page() {
		dsl.verifySocialSitesIcons();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Bread_Crumbs_In_PDP_Page() {
		dsl.verifyBreadCrumbs();
	}
}
