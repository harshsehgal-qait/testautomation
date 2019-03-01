package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase_VerifyHomePageHeaderLinks extends KateSpadeTest {
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
	public void TestStep02_VerifyHeaderLinks() {
		dsl.verifyAndHoverAtHeaderLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyNewSubcategoriesLinks() {
		dsl.verifySubcategoriesOfHeaderLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyStoreLocatoreIcon() {
		dsl.verifyStoreLocatoreIcon();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyHelpLinkIsDisplayed() {
		dsl.verifyHelpLink();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifySearchIconIsDisplayed() {
		dsl.verifySearchIconIsDisplayed();

	}
}