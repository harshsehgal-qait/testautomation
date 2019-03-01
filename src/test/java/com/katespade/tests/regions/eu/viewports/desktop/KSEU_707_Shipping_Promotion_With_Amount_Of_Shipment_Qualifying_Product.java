package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_707_Shipping_Promotion_With_Amount_Of_Shipment_Qualifying_Product extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> product1;
	Map<String, String> product2;
	Map<String, String> product3;
	Map<String, String> product4;
	String qty = "2";
	String productIndex = "1";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		product1 = testData.get("Products").get(13);
		product2 = testData.get("Products").get(14);
		product3 = testData.get("Products").get(15);
		product4 = testData.get("Products").get(16);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(product1.get("Style Number"));
	}

	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_Promotional_Message_In_Product_PDP() {
		dsl.verifyPromotionalMessageForAProduct(product1.get("Promotional Message"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
		
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Shipping_Promo_Is_Applied() {
		dsl.checkoutFromCartPage();
		dsl.verifyShippingDiscountIsAppliedInShippingPage();
		dsl.clickOnBagIcon();
		dsl.verifyUserIsAbleToRemoveProductFromShoppingCart(productIndex);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_SearchAProductFromLandingPage() {
		dsl.searchProduct(product2.get("Style Number"));
	}

	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Promotional_Message_In_Product_PDP() {
		dsl.verifyPromotionalMessageForAProduct(product2.get("Promotional Message"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
		
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_Shipping_Promo_Is_Applied() {
		dsl.checkoutFromCartPage();
		dsl.verifyShippingDiscountIsAppliedInShippingPage();
		dsl.clickOnBagIcon();
		dsl.verifyUserIsAbleToRemoveProductFromShoppingCart(productIndex);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_SearchAProductFromLandingPage() {
		dsl.searchProduct(product3.get("Style Number"));
	}

	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Promotional_Message_In_Product_PDP() {
		dsl.verifyPromotionalMessageForAProduct(product3.get("Promotional Message"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
		
	@Test(groups = { "desktop", "mobile" })
	public void TestStep13_Verify_Shipping_Promo_Is_Applied() {
		dsl.checkoutFromCartPage();
		dsl.verifyShippingDiscountIsAppliedInShippingPage();
		dsl.clickOnBagIcon();
		dsl.verifyUserIsAbleToRemoveProductFromShoppingCart(productIndex);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep14_SearchAProductFromLandingPage() {
		dsl.searchProduct(product4.get("Style Number"));
	}

	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep15_Verify_Promotional_Message_In_Product_PDP() {
		dsl.verifyPromotionalMessageForAProduct(product4.get("Promotional Message"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep16_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
		
	@Test(groups = { "desktop", "mobile" })
	public void TestStep17_Verify_Shipping_Promo_Is_Applied() {
		dsl.checkoutFromCartPage();
		dsl.verifyShippingDiscountIsAppliedInShippingPage();
		dsl.clickOnBagIcon();
		dsl.verifyUserIsAbleToRemoveProductFromShoppingCart(productIndex);
	}
	
}
