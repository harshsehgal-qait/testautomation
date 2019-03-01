package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

/*
 * Promotional message is commented out as of now
 */

public class KSEU_670_671_672_Product_Tiles_Included_Elements extends KateSpadeTest {

	Map<String, String> homePageURL;
	String categoryIndex = "2";
	String promotionalMsg = "Bonus Bonus !!";
	String prodIndex = "1";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Click_On_The_Category_From_Header() {
		dsl.clickCategoryOnHeader(categoryIndex);
	}
	
	/*@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Product_Images_are_Loaded_From_Scene7() {
		dsl.verifyProductImagesAreLoadedUsingAdobeScene7();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_Clickng_Frst_Prodct_Image_On_Shop_Grd_Page_Nav_To_Corspndng_PDP() {
		dsl.verifyClickingFrstProductImageOnShopGridPageNavToCorrespondngPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Clickng_Frst_Prodct_Name_On_Shop_Grd_Page_Nav_To_Corspndng_PDP() {
		dsl.verifyClickingFrstProductNameOnShopGridPageNavToCorrespondngPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Clickng_Quick_Buy_Icon_On_Shop_Grd_Page_Nav_To_Corspndng_PDP() {
		dsl.verifyClickingQuickBuyIconOnShopGridPageNavToCorrespondngQuickBuyPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Promotional_Msg_Are_Appearing_On_Shop_Grid_Page() {
//		dsl.verifyPromotionalMsgAreAppearingInShopGridPage(promotionalMsg);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Alternate_Img_Are_Displayed_On_MouseOver_On_Shop_Grid_Page() {
		dsl.verifyAlternateImagesAreDisplayedOnMouseOver();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_Swatch_List_On_Shop_Grid_Page() {
		dsl.verifySwatchListOnTheShopGridPage(prodIndex);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Pricing_Structure_On_Shop_Grid_Page() {
		dsl.verifyThePricingStructureOfProductInShopGridPage();
	}*/
}
