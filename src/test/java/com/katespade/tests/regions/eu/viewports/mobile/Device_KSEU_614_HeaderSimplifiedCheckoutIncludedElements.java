package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_614_HeaderSimplifiedCheckoutIncludedElements extends KateSpadeTest {
	
	Map<String, String> homepageURL;
	Map<String, String> products;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
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
	public void Test_Step04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Mini_Cart_Window_Bag_Icon_Functionality() {
		dsl.verifyMiniCartInMobile();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Wishlist_Icon_Is_Not_Displayed_In_Simplified_Header() {
		dsl.verifyWishlistIconIsNotDisplayedInSimplifiedHeaderAtShippingPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_KS_Logo_In_Simplified_Header_In_Chkout_Flow_And_Its_Linking_To_HomePage() {
		dsl.verifySimplifiedHeaderKSLogoAndItsLinkingToHomepage(homepageURL.get("URL"));
	}

}
