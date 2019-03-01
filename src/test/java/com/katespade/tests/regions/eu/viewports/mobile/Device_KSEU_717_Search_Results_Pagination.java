package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_717_Search_Results_Pagination extends KateSpadeTest{

	Map<String, String> url;
	String searchedItem ="bags";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(searchedItem);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_The_Max_Product_Count_On_The_Search_Page() {
		dsl.verifyTheMaxProductCountInSearchPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_User_Is_Navigated_To_Same_Pos_In_Search_Page_When_Click_Back_On_PDP() {
		dsl.verifyUserIsNaviagatedToSamePositionInSearchPageWhenClickBackOnPDP(searchedItem);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Lazy_Loading_On_The_Search_Page() {
		dsl.verifyLazyLoadingOnSearchPage();
	}
}
