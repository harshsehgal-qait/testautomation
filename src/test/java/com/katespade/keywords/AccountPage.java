package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.PropFileHandler;

public class AccountPage extends GetPage {

	WebDriver driver;

	public AccountPage(WebDriver driver) {
		super(driver, "Katespade/AccountPage");
		this.driver = driver;
	}

	public void loginWithRegisteredUser(String userName, String password) {
		wait.waitForLoad();
		scrollUpToPage();

		isElementDisplayed("textbox_login_emailAddress");
		sendText(element("textbox_login_emailAddress"), userName);
		msg.log("'" + userName + "' is entered");

		isElementDisplayed("textbox_login_password");
		sendText(element("textbox_login_password"), password);
		msg.log("'" + password + "' is entered");

		isElementDisplayed("button_signIn_new");
		clickUsingJS(element("button_signIn_new"));
		msg.log("Clicked on 'SIGN IN' button !!!");
	}

	public void verifyUserIsOnSingInPage() {
		wait.waitForLoad();
		isElementDisplayed("lbl_signInHeader");
		isElementDisplayed("username_field");
		isElementDisplayed("password_field");
		msg.log(element("lbl_signInHeader").getText());
		isElementDisplayed("link_forgotPas");
		isElementDisplayed("lbl_rememberMe");
		msg.log("verified user is on singIn page");
	}

	public void veriyUserIsOnRegisterAccountPageOnMobile() {
		wait.waitForLoad();
		isElementDisplayed("lbl_createAccount");
		msg.log(element("lbl_createAccount").getText());
		isElementDisplayed("select_myAccount");
		element("select_myAccount").click();
		isElementDisplayed("lbl_accountSetting");
		msg.log(element("lbl_accountSetting").getText());
		isElementDisplayed("link_shopConfiedently");
		msg.log(element("link_shopConfiedently").getText());
		isElementDisplayed("lbl_doNotRecieveEmail");
		msg.log(element("lbl_doNotRecieveEmail").getText());
		isElementDisplayed("textbox_FirstName");
		isElementDisplayed("textbox_LastName");
		isElementDisplayed("textbox_eMailAddress");
		isElementDisplayed("textbox_confirmEmail");
		isElementDisplayed("textbox_password");
		isElementDisplayed("textbox_confirmPassword");
		isElementDisplayed("textbox_LastName");
		isElementDisplayed("textbox_LastName");
		isElementDisplayed("button_apply");
	}

	public void mobile_loginWithRegisteredUser(String userName, String password) {
		wait.waitForLoad();
		hardWait(2);
		isElementDisplayed("link_signin");
		element("link_signin").click();
		hardWait(3);
		msg.log("User clicks on signin button");
		isElementDisplayed("lbl_signInHeader");
		wait.waitForElementToBeVisible(element("username_field"));
		isElementDisplayed("username_field");
		sendText(element("username_field"), userName);
		isElementDisplayed("password_field");
		sendText(element("password_field"), password);
		clickUsingJS(element("button_login"));
		msg.log("User logins into the application");
	}

	public void verifyAccountMenuTabOptions() {
		wait.waitForLoad();
		isElementDisplayed("link_addressBook");
		isElementDisplayed("link_creditCard");
		isElementDisplayed("link_wishlist");
		isElementDisplayed("link_orderHistory");
		logMessage("Account menu links are verified");
	}

	public void verifyAccountPageHolderName() {
		isElementDisplayed("accountHolderName");
		msg.log(element("accountHolderName").getText().trim() + " is displayed");
		isElementDisplayed("title_logout");
		msg.log(element("title_logout").getText() + " is displayed");
	}

	public void verifyAccountPageNames() {
		List<WebElement> allSuggestions = elements("accountPageName");
		for (WebElement pageName : allSuggestions) {
			msg.log(pageName.getText() + " page name is displayed");
		}
	}

	public void clickOnPersonalData(String personalAccount) {
		element("link_personalData", personalAccount).click();
		msg.log("User clicks on: " + personalAccount);
		wait.waitForLoad();
		isElementDisplayed("heading_peronalData");
		msg.log(element("heading_peronalData").getText() + " is displayed");
		String url = getCurrentURL();
		assertEquals(url.contains("PersonalData"), true, "user is not on Personal Data Page");
		msg.log("User is on Personal Data page");
	}

	public void verifyPersonalDataPage() {
		isElementDisplayed("pd_name_email");
		msg.log(element("pd_name_email").getText() + " is displayed");
		isElementDisplayed("label_name");
		msg.log(element("label_name").getText() + " is displayed");
		isElementDisplayed("user_name");
		String name = executeJavascriptReturnsString("return $('.read-only.name').text()");
		msg.log(name);
		isElementDisplayed("email_id");
		String email = executeJavascriptReturnsString("return $('.read-only.email').text()");
		msg.log(email);
		isElementDisplayed("edit_info");
		element("edit_info").click();
		msg.log("User clicks on");
	}

