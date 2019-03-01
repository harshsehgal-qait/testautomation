package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class KSEU_715_VerifySearchIncludedElements extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Name"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifySearchResultPage() {
		dsl.verifyHeaderReturnsSearchQuerryWitchTotalNumberOfResults();
		dsl.verifyFilterOptionIsPresent();
		dsl.verifySortingFiltersArePresent();
	}
}
