package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_683_Verify_Registered_User_Is_Able_To_Add_A_Product_To_Their_Bag extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(0);
		users = testData.get("Users").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_LoginIntoKateSpade() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_SelectTheFirstProduct() {
//		dsl.selectFirstProduct();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Green_Color_Of_The_Button_After_Selecting_Attributes() {
//		dsl.verifyTheGreenColorOfTheButtonAfterSelectingAttribute();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_User_Is_Able_To_Add_Product_To_MiniCart() {
		dsl.verifyUserIsAbleToAddProductToMiniCart();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Selected_Product_Is_Visible_In_Bag() {
		dsl.verifyDetailsOfProductsPresentInMiniCartPage();
		dsl.checkoutFromMiniCartWindow();
		dsl.verifyUserIsAbleToRemoveProductFromShoppingCart("1");
	}
}
