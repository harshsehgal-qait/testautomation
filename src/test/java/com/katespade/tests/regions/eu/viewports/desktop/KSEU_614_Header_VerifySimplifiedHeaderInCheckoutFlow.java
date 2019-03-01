package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_614_Header_VerifySimplifiedHeaderInCheckoutFlow extends KateSpadeTest{

	Map<String, String> homePageURL;
	Map<String, String> products;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Name"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Mini_Cart_Window_Bag_Icon_Functionality()  {
		dsl.verifyMiniCartWindow();
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
	public void Test_Step07_Verify_Wishlist_Icon_Is_Not_Displayed_In_Simplified_Header(){
		dsl.verifyWishlistIconIsNotDisplayedInSimplifiedHeaderAtShippingPage();
	} 
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_KS_Logo_In_Simplified_Header_In_Chkout_Flow_And_Its_Linking_To_HomePage() {
		dsl.verifySimplifiedHeaderKSLogoAndItsLinkingToHomepage(homePageURL.get("URL"));
	}

}
