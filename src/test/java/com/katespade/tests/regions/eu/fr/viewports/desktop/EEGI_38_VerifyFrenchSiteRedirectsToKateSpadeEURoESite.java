package com.katespade.tests.regions.eu.fr.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EEGI_38_VerifyFrenchSiteRedirectsToKateSpadeEURoESite extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify__Page() {
		/*
		 Don't have below mentioned French sites on Development environment
			1. https://fr.katespade.com/fr-fr
			2. https://fr.katespade.com/en-fr
		 */
	}

}