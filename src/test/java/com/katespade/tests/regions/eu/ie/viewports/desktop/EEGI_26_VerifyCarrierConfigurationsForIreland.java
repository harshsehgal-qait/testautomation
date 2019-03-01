package com.katespade.tests.regions.eu.ie.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_26_VerifyCarrierConfigurationsForIreland extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> countryDetails;
	Map<String, String> users;
	Map<String, String> countrySelectorPage;
	Map<String, String> shipping;
	Map<String, String> searchProduct;
	Map<String, String> shippingMethodForGermany;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		countryDetails = testData.get("Country And Currency").get(0);
		shippingMethodForGermany = testData.get("Country And Currency").get(2);
		users = testData.get("Users").get(0);
		countrySelectorPage = testData.get("Page Names").get(4);
		shipping = testData.get("GuestShippingDetails").get(1);
		searchProduct = testData.get("Products").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Select_Irish_Locale_In_Country_Selector() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPage.get("Text Verification"));
		dsl.clickOnCountry(countryDetails.get("Country"));
		dsl.verifyLocaleInKateSpadeRoESiteHomePage(countryDetails.get("Locale"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Add_Product_To_Shopping_Cart() {
		dsl.searchProduct(searchProduct.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Proceed_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Select_Ireland_As_Country_From_Country_DropDown() {
		dsl.verifyLocaleIsSelectedByDefault(countryDetails.get("Country"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_2day_IE_Shipping_method() {
		dsl.verifyShippingMethod(countryDetails.get("Shipping Method"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Change_Country_Other_than_Ireland() {
		dsl.selectCountryFromDropDown(shipping.get("Country"));
		dsl.fillingOfDetails(users.get("Username"), shipping.get("First Name"), shipping.get("Last Name"),
				shipping.get("Address"), shipping.get("Zip"), shipping.get("City"), shipping.get("Phone Number"));
		dsl.enterCity(shipping.get("City"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Change_Of_Shipping_Method_To_Ground_Shipping() {
		dsl.verifyShippingMethod(shippingMethodForGermany.get("Shipping Method"));
	}

}
