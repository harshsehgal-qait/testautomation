package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KSEU_623_VerifyHomePageFooterLinks extends KateSpadeTest {
	
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
	public void Test_Step02_Verify_About_Us_Footer_Links() {
		dsl.verifyAboutUsFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Customer_Care_Footer_Links() {
		dsl.verifyCustomerCareFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Careers_Footer_Links() {
		dsl.verifyCareersFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_My_Account_Footer_Links() {
		dsl.verifyMyAccountFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Email_Sign_Up_Functionality() {
		dsl.verifyEmailSignUpFunctionalityInFooter();
	}
		
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Comapny_Links_And_The_Page_That_They_Navigate_To() {
		dsl.verifyComapnyLinkNameInFooter();
		dsl.verifyComapnyLinksAndThePageThatTheyNavigateTo();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Social_Media_Links_Are_Active() {
		dsl.verifySocialMediaIconsFR();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Go_To_Your_Order_From_Footer() {
		dsl.goToYourOrderFromFooter();
	}
	
}