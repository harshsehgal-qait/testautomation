package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_615_Header_Verify_Promotional_Messaging_Bar_Appears_On_Header extends KateSpadeTest{
	Map<String, String> url;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_verify_Promotional_Messaging_Bar_Appears_On_Header() {
		dsl.verifyPromotionalMessagingBarAppearsOnHeader();
	}
}
