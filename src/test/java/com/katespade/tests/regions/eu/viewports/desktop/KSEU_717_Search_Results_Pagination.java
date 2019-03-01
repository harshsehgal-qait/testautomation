package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_717_Search_Results_Pagination extends KateSpadeTest {

	Map<String, String> homePageURL;
	String searchedItems = "bags";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Search_A_Product_From_Landing_Page() {
		dsl.searchProduct(searchedItems);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_The_Max_Product_Count_On_The_Search_Page() {
		dsl.verifyTheMaxProductCountInSearchPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_User_Is_Navigated_To_Same_Pos_In_Search_Page_When_Click_Back_On_PDP() {
		dsl.verifyUserIsNaviagatedToSamePositionInSearchPageWhenClickBackOnPDP(searchedItems);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Lazy_Loading_On_The_Search_Page() {
		dsl.verifyLazyLoadingOnSearchPage();
	}
	
}
