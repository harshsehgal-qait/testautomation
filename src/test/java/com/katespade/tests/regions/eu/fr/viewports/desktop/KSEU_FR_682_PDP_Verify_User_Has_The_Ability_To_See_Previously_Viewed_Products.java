package com.katespade.tests.regions.eu.fr.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_FR_682_PDP_Verify_User_Has_The_Ability_To_See_Previously_Viewed_Products extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> product1;
	Map<String, String> product2;
	Map<String, String> product3;
	Map<String, String> product4;
	Map<String, String> recentlyViewedHeader;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		product1 = testData.get("Products").get(0);
		product2 = testData.get("Products").get(1);
		product3 = testData.get("Products").get(2);
		product4 = testData.get("Products").get(3);
		recentlyViewedHeader = testData.get("Page Names").get(25);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchFirstProductFromPDPPage_And_Verify_Alignment_Of_Recently_Viewed_Item() {
		dsl.searchProduct(product1.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyTheAlignmentOfTheRecentlyViewdProductImage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SearchSecondProductFromPDPPage() {
		dsl.searchProduct(product2.get("Style Number"));
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_SearchThirdProductFromPDPPage() {
		dsl.searchProduct(product3.get("Style Number"));
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_SearchFourthProductFromPDPPage() {
		dsl.searchProduct(product4.get("Style Number"));
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Details_Of_The_Most_Recently_Viewed_Section() {
		dsl.verifyTheDetailsOfMostRecentlyViewedSection(recentlyViewedHeader.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Selected_Most_Recently_Viewed_Product_Redirect_To_PDP_Page() {
		dsl.selectProductFromMostRecentlyViewdListAndVerifyInPDPPage();
	}
}
