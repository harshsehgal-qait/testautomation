package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_680_PDP_Verify_UserIs_Able_To_Add_Item_To_Wishlist extends KateSpadeTest{

	Map<String, String> homePageURL;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> products1;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
		products1 = testData.get("Products").get(10);
		users = testData.get("Users").get(2);
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
	public void Test_Step04_Verify_User_Is_Needed_To_Choose_Attributes_Before_Adding_To_Wishlist() {
		dsl.verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_User_Is_Able_To_Add_Product_To_Wishlist_When_Logged_In() {
		dsl.verifyUserIsAbleToAddProductToWishlistWhenLoggedIn(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_User_Can_Add_Product_To_Wishlist_Directly_When_Already_Logged_In() {
		dsl.verifyUserIsAbleToAddProductToWishlistDirectlyWhenAlreadyLoggedIn(products1.get("Style Number"));
	}
	
}
