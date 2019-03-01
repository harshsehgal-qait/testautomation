package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_636_Verify_Contact_Us_Page extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> product;
	Map<String, String> content;
	Map<String, String> content2;
	Map<String, String> pageName;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(2);
		product = testData.get("Products").get(1);
		content = testData.get("Page Names").get(1);
		content2 = testData.get("Page Names").get(2);
		pageName = testData.get("Page Names").get(1);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_User_Goes_To_Contact_Us_Page_From_Footer() {
		dsl.goToCustomerCarePageFromFooter();
		dsl.verfifyUserlandsOnContactUsPage(pageName.get("Page Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Page_Description_Text_On_Contact_Us_Page() {
        // commented due to bug KSEU-1763
		// dsl.verifyPageDescriptionTextOnContactUsPage(content.get("Text Verification"));
	}

	// Customer care is with h1 label but should be with H2 label-Issue -KSEU-1042
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Customer_Care_Has_HeaderH2() {
		dsl.verifyCustomerCareHasHeaderH2();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Page_Name_Has_HeaderH1() {
		dsl.verifyPageNameHasHeaderH1();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Need_Help_SubHeader() {
		dsl.verifyNeedHelpSubHeader();
	}

	// chat with us is a broken link:KSEU-819
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Chat_With_Us_And_Email_Us() {
		dsl.verfiyChatWithUSAndEmailUs();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Contact_Us_Page_Is_Set_In_Library_Folder_Structure_For_Customer_Care() {
		dsl.expandContactUsOptions();
		dsl.verifyCustomerCareLeftHandNavigation(content2.get("Text Verification"));
	}

}