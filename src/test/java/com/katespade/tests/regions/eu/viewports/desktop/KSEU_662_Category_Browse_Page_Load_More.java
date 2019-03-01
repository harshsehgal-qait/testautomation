package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_662_Category_Browse_Page_Load_More extends KateSpadeTest{

	Map<String, String> url;
	String categoryIndex="3";

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
		dsl.clickCategoryOnHeader(categoryIndex);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_The_Max_Product_Count_On_The_Shop_Grid_Page() {
		dsl.verifyTheMaxProductCountInShopgridPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_User_Is_Navigated_To_Same_Pos_In_Shp_Grd_When_Click_Back_On_PDP() {
		dsl.verifyUserIsNaviagatedToSamePositionInShpGrdPageWhenClickBackOnPDP();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Lazy_Loading_On_The_Shop_Grid_Page() {
		dsl.verifyLazyLoadingOnShopGridPage();
	}
	
	
}
