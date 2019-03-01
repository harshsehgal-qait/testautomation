package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class KSEU_737_VerifyThatRregisteredUserCanEnterTheirShippingAddressOnTheShippingAddressPage
		extends KateSpadeTest {

	String zip;
	Map<String, String> homePageURL;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> shipping;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Registered_User_Is_Able_To_Login() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Search_A_Product_From_Landing_Page() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Select_The_First_Product() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_User_Is_Able_To_Select_An_Address_From_Dropdown() {
		dsl.verifyUserIsAbleToSelectAnAddressFromDropdown();
		dsl.verifyAfterSelectingAddressFRomDropdownShippingFieldsAeAutoPopulatedFromAddressSelected();
		dsl.selectTheAddressDropdownAndPickanotherAddress();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Clear_The_Shipping_Detail_Data_And_Enter_New_Data_Manually() {
		zip = dsl.enterTheShippingDetailDataAndEnterNewDataManually(users.get("Username"), shipping.get("First Name"),
				shipping.get("Last Name"), shipping.get("Address"), shipping.get("Zip"), shipping.get("Phone Number"));
		dsl.selectTheCheckBoxAddToAddressBookAndUseThisBillingAddress();
		dsl.clickContinuePaymentButton();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_Shipping_Address_Is_Reflected_In_Billing_Address() {
		dsl.verifyShippingAddressIsReflectedInBillingAddress(zip);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_Address_Added_From_Shipping_Page_Is_Added_In_Address_Book() {
		dsl.verifyAddressAddedFromShippingPageIsAddedInAddressBook(zip);
	}

}
