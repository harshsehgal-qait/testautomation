package com.katespade.tests.regions.eu.ie.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EEGI_15_VerifyIrelandLocaleToCountrySelectorPage extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> country;
	Map<String, String> countrySelectorPageHeader;
	Map<String, String> listOfCountries;
	Map<String, String> shippingLabel;
	Map<String, String> countryDetails;
	Map<String, String> countryDetails1;
	Map<String, String> homepageURLForNL;
	Map<String, String> searchProduct;
	Map<String, String> changeShippingDestinationMsg;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		homepageURLForNL = testData.get("BASE URL").get(2);
		countrySelectorPageHeader = testData.get("Page Names").get(4);
		listOfCountries = testData.get("Page Names").get(5);
		shippingLabel = testData.get("Page Names").get(3);
		countryDetails = testData.get("Country And Currency").get(0);
		countryDetails1 = testData.get("Country And Currency").get(1);
		searchProduct = testData.get("Products").get(2);
		changeShippingDestinationMsg = testData.get("Page Names").get(6);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Ireland_Is_Added_In_Country_Selector_Page() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader.get("Text Verification"));
		dsl.verifyCountryIsAddedInCountrySelectionPage(countryDetails.get("Country"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Order_Of_Countries_In_List_On_Country_Selector_Page() {
		dsl.verifyOrderOfCountriesInListOnCountrySelectorPage(listOfCountries.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_User_Is_Directed_To_Home_Page_With_IE_Locale() {
		dsl.clickOnCountry(countryDetails.get("Country"));
		dsl.clickOnCountryLinkOnCountrySelectionPage(homePageURL.get("URL"));
		dsl.verifyLocaleInKateSpadeRoESiteHomePage(countryDetails.get("Locale"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Country_Selector_Logo_In_Header() {
		dsl.verifyCountrySelectorLogoInHeader(shippingLabel.get("Text Verification"), 
				countryDetails.get("Country"), countryDetails.get("Currency"));
		dsl.clickOnNavigationOverlay();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_User_Is_Navigated_Back_To_Country_Selection_Page() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_User_Select_Netherlands() {
		dsl.clickOnCountry(countryDetails1.get("Country"));
		dsl.clickOnCountryLinkOnCountrySelectionPage(homepageURLForNL.get("URL"));
		dsl.verifyLocaleInKateSpadeRoESiteHomePage(countryDetails1.get("Locale"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Search_A_Product_From_LandingPage() {
		dsl.searchProduct(searchProduct.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Select_The_First_Product() {
		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.waitForMiniCartWindowToDisappear();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_User_Is_Navigated_Back_To_Country_Selection_Page() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_Message_Appears_When_User_Select_Country() {
		dsl.clickOnCountry(countryDetails.get("Country"));
		dsl.verifyMessageOfItemRemoveFromShoppingBag(changeShippingDestinationMsg.get("Text Verification"));
	}

}