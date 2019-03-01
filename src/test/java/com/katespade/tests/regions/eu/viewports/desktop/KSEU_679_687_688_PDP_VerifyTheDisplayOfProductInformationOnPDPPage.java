package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

/*
 *  Promotinal Message is commented out as of now
 */

public class KSEU_679_687_688_PDP_VerifyTheDisplayOfProductInformationOnPDPPage extends KateSpadeTest{
	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> product1;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(3);
		product1 = testData.get("Products").get(5);
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
	public void TestStep04_Verify_That_Promotional_Message_Is_Displayed_For_The_Product() {
		//dsl.verifyPromotionalMessageForAProduct(products.get("Promotional Message"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_The_Pricing_Structure_Of_Product_According_To_The_Country() {
		dsl.verifyThePricingStructureOfProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Inventory_Messaging_Is_Present_On_The_PDP_Page() {
		dsl.verifyInventoryMessagingIsPresentOnPDPPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_That_Size_Swatches_Are_Present_For_The_Product() {
		dsl.searchProduct(product1.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyThatSizeSwatchesIsPresentForTheProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Promotional_And_Marketing_Material_Is_Displayed_In_PDP() {
//		dsl.verifyPromotionalAndMarketingMaterialIsDisplayedInPDP();                               
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Select_Attributes() {
		dsl.selectColorSwatch(0);
		dsl.selectSizeSwatch(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Size_Chart_Is_Present_To_Guide_The_User_In_PDP() {
		dsl.verifySizeChartIsPresentToGuideTheUserInPDP();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_User_Is_Able_To_Add_Product_Bag() {
		dsl.verifyGreenColorOfAddToBagButton();
	}
	
}
