package com.katespade.tests.regions.eu.ie.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_16_32_VerifySelectedCountryLocaleGlobalElementsAndStorefront extends KateSpadeTest {
	
	int cartQuantity;
	String productPrice = "";
	Map<String, String> homePageURL;
	Map<String, String> countryDetails;
	Map<String, String> shippingLabel;
	Map<String, String> users;
	Map<String, String> searchProduct;
	Map<String, String> countrySelectorPageHeader;
	Map<String, String> shippingDetails;
	Map<String, String> countryDetailsIreland;
	Map<String, String> emptyCartHeader;
	Map<String, String> changeShippingDestinationMsg;
	Map<String, String> countryOtherThanRoESite;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		countryDetails = testData.get("Country And Currency").get(0);
		shippingLabel = testData.get("Page Names").get(3);
		users = testData.get("Users").get(0);
		countrySelectorPageHeader = testData.get("Page Names").get(4);
		shippingDetails = testData.get("GuestShippingDetails").get(0);
		searchProduct = testData.get("Products").get(2);
		countryDetailsIreland = testData.get("Country And Currency").get(2);
		emptyCartHeader = testData.get("Page Names").get(8);
		changeShippingDestinationMsg = testData.get("Page Names").get(6);
		countryOtherThanRoESite = testData.get("Country And Currency").get(3);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Select_Ireland_Country_From_Country_Selector_Page() {
		dsl.clickOnCountrySelectorIcon();
		dsl.clickOnCountry("Ireland");
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Select_Product_Searched_And_Fetch_Product_Price() {
		dsl.searchProduct(searchProduct.get("Style Number"));
		dsl.selectFirstProduct();
		productPrice = dsl.pickPriceOfSearchedProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Shopping_Cart_Is_Empty() {
		dsl.clickOnBagIcon();
		dsl.verifyShoppingCartIsEmpty(emptyCartHeader.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Select_France_Country_From_Country_Selector_Page() {
		dsl.clickOnCountrySelectorIcon();
		dsl.clickOnCountry("France");
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Country_Switch_Dialog_Box_Does_Not_Appear_When_Cart_Is_Empty() {
		dsl.verifyCountrySwitchDialogBoxDoesNotAppearWhenCartIsEmpty();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Product_Price_Used_By_Franch_Locale_Will_Be_Same_For_Other_RoE_Countries() {
		dsl.searchProduct(searchProduct.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyProductPriceUsedByFranceFrenchLocaleWillBeSameForOtherRoECountries(productPrice);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Clicking_Selected_Country_CTA_User_Redirects_To_HomePage_With_Their_Locale() {
		dsl.clickOnCountrySelectorIcon();
		dsl.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader.get("Text Verification"));
		dsl.clickOnCountry(countryDetails.get("Country"));
		dsl.clickOnCountryLinkOnCountrySelectionPage(homePageURL.get("URL"));
		dsl.verifyLocaleInKateSpadeRoESiteHomePage(countryDetails.get("Locale"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_Country_Selector_Logo_In_Header() {
		dsl.verifyCountrySelectorLogoInHeader(shippingLabel.get("Text Verification"), countryDetails.get("Country"),
				countryDetails.get("Currency"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Login_Into_KateSpade_EU_RoE() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Navigate_To_Address_Book_Page_And_Add_Address() {
		dsl.navigateToAddressBookPage();
		dsl.clickToAddAddress();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step12_Verify_Ireland_Appears_In_Country_Dropdown() {
		dsl.verifyLocaleInCountryDropdown(countryDetails.get("Country"));
		dsl.closeAddressModal();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step13_Add_A_Product_To_Cart() {
		dsl.searchProduct(searchProduct.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step14_Proceed_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step15_Verify_Selected_Locale_Is_PreSelected_In_Country_DropDown_Of_Shipping_Page() {
		dsl.verifyLocaleIsSelectedByDefault(countryDetails.get("Country"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step16_Proceed_To_Billing_Page() {
		dsl.enterShipingDetails(users.get("Username"), shippingDetails.get("First Name"), 
				shippingDetails.get("Last Name"), shippingDetails.get("Address"), 
				shippingDetails.get("Zip"), shippingDetails.get("City"), shippingDetails.get("Phone Number"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step17_Verify_Selected_Locale_Is_PreSelected_In_Country_DropDown_Of_Billing_Page() {
		dsl.verifyLocaleIsSelectedByDefaultOnBillingPage(countryDetails.get("Country"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step18_Verify_For_Logged_In_Customers_Cart_Will_Not_Be_Deleted_On_Switching_Within_Countries_In_KateSpade_RoE_Site() {
		dsl.navigateToHomePage();
		dsl.clickOnBagIcon();
		cartQuantity = dsl.noOfItemsInCart();
		dsl.verifyCartWillNotBeDeletedOnSwitchingWithinCountriesInRoESite(cartQuantity, 
				changeShippingDestinationMsg.get("Text Verification"), shippingLabel.get("Text Verification"),
				countryDetails.get("Currency"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step19_Verify_Cart_Will_Be_Deleted_On_Switching_Country_Other_Than_RoE_Sites() {
		cartQuantity = dsl.noOfItemsInCart();
		dsl.clickOnCountrySelectorIcon();
		dsl.clickOnCountry(countryOtherThanRoESite.get("Country"));
		dsl.verifyMessageOfItemRemoveFromShoppingBag(changeShippingDestinationMsg.get("Text Verification"));
		dsl.clickOnContinueButtonFromCountrySwitchDialog();
		dsl.verifyItemsHaveBeenRemovedFromShoppingBagOnSwitchingCountryOtherThanRoESites(cartQuantity, 
				shippingLabel.get("Text Verification"), countryOtherThanRoESite.get("Country"), 
				countryOtherThanRoESite.get("Currency"));
	}

}