package com.katespade.tests.regions.eu.roe.viewports.desktop;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class KSEU_ROE_647_VerifyUserCanChangeCountryFromCountrySelector extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> region;
	String country = "United Kingdom";
	String HomeCountry = "Germany";
//	String uk = "ksEuUk";
//	String roe = "ksEuRoe";
	String uk = "en-gb";
	String roe = "en-de";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		region = testData.get("Page Names").get(7);
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
	public void TestStep04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
//		dsl.verifyProductIsAddedIntoTheBag();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyUserCanChangeCountryFromCartPage() {
		dsl.verifyShoppingCartPage();
		dsl.verifyCountryToggleIcon();
		dsl.clickOnCountryToggle();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyPageForCountrySelector() {
		dsl.verifyRegionHeading(region.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_SelectACountryFromCountrySelectorPageAndClickOnCONTINUEbutton_Germany() {
		dsl.selectACountryFromCountrySelectorPage(country);
		dsl.verifyCountrySwitchDialogIsDisplayed();
		dsl.clickOnContinueButtonFromCountrySwitchDialog();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyUserLandsOnSelectedCountry() {
		dsl.verifyUserLandsOnSelectedCountry(uk);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyAfterChangingTheCountryAddedBagIsRemoved() {
		dsl.verifyAfterChangingTheCountryAddedProductIsRemoved();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifyCountrySelectorHeaderIsUpdated() {
		dsl.verifyCountrySelectorHeaderIsUpdated(country);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_SelectACountryFromCountrySelectorPageAndClickingOnCANCELbutton_Germany() {
		dsl.goBackToThePreviousCountryByClickingOnBackButtonFromTheBrowser(roe);
		dsl.clickOnCountryToggle();
		dsl.verifyRegionHeading(region.get("Text Verification"));
		dsl.selectACountryFromCountrySelectorPage(country);
		dsl.verifyCountrySwitchDialogIsDisplayed();
		dsl.clickOnCancelButtonFromCountrySwitchDialog();
		dsl.verifyOnCancellingTheCountrySwitchTheMyBagRetainsTheProductThatIsAddedToTheBag();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_VerifyCountrySelectorHeaderIsUpdated() {
		dsl.verifyCountrySelectorHeaderIsUpdated(HomeCountry);
	}
}
