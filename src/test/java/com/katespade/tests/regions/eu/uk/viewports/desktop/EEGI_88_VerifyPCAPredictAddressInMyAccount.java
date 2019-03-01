package com.katespade.tests.regions.eu.uk.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_88_VerifyPCAPredictAddressInMyAccount extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> countryDetails;
	Map<String, String> shippingLabel;
	Map<String, String> users;
	Map<String, String> products;
	Map<String, String> countrySelectorPage;
	Map<String, String> shipping;
	Map<String, String> placeholder;
	Map<String, String> errorMsg;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		countryDetails = testData.get("Country And Currency").get(0);
		shippingLabel = testData.get("Page Names").get(3);
		users = testData.get("Users").get(0);
		products = testData.get("Products").get(2);
		countrySelectorPage = testData.get("Page Names").get(4);
		shipping = testData.get("GuestShippingDetails").get(0);
		placeholder = testData.get("Page Names").get(28);
		errorMsg = testData.get("Page Names").get(31);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(homePageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_LogIn_As_Existing_User() {
		dsl.loginAsRegisteredUser(users.get("Username"), users.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Navigate_To_Address_Book_Page_And_Add_Address() {
		dsl.navigateToAddressBookPage();
		dsl.clickToAddAddress();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Fields_Appear_In_Add_Address_Modal_window() {
		dsl.verifyFieldsAreDisplayed();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_New_AddressFind_Field_Underneath_Country_Field() {
		dsl.verifyNewAddressFindFieldByName(placeholder.get("Text Verification"));
		dsl.verifyPositionOfNewAddressFindField();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Address_Fields_Expand_On_Clicking_Enter_Manually() {
		dsl.verifyUserIsAbleToEnterAddressManually();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Address_Fields_Collapse_On_Clicking_Search() {
		dsl.verifyAddressDetailsCollapseWhenSearchClicked();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Enter_Manually_Link_Is_Underneath_New_Find_Address_Field() {
		dsl.verifyPositionOfEnterManuallyLink();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_Enter_Manually_Link_Changes_To_Search_Link_On_Clicking() {
		dsl.verifyChangeOfEnterManuallyToSearchOnClick();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_AutoSuggestion_User_Input_In_Find_Address() {
		dsl.verifySuggestionsAppearWhenAtleast3CharInput();
		dsl.verifyAddressSearchIsNotCaseSensitive(shipping.get("City"));
		dsl.verifyPostCodesSearchByDifferentFormats(shipping.get("Zip"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_Address_Fields_Populate_After_Selecting_From_Suggestion_Drop_Down() {
		dsl.verifyAddressFieldsPopulateAndInUpperCase(shipping.get("City"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step12_Verify_Type_Post_Code_Or_First_Line_Of_Address_Field_Is_Required_Field_When_Address_Fields_Are_Collapsed() {
		dsl.clickCrossBtn();
		dsl.clickToAddAddress();
		dsl.verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(shipping.get("Address"), shipping.get("First Name"), 
				shipping.get("Last Name"), shipping.get("Zip"), shipping.get("Phone Number"));
		dsl.verifyErrorMessageForAddressField(errorMsg.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step13_Verify_Type_Post_Code_Or_First_Line_Of_Address_Field_Is_Not_Required_Field_When_Address_Fields_Are_Expanded() {
		dsl.clickCrossBtn();
		dsl.clickToAddAddress();
		dsl.verifyNewFindAddressFieldIsNotRequiredFieldWhenExpanded(shipping.get("Address"), 
				shipping.get("First Name"), shipping.get("Last Name"), shipping.get("Zip"), 
				shipping.get("Phone Number"),shipping.get("Address"), shipping.get("City"));
	}
	
}
