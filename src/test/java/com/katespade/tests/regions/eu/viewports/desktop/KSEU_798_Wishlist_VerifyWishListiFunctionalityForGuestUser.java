/*
 - KSEU-798 :Wishlist - Login - Included Elements
 - 
 - 
  * */
package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

/*KSEU-798- Wishlist - Login - Included Elements
 * 
 * */
public class KSEU_798_Wishlist_VerifyWishListiFunctionalityForGuestUser extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> pageName;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyWishlistAccessFromHeader() {
		dsl.verifyWishlistCanBeAccessdedFromHeader();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_CreateAccountIfUserIsNotRegistered() {
		dsl.createAnAccountOnWishlistPageIfUserIsnotRegisterd();
		
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyUserInWishlistPage() {
		dsl.verifyUserLandsOnWishlistPage();
	}
	
	
	
}
