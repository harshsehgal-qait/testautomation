package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KSEU_625_664_665VerificationOFShopGridQuickBuyCheckout extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> products;
	Map<String, String> shipping;
	Map<String, String> users;
	Map<String, String> creditCards;
	String categoryIndex = "6";
	String subCategoryWithNoSubOption = "watches";
	String subCategoryWithNoSubOptionFr = "montres";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		shipping = testData.get("GuestShippingDetails").get(0);
		users = testData.get("Users").get(0);
		creditCards = testData.get("CardDetails").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Click_On_The_Category_From_Header() {
		dsl.clickOnTheSubCategoryLinkOnTheHeader(categoryIndex, subCategoryWithNoSubOption,
				subCategoryWithNoSubOptionFr);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Quick_Buy_Landing_Page() {
		dsl.verifyQuickBuyPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Add_Product_In_MiniCart_From_Quick_Buy_Page() {
		dsl.clickOnQuickByAddToBag();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Product_Is_Added_Into_MiniCart_Window() {
		dsl.verifyMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_User_Checkout_From_MiniCart() {
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Shipping_Page_And_Navigate_To_Shipping_Tab() {
		dsl.verifyShoppingCartPage();
		dsl.verifyProductDetailsOnShoppingCartPage();
		dsl.checkoutFromCartPage();
	}
	
}