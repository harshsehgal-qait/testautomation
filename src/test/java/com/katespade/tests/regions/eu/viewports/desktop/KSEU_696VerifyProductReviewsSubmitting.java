package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_696VerifyProductReviewsSubmitting extends KateSpadeTest{
	Map<String, String> url;
	Map<String, String> products;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(4);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_verifyReviewIconFeature() {
		dsl.verifyWriteAReviewIconOnPdp();
		dsl.navigateToReviewPage();
	}
	
}
