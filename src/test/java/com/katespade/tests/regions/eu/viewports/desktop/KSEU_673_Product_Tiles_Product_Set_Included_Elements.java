package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_673_Product_Tiles_Product_Set_Included_Elements extends KateSpadeTest{

	Map<String, String> url;
	String categoryIndex = "9";
	String subCategory = "gifts for the bride";
	String subCategoryFr = "gifts for the bride";

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
		dsl.clickOnTheSubCategoryLinkOnTheHeader(categoryIndex, subCategory,subCategoryFr);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_Clickng_Frst_Prodct_Image_On_Shop_Grd_Page_Nav_To_Corspndng_PDP() {
		dsl.verifyClickingFrstProductImageOnShopGridPageNavToCorrespondngPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Clickng_Frst_Prodct_Name_On_Shop_Grd_Page_Nav_To_Corspndng_PDP() {
		dsl.verifyClickingFrstProductNameOnShopGridPageNavToCorrespondngPDP();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Alternate_Img_Are_Displayed_On_MouseOver_On_Shop_Grid_Page() {
		dsl.verifyAlternateImagesAreDisplayedOnMouseOver();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Text_Buy_All_Or_None_Text_Display_For_Product_Set_On_Shop_Grid_Page() {
		dsl.verifyTextBuyAllOrNoneDisplayForProductSet();
	}
}
