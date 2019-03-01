package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_742_Checkout_Continue extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> products;
	String recommendationHeader = "you may also like";
	String recommendationHeaderFr = "vous aimerez aussi";

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
	public void Test_Step02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Recommendations_In_Cart_Page() {
		dsl.verifyTheHeaderOfTheRecommendationHeaderSectionInShopingCartPage(recommendationHeader,
				recommendationHeaderFr);
		dsl.verifyTheMaxLimitOfRecommendedItemsInCartPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_That_After_Clicking_Checkout_Button_In_Shopping_Page_User_Is_Navigated_To_Shipping_Page() {
		dsl.scrollUpToPage();
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Enter_Invalid_Shipping_Details_And_Verify_Error_Msg_Is_Displayed() {
		dsl.verifyErrorsOnTheMandatoryFieldsInShipingPageAfterEnteringInvalidDetails();
	}

}
