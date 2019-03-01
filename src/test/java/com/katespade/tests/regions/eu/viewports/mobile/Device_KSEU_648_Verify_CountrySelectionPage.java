package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_648_Verify_CountrySelectionPage extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> region;
	Map<String, String> users;

	String country = "Germany";
	String HomeCountry = "United Kingdom";
	String germany = "ksEuRoe";
	String uk = "ksEuUk";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		region = testData.get("Page Names").get(7);
		users = testData.get("Users").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_LoginIntoKateSpade() throws InterruptedException {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}
	
	
	/* Querry:- For old registered user-while toggling to country user logs out

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
		dsl.verifyProductIsAddedIntoTheBag();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyUserCanChangeCountryFromCartPage() {
		dsl.verifyShoppingCartPage();
		dsl.verifyCountryToggleIcon();
		dsl.clickOnCountryToggle();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyPageForCountrySelector() {
		dsl.verifyRegionHeading(region.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_SelectACountryFromCountrySelectorPageAndClickOnCONTINUEbutton_Germany() {
		dsl.selectACountryFromCountrySelectorPage_Germany(country);
		dsl.verifyCountrySwitchDialogIsDisplayed();
		dsl.clickOnContinueButtonFromCountrySwitchDialog();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyUserLandsOnSelectedCountry() {
		dsl.verifyUserLandsOnSelectedCountry(germany);
	}

	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifyAfterChangingTheCountryAddedBagIsRemovedButUserIsStillLoggedIn() {
		dsl.verifyAfterChangingTheCountryAddedProductIsRemoved();
		dsl.verifyUserIsStillLoggedIn();
		dsl.goBackToThePreviousCountryByClickingOnBackButtonFromTheBrowser(uk);
		
	}*/
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_VerifyThatUserIsOnShippingPageANdCountrySelectorIsNotPresentOnCheckout() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
		dsl.verifyCountrySelectorIsNotPresentOnCheckout();
		
	}

	
}
