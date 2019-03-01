package com.katespade.tests.regions.eu.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KSEU_795_VerifyOrderHistoryforRegisteredUser extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> users;
	Map<String, String> orderNo;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(2);
		orderNo = testData.get("Order Details").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyRegisteredUserIsAbleToLoginIntoKateSpade() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyandClickOnOrderHistory() {
		dsl.verifyandClickOnOrderHistory();
		dsl.verfifyUserlandsOnOrderHistoryPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyOrderNumberInOrderHistory() {
//		dsl.verifyOrderNumberInOrderHistory(orderNo.get("Order No"));
		dsl.verifyAllPreviousOrdersPlacedAreDisplayedAndLimitOfOrderInPOrderHistoryPage();
	}

}