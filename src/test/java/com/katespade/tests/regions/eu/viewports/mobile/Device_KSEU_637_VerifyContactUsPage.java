package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_637_VerifyContactUsPage extends KateSpadeTest {

	Map<String, String> homepageURL;
	Map<String, String> pageName;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		pageName = testData.get("Page Names").get(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_User_Goes_To_Contact_Us_Page_From_Footer() {
		dsl.goToCustomerCarePageFromFooter();
		dsl.device_verifyUserlandsOnContactUsPage(pageName.get("Page Name"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Contact_Us_Dropdown() {
		dsl.verifyContactUsDropdown();
	}

}