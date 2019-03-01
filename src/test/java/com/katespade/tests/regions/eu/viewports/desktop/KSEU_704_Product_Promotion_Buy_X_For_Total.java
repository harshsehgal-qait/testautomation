package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_704_Product_Promotion_Buy_X_For_Total extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> products;
	String qty = "2";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(8);
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
	public void TestStep05_Update_the_Quantity_Of_The_First_product_In_The_Bag() {
		dsl.updateTheQuantityOfFirstProductInShoppingCartPage(qty);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Promo_Msg_And_Product_Price_After_Valid_Promo_Is_Applied() {
		dsl.verifyPromotionalMessageAndItemPriceAfterPromotionIsApplied();
	}
}
