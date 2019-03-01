package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_689_690_691_ProductSet_General_Consideration_Image_Details_Associated_Product_Grid extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	String qty ="1";
	int thmbImgIndx =1;
	String recentlyViewedHeader= "your most recently viewed items";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(6);
		users = testData.get("Users").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_Details_Of_The_PDP_Page_When_Product_Set_Item_Is_Searched() {
		dsl.verifyDetailsOfPDPPageWhenProductSetItemIsSearched();
		dsl.verifyAddAllToBagButtonAndProdSetPriceIsDisplayed();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_Clicking_Alternative_Images_Replace_Main_Images() {
		dsl.verifyClickingAlternativeImageReplacedTheMainImage(thmbImgIndx);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Social_Sites_Icon() {
		dsl.verifySocialSitesIcons();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Promotional_And_Marketing_Material_Is_Displayed_In_PDP() {
		dsl.verifyPromotionalAndMarketingMaterialIsDisplayedInPDP();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Most_Recently_Viewed_Section_In_PDP() {
		dsl.verifyMostRecentlyViewedSection(recentlyViewedHeader);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_On_Clicking_Product_Name_From_Product_Set_List_User_Is_Navigated_To_PDP_Page() {
		dsl.verifyOnClickingProductNameFromProductSetUserWillBeNavigatedToItsPDPPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_The_Pricing_Structure_Of_Product_According_To_The_Country() {
		dsl.verifyThePricingStructureOfProductSet();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_The_Main_Product_Set_Image_And_Alternate_Product_Image_Is_Displayed() {
		dsl.verifyTheMainProductSetImageAndAlternateProductImageIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_The_Quantity_Dropdown_Selected_Is_1_By_Default() {
		dsl.verifyTheQuantityDropdownSelected(qty);
	}

}