	public void logoutFromTheApplicationFromAccountPage() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			isElementDisplayed("lbl_username");
			isElementDisplayed("link_logout");
			element("link_logout").click();
			msg.log("User LogOuts form application");
		} else {
			isElementDisplayed("link_logout");
			element("link_logout").click();
			msg.log("User LogOuts form application");
		}
	}

	public void verifyEditInfoTab(String password) {
		isElementDisplayed("changename_header");
		List<WebElement> allSuggestions = elements("requiredCustomerDetaials");
		for (WebElement details : allSuggestions) {
			msg.log(details.getText() + " detail is displayed");
		}
		isElementDisplayed("label_firstName");
		element("label_firstName").clear();
		msg.log("User clears the first name field");
		long Random = System.currentTimeMillis();
		String firstName = "A" + Random;
		PropFileHandler.writeProperty("firstName", firstName);
		sendText(element("textbox_FirstName"), firstName);
		msg.log(firstName + " is entered");
		msg.log("User edits the name");
		isElementDisplayed("textbox_password");
		sendText(element("textbox_password"), password);
		isElementDisplayed("button_applyNow");
		element("button_applyNow").click();
		msg.log("User clicks on apply now buton");
	}

	public void clickAndVerifyOnAddressBook(String addressBook) {
		hardWait(2);
		element("secondary_navi", addressBook).click();
		msg.log("User clicks on:" + addressBook);
		isElementDisplayed("addressBook_page");
		msg.log(element("addressBook_page").getText() + " is displayed");
		String url = getCurrentURL();
		assertEquals(url.contains("address-book"), true, "User is not on Address Book page");
		msg.log("User is on Address Book page");
	}

	public void clickAndVerifyOnOrderHistory(String orderHistory) {
		wait.waitForLoad();
		scrollWindow(0, 300);
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			executeJavascript("$('.first-a')[0].click()");
			hardWait(5);
		}
		element("secondary_navi", orderHistory).click();
		msg.log("User clicks on:" + orderHistory);
		isElementDisplayed("orderHistory_page");
		msg.log(element("orderHistory_page").getText() + " is displayed");
		String url = getCurrentURL();
		assertEquals(url.contains("orders"), true, "User is not on orders history page");
		msg.log("User is on orders history page");
	}

	public void clickAndVerifyOnGiftRegistry(String giftRegistry) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			executeJavascript("$('.first-a')[0].click()");
			hardWait(5);
		}
		element("secondary_navi", giftRegistry).click();
		msg.log("User clicks on:" + giftRegistry);
		isElementDisplayed("giftregistry_page");
		msg.log(element("giftregistry_page").getText() + " is displayed");
		String url = getCurrentURL();
		assertEquals(url.contains("gift-registry"), true, "User is not on gift-registry page");
		msg.log("User is on gift-registry page");
	}

	public void mobile_logoutFromApplication() {
		wait.waitForLoad();
		isElementDisplayed("hamburger_username");
		clickUsingJS(element("hamburger_username"));
		msg.log("Username is clicked on the hamburger menu");
		wait.waitForLoad();
		executeJavascript("$('.account-logout a')[0].click()");
		msg.log("User clicks on logout link");
	}

	public void logoutFromApplicationOnMobile() {
		wait.waitForLoad();
		executeJavascript("$('.account-logout a')[0].click()");
		msg.log("User clicks on logout:");
	}

	public void clickOnRegisterButton() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		clickUsingJS(element("button_CreateAccount_new"));
		msg.log("User clicks on Create An Account button");
	}

	public void enterRegisterInformation() {
		long Random = System.currentTimeMillis();
		String firstName = "A" + Random;
		msg.log("New User first Name : " + firstName);
		String lastName = "A" + Random;
		msg.log("New User lastName : " + lastName);
		String emailAddress = "A" + Random + "@qa.com";
		msg.log("New User emailAddress : " + emailAddress);
		String password = "auto1234";
		msg.log("New User password : " + password);

		PropFileHandler.writeProperty("firstName", firstName);
		PropFileHandler.writeProperty("lastName", lastName);
		PropFileHandler.writeProperty("emailAddress", emailAddress);
		PropFileHandler.writeProperty("password", password);
		wait.waitForLoad();
		isElementDisplayed("textbox_FirstName");
		sendText(element("textbox_FirstName"), firstName);
		isElementDisplayed("textbox_LastName");
		sendText(element("textbox_LastName"), lastName);
		isElementDisplayed("textbox_eMailAddress");
		sendText(element("textbox_eMailAddress"), emailAddress);
		isElementDisplayed("textbox_confirmEmail");
		sendText(element("textbox_confirmEmail"), emailAddress);
		isElementDisplayed("textbox_password");
		sendText(element("textbox_password"), password);
		isElementDisplayed("textbox_confirmPassword");
		sendText(element("textbox_confirmPassword"), password);

		isElementDisplayed("chkbx_privacyOptIn");
		clickUsingJS(element("chkbx_privacyOptIn"));
		msg.log("Privacy opt in check box is clicked");

		clickUsingJS(element("button_apply"));
		msg.log("Apply button is clicked after filling information");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(4);
		}

		wait.waitForElementToBeVisible(element("header_username"));
		isElementDisplayed("header_username");
	}

	public void editNameAccountInformation(String existin_password) {
		long Random = System.currentTimeMillis();
		String firstName = "A" + Random;
		String lastName = "A" + Random;
		String emailAddress = "Auto" + Random + "@qa.com";
		PropFileHandler.writeProperty("firstName", firstName);
		PropFileHandler.writeProperty("lastName", lastName);
		PropFileHandler.writeProperty("emailAddress", emailAddress);
		isElementDisplayed("textbox_FirstName");
		sendText(element("textbox_FirstName"), firstName);
		msg.log(firstName + " is entered");
		isElementDisplayed("textbox_LastName");
		sendText(element("textbox_LastName"), lastName);
		msg.log(lastName + " is entered");
		isElementDisplayed("textbox_eMailAddress");
		sendText(element("textbox_eMailAddress"), emailAddress);
		msg.log(emailAddress + " is entered");
		isElementDisplayed("textbox_confirmEmail");
		sendText(element("textbox_confirmEmail"), emailAddress);
		msg.log(emailAddress + " is entered");
		sendText(element("textbox_password"), existin_password);
		msg.log(existin_password + " is entered");
		isElementDisplayed("chkbx_privacyOptIn");
		clickUsingJS(element("chkbx_privacyOptIn"));
		msg.log("Privacy opt in check box is clicked");
		isElementDisplayed("button_apply");
		clickUsingJS(element("button_apply"));
		msg.log("User clicks on apply button");
	}

	public void goToAddressBook() {
		wait.waitForLoad();
		isElementDisplayed("link_addressBook");
		clickUsingJS(element("link_addressBook"));
		msg.log("Clicked on 'Address Book' link");
		wait.waitForLoad();
		isElementDisplayed("page_header");
	}

	public void mobile_goToAddressBook() {
		wait.waitForLoad();
		isElementDisplayed("mobile_link_addressBook");
		clickUsingJS(element("mobile_link_addressBook"));
		msg.log("User clicks on Address Book");
		wait.waitForLoad();
		isElementDisplayed("page_header");
	}

	public void addAddressInAddressBook(String addressName, String firstName, String lastName, String postalCode,
			String phoneNumber, String address1, String city) {

		isElementDisplayed("button_Add_New_Address");
		element("button_Add_New_Address").click();
		msg.log("Clicked on 'Add new Address' button");

		isElementDisplayed("label_addAddress");
		
		isElementDisplayed("textbox_addressName");
		sendText(element("textbox_addressName"), addressName);
		msg.log(addressName + " is entered");
		
		isElementDisplayed("textbox_address_FirstName");
		sendText(element("textbox_address_FirstName"), firstName);
		msg.log(firstName + " is entered");
		
		isElementDisplayed("textbox_address_LastName");
		sendText(element("textbox_address_LastName"), lastName);
		msg.log(lastName + " is entered");

		if (getCurrentURL().contains("ksEuRoe/en-de") || getCurrentURL().contains("en-de")) {
			executeJavascript("$('select#dwfrm_profile_address_country').val('DE')");
		}

		/*
		 * isElementDisplayed("textbox_address_PostalCode");
		 * sendText(element("textbox_address_PostalCode"), postalCode);
		 * msg.log(postalCode + " is entered");
		 */
		
		if(getCurrentURL().contains("en-gb")) {
			verifyAddressFieldsPopulateAndInUpperCase(postalCode);
		}

		isElementDisplayed("textbox_address_Phone");
		sendText(element("textbox_address_Phone"), phoneNumber);
		msg.log(phoneNumber + " is entered");

		/*
		 * String url = driver.getCurrentUrl(); if
		 * (url.contains("ksEuUk")||url.contains("en-gb")) {
		 * 
		 * isElementDisplayed("enter_manually"); msg.log("Enter Manually is displayed");
		 * 
		 * Address is entered manually as search address functionality not found
		 * 
		 * element("enter_manually").click();
		 * 
		 * 
		 * isElementDisplayed("find_address"); element("find_address").click();
		 * msg.log("User clicks on find Address");
		 * 
		 * isElementDisplayed("address_dropdown"); element("address_dropdown").click();
		 * 
		 * List<WebElement> myaddress = elements("add_options"); for (WebElement options
		 * : myaddress) { msg.log(options.getText()); } element("add_option_1").click();
		 * msg.log("User selected : " + (element("add_option_1").getText()));
		 * msg.log("User selected Address from dropdown");
		 * 
		 * isElementDisplayed("search_again"); msg.log("Search again is displayed");
		 * 
		 * isElementDisplayed("textbox_address1");
		 * msg.log("Address1 TexttBox is displayed");
		 * 
		 * isElementDisplayed("textbox_city"); sendText(element("textbox_city"), city);
		 * msg.log(city + "is entered");
		 * 
		 * if(ConfigPropertyReader.getProperty("browser").contains("ios")){
		 * scrollWindow(0, 300); } isElementDisplayed("button_apply_AddAddress");
		 * wait.waitForElementToBeClickable(element("button_apply_AddAddress"));
		 * clickUsingJS(element("button_apply_AddAddress"));
		 * msg.log("User clicks on Apply Button");
		 * 
		 * } else { isElementDisplayed("textbox_address1");
		 * sendText(element("textbox_address1"), address1); msg.log(address1 +
		 * "is entered"); isElementDisplayed("textbox_city");
		 * sendText(element("textbox_city"), city); msg.log(city + "is entered");
		 * if(ConfigPropertyReader.getProperty("browser").contains("ios")){
		 * scrollWindow(0, 300); }
		 * 
		 * }
		 */
		/*
		 * isElementDisplayed("textbox_address1"); sendText(element("textbox_address1"),
		 * address1); msg.log(address1 + "is entered");
		 * isElementDisplayed("textbox_city"); sendText(element("textbox_city"), city);
		 * msg.log(city + "is entered");
		 */
		
		isElementDisplayed("button_apply_AddAddress");
		element("button_apply_AddAddress").click();
		msg.log("Clicked on 'Apply' button");
	}

	public void verifiedSavedAddresss() {
		isElementDisplayed("savedAddress");
		isElementDisplayed("address_title");
		isElementDisplayed("address_name");
		isElementDisplayed("address_location");
		msg.log("Address is saved");
		isElementDisplayed("btn_editAddress");
		isElementDisplayed("btn_deleteAddress");
		// isElementNotDisplayed("make_default_btn");
	}

	public void verifyandClickOnOrderHistory() {
		wait.waitForLoad();
		String url = getCurrentURL();
		assertEquals(url.contains("account"), true, "User is not on account page");
		msg.log("user lands on account page");
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			expandMyAccountOptionsInMobile();
		}
		isElementDisplayed("order_history");
		clickUsingJS(element("order_history"));
		msg.log("User clicks on Order History from My account Page");
	}

	public void navigateToOrderHistoryPage() {
		wait.waitForLoad();
		isElementDisplayed("link_orderHistory");
		clickUsingJS(element("link_orderHistory"));
		msg.log("User clicks on the Order history link");
	}

	public void verifyLandingPage() {
		verifyPageTitleContains();
	}

	public void verfifyUserlandsOnOrderHistoryPage() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		String url = getCurrentURL();
		isElementDisplayed("page_heading");
		assertEquals(url.contains("order"), true, "User is not on order page");
		msg.log("User lands on " + (element("page_heading").getText() + "page"));
	}

	public void verifyOrderNumberInOrderHistory(String orderNum) {
		wait.waitForLoad();
		isElementDisplayed("lst_of_orders", orderNum);
		msg.log(orderNum + " is displayed");
	}

	public void verifyOrderDetailsInOrderHistory(String OrderDate, String OrderStatus, String OrderNum, String TrackNum,
			String OrderAdd, String Items, String Total) {
		isElementDisplayed("lst_order_details", OrderDate);
		msg.log(OrderDate + " is displayed");
		isElementDisplayed("lst_order_details", OrderStatus);
		msg.log(OrderStatus + " is displayed");
		isElementDisplayed("lst_order_details", OrderNum);
		msg.log(OrderNum + " is displayed");
		isElementDisplayed("lst_order_details", TrackNum);
		msg.log(TrackNum + " is displayed");
		isElementDisplayed("lst_order_details", OrderAdd);
		msg.log(OrderAdd + " is displayed");
		isElementDisplayed("lst_order_details", Items);
		msg.log(TrackNum + " is displayed");
		isElementDisplayed("lst_order_details", Total);
		msg.log(Total + " is displayed");
	}

	public void goToEditAccountPage() {
		wait.waitForLoad();
		isElementDisplayed("link_myInformation");
		element("link_myInformation").click();
		wait.waitForLoad();
		isElementDisplayed("lbl_primaryHeader");
		msg.log("User navigates to edit account page");
	}

	public void verifyWishlistCanBeAccessdedMyAccount() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		hardWait(1);
		executeJavascript("$('#wishlist a')[0].click()");
		msg.log("Clicked on wishlist button from My Account Page");

	}

	public void validateThatOnClickSignInLinkTakesUserToMyAccountLandingLoginPage() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(5);
		}
		/*
		 * String url = getCurrentURL(); assertEquals(url.contains("account"), true,
		 * "User is not on account page"); msg.log("User is on account page");
		 */
		// isElementDisplayed("label_signInYourEmail");

		isElementDisplayed("label_signIn");
		isElementDisplayed("textbox_login_emailAddress");
		isElementDisplayed("textbox_login_password");

		isElementDisplayed("label_RememberMe");
		// isElementDisplayed("label_forgotPassword");
		isElementDisplayed("label_forgotPassword_new");
		// isElementDisplayed("button_signin");
		scrollUpToPage();
		hardWait(1);
		isElementDisplayed("button_signIn_new");
		// List<WebElement> text = elements("button_yourOrderHistory");
		List<WebElement> text = elements("text_checkOrder_new");
		for (WebElement allText : text) {
			isElementsDisplayed(text);
			msg.log(allText.getText() + " is displayed");
		}
		// isElementDisplayed("button_CreateAnAccount");
		// isElementDisplayed("button_yourOrderHistory");
		isElementDisplayed("button_CreateAccount_new");
		isElementDisplayed("text_checkOrder_new");
	}

	public void validateThatOnClickRegisterLinkTakesUserToLoginCreateAccountLandingPage() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		String url = getCurrentURL();
		msg.log(url);
		assertEquals(url.toLowerCase().contains("register"), true, "User is not on create an account page");
		msg.log("User is on create an account page");
		isElementNotDisplayed("label_signInYourEmail");
		isElementDisplayed("page_heading");
		isElementDisplayed("textbox_FirstName");
		isElementDisplayed("textbox_LastName");
		isElementDisplayed("textbox_eMailAddress");
		isElementDisplayed("textbox_confirmEmail");
		isElementDisplayed("textbox_password");
		isElementDisplayed("textbox_confirmPassword");
		isElementDisplayed("textbox_LastName");
		isElementDisplayed("button_apply");
	}

	public void verifyEmailANdPasswordAreRequiredToLogin() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		isElementDisplayed("button_signIn_new");
		clickUsingJS(element("button_signIn_new"));

		// isElementDisplayed("errorMsg_email");
		// msg.log(element("errorMsg_email").getText());
		isElementDisplayed("errorMsg_password");
		msg.log(element("errorMsg_password").getText());
	}

	public void verifyAfterEnteringValidCredentialsLogInDisplayesHiNameOfUserAndMyAccountPageIsOpen() {
		isElementDisplayed("header_username");
		msg.log(element("header_username").getText());
		hover(element("header_username"));
		msg.log("User hovers on Username");
		isElementDisplayed("link_myAccount");
		assertEquals(element("link_myAccount").getCssValue("font-weight"), "900",
				"My account link is not selected in the side menue");
		msg.log("My account page is selected in the side menue");
		assertNotEquals(element("link_myAccount").getCssValue("font-weight"),
				element("link_myInformation").getCssValue("font-weight"),
				"Check the UI of side menu in my account page");
		msg.log("Verified My account page is selected in the side menue");

	}

	public void verifyOnEnteringWrongCredentialsErrorMessageAppears(String username, String password, String text) {
		wait.waitForLoad();
		isElementDisplayed("textbox_login_emailAddress");
		sendText(element("textbox_login_emailAddress"), username);
		msg.log(username + " is entered");
		isElementDisplayed("textbox_login_password");
		sendText(element("textbox_login_password"), password);
		msg.log(password + " is entered");
		isElementDisplayed("button_signIn_new");
		element("button_signIn_new").click();
		msg.log("Login button is clicked");
		isElementDisplayed("errorMsg_invalidCreds");
		msg.log(element("errorMsg_invalidCreds").getText());
		assertEquals(element("errorMsg_invalidCreds").getText().toUpperCase(), text.toUpperCase(),
				"Sorry message is not displayed");
		msg.log(text + ": is the error message");
		element("textbox_login_emailAddress").clear();
		element("textbox_login_password").clear();
	}

	public void verifyForgotPasswordLink(String username) {
		isElementDisplayed("label_forgotPassword");
		executeJavascript("$('#password-reset').click()");
		// element("label_forgotPassword").click();
		msg.log("User clicks on Forgot password");
		isElementDisplayed("label_resetPassword");
		msg.log(element("label_resetPassword").getText());
		isElementDisplayed("text_resetPassword");
		msg.log(element("text_resetPassword").getText());
		isElementDisplayed("forgotPassword_field_email");
		isElementDisplayed("forgotpassword_send_button");
		sendText(element("forgotPassword_field_email"), username);
		msg.log(username + ": is enterd");
		clickUsingJS(element("forgotpassword_send_button"));
		List<WebElement> thankyou = elements("forgotpassword_thankyou_text");
		for (WebElement el : thankyou) {
			msg.log(el.getText());
		}
		isElementDisplayed("goBackToHomePage");
		element("goBackToHomePage").click();
		msg.log("User clicks on Go Back To home Page link");
	}

	public void clickOnAddNewAddress() {
		isElementDisplayed("button_Add_New_Address");
		// clickUsingJS(element("button_Add_New_Address"));
		executeJavascript("$('.address-create.button.secondary').click()");
		msg.log("Clicked on 'Add new Address' button");
	}

	public void verifyUserLandsOnAddBookPageOnClickingCancleBtn() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("cancle_btn");
		element("cancle_btn").click();
		msg.log("Clicked on 'Cancel' button");
		
		wait.waitForElementToBeVisible(element("address_book_page"));
		isElementDisplayed("address_book_page");
		msg.log("Navigated to Address book page: " + element("address_book_page").getText());
	}

	public void verifyUserLandsOnAddBookPageOnClickingXBtn() {
		isElementDisplayed("cross_btn");
		element("cross_btn").click();
		msg.log("Clicked on cross button");
		
		wait.waitForElementToBeVisible(element("address_book_page"));
		isElementDisplayed("address_book_page");
		msg.log("Navigated to Address book page");
	}

	public void verifyUserIsAbleToEditAddress(String firstName) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		try {
			isElementDisplayed("edit_btn");
			clickUsingJS(element("edit_btn"));
			msg.log("Clicked on 'Edit' button");
			
			isElementDisplayed("edit_header");
			isElementDisplayed("textbox_address_FirstName");
			sendText(element("textbox_address_FirstName"), firstName);
			msg.log(firstName + " is entered");
		} catch (AssertionError er) {
			Assert.fail("Account does NOT have any Saved card to edit !!!");
		}
	}

	public void verifyUserIsAbleToUpdateAddress() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("update_btn");
		clickUsingJS(element("update_btn"));
		msg.log("Clicked on 'Update' button");
		
		wait.waitForElementToBeVisible(element("address_book_page"));
		isElementDisplayed("address_book_page");
		msg.log("Navigated to Address book page");
	}

	public void verifyUserIsAbleToDeleteAddress() {
		try {
			int sizeOfContent = elements("delete_btn").size();
			msg.log("No. of delete button: " + sizeOfContent);
			
			for (int i = 0; i < 2; i++) {
				isElementDisplayed("btn_deleteByIndex", "1");
				clickUsingJS(element("btn_deleteByIndex", "1"));
				msg.log("Clicked on 'Delete' button");
				
				isElementDisplayed("btn_yes");
				clickUsingJS(element("btn_yes"));
				msg.log("Clicked on 'Yes' button");
				
				hardWait(3);
				wait.waitForPageToLoadCompletely();
			}
			isElementNotDisplayed("savedAddress");
		} catch (Exception | AssertionError er) {
			Assert.fail("This account does NOT have any Saved card !!!");
		}
	}
	
	public void verifyUserIsAbleTocloseAddWindowOnClickingXBtn() {
		try {
			isElementDisplayed("edit_btn");
			executeJavascript("$('.button.address-edit.edit-button').click()");
			msg.log("Clicked on 'Edit' button");
			
			isElementDisplayed("cross_btn");
			element("cross_btn").click();
			msg.log("Clicked on cross button");
			
			wait.waitForElementToBeVisible(element("address_book_page"));
			isElementDisplayed("address_book_page");
			msg.log("Navigated to Address book page");
		} catch (Exception | AssertionError er) {
			Assert.fail("This account does NOT have any Saved card !!!");
		}
	}

	public void clickOnCreditCardLinkOnMyAccountPage() {
		wait.waitForLoad();
		isElementDisplayed("link_creditCard");
		element("link_creditCard").click();
		msg.log("User clicked on the Credit Card Link");
	}

	public void verifyNewlyAddedCardIsDisplayedOnAccountPage(String savedCardName) {
		wait.waitForLoad();
		isElementDisplayed("savedCardName", savedCardName);
		msg.pass("Verified Saved Card is displayed on account page");
	}

	public void deleteSavedCardByItsNameOnAccountPage(String savedCardName) {
		wait.waitForLoad();
		isElementDisplayed("btn_deleteSavedCard", savedCardName);
		element("btn_deleteSavedCard", savedCardName).click();
		isElementDisplayed("btn_yes");
		element("btn_yes").click();
		msg.log("Verified: Saved Card is deleted");
	}

	public void goToAddressBookFromAccountPage() {
		wait.waitForLoad();
		isElementDisplayed("link_addressBook");
		element("link_addressBook").click();
		msg.log("User clicks on Address Book");
	}

	public void verifyImNewHereAndClickOnCheckYourOrderLink() {
		isElementDisplayed("button_yourOrderHistory");
		element("button_yourOrderHistory").click();
		msg.log("User clicks on Check your order button");
	}

	public void GoToMyAccountPage() {
		isElementDisplayed("header_username");
		hover(element("header_username"));
		isElementDisplayed("myaccount_link");
		element("myaccount_link").click();
		msg.log("User clicks on my account link");
	}

	public void mobile_GoToMyAccountPage() {
		isElementDisplayed("myaccount_link");
		element("myaccount_link").click();
		msg.log("User clicks on my account link");
	}

	public boolean foundAddress(String zip) {
		wait.waitForLoad();
		
		isElementDisplayed("link_addressBook");
		element("link_addressBook").click();
		msg.log("User clicks on Address Book");
		
		isElementDisplayed("address_book_page");
		List<WebElement> zipcodes = elements("addressbook_zips");
		for (WebElement value : zipcodes) {
			if (value.getText().replaceAll("[^a-zA-Z]+", " ").trim().toLowerCase()
					.contains(zip.trim().toLowerCase().replaceAll("[^a-zA-Z]+", " "))) {
				return true;
			}
		}
		return false;
	}

	public void selectAddressBook(String zip) {
		Assert.assertTrue(foundAddress(zip));
	}

	public void verifyUserIsInAccountPage() {
		wait.waitForLoad();

		msg.log("Actual Current URL: " + getCurrentURL());
		msg.log("Expected Result (contains): 'account'");

		Assert.assertEquals(getCurrentURL().contains("account"),
				"[ASSERTION FAILED]: User is not navigated to my account page");
		msg.pass("User is navigated to my account page");
	}

	public void verifyErrorMessageIsDisplayed() {
		wait.waitForLoad();
		isElementDisplayed("list_errorMsg");
		List<WebElement> errorMsg = elements("list_errorMsg");
		for (WebElement el : errorMsg) {
			msg.log("Error Msg displayed: " + el.getText());
		}
	}

	public void clickCheckYourOrderButton() {
		wait.waitForLoad();
		isElementDisplayed("btn_chkOrder");
		element("btn_chkOrder").click();
		msg.log("Check your Order button is clicked");
	}

	public void verifyUserIsStillLoggedIn() {
		isElementDisplayed("header_username");
		msg.log(element("header_username").getText());
		hover(element("header_username"));
		msg.log("User hovers on Username");
		isElementDisplayed("link_myAccount");
	}

	public void goToCreditCardFromContentAssest() {
		isElementDisplayed("contentAsset_creditCard");
		element("contentAsset_creditCard").click();
		msg.log("User clicks on credit card from content asset");
	}

	public void verifyUserCanAddANewAddress() {
		isElementDisplayed("button_addNewAddress");
		element("button_addNewAddress").click();
		msg.log("User clicks on add a new credit card");
		isElementDisplayed("dialogContainer_addCreditCardLabel");
		isElementDisplayed("crossbutton_addcredit");
		isElementDisplayed("addcredit_typefield");
	}

	public void verifyAddCreditCardsAttributesArePresent() {
		isElementDisplayed("addcredit_typefield");
		isElementDisplayed("addcreitcard_owner");
		isElementDisplayed("addcreditcard_number");
		String number = element("addcreditcard_number").getAttribute("maxlength");
		assertEquals(number, "16", "User can enter more than 16 characters in credit card number field");
		msg.log("User can not enter more than 16 characters in credit card number field");
		isElementDisplayed("addcreditcard_startdate");
		isElementDisplayed("addcreditcard_expdate");
		isElementDisplayed("addcreditcard_issueNo");
		isElementDisplayed("addcreditcard_cvv");
	}

	public void verifyAllTheValidationChecksAreAvailable() {
		element("addcredit_typefield").click();
		element("addcreditcard_startdate").click();
		isElementDisplayed("addcredit_typefield_error");

		element("addcreitcard_owner").click();
		element("addcreditcard_startdate").click();
		isElementDisplayed("addcreitcard_owner_error");

		element("addcreditcard_number").click();
		element("addcreditcard_startdate").click();
		isElementDisplayed("addcreditcard_number_error");

		element("addcreditcard_expdate").click();
		element("addcreditcard_startdate").click();
		isElementDisplayed("addcreditcard_expdate_error");

		element("addcreditcard_cvv").click();
		element("addcreditcard_number").click();
		isElementDisplayed("addcreditcard_cvv_error");
	}

	public void enterTheValidCreditCardValues(String type, String cardname, String number, String cvv) {
		selectOptionFromDropDownList("addcredit_typedropdown", type);
		sendText(element("addcreitcard_owner"), cardname);
		sendText(element("addcreditcard_number"), number);
		sendText(element("addcreditcard_expdate"), "10/2020");
		sendText(element("addcreditcard_cvv"), cvv);

		element("addcreditcard_cvv").sendKeys(Keys.TAB);
		hardWait(2);

		// clickByJavascript(element("addcredit_applyButton"));
		executeJavascript("document.getElementById('add-card-submit').click()");
		msg.log("Clicked on 'APPLY' button");
	}

	public void enterInvalidCreditDetail() {
		selectOptionFromDropDownList("addcredit_typedropdown", "Visa");
		element("addcreitcard_owner").sendKeys(Keys.TAB);
		element("addcreditcard_number").sendKeys(Keys.TAB);
		isElementDisplayed("addcreitcard_owner_error");
		sendText(element("addcreditcard_expdate"), "01/2016");
		element("addcreditcard_cvv").click();
		isElementDisplayed("addcreditcard_expdate_error");
	}

	public void verifyOnClickingOnCancelButtonUserIsBackOnWalletPage() {
		isElementDisplayed("addcredit_cancelButton");
		clickUsingJS(element("addcredit_cancelButton"));
		msg.log("Clicked on 'Cancel' button");

		isElementDisplayed("creditcard_pageheader");
		isElementNotDisplayed("addcreitcard_owner");
	}

	public void verifyUserClicksOnSideNav(int location) {
		(elements("side_nav")).get(location).isDisplayed();
		(elements("side_nav")).get(location).click();
		msg.log("User clicks on My account");
		wait.waitForLoad();
	}

	public void mobile_verifyUserClicksOnSideNav(int location) {
		String loc = Integer.toString(location);
		boolean display = executeJavascriptReturnsBoolean(
				" return $('.secondary-navigation .content-asset li a').is(':visible')");
		Assert.assertEquals(display, true, "[Assert Fail] : element is not displayed");
		msg.log("[Assert Pass] : element is displayed");
		executeJavascript("$('.secondary-navigation .content-asset li a')[" + loc + "].click()");
		msg.log("element is clicked");
	}

	public void verifyUserLandsOnCorrectPage(String PageName) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		}
		isElementDisplayed("page_header");
		element("page_header").getText();
		String url = getCurrentURL();
		msg.log("" + url);
		assertEquals(url.toLowerCase().contains(PageName.toLowerCase()), true, "User is not on " + PageName + " Page");
		msg.log("User is on " + PageName + " Page");
	}

	public void verifyTheResultsOnceUserClicksOnApplyButton() {
		try {
			element("creditcard_errormsg").isDisplayed();
			msg.log(element("creditcard_errormsg").getText());
		} catch (Exception | AssertionError e) {
			msg.pass("User does not get an error on adding Credit card !!!");
			try {
				isElementDisplayed("creditcard_savedHeader");
				msg.pass("Credit card has been added to Accounr page !!!");
			} catch (Exception | AssertionError er) {
				
			}
		}
	}

	public void verifyUserIsOnAddressBookPage() {
		isElementDisplayed("page_header");
		msg.log("Actual Result: " + getCurrentURL());
		msg.log("Expected Result: 'addressbook'");
		assertTrue(getCurrentURL().contains("addressbook"), "[ASSRTION FAILED]: User is not on the address book page");
		msg.pass("User is on address book Page");
	}

	public void verifyUserIsableToMakeNewlyAddedAddressAsDefaultAddress(String defautFirstName) {
		isElementDisplayed("make_default_btn");
		clickUsingJS(element("make_default_btn"));
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		String addName = element("address_name").getText().toLowerCase();
		msg.log(addName);
		msg.log(defautFirstName);
		assertEquals(addName.contains(defautFirstName.toLowerCase()), true, "User is not able to make default address");
		msg.log("User is able to make default address");
	}

	public void verifyErrorMsgIfAlreadyRegisteredEmailIsUsedForAccontCreation(String alredayRegisteredEmail) {
		wait.waitForLoad();
		isElementDisplayed("textbox_FirstName");
		sendText(element("textbox_FirstName"), "a");
		isElementDisplayed("textbox_LastName");
		sendText(element("textbox_LastName"), "b");
		isElementDisplayed("textbox_eMailAddress");
		sendText(element("textbox_eMailAddress"), alredayRegisteredEmail);
		isElementDisplayed("textbox_confirmEmail");
		sendText(element("textbox_confirmEmail"), alredayRegisteredEmail);
		isElementDisplayed("textbox_password");
		sendText(element("textbox_password"), "11111111");
		isElementDisplayed("textbox_confirmPassword");
		sendText(element("textbox_confirmPassword"), "11111111");

		verifyPrivacyPolicyCheckBoxIsUncheckedByDefault();
		verifyPrivacyPolicyCheckBoxIsRequiredToCheckedForAccountRegistration();

		clickUsingJS(element("button_apply"));
		msg.log("Apply button is clicked after filling email which is already registered ");

		isElementDisplayed("alreadyRegstrdErrorMsg");
		msg.log("Error message is dispalyed for already registered mail : "
				+ element("alreadyRegstrdErrorMsg").getText());
	}

	public void verifyPrivacyPolicyCheckBoxIsUncheckedByDefault() {
		wait.waitForLoad();
		isElementDisplayed("chkbx_privacyOptIn");
		boolean chkbx = executeJavascriptReturnsBoolean("return $('#privacy-opt-in')[0].checked");
		assertEquals(chkbx, false, "[Asser Failed]: Privacy opt in checkbox is checked by default");
		msg.log("Privacy Opt in checkbox is not selected by default as expected");
	}

	public void verifyPrivacyPolicyCheckBoxIsRequiredToCheckedForAccountRegistration() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("disabled_submitBtn");
		isElementDisplayed("chkbx_privacyOptIn");
		clickUsingJS(element("chkbx_privacyOptIn"));
		msg.log("Privacy opt in checkbox is clicked");
		isElementNotDisplayed("disabled_submitBtn");
		msg.log("Verified: Privacy Opt in checkbox is needed to check to complete registration");
	}

	public void verifyAddToMailingListRadioBtnIsNotChecked() {
		wait.waitForLoad();
		isElementDisplayed("radioBtn_addToMailingList");
		String chkdClass = executeJavascriptReturnsString("return $('.add-to-email [checked]')[0].getAttribute('id')");
		assertEquals(chkdClass.contains("1"), true,
				"[Assert Fail] : Add to mailing list radio btn is by default checked ");
		msg.log("Verified : Add to mailing list is by default not checked as expected");
	}

	public void verifySubtext(String subText, int location) {
		elements("subText").get(location).isDisplayed();
		msg.log(elements("subText").get(location).getText());
		assertEquals(elements("subText").get(location).getText().contains(subText), true, "SubText is not displayed");
		msg.log("SubText is displayed");

	}

	public void clickOnMyInformation() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollUpToPage();
			hardWait(2);
		}
		isElementDisplayed("link_myInformation");
		clickUsingJS(element("link_myInformation"));
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		msg.log("User clicks on My Information");

	}

	public void verifyUserClicksOnSideNavAndLandsOnCorrectPage(String pageName, String pageHeader) {
		wait.waitForLoad();
		// user lands on My Info Page
		isElementDisplayed("page_header");
		msg.log("Page Header : " + element("page_header").getText());
		assertEquals(element("page_header").getText().contains(pageHeader), true,
				"User is not on " + pageHeader + " Page");
		msg.log("User is on " + pageHeader + " Page");

		String url = getCurrentURL();
		msg.log(url);
		assertEquals(url.toLowerCase().contains(pageName.toLowerCase()), true, "User is not on " + pageName + " Page");
		msg.log("User is on " + pageName + " Page");
	}

	public void verifyFirstAndLastNameFieldIsPresent() {
		// First Name
		isElementDisplayed("firstName");
		msg.log("First Name :" + element("firstName").getAttribute("value"));
		element("firstName").clear();
		element("firstName").sendKeys(Keys.TAB);
		isElementDisplayed("firstName_error");
		// Last Name
		isElementDisplayed("lastName");
		msg.log("Last Name :" + element("lastName").getAttribute("value"));
		element("lastName").clear();
		element("lastName").sendKeys(Keys.TAB);
		isElementDisplayed("lastName_error");
	}

	public void mobile_verifyFirstAndLastNameFieldIsPresent() {
		// First Name
		isElementDisplayed("firstName");
		msg.log("First Name :" + element("firstName").getAttribute("value"));
		element("firstName").clear();
		element("lastName").click();
		isElementDisplayed("firstName_error");
		// Last Name
		isElementDisplayed("lastName");
		msg.log("Last Name :" + element("lastName").getAttribute("value"));
		element("lastName").clear();
		element("firstName").click();
		isElementDisplayed("lastName_error");
	}

	public void verifyEmailAddressFieldIsPresent() {
		isElementDisplayed("emailId");
		msg.log("Email ID :" + element("emailId").getAttribute("value"));
		element("emailId").clear();
		element("emailId").sendKeys(Keys.TAB);
		isElementDisplayed("emailId_error");
	}

	public void mobile_verifyEmailAddressFieldIsPresent() {
		isElementDisplayed("emailId");
		msg.log("Email ID :" + element("emailId").getAttribute("value"));
		element("emailId").clear();
		element("confirmEmail").click();
		isElementDisplayed("emailId_error");
	}

	public void verifyWhenValidAndInvalidEmailIdIsEntered(String validEmail, String inValidEmail) {
		isElementDisplayed("emailId");
		element("emailId").clear();
		// vaild ID
		sendText(element("emailId"), validEmail);
		isElementNotDisplayed("emailId_error");
		// Invalid ID
		element("emailId").clear();
		sendText(element("emailId"), inValidEmail);
		element("emailId").sendKeys(Keys.TAB);
		isElementDisplayed("emailId_error");
	}

	public void mobile_verifyWhenValidAndInvalidEmailIdIsEntered(String validEmail, String inValidEmail) {
		isElementDisplayed("emailId");
		element("emailId").clear();
		// vaild ID
		sendText(element("emailId"), validEmail);
		isElementNotDisplayed("emailId_error");
		// Invalid ID
		element("emailId").clear();
		sendText(element("emailId"), inValidEmail);
		element("confirmEmail").click();
		isElementDisplayed("emailId_error");
	}

	public void verifyErrorMessageOnEnteringInvalidEmailID(String errorMsg) {
		isElementDisplayed("emailId_error");
		msg.log("Error msg : " + element("emailId_error").getAttribute("value"));
		assertEquals(element("emailId_error").getText().contains(errorMsg), true, "Error message is not displayed");
		msg.log("Error message is displayed");
	}

	public void enterOriginalEmailId(String orgEmail) {
		element("emailId").clear();
		sendText(element("emailId"), orgEmail);
	}

	public void verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(String inValidEmail) {
		isElementDisplayed("confirmEmail");
		sendText(element("confirmEmail"), inValidEmail);
		element("confirmEmail").sendKeys(Keys.TAB);
		isElementDisplayed("confirmEmail_error");
	}

	public void mobile_verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(String inValidEmail) {
		isElementDisplayed("confirmEmail");
		sendText(element("confirmEmail"), inValidEmail);
		element("acc_password").click();
		isElementDisplayed("confirmEmail_error");
	}

	public void verifyPasswordField(String password1, String errorMsg) {
		isElementDisplayed("acc_password");
		// Password with less than 7 characters
		sendText(element("acc_password"), password1);
		element("acc_password").sendKeys(Keys.TAB);
		isElementDisplayed("acc_password_error");

		msg.log(element("acc_password_error").getText());
		assertEquals(element("acc_password_error").getText().contains(errorMsg), true,
				"Password error is not diaplyed");
		msg.log("Password error is diaplyed");

		String maxlenthOfPassword = executeJavascriptReturnsString(
				"return $('#dwfrm_profile_login_password').attr('maxlength')");
		assertEquals(maxlenthOfPassword.contains("20"), true, "Max length of password is not 20");
		msg.log("Max length of password is 20");
	}

	public void mobile_verifyPasswordField(String password1, String errorMsg) {
		isElementDisplayed("acc_password");
		// Password with less than 7 characters
		sendText(element("acc_password"), password1);
		element("confirmEmail").click();
		isElementDisplayed("acc_password_error");

		msg.log(element("acc_password_error").getText());
		assertEquals(element("acc_password_error").getText().contains(errorMsg), true,
				"Password error is not diaplyed");
		msg.log("Password error is diaplyed");

		String maxlenthOfPassword = executeJavascriptReturnsString(
				"return $('#dwfrm_profile_login_password').attr('maxlength')");
		assertEquals(maxlenthOfPassword.contains("20"), true, "Max length of password is not 20");
		msg.log("Max length of password is 20");
	}

	public void verifyRadioButtonFieldAndOptInText(String optInMail) {
		isElementDisplayed("optIn_msg");
		msg.log(element("optIn_msg").getText());
		Assert.assertEquals(element("optIn_msg").getText().contains(optInMail), true,
				"Msg to opt in to mailing list is not displayed");
		msg.log("Msg to opt in to mailinlist is displayed");
		boolean opt_in = executeJavascriptReturnsBoolean("return $('.input-radio').eq(0).is(':visible')");
		Assert.assertTrue(opt_in);
		executeJavascript("$('.input-radio').eq(0).click()");
		msg.log("User clicks on opt_in radio btn");
		boolean opt_in_radio_btn = executeJavascriptReturnsBoolean("return $('.input-radio').eq(0).is(':enabled')");
		Assert.assertTrue(opt_in_radio_btn);
		msg.log("opt_in radio btn is enabled");
	}

	public void verifyRadioButtonFieldAndOptOutText(String optOutFromMail) {
		isElementDisplayed("optOut_msg");
		msg.log(element("optOut_msg").getText());
		assertEquals(element("optOut_msg").getText().contains(optOutFromMail), true,
				"Msg to opt out from mailing list is not displayed");
		msg.log("Msg to opt out from mailinlist is displayed");
		boolean opt_out = executeJavascriptReturnsBoolean("return $('.input-radio').eq(1).is(':visible')");
		Assert.assertTrue(opt_out);
		executeJavascript("$('.input-radio').eq(1).click()");
		msg.log("User clicks on opt out radio btn");
		boolean opt_out_radio_btn = executeJavascriptReturnsBoolean("return $('.input-radio').eq(1).is(':enabled')");
		Assert.assertTrue(opt_out_radio_btn);
		msg.log("opt_in radio btn is enabled");
	}

	public void editLastNameAndAddUserInfoInPersonalInfo(String lastName, String usersEmail, String userspassword) {
		isElementDisplayed("lastName");
		element("lastName").clear();
		sendText(element("lastName"), lastName);
		msg.log("Last Name entered : " + element("lastName").getAttribute("value"));

		isElementDisplayed("confirmEmail");
		sendText(element("confirmEmail"), usersEmail);

		isElementDisplayed("acc_password");
		sendText(element("acc_password"), userspassword);

		executeJavascript("$('.input-radio').eq(0).click()");
		msg.log("User clicks on opt_in radio btn");

		isElementDisplayed("chkbx_privacyOptIn");
		clickUsingJS(element("chkbx_privacyOptIn"));
		msg.log("Privacy opt in check box is clicked");
	}

	public void clickOnSubmitbtn() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		}
		
		isElementDisplayed("submit_btn");
		
		clickUsingJS(element("submit_btn"));
		msg.log("Clicked on 'Submit' button");		
	}

	public void verifylastNameisUpdatedSuccessfully(String lastName) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		isElementDisplayed("page_heading");
		
		msg.log("Actual Page Heading: " + element("page_heading").getText());
		msg.log("Expected Result: " + lastName);
		Assert.assertEquals(element("page_heading").getText().contains(lastName), true,
				"[ASSERTION FAILED]: User is NOT able to update info successfullly");
		msg.pass("User is able to update info successfullly");
	}

	public void verifyChangePasswordFeildsCannotLeftBeEmpty() {
		isElementDisplayed("changePassword_header");
		// Current Password
		isElementDisplayed("current_password");
		element("current_password").sendKeys(Keys.TAB);
		isElementDisplayed("current_password_error");
		// New Password
		isElementDisplayed("new_password");
		element("new_password").sendKeys(Keys.TAB);
		isElementDisplayed("new_password_error");
		// Confirm New Password
		isElementDisplayed("confirm_new_password");
		element("confirm_new_password").sendKeys(Keys.TAB);
		isElementDisplayed("confirm_new_password_error");
	}

	public void mobile_verifyChangePasswordFeildsCannotLeftBeEmpty() {
		isElementDisplayed("changePassword_header");
		// Current Password
		isElementDisplayed("current_password");
		element("current_password").click();
		element("new_password").click();
		isElementDisplayed("current_password_error");
		// New Password
		isElementDisplayed("new_password");
		element("confirm_new_password").click();
		isElementDisplayed("new_password_error");
		// Confirm New Password
		isElementDisplayed("confirm_new_password");
		element("new_password").click();
		isElementDisplayed("confirm_new_password_error");
	}

	public void verifyNewPasswordFeildLength(String password1, String errorMsg) {
		isElementDisplayed("new_password");
		// Password with less than 7 characters
		sendText(element("new_password"), password1);
		element("acc_password").sendKeys(Keys.TAB);
		isElementDisplayed("new_password_error");

		msg.log(element("new_password_error").getText());
		assertEquals(element("new_password_error").getText().contains(errorMsg), true,
				"Password error is not diaplyed");
		msg.log("Password error is diaplyed");

		String maxlenthOfPassword = executeJavascriptReturnsString(
				"return $('#dwfrm_profile_login_newpassword').attr('maxlength')");
		assertEquals(maxlenthOfPassword.contains("20"), true, "Max length of password is not 20");
		msg.log("Max length of password is 20");
	}

	public void verifyConfirmPasswordFeildShouldBeSameAsNewPassword(String newPassword, String confirmPassword) {
		isElementDisplayed("new_password");
		sendText(element("new_password"), newPassword); // new password with valid number of characters
		msg.log("New Password Entered : " + newPassword);
		isElementDisplayed("confirm_new_password");
		sendText(element("confirm_new_password"), confirmPassword);
		msg.log("Confirm Password Entered : " + confirmPassword);
		isElementNotDisplayed("confirm_new_password_error");
	}

	public void verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(String newPassword,
			String confirmPassword, String errMsg) {
		isElementDisplayed("new_password");
		sendText(element("new_password"), newPassword); // new password with valid number of characters
		msg.log("New Password Entered : " + newPassword);

		isElementDisplayed("confirm_new_password");
		sendText(element("confirm_new_password"), confirmPassword);
		msg.log("Confirm Password Entered : " + confirmPassword);
		element("confirm_new_password").sendKeys(Keys.TAB);
		isElementDisplayed("confirm_new_password_error");

		assertEquals(element("confirm_new_password_error").getText().contains(errMsg), true,
				"Error message does not match");
		msg.log("Error message matches");
	}

	public void mobile_verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(String newPassword,
			String confirmPassword, String errMsg) {
		isElementDisplayed("new_password");
		sendText(element("new_password"), newPassword); // new password with valid number of characters
		msg.log("New Password Entered : " + newPassword);

		isElementDisplayed("confirm_new_password");
		sendText(element("confirm_new_password"), confirmPassword);
		msg.log("Confirm Password Entered : " + confirmPassword);
		element("new_password").click();
		isElementDisplayed("confirm_new_password_error");

		assertEquals(element("confirm_new_password_error").getText().contains(errMsg), true,
				"Error message does not match");
		msg.log("Error message matches");
	}

	public void enterPasswordFeilds(String currentPass, String newPass, String confirmPass) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("current_password");
		sendText(element("current_password"), currentPass);
		msg.log("New Password Entered : " + currentPass);

		isElementDisplayed("new_password");
		sendText(element("new_password"), newPass); // new password with valid number of characters
		msg.log("New Password Entered : " + newPass);

		isElementDisplayed("confirm_new_password");
		sendText(element("confirm_new_password"), confirmPass);
		msg.log("Confirm Password Entered : " + confirmPass);
	}

	public void clickOnApplyBtn() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		isElementDisplayed("apply_btn");
		clickUsingJS(element("apply_btn"));
		msg.log("Clicked on 'Apply' button");
	}

	public void verifyErrorMessageWhenWrongCurrentPasswordIsEntered(String errMsg) {
		wait.waitForLoad();
		isElementDisplayed("wrong_curr_pass_error");
		assertEquals(element("wrong_curr_pass_error").getText().contains(errMsg), true, "Error message does not match");
		msg.log("Error message matches");
	}

	public void verifyUserUpdatesThePasswordSuccessfully(String pageName) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		String url = getCurrentURL();
		msg.log(url);
		assertEquals(url.toLowerCase().contains(pageName.toLowerCase()), true,
				"User is not able to update password successfully");
		msg.log("User is able to update password successfully");
	}

	public void verifyAddressBookFeilds() {
		isElementDisplayed("button_Add_New_Address");
		element("button_Add_New_Address").click();
		msg.log("Add new Address button is clicked");

		isElementDisplayed("label_addAddress");

		isElementDisplayed("textbox_addressName");
		element("textbox_addressName").sendKeys(Keys.TAB);
		isElementDisplayed("addressName_error");

		isElementDisplayed("textbox_address_FirstName");
		element("textbox_address_FirstName").sendKeys(Keys.TAB);
		isElementDisplayed("add_firstName_error");

		isElementDisplayed("textbox_address_LastName");
		element("textbox_address_LastName").sendKeys(Keys.TAB);
		isElementDisplayed("add_lastName_error");

		isElementDisplayed("textbox_FindAddress");
		element("textbox_FindAddress").sendKeys(Keys.TAB);
		isElementDisplayed("span_addressFindError");

		isElementDisplayed("textbox_address_Phone");
		element("textbox_address_Phone").sendKeys(Keys.TAB);
		isElementDisplayed("add_phoneNum_error");

		isElementDisplayed("enter_manually");
		msg.log("Enter Manually is displayed");
	}

	public void mobile_verifyAddressBookFeilds() {
		isElementDisplayed("button_Add_New_Address");
		element("button_Add_New_Address").click();
		msg.log("Add new Address button is clicked");

		isElementDisplayed("label_addAddress");

		isElementDisplayed("textbox_addressName");
		element("textbox_addressName").click();
		element("textbox_address_FirstName").click();
		isElementDisplayed("addressName_error");

		isElementDisplayed("textbox_address_FirstName");
		element("textbox_address_FirstName").click();
		element("textbox_address_LastName").click();
		isElementDisplayed("add_firstName_error");

		isElementDisplayed("textbox_address_LastName");
		element("textbox_address_LastName").click();
		element("textbox_address_PostalCode").click();
		isElementDisplayed("add_lastName_error");

		isElementDisplayed("textbox_address_PostalCode");
		element("textbox_address_PostalCode").click();
		element("textbox_address_Phone").click();
		isElementDisplayed("add_postalCode_error");

		isElementDisplayed("textbox_address_Phone");
		element("textbox_address_Phone").click();
		element("textbox_address_PostalCode").click();
		isElementDisplayed("add_phoneNum_error");

		isElementDisplayed("enter_manually");
		msg.log("Enter Manually is displayed");
	}

	public void verifyErrorMsgWhenInvalidPostalCodeIsEntered(String errMsg, String enterManuallyMsg) {
		isElementDisplayed("textbox_address_PostalCode");
		sendText(element("textbox_address_PostalCode"), "121212");
		msg.log("Pincode entered: 121212");
		isElementDisplayed("find_address");
		element("find_address").click();
		msg.log("User clicks on find Address");
		isElementDisplayed("add_postalCode_error");
		msg.log(element("add_postalCode_error").getText());
		assertEquals(element("add_postalCode_error").getText().contains(errMsg), true, "Error msg is not displayed");
		msg.log("Error msg is displayed");
		wait.waitForElementToBeVisible(element("addressNotFound"));
		msg.log(element("addressNotFound").getText());
		isElementDisplayed("addressNotFound");
		assertEquals(element("addressNotFound").getText().contains(enterManuallyMsg), true,
				"Address not found error msg is not displayed");
		msg.log("Address not found error msg is displayed");

	}

	public void verifyWeHaveFoundMsgWhenValidPostalCodeIsEntered(String zipCode) {
		isElementDisplayed("textbox_address_PostalCode");
		sendText(element("textbox_address_PostalCode"), zipCode);
		msg.log(zipCode + " is entered");
		isElementDisplayed("find_address");
		clickUsingJS(element("find_address"));
		msg.log("User clicks on find Address");
		isElementDisplayed("weHaveFound");
		msg.log(element("weHaveFound").getText());
		assertEquals(element("weHaveFound").getText().contains("we have found the following addresses"), true,
				"Msg is not displayed");
		msg.log("Msg is diaplayed");
	}

	public void clickOnEnterManuallyLink() {
		isElementDisplayed("enter_manually");
		clickUsingJS(element("enter_manually"));
		msg.log("User clicks on 'enter manually' link");
	}

	public void verifyEnterManuallyFields() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("company_text");
			isElementDisplayed("textbox_address1");
			element("textbox_address1").click();
			element("address2_text").click();
			isElementDisplayed("address1_error");

			isElementDisplayed("address2_text");

			isElementDisplayed("textbox_city");
			element("textbox_city").click();
			element("textbox_address_Phone").click();
			isElementDisplayed("city_error_msg");

			isElementDisplayed("country_text");
		} else {
			isElementDisplayed("company_text");

			isElementDisplayed("textbox_address1");
			element("textbox_address1").sendKeys(Keys.TAB);
			isElementDisplayed("address1_error");

			isElementDisplayed("address2_text");

			isElementDisplayed("textbox_city");
			element("textbox_city").sendKeys(Keys.TAB);
			isElementDisplayed("city_error_msg");
			isElementDisplayed("country_text");
		}
		assertTrue(isElementDisplayed("textbox_FindAddress"), "[ASSERTION FAILED] : Find Address field is not shown!!");
		msg.pass("Find Address field is shown!!");
	}

	public void expandMyAccountOptionsInMobile() {
		hardWait(2);
		scrollUpToPage();
		wait.waitForElementToBeVisible(element("select_myAccount"));
		isElementDisplayed("select_myAccount");
		clickUsingJS(element("select_myAccount"));
		msg.log("My account option is expanded ");
	}

	public void backButton() {
		backButton();
	}

	public void mobileBackButton() {
		executeJavascript("window.history.back();");
	}

	public void clickToAddAddressButton() {
		isElementDisplayed("button_Add_New_Address");
		try {
			element("button_Add_New_Address").click();
		} catch (Exception e) {
			clickByJavascript(element("button_Add_New_Address"));
		}
		msg.log("Add new Address button is clicked");
	}

	public void verifyLocaleInCountryDropdown(String country) {
		isElementDisplayed("dropDown_Country");
		msg.log("Country drop down is displayed!!");
		element("dropDown_Country").click();
		msg.log("Country drop down is clicked!!");
		boolean flag = false;
		for (WebElement e : elements("dropDown_Country_option")) {
			if (e.getText().equalsIgnoreCase(country)) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, "[ASSERTION FAILED]: " + country + " is not present in country drop down!!");
	}

	public void closeAddressModal() {
		element("button_Close").click();
		msg.log("Close button of add address modal window is clicked!!");
	}

	public void verifyLoginFormOnKateSpadeRoEAccountPage(String loginAccountHeader, String emailAddressPlaceHolderText,
			String passwordPlaceHolderText, String rememberMeCheckBoxText, String forgotPasswordLinkText,
			String signInButtonText, String createAnAccountButtonText) {
		Assert.assertEquals(element("label_signInYourEmail").getText(), loginAccountHeader,
				"[ASSERTION FAILED]: '" + loginAccountHeader + "' 'Login Account' header is NOT appearing !!!");
		msg.pass("'" + loginAccountHeader + "' 'Login Account' header is appearing !!!");

		Assert.assertEquals(element("input_loginUsername").getAttribute("placeholder"), emailAddressPlaceHolderText,
				"[ASSERTION FAILED]: '" + emailAddressPlaceHolderText
						+ "' 'Email Address' placeholder is NOT appearing !!!");
		msg.pass("'" + emailAddressPlaceHolderText + "' 'Email Address' placeholder is appearing !!!");

		Assert.assertEquals(element("input_loginPassword").getAttribute("placeholder"), passwordPlaceHolderText,
				"[ASSERTION FAILED]: '" + passwordPlaceHolderText + "' 'Password' placeholder is NOT appearing !!!");
		msg.pass("'" + passwordPlaceHolderText + "' 'Password' placeholder is appearing !!!");

		Assert.assertEquals(element("label_rememberMe").getText(), rememberMeCheckBoxText, "[ASSERTION FAILED]: '"
				+ rememberMeCheckBoxText + "' 'Remember Me' checkbox text is NOT appearing !!!");
		msg.pass("'" + rememberMeCheckBoxText + "' 'Remember Me' checkbox text is appearing !!!");

		Assert.assertEquals(element("lnk_forgotPassword").getText().trim(), forgotPasswordLinkText,
				"[ASSERTION FAILED]: '" + forgotPasswordLinkText + "' 'Forgot Password' linktext is NOT appearing !!!");
		msg.pass("'" + forgotPasswordLinkText + "' 'Forgot Password' linktext is appearing !!!");

		Assert.assertEquals(element("btn_signIn").getText().trim(), signInButtonText,
				"[ASSERTION FAILED]: '" + signInButtonText + "' 'Sign In' button text is NOT appearing !!!");
		msg.pass("'" + signInButtonText + "' 'Sign In' button textt is appearing !!!");

		Assert.assertEquals(element("btn_createAnAccount").getText(), createAnAccountButtonText, "[ASSERTION FAILED]: '"
				+ createAnAccountButtonText + "' 'Create An Account' button text is NOT appearing !!!");
		msg.pass("'" + createAnAccountButtonText + "' 'Create An Account' button text is appearing !!!");

		msg.pass("Verified Login Form on Kate Spade RoE Account page");
	}

	public void verifyFieldsOnAddAddressModal() {
		isElementDisplayed("label_addAddress");
		Assert.assertTrue(isElementDisplayed("textbox_addressName"), "[ASSERTION FAILED] : Address Name field is not shown!!");
		msg.pass("Address Name field is shown!!");

		Assert.assertTrue(isElementDisplayed("textbox_address_FirstName"),
				"[ASSERTION FAILED]: First Name field is NOT shown !!!");
		msg.pass("First Name field is shown !!");

		Assert.assertTrue(isElementDisplayed("textbox_address_LastName"),
				"[ASSERTION FAILED] : Last Name field is not shown!!");
		msg.pass("Last Name field is shown!!");

		Assert.assertTrue(isElementDisplayed("dropDown_Country"), "[ASSERTION FAILED] : Country field is not shown!!");
		msg.pass("Country field is shown!!");
		
		Assert.assertTrue(isElementDisplayed("textbox_address_Phone"),
				"[ASSERTION FAILED] : Phone number field is not shown!!");
		msg.pass("Phone number field is shown!!");

		Assert.assertTrue(isElementDisplayed("enter_manually"), "[ASSERTION FAILED] : Enter manually field is not shown!!");
		msg.pass("Enter manually field is displayed!!");

		Assert.assertTrue(isElementDisplayed("textbox_FindAddress"),
				"[ASSERTION FAILED] : Find Address by typing atleast 3 characters field is not shown!!");
		msg.pass("Field with placeholder " + element("textbox_FindAddress").getAttribute("placeholder") + "is shown!!");
	}

	public void clickOnSearchLink() {
		element("search_again").click();
		msg.log("Search link is clicked!!");
	}

	public void verifyAddressFeildsNotDisplayed() {
		hardWait(2);

		assertTrue(isElementNotDisplayed("text_FieldsCollapsed", "company"),
				"[ASSERTION FAILED] : Company text field is not shown !!!");
		assertTrue(isElementNotDisplayed("text_FieldsCollapsed", "Address 1*"),
				"[ASSERTION FAILED] : Address 1 text field is not shown !!!");
		assertTrue(isElementNotDisplayed("text_FieldsCollapsed", "Address 2"),
				"[ASSERTION FAILED] : Address 2 text field is not shown !!!");
		assertTrue(isElementNotDisplayed("text_FieldsCollapsed", "City*"),
				"[ASSERTION FAILED] : City text field is not shown !!!");
		assertTrue(isElementNotDisplayed("text_FieldsCollapsed", "County"),
				"[ASSERTION FAILED] : Country text field is not shown !!!");
		assertTrue(isElementNotDisplayed("text_FieldsCollapsed", "Postal Code*"),
				"[ASSERTION FAILED] : Postal Code field is not shown !!!");
		msg.pass("Address fields are collapsed !!!");
	}
	
	public void verifyNewAddressFindFieldByName(String placeholder) {
		isElementDisplayed("textbox_FindAddress");
		
		if(!getCurrentURL().contains("CO")) {
			Assert.assertEquals(
					element("textbox_FindAddress").getAttribute("placeholder").toUpperCase().trim(),
					placeholder.toUpperCase().trim(), 
					"[ASSERTION FAILED]: 'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' "
							+ "field is NOT appearing !!!");
		}
		msg.pass("New Address Find input field with '" + placeholder + "' is displayed !!!");
	}
	
	@SuppressWarnings("unused")
	public void verifyPositionOfNewAddressFindField() {
		int yCoordinateOfCountry = element("dropDown_Country").getLocation().getY();
		int yCoordinateOfNewField = element("textbox_FindAddress").getLocation().getY();
		int yCoordinateOfPhone = element("textbox_address_Phone").getLocation().getY();

		Assert.assertTrue(yCoordinateOfPhone > yCoordinateOfNewField,
				"[ASSERTION FAILED] : Phone field is below New Address Find Field!!");

		Assert.assertTrue(isElementDisplayed("input_NewFindAdd_Relative_To_Country"),
				"[ASSERTION FAILED] : Country is above New Address Find Field!!");
	}

	public void verifyPositionOfEnterManuallyLink() {
		int yCoordinateOfEnter = element("enter_manually").getLocation().getY();
		int yCoordinateOfNewAdd = element("textbox_FindAddress").getLocation().getY();
		
		Assert.assertTrue(yCoordinateOfEnter > yCoordinateOfNewAdd,
				"[ASSERTION FAILED]: Enter manually link is not underneath New address find text box !!!");
		msg.pass("Enter manually link is underneath New address find text box with placeholder '"
				+ element("textbox_FindAddress").getAttribute("placeholder") + "'");
	}

	public void verifyChangeOfEnterManuallyToSearchOnClick() {
		executeJavascript("document.getElementsByClassName('t046-close')[0].click()");
		msg.log("Clicked on 'Close' icon of Feedback dialog box");
		
		element("enter_manually").click();
		msg.log("Clicked on 'enter manually' link");

		hardWait(2);
		assertTrue(isElementDisplayed("search_again"),
				"[ASSERTION FAILED] : Search link is not displayed after clicking Enter maually link!!!!!!!!! ");
		msg.pass("Search link is displayed after clicking Enter maually link!!!!!!!!! ");
	}

	public void verifySuggestionsAppearWhenAtleast3CharInput() {
		isElementDisplayed("textbox_FindAddress");
		
		element("textbox_FindAddress").click();
		msg.log("Clicked on 'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input field");
		
		element("textbox_FindAddress").sendKeys("L");
		hardWait(2);
		try {
			Assert.assertTrue(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"));
		} catch (AssertionError | Exception ex) {
			msg.log("Suggestion must not appear for One char!!");
		}
		
		element("textbox_FindAddress").sendKeys("O");
		hardWait(2);
		try {
			Assert.assertTrue(element("dropdown_AddressSuggest").getAttribute("style").
					contains("display: none;"));
		} catch (AssertionError e) {
			msg.fail("Suggestion must not appear for Two char!!");
		}
		
		element("textbox_FindAddress").sendKeys("N");
		hardWait(2);	
		try {
			Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
					"[ASSERTION FAILED] : Suggestion DropDown of Addresses NOT shown when THREE characters are entered");
			msg.pass("Suggestion DropDown of Addresses shown when Three characters are entered !!!");
		} catch (AssertionError er) {
			Assert.fail("AUTO SUGGESTIONS for Addresses are NOT appearing even when 3 CHARACTERS are typed !!!");
		}
	}
	
	public void verifyAddressSearchIsNotCaseSensitive(String city) {
		isElementDisplayed("textbox_FindAddress");
		try {
			element("textbox_FindAddress").click();
		} catch (Exception e) {
			clickByJavascript(element("textbox_FindAddress"));
		}
		
		element("textbox_FindAddress").clear();
		for (int index = 0; index < city.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(city.toLowerCase().charAt(index)));
			hardWait(1);
		}
		
		hardWait(1);
		isElementDisplayed("dropdown_AddressSuggest");
		Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
				"[ASSERTION FAILED]: Suggestion DropDown of Addresses not shown when " + city
						+ " is entered in lower case");
		msg.pass("Suggestion DropDown of Addresses shown when " + city + " is entered in lower case");
		
		for (WebElement elem : elements("dropdown_ListOfAddress")) {
			System.out.println("Title: " + elem.getAttribute("title"));
			assertTrue(elem.getAttribute("title").contains(city),
					"[ASSERTION FAILED]: Suggestions do not contain " + city + " in addresses!!!!");
			msg.pass("Suggestions contain " + city + " in addresses !!!");
		}
		msg.log("Results are verified for lowercase !!!");
		
		element("textbox_FindAddress").clear();
		for (int index = 0; index < city.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(city.toUpperCase().charAt(index)));
			hardWait(1);
		}
		
		hardWait(1);
		isElementDisplayed("dropdown_AddressSuggest");
		Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
				"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + city
						+ " is entered in upper case");
		msg.pass("Suggestion DropDown of Addresses shown when " + city + " is entered in upper case");
		
		for (WebElement e : elements("dropdown_ListOfAddress")) {
			Assert.assertTrue(e.getAttribute("title").contains(city),
					"[ASSERTION FAILED] : Suggestions do not contain " + city + " in addresses!!!!");
			msg.pass("Suggestions contain " + city + " in addresses!!!!");
		}
		msg.pass("Results are verified for upper case!!!");
	}

	public void verifyPostCodesSearchByDifferentFormats(String zip) {
		msg.log("Post code " + zip + " entered with space");
		isElementDisplayed("textbox_FindAddress");
		try {
			element("textbox_FindAddress").click();
		} catch (Exception e) {
			clickByJavascript(element("textbox_FindAddress"));
		}
		element("textbox_FindAddress").clear();
		for (int index = 0; index < zip.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(zip.charAt(index)));
			hardWait(1);
		}
		
		element("textbox_FindAddress").sendKeys(Keys.SPACE);
		hardWait(2);
		isElementDisplayed("dropdown_AddressSuggest");
		assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
				"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + zip
						+ " is entered with space");
		msg.pass("Suggestion DropDown of Addresses shown when " + zip + " is entered with space");
		
		for (WebElement e : elements("dropdown_ListOfAddress")) {
			assertTrue(e.getAttribute("title").contains(zip),
					"[ASSERTION FAILED] : Suggestions do not contain " + zip + " in addresses!!!!");
			msg.pass("Suggestions contain " + zip + " in addresses!!!!");
			break;
		}
		msg.log("Results are verified for Post code entered with space!!!");

		msg.log("Post code" + zip + "  entered without space");
		String zipWithoutSpace = zip.replace(" ", "");
		element("textbox_FindAddress").clear();
		for (int index = 0; index < zipWithoutSpace.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(zipWithoutSpace.charAt(index)));
			hardWait(1);
		}
		
		hardWait(2);
		isElementDisplayed("dropdown_AddressSuggest");
		Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
				"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + zip
						+ " is entered without space");
		msg.pass("Suggestion DropDown of Addresses shown when " + zip + " is entered without space");
		
		for (WebElement e : elements("dropdown_ListOfAddress")) {
			Assert.assertTrue(e.getAttribute("title").contains(zip),
					"[ASSERTION FAILED] : Suggestions do not contain " + zip + " in addresses!!!!");
			msg.pass("Suggestions contain " + zip + " in addresses!!!!");
		}
		msg.log("Results are verified for Post code entered without space!!!");

		msg.log("Post code" + zip + "  entered in upper case");
		String zipUpper = zip.toUpperCase();
		element("textbox_FindAddress").clear();
		for (int index = 0; index < zipUpper.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(zipUpper.charAt(index)));
			hardWait(1);
		}
		
		hardWait(2);
		isElementDisplayed("dropdown_AddressSuggest");
		Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
				"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + zip
						+ " is entered in upper case");
		msg.pass("Suggestion DropDown of Addresses shown when " + zip + " is entered in upper case");
		
		for (WebElement e : elements("dropdown_ListOfAddress")) {
			Assert.assertTrue(e.getAttribute("title").contains(zip),
					"[ASSERTION FAILED] : Suggestions do not contain " + zip + " in addresses!!!!");
			msg.pass("Suggestions contain " + zip + " in addresses!!!!");
		}
		msg.log("Results are verified for Post code entered in upper case!!!");

		msg.log("Post code" + zip + "  entered in lower case");
		String zipLower = zip.toLowerCase();
		element("textbox_FindAddress").clear();
		for (int index = 0; index < zipLower.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(zipLower.charAt(index)));
			hardWait(1);
		}
		
		hardWait(2);
		isElementDisplayed("dropdown_AddressSuggest");
		Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
				"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + zip
						+ " is entered in lower case");
		msg.pass("Suggestion DropDown of Addresses shown when " + zip + " is entered in lower case");
		
		for (WebElement e : elements("dropdown_ListOfAddress")) {
			Assert.assertTrue(e.getAttribute("title").contains(zip),
					"[ASSERTION FAILED] : Suggestions do not contain " + zip + " in addresses!!!!");
			msg.pass("Suggestions contain " + zip + " in addresses!!!!");
		}
		msg.pass("Results are verified for Post code entered in lower case!!!");
	}

	public void verifyAddressFieldsPopulateAndInUpperCase(String city) {
		isElementDisplayed("textbox_FindAddress");
		try {
			element("textbox_FindAddress").click();
		} catch (Exception e) {
			clickByJavascript(element("textbox_FindAddress"));
		}
		msg.log("Clicked on 'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input field");
		
		element("textbox_FindAddress").clear();
		msg.log("CLEARED 'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input field");
		
		for (int index = 0; index < city.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(city.charAt(index)));
			hardWait(1);
		}
		
		hardWait(2);
		try {
			isElementDisplayed("dropdown_AddressSuggest");
			Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
					"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + city
							+ " is entered !!!");
			msg.pass("Suggestion DropDown of Addresses shown when " + city + " is entered !!!");
			
			String addressFound = elements("dropdown_ListOfAddress").get(0).getText().toUpperCase();
			msg.log("Address Found: " + addressFound);
			
			try {
				hardWait(2);
				msg.log("List of Address: " + elements("dropdown_ListOfAddress").size());
				
				while(elements("dropdown_ListOfAddress").size() > 0) {
					addressFound = elements("dropdown_ListOfAddress").get(0).getText().toUpperCase();
					msg.log("Address Found: " + addressFound);
					
					clickUsingJS(elements("dropdown_ListOfAddress").get(0));
					msg.log("Selected the matched address from PCA Lookup list of address");
					
					hardWait(2);
					msg.log("List of suggested Address: " + elements("dropdown_ListOfAddress").size());
				}
			} catch (TimeoutException ex) {
				msg.log("Selected all the Address fields from PCA LookUp list");
			}
			
			msg.log("Actual Address Field: " + executeJavascriptReturnsString("return $('#dwfrm_profile_address_address1').val()").toUpperCase());
			msg.log("Expected Address Field: " + addressFound);
			Assert.assertTrue(addressFound.toLowerCase().contains(
					executeJavascriptReturnsString("return $('#dwfrm_profile_address_address1').val()").toLowerCase()),
					"[ASSERTION FAILED]: Address Field is NOT Auto-Populated as expected !!!");
			msg.pass("Address field is populated !!!");
			
			msg.log("Actual City Field: " + executeJavascriptReturnsString("return $('#dwfrm_profile_address_city').val()").toUpperCase());
			Assert.assertTrue(addressFound.toLowerCase().contains(
					executeJavascriptReturnsString("return $('#dwfrm_profile_address_city').val()").toLowerCase()), 
					"[ASSERTION FAILED]: City Field is NOT Auto-Populated as expected !!!");
			msg.pass("City field is populated !!!");
			
			msg.log("Actual Postal Code Field: " + executeJavascriptReturnsString("return $('#dwfrm_profile_address_postal').val()").toUpperCase());
			Assert.assertTrue(addressFound.toLowerCase().contains(
					executeJavascriptReturnsString("return $('#dwfrm_profile_address_postal').val()").toLowerCase()), 
					"[ASSERTION FAILED]: Postal Code Field is NOT Auto-Populated as expected !!!");
			msg.pass("Postal Code field is populated !!!");
		} catch(AssertionError er) {
			Assert.fail("Auto suggestions for Addresses are NOT appearing even when 3 characters are typed !!!");
		}
	}

	public void clickCrossBtn() {
		try {
			element("cross_btn").click();
		} catch (Exception e) {
			clickByJavascript(element("cross_btn"));
		}
		msg.log("Cross btn clicked to close address modal");
	}

	public void verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(String addressName, String firstName,
			String lastName, String postalCode, String phoneNumber) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForPageToLoadCompletely();
			hardWait(2);
		}
		isElementDisplayed("label_addAddress");
		
		isElementDisplayed("textbox_addressName");
		sendText(element("textbox_addressName"), "");
		msg.log(addressName + " is entered");
		
		isElementDisplayed("textbox_address_FirstName");
		sendText(element("textbox_address_FirstName"), firstName);
		msg.log(firstName + " is entered");
		
		isElementDisplayed("textbox_address_LastName");
		sendText(element("textbox_address_LastName"), lastName);
		msg.log(lastName + " is entered");
		
		isElementDisplayed("textbox_address_Phone");
		sendText(element("textbox_address_Phone"), phoneNumber);
		msg.log(phoneNumber + " is entered");
		
		isElementDisplayed("button_apply_AddAddress");
		element("button_apply_AddAddress").click();
		msg.log("User clicks on Apply Button");
	}

	public void verifyNewFindAddressFieldIsNotRequiredFieldWhenExpanded(String addressName, String firstName,
			String lastName, String postalCode, String phoneNumber, String address1, String city) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		isElementDisplayed("label_addAddress");
		hardWait(4);
		
		isElementDisplayed("textbox_addressName");
		sendText(element("textbox_addressName"), addressName);
		msg.log(addressName + " is entered");
		
		isElementDisplayed("textbox_address_FirstName");
		sendText(element("textbox_address_FirstName"), firstName);
		msg.log(firstName + " is entered");
		
		isElementDisplayed("textbox_address_LastName");
		sendText(element("textbox_address_LastName"), lastName);
		msg.log(lastName + " is entered");
		
		clickOnEnterManuallyLink();
		
		isElementDisplayed("textbox_address_Phone");
		sendText(element("textbox_address_Phone"), phoneNumber);
		msg.log(phoneNumber + " is entered");
		
		isElementDisplayed("textbox_address1");
		sendText(element("textbox_address1"), address1);
		msg.log(address1 + "is entered");
		
		isElementDisplayed("textbox_city");
		sendText(element("textbox_city"), city);
		msg.log(city + "is entered");
		
		isElementDisplayed("textbox_address_PostalCode");
		sendText(element("textbox_address_PostalCode"), postalCode);
		msg.log(postalCode + " is entered");
		
		isElementDisplayed("button_apply_AddAddress");
		element("button_apply_AddAddress").click();
		msg.log("Clicked on 'Apply' button");
		
		hardWait(2);
		try {
			Assert.assertTrue(isElementNotDisplayed("label_addAddress"), 
					"[ASSERTION FAILED] : New Address is NOT added !!!");
			msg.pass("'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input "
					+ "field is NOT a required field when address fields are expanded !!!");
		} catch(AssertionError er) {
			Assert.fail("");
		}
	}

	public void verifyErrorMessageForAddressField(String errormsg) {
		isElementDisplayed("span_addressFindError");
		
		Assert.assertEquals(element("span_addressFindError").getText().toLowerCase(), 
				errormsg.toLowerCase(), 
				"[ASSERTION FAILED]: Error message is NOT appearing for 'FIND ADDRESS' input field !!!");
		msg.pass("'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input "
				+ "field is a REQUIRED field when address fields are collapsed !!!");
	}

	public void verifyUserCanAddANewCard() {
		isElementDisplayed("button_addNewAddress");
		element("button_addNewAddress").click();
		msg.log("Clicked on 'ADD A CREDIT/DEBIT CARD' button");

		isElementDisplayed("dialogContainer_addCreditCardLabel");
		isElementDisplayed("crossbutton_addcredit");
		isElementDisplayed("addcredit_typefield");
	}

	public void verifyUserCanRemoveSavedCard(int i) {
		int noOfCardsBefore = elements("creditcard_savedHeader").size();
		
		isElementDisplayed("button_DeleteCard");
		clickByJavascript(elements("button_DeleteCard").get(i));
		msg.log("Clicked on 'Delete' button of Saved card");

		// Wait for confirmation alert box
		hardWait(2);

		clickByJavascript(element("button_DeleteCard"));

		// Wait for confirmation alert box
		hardWait(2);

		clickByJavascript(element("button_ConfirmDelete"));

		// Wait for card to be deleted
		hardWait(2);

		int noOfCardsAfter;
		try {
			noOfCardsAfter = elements("creditcard_savedHeader").size();
		} catch (Exception | AssertionError e) {
			noOfCardsAfter = 0;
		}
		Assert.assertTrue(noOfCardsBefore > noOfCardsAfter, "[ASSERTION FAILED] : Saved Card is not removed!!");
		msg.pass("Saved Cards are removed!!");
	}

}