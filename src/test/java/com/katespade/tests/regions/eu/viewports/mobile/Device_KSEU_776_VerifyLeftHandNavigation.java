package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_776_VerifyLeftHandNavigation extends KateSpadeTest{
	
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> products;
	Map<String, String> pageName;
	Map<String, String> myInfoText;
	Map<String, String> addText;
	Map<String, String> cardsText;
	Map<String, String> orderText;
	Map<String, String> wishlistText;
	int Position = 1;
	String myAccount = "account";
	String myInfo = "profile";
	String addBook = "addressbook";
	String cards = "wallet";
	String orderHistory = "orders";
	String wishlist = "wishlist";
	String secureShopping = "secure-shopping-guarantee";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		users = testData.get("Users").get(0);
		pageName = testData.get("Page Names").get(0);
		myInfoText = testData.get("Page Names").get(12);
		addText = testData.get("Page Names").get(13);
		cardsText = testData.get("Page Names").get(14);
		orderText = testData.get("Page Names").get(15);
		wishlistText = testData.get("Page Names").get(16);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyRegisteredUserIsAbleToLoginIntoKateSpade() throws InterruptedException {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyUserLandsOnProfilePageOnClickingMyInfo() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, myInfo);
		Position++;
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserLandsOnAddressBookPageOnClickingMyAddBook() {	
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, addBook);
		Position++;
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyUserLandsOnCardDetailsPageOnClickingCards() {	
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, cards);
		Position++;
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyUserLandsOnOrderHistoryPageOnClickingOrderHistory() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, orderHistory);
		Position++;
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyUserLandsOnWishlistPageOnClickingWishlist() {
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, wishlist);
		Position++;
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyUserLandsOnSecureShoppingPageOnClickingSecureShopping() {	
		dsl.expandMyAccountOptionsInMobile();
		dsl.mobile_verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, secureShopping);
		Position++;
	}
}
