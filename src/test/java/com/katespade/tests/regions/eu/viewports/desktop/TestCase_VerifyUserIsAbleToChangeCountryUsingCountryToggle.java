package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase_VerifyUserIsAbleToChangeCountryUsingCountryToggle extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> creditCards;
	Map<String, String> giftCards;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyCountryToggleIcon() {
		dsl.verifyCountryToggleIcon();
		dsl.clickOnCountryToggle();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyutilityNavigationBar() {
		dsl.utilityNavigationBar();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyCountrySelectorLink() {
		dsl.verifyCountrySelectorLink();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyCountryLinks() {
		dsl.verifyCountryLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyStoreLocatoreIcon() {
		dsl.verifyStoreLocatoreIcon();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyHelpLinkIsDisplayed() {
		dsl.verifyHelpLink();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyKatespadeLogo() {
		dsl.verifyKatespadeSpadeLogo();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifySearchIconIsDisplayed() {
		dsl.verifySearchIconIsDisplayed();

	}
}
