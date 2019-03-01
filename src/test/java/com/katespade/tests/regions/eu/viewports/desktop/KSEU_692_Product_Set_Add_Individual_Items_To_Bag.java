package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_692_Product_Set_Add_Individual_Items_To_Bag extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> products;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(6);
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
	public void TestStep03_Verify_Details_Of_The_Page_When_Product_Set_Item_Is_Searched(){
		dsl.verifyDetailsOfPDPPageWhenProductSetItemIsSearched();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_Add_All_Products_To_Bag_For_Product_Set() throws InterruptedException{
		dsl.verifyIndividualItemsInProductsSetPDPIsAddedToMiniCart();
	}
}
