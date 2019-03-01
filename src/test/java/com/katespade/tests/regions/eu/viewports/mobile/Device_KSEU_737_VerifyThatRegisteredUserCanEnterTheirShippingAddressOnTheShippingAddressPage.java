package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_737_VerifyThatRegisteredUserCanEnterTheirShippingAddressOnTheShippingAddressPage
		extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;
	String zip;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(0);
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

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyUserIsAbleToSelectAnAddressFromDropdown() {
		dsl.verifyUserIsAbleToSelectAnAddressFromDropdown();
		dsl.verifyAfterSelectingAddressFRomDropdownShippingFieldsAeAutoPopulatedFromAddressSelected();
		dsl.selectTheAddressDropdownAndPickanotherAddress();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_ClearTheShippingDetailDataAndEnterNewDataManually() {
		zip = dsl.enterTheShippingDetailDataAndEnterNewDataManually(users.get("Username"), shipping.get("First Name"),
				shipping.get("Last Name"), shipping.get("Address"), shipping.get("Zip"), shipping.get("Phone Number"));
		dsl.selectTheCheckBoxAddToAddressBookAndUseThisBillingAddress();
		dsl.clickContinuePaymentButton();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyShippingAddressIsReflectedInBillingAddress() {
		dsl.verifyShippingAddressIsReflectedInBillingAddress(zip);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifyAddressAddedFromShippingPageIsAddedInAddressBook() {
		dsl.mobile_verifyAddressAddedFromShippingPageIsAddedInAddressBook(zip);
	}
}
