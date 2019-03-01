package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase_VerificationOfShopGridPage extends KateSpadeTest {
	
	Map<String, String> homepageURL;
	Map<String, String> products;
	String categoryIndex = "2";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_Click_On_The_Category_From_Header() {
		dsl.clickCategoryOnHeader(categoryIndex);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_verifyBreadCrumOnShopGrid() {
		dsl.verifyBreadCrumbIcon();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyQuickViewIconOnShopGridAndPriceRange() {
		dsl.verifyQuickBuyIcon();
	}

}