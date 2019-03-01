package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_776_VerifyLeftHandNavigation extends KateSpadeTest{

	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> products;
	Map<String, String> pageName;
	Map<String, String> myInfoText;
	Map<String, String> addText;
	Map<String, String> cardsText;
	Map<String, String> orderText;
	Map<String, String> wishlistText;
	int Position = 0;
	String myAccount = "account";
	String myInfo = "profile";
	String addBook = "address";
	String cards = "wallet";
	String orderHistory = "orders";
	String wishlist = "wishlist";
	String secureShopping = "secure-shopping-guarantee";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
		users = testData.get("Users").get(2);
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
		dsl.clickOnSignInLink();
		dsl.loginWithRegisteredUser(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifySubTextOnMyAccount() {
		dsl.verifySubtext(myInfoText.get("Text Verification"),0);
		dsl.verifySubtext(addText.get("Text Verification"),1);
		dsl.verifySubtext(cardsText.get("Text Verification"),2);
		dsl.verifySubtext(orderText.get("Text Verification"),3);
		dsl.verifySubtext(wishlistText.get("Text Verification"),4);	

	}
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserLandsOnAccountPageOnClickingMyAccount() {
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, myAccount);
		Position++;

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyUserLandsOnProfilePageOnClickingMyInfo() {	
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, myInfo);
		Position++;
		}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_VerifyUserLandsOnAddressBookPageOnClickingMyAddBook() {		
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, addBook);
		Position++;
	}
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyUserLandsOnCardDetailsPageOnClickingCards() {	
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, cards);
		Position++;
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifyUserLandsOnOrderHistoryPageOnClickingOrderHistory() {
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, orderHistory);
		Position++;
	}
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_VerifyUserLandsOnWishlistPageOnClickingWishlist() {
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, wishlist);
		Position++;
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_VerifyUserLandsOnSecureShoppingPageOnClickingSecureShopping() {		
		dsl.verifyUserClicksOnSideNavAndLandsOnCorrectPage(Position, secureShopping);
		Position++;
	}
	
}
