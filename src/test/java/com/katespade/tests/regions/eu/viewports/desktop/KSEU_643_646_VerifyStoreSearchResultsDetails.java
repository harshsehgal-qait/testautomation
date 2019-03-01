package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_643_646_VerifyStoreSearchResultsDetails extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> guestShippingDetails;
	Map<String, String> pageName;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		guestShippingDetails = testData.get("GuestShippingDetails").get(3);
		pageName = testData.get("Page Names").get(20);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_Verify_Store_Locator_Icon_HomePage() {
		dsl.verifyStoreLocatorIconInHomepage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_Verify_User_Is_Able_To_Navigate_Store_Locator_Page() {
		dsl.navigateToStoreLocatorPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Verify_User_Is_Able_To_Search_Store_Using_ZipCode_And_Verify_Details_Of_The_Page() {
		dsl.verifyUserIsAbleToSearchStore(guestShippingDetails.get("Zip"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_Srore_Name_On_Store_Detail_Page_Is_Hyperlink() {
		dsl.verifyStoreNameOnSearchPageIsHyperlinkandNavigatesUserToStoreDetailPage(pageName.get("Page Name"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Other_Details_On_Store_Results_Page() {
		dsl.verifyOtherDetailsOnStoreDetailPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_User_Goes_Back_To_Search_Page() {
		dsl.userGoesBackToSearchPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_The_Direction_Details_Link_Of_First_Store_Searched() {
		dsl.verifyTheDirectionDetailsLinkOfFirstStoreSearched();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_The_Store_Details_Link_Of_First_Store_Searched() {
		dsl.verifyTheStoreDetailsLinkOfFirstStoreSearched();
	}

}
