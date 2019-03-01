//Covered 723 step 4
package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_720_723_724_727_ShoppingBag_Checkout_Flow extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(2);
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
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_User_Is_Navigated_To_Bag_Page_And_Verify_Global_Banner_Slot_Is_Present() {
		dsl.verifyGlobalBannerSlotIsPresentOnBagPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_The_Pricing_Structure_Of_Order_Total_InShopping_Page() {
		dsl.verifyThePricingStructureOfOrderTotalInShoppingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_That_Customer_Service_Telephone_Number_Is_Displayed() {
		dsl.verifyThatCustomerServiceTelephoneNumberIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Information_On_The_Order_Summary_Box() {
		dsl.verifyTheInformationOnOrderSummaryBox();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_The_Sticky_Feature_Of_Summary_Box() {
		dsl.verifyTheStickyNatureOfOrderSummaryBoxOnCartPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

}
