//Covered 723 step 4
package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_720_723_724_727_ShoppingBag_Checkout_Flow extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> products;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
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
	public void Test_Step04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_User_Is_Navigated_To_Bag_Page_And_Verify_Global_Banner_Slot_Is_Present() {
		dsl.verifyGlobalBannerSlotIsPresentOnBagPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_The_Pricing_Structure_Of_Order_Total_In_Shopping_Page() {
		dsl.verifyThePricingStructureOfOrderTotalInShoppingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_That_Customer_Service_Telephone_Number_Is_Displayed() {
		dsl.verifyThatCustomerServiceTelephoneNumberIsDisplayed();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Information_On_The_Order_Summary_Box() {
		dsl.verifyTheInformationOnOrderSummaryBox();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_The_Sticky_Feature_Of_Summary_Box() {
		dsl.verifyTheStickyNatureOfOrderSummaryBoxOnCartPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

}
