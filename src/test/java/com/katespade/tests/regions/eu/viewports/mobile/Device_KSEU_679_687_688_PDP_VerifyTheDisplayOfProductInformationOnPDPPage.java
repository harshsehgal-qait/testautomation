package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_679_687_688_PDP_VerifyTheDisplayOfProductInformationOnPDPPage extends KateSpadeTest {
	
	Map<String, String> homepageURL;
	Map<String, String> products;
	Map<String, String> product1;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(9);
		product1 = testData.get("Products").get(5);
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
		// dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_That_Promotional_Message_Is_Displayed_For_The_Product() {
		dsl.verifyPromotionalMessageForAProduct(products.get("Promotional Message"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_The_Pricing_Structure_Of_Product_According_To_The_Country() {
		dsl.verifyThePricingStructureOfProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Inventory_Messaging_Is_Present_On_The_PDP_Page() {
		dsl.verifyInventoryMessagingIsPresentOnPDPPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_That_Size_Swatches_Are_Present_For_The_Product() {
		dsl.searchProduct(product1.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyThatSizeSwatchesIsPresentForTheProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Promotional_And_Marketing_Material_Is_Displayed_In_PDP() {
		dsl.verifyPromotionalAndMarketingMaterialIsDisplayedInPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_Size_Chart_Is_Present_To_Guide_The_User_In_PDP() {
		dsl.verifySizeChartIsPresentToGuideTheUserInPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_User_Is_Able_To_Add_Product_Bag() {
		dsl.selectColorSwatch(0);
		dsl.selectSizeSwatch(0);
		dsl.addTheProductToBag();
		dsl.verifyMiniCartInMobile();
		dsl.verifyUserIsInPDPPage();
	}

}
