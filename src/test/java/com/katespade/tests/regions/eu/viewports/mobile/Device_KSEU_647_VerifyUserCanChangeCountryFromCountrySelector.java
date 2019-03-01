package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_647_VerifyUserCanChangeCountryFromCountrySelector extends KateSpadeTest {

	Map<String, String> homepageURL;
	Map<String, String> products;
	Map<String, String> region;
	String country = "Germany";
	String HomeCountry = "United Kingdom";
	String uk = "en-GB";
	
	// As per new set of Requirements i.e., "https://5thandpacific.atlassian.net/wiki/spaces/EEGI/pages/744063244/1.01+Add+German+Language+to+DE+Locale"
	String germany = "de_DE";
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		region = testData.get("Page Names").get(7);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Search_A_Product_From_Landing_Page() {
		dsl.searchProduct(products.get("Style Number"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Select_The_First_Product() {
		dsl.selectFirstProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Add_Product_To_Bag_And_Verify_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_User_Can_Change_Country_From_Cart_Page() {
		dsl.verifyShoppingCartPage();
		dsl.verifyCountryToggleIcon();
		dsl.clickOnCountryToggle();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Page_For_Country_Selector() {
		dsl.verifyRegionHeading(region.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Select_A_Country_From_Country_Selector_Page_And_Click_On_CONTINUE_button_Germany() {
		dsl.selectACountryFromCountrySelectorPage(country);
		dsl.verifyCountrySwitchDialogIsDisplayed();
		dsl.clickOnContinueButtonFromCountrySwitchDialog();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_User_Lands_On_Selected_Country() {
		dsl.verifyUserLandsOnSelectedCountry(germany);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_After_Changing_The_Country_Added_Bag_Is_Removed() {
		dsl.verifyAfterChangingTheCountryAddedProductIsRemoved();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_Country_Selector_Header_Is_Updated() {
		dsl.openHamburgerMenu();
		dsl.verifyCountrySelectorHeaderIsUpdated(country);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Select_A_Country_From_Country_Selector_Page_And_Clicking_On_CANCEL_Button_Germany() {
		dsl.goBackToThePreviousCountryByClickingOnBackButtonFromTheBrowser(uk);
		dsl.clickOnCountryToggle();
		dsl.verifyRegionHeading(region.get("Text Verification"));
		dsl.selectACountryFromCountrySelectorPage(country);
		dsl.verifyCountrySwitchDialogIsDisplayed();
		dsl.clickOnCancelButtonFromCountrySwitchDialog();
		dsl.verifyOnCancellingTheCountrySwitchTheMyBagRetainsTheProductThatIsAddedToTheBag();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step12_Verify_Country_Selector_Header_Is_Updated() {
		dsl.openHamburgerMenu();
		dsl.verifyCountrySelectorHeaderIsUpdated(HomeCountry);
	}
}
