package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_640_641_644_VerifyStoreLocatorFunctionality extends KateSpadeTest {

	Map<String, String> homePageURL;
	Map<String, String> guestShippingDetails;
	Map<String, String> HalfUkZip;
	Map<String, String> refineResults;
	Map<String, String> noStoresHeader;
	String retail = "retail";
	String outlet = "outlet";
	String stockist = "stockist";
	String TextInPlaceholder = "Enter Address, City, Post Code or country";
	String TextInPlaceholderFr = "Entrez une adresse, une ville, un code postal ou un pays";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		guestShippingDetails = testData.get("GuestShippingDetails").get(0);
		refineResults = testData.get("Page Names").get(6);
		noStoresHeader = testData.get("Page Names").get(20);
		HalfUkZip = testData.get("GuestShippingDetails").get(0);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_Store_Locator_Icon_HomePage() {
		dsl.verifyStoreLocatorIconInHomepage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_User_Is_Able_To_Navigate_Store_Locator_Page() {
		dsl.navigateToStoreLocatorPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Find_Store_Near_You_Header_Is_H1_Label() {
		dsl.verifyFindStoreNearYouHeaderIsH1();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Details_In_Store_Locator_Page() {
		dsl.verifyDetailsOfStoreLocatorPage(TextInPlaceholder, TextInPlaceholderFr);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_User_Is_Able_To_Search_Store_Using_ZipCode_And_Verify_Details_Of_The_Page() {
		dsl.verifyUserIsAbleToSearchStore(guestShippingDetails.get("Zip"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Details_Of_The_Stores_On_The_Map_After_Search_Button_Is_Clicked() {
		dsl.verifyStoreDetailsAndStoreDirections(noStoresHeader.get("Page Name"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_User_Is_Able_To_Search_Store_Using_Half_UK_ZipCode_And_Verify_Details_Of_The_Page() {
		dsl.navigateToStoreLocatorPage();
		dsl.verifyUserIsAbleToSearchStoreUsingHalfUKZipCodeAndVerifyDetailsOfThePage(HalfUkZip.get("Zip"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_User_Is_Able_To_Search_Store_Using_Address_And_Verify_Details_Of_The_Page() {
		dsl.navigateToStoreLocatorPage();
		dsl.verifyUserIsAbleToSearchStore(guestShippingDetails.get("Address"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_User_Is_Able_To_Navigate_To_First_Store() {
		dsl.verifyTheAddressDetailsOfStoreOnStoreDetailPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_User_Is_Able_To_Search_Store_Using_Country_And_Verify_Details_Of_The_Page() {
		dsl.navigateToStoreLocatorPage();
		dsl.verifyUserIsAbleToSearchStore(guestShippingDetails.get("Country"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step12_Verify_User_Is_Able_To_Search_Store_Using_City_And_Verify_Details_Of_The_Page() {
		dsl.verifyUserIsAbleToSearchStore(guestShippingDetails.get("City"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step13_Verify_Number_Of_Search_Results_Header_Is_H2() {
		dsl.verifyNumberOfSearchResultsHeaderIsH2();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step14_Verify_Customers_Can_Further_Refine_Store_Search_Results_List() {
		dsl.verifyCustomersCanFurtherRefineStoreSearchResultsList(refineResults.get("Text Verification"), retail,
				outlet, stockist);
	}
	
	/* Pending
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step15_Verify_Three_Stores_Results_Displayed_Per_Row_When_Searched() {
		dsl.verifyThreeStoresResultsWillBeDisplayedPerRow();
	}*/

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step15_Verify_Details_When_No_Stores_Are_Found() {
		dsl.verifyDetailsWhenNoStoresAreFound(guestShippingDetails.get("Phone Number"),
				noStoresHeader.get("Text Verification"));
	}

}
