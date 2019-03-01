package com.katespade.tests.regions.eu.de.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EEGI_62_VerifyGermanLocaleElementsAndStorefront extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> defaultLocale;
	Map<String, String> countryShippingTo;
	Map<String, String> countryDetails;
	Map<String, String> countrySelectorPageHeader;
	Map<String, String> storeLocatorTitle;
	Map<String, String> needHelpTitle;
	Map<String, String> signInAndRegisterLink;
	Map<String, String> wishListTitle;
	Map<String, String> bagTitle;
	Map<String, String> loginAccountHeader;
	Map<String, String> emailAddressPlaceHolderText;
	Map<String, String> passwordPlaceHolderText;
	Map<String, String> rememberMeCheckBoxText;
	Map<String, String> forgotPasswordLinkText;
	Map<String, String> signInButtonText;
	Map<String, String> createAnAccountButtonText;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		defaultLocale = testData.get("BASE URL").get(1);
		countryDetails = testData.get("Country And Currency").get(0);
		countryShippingTo = testData.get("Page Names").get(3);
		countrySelectorPageHeader = testData.get("Page Names").get(4);
		storeLocatorTitle = testData.get("Page Names").get(6);
		needHelpTitle = testData.get("Page Names").get(7);
		signInAndRegisterLink = testData.get("Page Names").get(8);
		wishListTitle = testData.get("Page Names").get(9);
		bagTitle = testData.get("Page Names").get(10);
		loginAccountHeader = testData.get("Page Names").get(11);
		emailAddressPlaceHolderText = testData.get("Page Names").get(12);
		passwordPlaceHolderText = testData.get("Page Names").get(13);
		rememberMeCheckBoxText = testData.get("Page Names").get(14);
		forgotPasswordLinkText = testData.get("Page Names").get(15);
		signInButtonText = testData.get("Page Names").get(16);
		createAnAccountButtonText = testData.get("Page Names").get(17);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(defaultLocale.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Default_Locale_German_Is_Selected_In_KateSpade_EU_RoE_Site() {
		dsl.verifyDefaultLocaleOfKateSpadeRoESite(defaultLocale.get("URL"), 
				countryDetails.get("RoE Site"), countryShippingTo.get("Text Verification"), 
				countryDetails.get("Country"), countryDetails.get("Currency"));
		dsl.verifyGermanLocaleToKateSpadeRoESiteHomePage(homePageURL.get("URL"), 
				countryDetails.get("Locale"));	
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_German_Locale_Is_Added_And_Enabled_In_KateSpade_EU_RoE_Site() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader.get("Text Verification"));
		dsl.verifyCountryIsAddedInCountrySelectionPage(countryDetails.get("Country"));
		dsl.clickOnCountryLink(countryDetails.get("Country"));
		dsl.verifyUserHasNavigatedToHomePageOfSelectedCountry(homePageURL.get("URL"));		
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Header_Elements_Of_German_Locale_In_KateSpade_EU_RoE_Site_Home_Page() {
		dsl.verifyDefaultLocaleOfKateSpadeRoESite(homePageURL.get("URL"), 
				countryDetails.get("RoE Site"), countryShippingTo.get("Text Verification"), 
				countryDetails.get("Country"), countryDetails.get("Currency"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Top_Banner_Elements_On_Home_Page_Of_KateSpade_EU_RoE_Site_Home_Page_Top_Banner() {
		dsl.verifyTopBannerLinksOnKateSpadeRoEHomePage(storeLocatorTitle.get("Text Verification"), 
				needHelpTitle.get("Text Verification"), signInAndRegisterLink.get("Text Verification"), 
				wishListTitle.get("Text Verification"), bagTitle.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Login_Form_Elements_On_Account_Page_Of_KateSpade_EU_RoE_Site() {
		dsl.clickOnSignInLink();
		dsl.verifyLoginFormOnKateSpadeRoEAccountPage(loginAccountHeader.get("Text Verification"), 
				emailAddressPlaceHolderText.get("Text Verification"), passwordPlaceHolderText.get("Text Verification"), 
				rememberMeCheckBoxText.get("Text Verification"), forgotPasswordLinkText.get("Text Verification"),
				signInButtonText.get("Text Verification"), createAnAccountButtonText.get("Text Verification"));
	}
	
}