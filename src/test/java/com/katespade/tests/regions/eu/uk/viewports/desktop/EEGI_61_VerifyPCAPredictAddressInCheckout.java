package com.katespade.tests.regions.eu.uk.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class EEGI_61_VerifyPCAPredictAddressInCheckout extends KateSpadeTest {
	
	Map<String, String> homePageURL;
	Map<String, String> users;
	Map<String, String> shipping;
	Map<String, String> searchProduct;
	Map<String, String> placeholder;
	Map<String, String> creditCard;
	Map<String, String> errorMsg;
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homePageURL = testData.get("BASE URL").get(0);
		users = testData.get("Users").get(3);
		shipping = testData.get("GuestShippingDetails").get(0);
		searchProduct = testData.get("Products").get(4);
		placeholder = testData.get("Page Names").get(28);
		creditCard = testData.get("CardDetails").get(2);
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
	public void Test_Step03_Navigate_To_Checkout_Page(){
		dsl.searchProduct(searchProduct.get("Style Number"));
		dsl.selectFirstProduct();
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Proceed_To_Shipping_Page() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Required_Fields_Appear_On_Shipng_Page() {
		dsl.verifyFieldsAppearOnShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_New_AddressFind_Field_Underneath_Country_Field_On_Shipping_Page() {
		dsl.verifyNewAddressFindFieldByName(placeholder.get("Text Verification"));
		dsl.verifyPositionOfNewAddressFindFieldOnShipngPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step07_Verify_Enter_Manually_Link_Changes_To_Search_Link_On_Clicking_On_Shipping_Page() {
		dsl.verifyChangeOfEnterManuallyToSearchOnClick();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step08_Verify_Address_Fields_Collapse_On_Clicking_Search_On_Shipping_Page() {
		dsl.verifyAddressDetailsCollapseWhenSearchClicked();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step09_Verify_Enter_Manually_Link_Is_Underneath_New_Find_Address_Field_Shipping_Page() {
		dsl.verifyPositionOfEnterManuallyLink();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step10_Verify_Type_Post_Code_Or_First_Line_Of_Address_Field_Is_Required_Field_When_Address_Fields_Are_Collapsed() {
		dsl.verifyNewFindAddressFieldIsRequiredFieldWhenCollapsedOnShipngPage(shipping.get("First Name"), 
				shipping.get("Last Name"),shipping.get("Phone Number"));
		dsl.verifyErrorMessageForAddressField(errorMsg.get("Text Verification"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step11_Verify_AutoSuggestion_User_Input_In_Find_Address_On_Shipping_Page() {
		dsl.verifySuggestionsAppearWhenAtleast3CharInput();
		dsl.verifyAddressSearchIsNotCaseSensitive(shipping.get("City"));
		dsl.verifyPostCodesSearchByDifferentFormats(shipping.get("Zip"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step12_Verify_Address_Fields_Populate_After_Selecting_From_Suggestion_Drop_Down() {
		dsl.verifyAddressFieldsPopulateAndInUpperCaseOnShipngPage(shipping.get("City"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step13_Verify_Type_Post_Code_Or_First_Line_Of_Address_Field_Is_Not_Required_Field_When_Address_Fields_Are_Expanded() {
		dsl.verifyNewFindAddressFieldIsNotRequiredFieldWhenExpandedOnShipngPage(users.get("Username"), 
				shipping.get("First Name"), shipping.get("Last Name"), shipping.get("Zip"), 
				shipping.get("Phone Number"),shipping.get("Address"), shipping.get("City"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step14_Navigate_To_Billing_Page() {
		dsl.verifyStep2BillingIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step15_Verify_Required_Fields_Appear_On_Billing_Page() {
		dsl.verifyFieldsAppearOnBillingPage();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step16_Verify_New_AddressFind_Field_Underneath_Country_Field_On_Billing_Page() {
		dsl.verifyNewAddressFindFieldByName(placeholder.get("Text Verification"));
		dsl.verifyPositionOfNewAddressFindFieldOnBillingPage();
	}
	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step17_Verify_Enter_Manually_Link_Changes_To_Search_Link_On_Clicking_On_Billing_Page() {
//		dsl.verifyChangeOfEnterManuallyToSearchOnClick();
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step18_Verify_Address_Fields_Collapse_On_Clicking_Search_On_Billing_Page() {
//		dsl.verifyAddressDetailsCollapseWhenSearchClicked();
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step19_Verify_Enter_Manually_Link_Is_Underneath_New_Find_Address_Field_On_Billing_Page() {
//		dsl.verifyPositionOfEnterManuallyLink();
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step20_Verify_AutoSuggestion_User_Input_In_Find_Address_On_Billing_Page() {
//		dsl.verifySuggestionsAppearWhenAtleast3CharInput();
//		dsl.verifyAddressSearchIsNotCaseSensitive(shipping.get("City"));
//		dsl.verifyPostCodesSearchByDifferentFormats(shipping.get("Zip"));
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step21_Billing_Page_Details() {
//		dsl.makePaymentFromMasterCard(creditCard.get("Number"), creditCard.get("Month/Year"), 
//				creditCard.get("Type"), creditCard.get("CVV"));                           
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step22_Verify_Type_Post_Code_Or_First_Line_Of_Address_Field_Is_Required_Field_When_Address_Fields_Are_Collapsed_On_Billing_Page() {
//		dsl.verifyNewFindAddressFieldIsRequiredFieldWhenCollapsedOnBillingPage(shipping.get("First Name"), shipping.get("Last Name"), shipping.get("Phone Number"));
//		//dsl.verifyErrorMessageForAddressField(errorMsg.get("Text Verification"));
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step23_Verify_Address_Fields_Populate_After_Selecting_From_Suggestion_Drop_Down_On_Billing_Page() {
//		dsl.verifyAddressFieldsPopulateAndInUpperCaseOnBillingPage(shipping.get("City"));
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void Test_Step24_Verify_Type_Post_Code_Or_First_Line_Of_Address_Field_Is_Not_Required_Field_When_Address_Fields_Are_Expanded_On_Billing_Page() {
//		dsl.enterBillingAddressDetails(shipping.get("First Name"), shipping.get("Last Name"), shipping.get("Address"),
//				shipping.get("Zip"), shipping.get("Phone Number"), shipping.get("City"));
//		dsl.verifyStep3OrderReviewIsActive();
//	}
//	
}
