package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class Device_KSEU_788_789_Verify_Guest_User_Can_Check_The_Status_Of_An_Order_And_Order_History_Details extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> orderDetails;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		orderDetails =  testData.get("Order Details").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}
	

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_Navigate_To_Check_Your_Order_Page_In_Mobile() {
		dsl.navigateToCheckYourOrderPageInMobile();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_Order_History_Page() {
		dsl.verifyOrderHistoryPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_Error_Msg_Displayed_For_Wrong_Order_Info() {
		dsl.verifyErrorMsgDisplayedForWrongorderInformation();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Check_Status_Of_Your_Order() {
		dsl.checkStatusOfYourOrder(orderDetails.get("Order No"), orderDetails.get("Order Email"), orderDetails.get("Order Postal Code"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Order_History_Details() throws InterruptedException {
		dsl.verifyDetailsInOrderDetailsPage();
		dsl.verifyContentAssetAccNavTextAndItsLeftNavigation();
		dsl.verifyOptionLinkingInContentAssetLeftNavigationForGuestUser();
		dsl.verifyLeftHandHelpText();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_On_Clicking_Return_To_Shopping_Link_User_Is_Navigated_To_Homepage() {
		dsl.clickOnReturnToShoppingLinkInOrderDetailsPage();
	}
}
