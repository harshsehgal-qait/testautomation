package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_791_792_793_796_Verify_That_Registered_User_Is_Able_To_See_Orders_In_Order_History_Page extends KateSpadeTest {

	/*
	 * Tracking number is commented out due to the bug
	 * Remove comment after the bug fix
	 * 
	 */
	
	Map<String, String> url;
	Map<String, String> users;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(2);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyRegisteredUserIsAbleToLoginIntoKateSpade() {
		dsl.openHamburgerMenu();
		dsl.mobile_loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Navigate_To_Order_History_Page() {
		dsl.verifyandClickOnOrderHistory();
		dsl.verifyUserIsNavigatedToOrderHistoryPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_All_Previous_Orders_Are_Dispalyed_And_Its_Limit_Per_Page() {
		dsl.verifyAllPreviousOrdersPlacedAreDisplayedAndLimitOfOrderInPOrderHistoryPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Logged_In_User_Can_Access_Order_History_From_Footer() {
		dsl.clickOrderHistoryLinkOnFooter();
		dsl.verifyUserIsNavigatedToOrderHistoryPage();
		dsl.mobile_logoutFromApplication();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Login_To_Application_From_Guest_Order_Check_Page() {
		dsl.clickCheckYourOrderButton();
		dsl.verifyUserIsNavigatedToCheckYourOrderPage();
		dsl.loginIntoAccountFromGuestOrderCheckPage(users.get("Username"), users.get("Password"));

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Navigate_To_Order_History_Page() {
		dsl.navigateToOrderHistoryPage();
		dsl.verifyUserIsNavigatedToOrderHistoryPage();
		dsl.verifyElementsInOrderHistoryPage();
		dsl.verifyThePricingStructureOfProductInOrderHistoryPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_On_Clicking_Order_Detail_Link_User_Navigated_To_Order_Details_Page() {
		dsl.clickFirstLinkOfOrderDetails();
		dsl.verifyUserIsNavigatedToOrderDetailsPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_Elements_In_Order_Details_Page() throws InterruptedException {
		dsl.verifyDetailsInOrderDetailsPage();
		dsl.verifyContentAssetAccNavTextAndItsLeftNavigation();
		dsl.verifyLeftHandHelpText();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_User_Is_Navigated_To_Order_History_Page_On_Clicking_Back_To_Order_History_Link() {
		dsl.clickOnBackToOrderHistoryLinkInOrderDetailsPage();
		dsl.verifyUserIsNavigatedToOrderHistoryPage();
	}
	
}
