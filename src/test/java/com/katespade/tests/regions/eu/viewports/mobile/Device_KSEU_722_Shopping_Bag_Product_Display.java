package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_722_Shopping_Bag_Product_Display extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> product1;
	Map<String, String> product2;
	Map<String, String> product3;
	String prodIndex = "0";
	String SelectQunt = "3";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		product1 = testData.get("Products").get(0);
		product2 = testData.get("Products").get(1);
		product3 = testData.get("Products").get(2);
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
	public void TestStep03_SelectTheFirstProduct() {
//		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.closeMiniCartWindowIniPhone();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_SearchAProductFromLandingPage() {
		dsl.searchProduct(product2.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.closeMiniCartWindowIniPhone();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_SearchAProductFromLandingPage() {
		dsl.searchProduct(product3.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Checkout_From_Mini_Cart_Window() {
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Edit_A_Product_In_Shooping_Page() {
		dsl.verifyUserIsAbleToEditAProduct(prodIndex, SelectQunt);
	}
}
