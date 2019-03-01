package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_620_CategoryNavigationIncludedElements extends KateSpadeTest {

	Map<String, String> homepageURL;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Top_Level_Categories_Displayed_Within_Hamburger_Menu() {
		dsl.verifyMobileCategoryNavigation();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_User_Taps_Element_On_Top_Level_Category_Sub_Categories_Present() {
		dsl.verifyMobileTopNavigationSubCategory();
	}

}