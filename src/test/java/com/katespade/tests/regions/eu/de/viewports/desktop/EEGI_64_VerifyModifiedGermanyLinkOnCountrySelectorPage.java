package com.katespade.tests.regions.eu.de.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EEGI_64_VerifyModifiedGermanyLinkOnCountrySelectorPage extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> shippingToLabel;
	Map<String, String> countryDetails;
	Map<String, String> countrySelectorPageHeader;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		countryDetails = testData.get("Country And Currency").get(0);
		shippingToLabel = testData.get("Page Names").get(3);
		countrySelectorPageHeader = testData.get("Page Names").get(4);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Germany_Country_Is_Added_In_Country_Selector_Page() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader.get("Text Verification"));
		dsl.verifyCountryIsAddedInCountrySelectionPage(countryDetails.get("Country"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Header_Text_In_Country_Selector_Page() {
		dsl.verifyHeaderTextInCountrySelectorPage(shippingToLabel.get("Text Verification"), 
				countryDetails.get("Locale"), countryDetails.get("Country"), countryDetails.get("Currency"));
	}

}