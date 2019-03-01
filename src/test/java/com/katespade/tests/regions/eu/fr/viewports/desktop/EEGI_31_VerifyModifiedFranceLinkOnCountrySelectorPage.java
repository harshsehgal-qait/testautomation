package com.katespade.tests.regions.eu.fr.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EEGI_31_VerifyModifiedFranceLinkOnCountrySelectorPage extends KateSpadeTest {
	
	String countryLinkFranceFrench = "France | French";
	String countryLinkFranceEnglish = "France | English";
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
	public void Test_Step02_Verify_France_Country_Link_Has_Been_Modified_In_Country_Selector_Page() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader.get("Text Verification"));
		dsl.verifyCountryLinkForFranceHasBeenModifiedInCountrySelectorPage(countryLinkFranceFrench,
				countryLinkFranceEnglish);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Franch_Country_Is_Added_In_Country_Selector_Page() {
		dsl.verifyCountryIsAddedInCountrySelectionPage(countryDetails.get("Country"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Header_Text_In_Country_Selector_Page() {
		dsl.verifyHeaderTextInCountrySelectorPage(shippingToLabel.get("Text Verification"), 
				countryDetails.get("Locale"), countryDetails.get("Country"), countryDetails.get("Currency"));
	}

}