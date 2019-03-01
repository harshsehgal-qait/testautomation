package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class ProductPaymentPage extends GetPage {

	String[] layoutTags = { "objects2" };
	WebDriver driver;
	static String totalOrder;

	public ProductPaymentPage(WebDriver driver) {
		super(driver, "Katespade/ProductPaymentPage");
	}

	public void user_enter_card_details(String cardno, String expiryDate, String cardType, String cvv) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_billing");
		msg.log("User is on billing page");
		isElementDisplayed("radioCreditCard");
		
		isElementDisplayed("creditCardOwner");
		sendText(element("creditCardOwner"), cardType);
		msg.log("user enter card name type" + cardType);

		isElementDisplayed("card_no");
		sendText(element("card_no"), cardno);
		msg.log(cardno);

		isElementDisplayed("expiryDate");
		sendText(element("expiryDate"), expiryDate);
		msg.log("user enter card expriry date" + expiryDate);

		isElementDisplayed("card_cvv");
		sendText(element("card_cvv"), cvv);
		msg.log("user enter card cvv" + cvv);
	}

	public void user_submit_payment() {
		isElementDisplayed("chk_sameAsShipping");
		clickUsingJS(element("chk_sameAsShipping"));
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("btn_placeOrder");
		executeJavascript("$('#billing-submit').click();");
		msg.log("submit order");
	}

	public void submitYourOrder() {
		isElementDisplayed("ship_address");
		isElementDisplayed("delivery_method");
		executeJavascript("$('.js-order-review-btn-submit').eq(2).click()");
	}

	public void verifyStep2BillingIsActive() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(5);
		}
		isElementDisplayed("lbl_stepNo");
		Assert.assertEquals(element("lbl_stepNo").getCssValue("background-color"), "rgba(72, 168, 66, 1)",
				"[ASSERTION FAILED]: Color of the dot is not green");
		msg.log("Color of the dot is green");
		msg.pass("STEP 2 i.e., Billing is ACTIVE");
	}

	public void verifyFieldsFromShippingPageArePopulatedIntoRelevantFieldsOfBillingPage(String fname, String lname,
			String postalCode, String phoneNo) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("input_fname");
		assertEquals(element("input_fname").getAttribute("value"), fname,
				"[Assert Fail]: First Name is not pre populated");
		msg.log("[Assert Pass]: Verified First name is pre populated");

		isElementDisplayed("input_lname");
		assertEquals(element("input_lname").getAttribute("value"), lname,
				"[Assert Fail]: Last name is not pre poulated");
		msg.log("[Assert Pass]: Verified last name is pre populated");

		isElementDisplayed("input_postalCode");
		assertEquals(element("input_postalCode").getAttribute("value"), postalCode,
				"[Assert Fail]: Postal Code is not pre populated");
		msg.log("[Assert Pass]: Verified postal code is pre populated");

		isElementDisplayed("input_phoneNo");
		assertEquals(element("input_phoneNo").getAttribute("value"), phoneNo,
				"[Assert Fail]: Phone number is not pre populated");
		msg.log("[Assert Pass]: Verified phone number is pre populated");

	}

	public void clickSameAsShippingAddressCheckboxOnBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("chk_sameAsShipping");
		clickUsingJS(element("chk_sameAsShipping"));
		msg.log("User clicked on the same as shipping checkbox");
	}

	public void verifyContinueToPlaceOrderButtonIsDisabledInBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_disabledPalceOrder");
		msg.log("Continue to place order is disabled");
	}

	public void verifyFieldsOfBillingPageAreEmpty() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(4);
		}
		isElementDisplayed("input_fname");
		assertEquals(element("input_fname").getAttribute("value").equals(""), true,
				"[Assert Fail]: First Name is  pre populated");
		msg.log("[Assert Pass]: Verified First name is not pre populated");

		isElementDisplayed("input_lname");
		assertEquals(element("input_lname").getAttribute("value").equals(""), true,
				"[Assert Fail]: Last name is  pre poulated");
		msg.log("[Assert Pass]: Verified last name is not pre populated");

		isElementDisplayed("input_postalCode");
		assertEquals(element("input_postalCode").getAttribute("value").equals(""), true,
				"[Assert Fail]: Postal Code is pre populated");
		msg.log("[Assert Pass]: Verified postal code is not pre populated");

		isElementDisplayed("input_phoneNo");
		assertEquals(element("input_phoneNo").getAttribute("value").equals(""), true,
				"[Assert Fail]: Phone number is  pre populated");
		msg.log("[Assert Pass]: Verified phone number is not pre populated");

	}

	public void verifyFieldsOfBillingPageAreNotEmpty() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(4);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		isElementDisplayed("input_fname");
		assertEquals(element("input_fname").getAttribute("value").equals(""), false,
				"[Assert Fail]: First Name is not pre populated");
		msg.log("[Assert Pass]: Verified First name is pre populated");

		isElementDisplayed("input_lname");
		assertEquals(element("input_lname").getAttribute("value").equals(""), false,
				"[Assert Fail]: Last name is not pre poulated");
		msg.log("[Assert Pass]: Verified last name is pre populated");

		isElementDisplayed("input_postalCode");
		assertEquals(element("input_postalCode").getAttribute("value").equals(""), false,
				"[Assert Fail]: Postal Code is not pre populated");
		msg.log("[Assert Pass]: Verified postal code is pre populated");

		isElementDisplayed("input_phoneNo");
		assertEquals(element("input_phoneNo").getAttribute("value").equals(""), false,
				"[Assert Fail]: Phone number is not pre populated");
		msg.log("[Assert Pass]: Verified phone number is pre populated");

	}

	public void enterBillingAddressDetails(String fname, String lname, String address, String zipcode, String phno,
			String city) {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("input_fname");
		sendText(element("input_fname"), fname);
		element("input_fname").sendKeys(Keys.TAB);
		msg.log("Entered first name" + fname);

		isElementDisplayed("input_lname");
		sendText(element("input_lname"), lname);
		element("input_lname").sendKeys(Keys.TAB);
		msg.log("Entered last name" + lname);

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en_GB")) {
			try {
				isElementDisplayed("link_enterManually");
				clickUsingJS(element("lnk_enterManually"));
				msg.log("Clicked on 'enter manually' link");
			} catch (AssertionError er) {
				msg.log("FIND ADDRESS input Field is ALREADY EXPANDED !!!");
			}
		}
		isElementDisplayed("input_city");
		sendText(element("input_city"), city);
		element("input_city").sendKeys(Keys.TAB);
		msg.log("City" + city);

		isElementDisplayed("input_postalCode");
		sendText(element("input_postalCode"), zipcode);
		element("input_postalCode").sendKeys(Keys.TAB);
		msg.log("User enters zip code" + zipcode);

		isElementDisplayed("input_address");
		sendText(element("input_address"), address);
		element("input_address").sendKeys(Keys.TAB);
		msg.log("Address" + address);

		isElementDisplayed("input_phone");
		sendText(element("input_phone"), phno);
		element("input_phone").sendKeys(Keys.TAB);
		msg.log("User enters city" + phno);

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}

		isElementDisplayed("btn_placeOrder");
		element("btn_placeOrder").click();
		msg.log("User clicks on place order button");
	}

	public void verifyErrorMessageDisplayedForRequiredFields() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(4);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		isElementDisplayed("input_errorFname");
		isElementDisplayed("input_errorLname");
		isElementDisplayed("input_errorPostalCode");
		isElementDisplayed("input_errorPhoneNo");

		msg.log("Verified: Error message is displayed for required fields");
	}

	public void selectFirstSavedCardInTheAccount(String cardName) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(2);
			scrollWindow(0, 300);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		isElementDisplayed("drpDwn_savedCards");
		element("drpDwn_savedCards").click();
		isElementDisplayed("select_savedCard");
		selectOptionFromDropDownList("select_savedCard", cardName);
		msg.log("First saved card is selected");
		hardWait(2);

	}

	public void verifyErrorMessageIsNotDisplayed() {
		wait.waitForPageToLoadCompletely();
		isElementNotDisplayed("lbl_error");
		msg.log("Error message is not displayed");
	}

	public void clickAddToAddressBookCheckbox() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("chk_addToAddressBook");
		clickUsingJS(element("chk_addToAddressBook"));
		msg.log("Add to address book checkbox button is clicked");
	}

	public void clickContinueToPlaceOrderButton() {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("btn_placeOrder");
		executeJavascript("$('#billing-submit').click();");
		msg.log("Continue to place order button is clicked");

	}

	public void verifyCreditCardIsTheByDefaultPaymentMethodOnTheBillingPage() {
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeVisible(element("radioCreditCard"));
		isElementDisplayed("radioCreditCard");
		boolean radioBtnSelected = executeJavascriptReturnsBoolean(" return $('#is-CREDIT_CARD').is(':checked')");
		assertEquals(radioBtnSelected, true, "[Assert Fail] : Credeit card payment method is not selected by default ");
		msg.log("Credit card payment method is by default selected");
	}

	public void verifyPaymentMethodFieldsOnBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("creditCardOwner");
		isElementDisplayed("card_no");
		isElementDisplayed("startDate");
		isElementDisplayed("expiryDate");
		isElementDisplayed("issueNumber");
		isElementDisplayed("card_cvv");
		msg.log("Verfied: Fields for the payment method are displayed");
	}

	public void verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank() {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("creditCardOwner");
		element("creditCardOwner").click();
		element("creditCardOwner").sendKeys(Keys.TAB);
		isElementDisplayed("creditCardOwnerError");

		isElementDisplayed("card_no");
		scrollDown(element("card_no"));
		element("card_no").click();
		element("card_no").sendKeys(Keys.TAB);
		isElementDisplayed("cardNumberError");

		wait.waitForElementToBeClickable(element("expiryDate"));
		isElementDisplayed("expiryDate");
		// element("expiryDate").click();
		element("expiryDate").sendKeys(Keys.TAB);
		isElementDisplayed("expiryDateError");

		isElementDisplayed("card_cvv");
		element("card_cvv").click();
		element("card_cvv").sendKeys(Keys.TAB);
		isElementDisplayed("securityCodeError");

		msg.log("Verified: Error Message is displayed when payment method fields are left  blank");
	}

	public void mobile_verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank() {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("creditCardOwner");
		element("creditCardOwner").click();
		element("card_no").click();
		isElementDisplayed("creditCardOwnerError");

		isElementDisplayed("card_no");
		scrollDown(element("card_no"));
		element("card_no").click();
		element("startDate").click();
		isElementDisplayed("cardNumberError");

		wait.waitForElementToBeClickable(element("expiryDate"));
		isElementDisplayed("expiryDate");
		element("expiryDate").click();
		element("card_cvv").click();
		isElementDisplayed("expiryDateError");

		isElementDisplayed("card_cvv");
		element("card_cvv").click();
		element("issueNumber").click();
		isElementDisplayed("securityCodeError");

		msg.log("Verified: Error Message is displayed when payment method fields are left  blank");
	}

	public void verifyCardTypeAvailableForPayment(String[] availableCard) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("drpdwn_cardType");
		element("drpdwn_cardType").click();
		msg.log(" Dropdown card type is clicked");

		isElementDisplayed("select_cardType");
		int i = 0;
		while (i < availableCard.length) {
			assertEquals(element("select_cardType").isDisplayed(), true,
					"ASSERT FAILED: " + availableCard[i] + " is not present ");
			logMessage("Verified:" + availableCard[i] + " is displayed");
			i++;
		}
	}

	public void verifyPreviouslySavedCardsAreDisplayedOnBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("drpDwn_savedCards");
		element("drpDwn_savedCards").click();
		isElementDisplayed("select_savedCard");
		int size = elements("select_savedCard").size();
		for (int i = 0; i < size; i++) {
			elements("select_savedCard").get(i).isDisplayed();
			elements("select_savedCard").get(i).getText();
		}
	}

	public void verifyFieldsOfPaymentMethodAreAutoPopulated() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("card_no");
		String cardNo = executeJavascriptReturnsString("return $('#creditCard_number')[0].value");
		hardWait(1);
		msg.log("" + cardNo);
		assertEquals(cardNo.equals(" "), false, "[Asert Fail]: Card no is not auto populate");
		isElementNotDisplayed("cardNumberError");
		msg.log("Card no is auto populate with value : " + cardNo);

		isElementDisplayed("expiryDate");
		String expiryDate = executeJavascriptReturnsString("return $('#adyen_creditCard_expDate')[0].value");
		assertEquals(expiryDate.equals(""), false, "[Asert Fail]: expiry Date is not auto populate");
		isElementNotDisplayed("expiryDateError");
		msg.log("expiry Date is auto populate with value : " + expiryDate);

		isElementDisplayed("card_cvv");
		String card_cvv = executeJavascriptReturnsString(" return $('#creditCard_cvn')[0].value");
		assertEquals(card_cvv.equals(""), false, "[Asert Fail]: card_cvv is not auto populate");
		isElementNotDisplayed("securityCodeError");
		msg.log("card_cvv is auto populate with value : " + card_cvv);

		isElementNotDisplayed("cardTypeError");
	}

	public void enterNameOnCardOnBillingPage(String cardOwnerName) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("creditCardOwner");
		sendText(element("creditCardOwner"), cardOwnerName);
		msg.log("User entered " + cardOwnerName);
	}

	public void verifyShippingAddressIsReflectedInBillingAddress(String zip) {
		waitForLoad();

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		}

		isElementDisplayed("lbl_billing");
		String classname = executeJavascriptReturnsString("return $('.step-2.active').attr('class')");
		msg.log(classname);

		Assert.assertEquals(classname.contains("active"), true, "[ASSERTION FAILED]: User is not on billing page !!!");
		msg.pass("User is on billing page");

		isElementDisplayed("input_postalCode");
		scrollDown(element("input_postalCode"));
		String zipcode = executeJavascriptReturnsString(
				" return $('#dwfrm_billing_billingAddress_addressFields_postal').val()");
		Assert.assertEquals(zipcode, zip, "Address of shipping page does not populated on billing page");
		msg.pass("Address of shipping page did populated on billing page");
	}

	public void clickOnKateSpadeLogo() {
		executeJavascript(" $(window).scrollTop(0);");
		isElementDisplayed("katespade_logo");
		element("katespade_logo").click();
		msg.log("User clicks on katespade logo");
		waitForLoad();
	}

	public void verifyUserIsOnBillingPage() {
		isElementDisplayed("lbl_billing");
		msg.log("User is on billing page");
	}

	public void verifyUserIsAbleToApplyAndRemovePromoCodeInBillingPage(String promoCode) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_subTotal");
		isElementDisplayed("lbl_orderTotal");
		double befPromoCodeSubTotalPrice = Double
				.parseDouble(element("lbl_subTotal").getText().replaceAll("[^0-9\\.]+", ""));
		double befPromoCodeOrderTotalPrice = Double
				.parseDouble(element("lbl_orderTotal").getText().replaceAll("[^0-9\\.]+", ""));
		applyPromoCodeInBillingPage(promoCode);
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeVisible(element("lbl_successCartCouponMsg"));
		double aftPromoCodeSubTotalPrice = Double
				.parseDouble(element("lbl_subTotal").getText().replaceAll("[^0-9\\.]+", ""));
		double aftPromoCodeOrderTotalPrice = Double
				.parseDouble(element("lbl_orderTotal").getText().replaceAll("[^0-9\\.]+", ""));
		assertEquals(befPromoCodeSubTotalPrice > aftPromoCodeSubTotalPrice, true,
				"[Assert Fail]: Promo code is not applied , sub total price is not as expected");
		assertEquals(befPromoCodeOrderTotalPrice > aftPromoCodeOrderTotalPrice, true,
				"[Assert Fail]: Promo code is not applied , order total is not as expected");
		msg.log("Promo code is applied ");
		isElementDisplayed("lbl_successCartCouponMsg");
		msg.log("Coupon successfully applied msg displayed : " + element("lbl_successCartCouponMsg").getText());
		removePromoCodeInBillingPage();
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToDisappear(element("lbl_successCartCouponMsg"));
		double aftPromoCodeRemoveSubTotalPrice = Double
				.parseDouble(element("lbl_subTotal").getText().replaceAll("[^0-9\\.]+", ""));
		double aftPromoCodeRemoveorderTotalPrice = Double
				.parseDouble(element("lbl_orderTotal").getText().replaceAll("[^0-9\\.]+", ""));
		assertEquals(befPromoCodeSubTotalPrice, aftPromoCodeRemoveSubTotalPrice,
				"[Assert Fail]: Promo code is not removed");
		assertEquals(befPromoCodeOrderTotalPrice, aftPromoCodeRemoveorderTotalPrice,
				"[Assert Fail]: Promo code is not removed");
		msg.log("Promo code is removed ");
	}

	public void applyPromoCodeInBillingPage(String promoCode) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("chkbx_applyPromoCode");
		clickUsingJS(element("chkbx_applyPromoCode"));
		msg.log("Apply Promo Code check box is clicked");
		isElementDisplayed("txtbx_enterPromoCode");
		sendText(element("txtbx_enterPromoCode"), promoCode);
		isElementDisplayed("btnApply");
		element("btnApply").click();
		msg.log("Apply button is clicked");
	}

	public void removePromoCodeInBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lnk_sumaryBx_removeCpn");
		clickUsingJS(element("lnk_sumaryBx_removeCpn"));
		msg.log("Remove btn  is clicked");
	}

	public void verifyErrorMsgDisplayedWhenPromoIsNotApplicableInBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("couponError");
		msg.log("Error message is displayed when promo code is not applicable " + element("couponError").getText());
	}

	public void userSelectsCardType(String cardType) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("drpdwn_cardType");
		// element("drpdwn_cardType").click();
		clickUsingJS(element("drpdwn_cardType"));
		msg.log("User clicks on card type to select the option");
		isElementDisplayed("select_cardType");
		selectOptionFromDropDownList("select_cardType", cardType);
		msg.log("First saved card is selected");
	}

	public void applyValidPromoCodeInBillingPage(String promoCode) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("txtbx_enterPromoCode");
		element("txtbx_enterPromoCode").clear();
		sendText(element("txtbx_enterPromoCode"), promoCode);
		isElementDisplayed("btnApply");
		element("btnApply").click();
		msg.log("Apply button is clicked");
		wait.waitForPageToLoadCompletely();
	}

	public void selectPayPalRadioBtnForPaymentInBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("radioBtn_paypal");
		element("radioBtn_paypal").click();
		msg.log("Pay pal radio btn is selected for the payment");
	}

	public void selectKlarnaRadioBtnForPaymentInBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("radioBtn_klarna");
		element("radioBtn_klarna").click();
		msg.log("Klarna radio btn is selected for the payment");
	}

	public void enterKlarnaPaymentDetails(String dob, String houseNo, String houseExtn) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("drpdn_gender");
		isElementDisplayed("txtbx_dob");
		sendText(element("txtbx_dob"), dob);
		msg.log("Date of birth field is set : " + dob);
		isElementDisplayed("txtbx_houseNo");
		sendText(element("txtbx_houseNo"), houseNo);
		msg.log("House number field is set : " + houseNo);

		if (getCurrentURL().contains("Sites-ksEuRoe-Site/en_NL") || getCurrentURL().contains("en-nl")) {
			isElementDisplayed("txtbx_houseExtn");
			sendText(element("txtbx_houseExtn"), houseExtn);
			msg.log("House extension field is set : " + houseExtn);
		}
	}

	public void verifyFunctionalityOfFindAddressButtonInBillingPage(String postalCode) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_findAddress");
		isElementDisplayed("input_postalCode");
		sendText(element("input_postalCode"), postalCode);
		msg.log("Postal code entered : " + postalCode);
		element("btn_findAddress").click();
		msg.log("Find address button is clicked");
		isElementDisplayed("header_postalAddress");
		msg.log("Postal Address header is displayed : " + element("header_postalAddress").getText());
		isElementDisplayed("drpdn_chooseAddress");
		element("drpdn_chooseAddress").click();
		msg.log("Choose address drop down is clicked");
		isElementDisplayed("option_chooseAddress", "2");
		element("option_chooseAddress", "2").click();
		msg.log("Address selected " + element("option_chooseAddress", "2").getText());

		verifyAddressFieldsArePrepopulatedInBillingPage();
	}

	public void verifyAddressFieldsArePrepopulatedInBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("input_address");
		element("input_address").click();
		element("input_address").sendKeys(Keys.TAB);
		isElementDisplayed("input_city");
		clickUsingJS(element("input_city"));
		element("input_city").sendKeys(Keys.TAB);
		isElementNotDisplayed("lbl_error");
		msg.log("Address filed is prepopulated in Billing page");
	}

	public void verifyCVNToolTipInPaymentPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("cvnTooltip");
		hover(element("cvnTooltip"));
		msg.log("Hovered on the CVN Tooltip");
		isElementDisplayed("cvnDetails");
		msg.log("On hover text appear: " + element("cvnDetails").getText());
	}

	public void verifyLocaleIsSelectedByDefaultOnBillingPage(String country) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
			wait.waitForPageToLoadCompletely();
			hardWait(2);
			scrollDown(element("dropDown_Country"));
		}
		isElementDisplayed("dropDown_Country");
		msg.log("Country drop down is displayed!!");
		boolean flag = false;
		for (WebElement e : elements("dropDown_Country_option")) {
			if (e.getText().equalsIgnoreCase(country)) {
				if (e.isSelected()) {
					flag = true;
				}
			}
		}
		assertTrue(flag, "[ASSERTION FAILED]: " + country + " is not pre- selected in country drop down!!");
		msg.pass(country + " is pre-selected in the country drop down in the shipping page!!");
	}

	public void verifyFieldsAppearOnBillingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("input_fname");
		isElementDisplayed("input_lname");
		isElementDisplayed("drpdwn_country");
		isElementDisplayed("textbox_FindAddress");
		isElementDisplayed("link_enterManually");
		isElementDisplayed("input_phone");
		msg.log("All required detail fields are displayed on Billing Page!!!");
	}
	
	public void verifyPositionOfNewAddressFindFieldOnBillingPage() {
		int yCoordinateOfCountry = element("dropDown_Country").getLocation().getY();
		int yCoordinateOfNewField = element("textbox_FindAddress").getLocation().getY();
		int yCoordinateOfPhone = element("input_phoneNo").getLocation().getY();

		assertTrue(yCoordinateOfPhone > yCoordinateOfNewField,
				"[ASSERTION FAILED] : Phone field is below New Address Find Field!!");

		// assertTrue(isElementDisplayed("input_NewFindAdd_Relative_To_Country"),
		// "[ASSERTION FAILED] : Country is above New Address Find Field!!");

		assertTrue(yCoordinateOfCountry < yCoordinateOfNewField,
				"[ASSERTION FAILED] : Country is above New Address Find Field!!");
	}

	public void verifyAddressFieldsPopulateAndInUpperCaseOnBillingPage(String city) {
		isElementDisplayed("textbox_FindAddress");
		try {
			element("textbox_FindAddress").click();
		} catch (Exception e) {
			clickByJavascript(element("textbox_FindAddress"));
		}
		element("textbox_FindAddress").clear();
		for (int index = 0; index < city.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(city.charAt(index)));
			hardWait(1);
		}
		hardWait(2);
		isElementDisplayed("dropdown_AddressSuggest");
		assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
				"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + city
						+ " is entered in lower case");
		msg.pass("Suggestion DropDown of Addresses shown when " + city + " is entered in lower case");
		String addressFound = elements("dropdown_ListOfAddress").get(0).getText().toUpperCase();

		System.out.println("Address Found: " + addressFound);
		elements("dropdown_ListOfAddress").get(0).click();

		// Waiting for drop down to populate data
		hardWait(3);

		try {
			element("textbox_FindAddress").click();
		} catch (Exception e) {
			clickByJavascript(element("textbox_FindAddress"));
		}

		assertTrue(addressFound.contains(executeJavascriptReturnsString(
				"return $('#dwfrm_billing_billingAddress_addressFields_address1').val()")));
		msg.pass("Address field is populated !!!");
		assertTrue(addressFound.contains(
				executeJavascriptReturnsString("return $('#dwfrm_billing_billingAddress_addressFields_city').val()")));
		msg.pass("City field is populated !!!");
		assertTrue(addressFound.contains(executeJavascriptReturnsString(
				"return $('#dwfrm_billing_billingAddress_addressFields_postal').val()")));
		msg.pass("Postal Code field is populated !!!");
	}

	public void verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(String firstName, String lastName,
			String phoneNumber) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForPageToLoadCompletely();
			hardWait(2);
		}
		isElementDisplayed("input_fname");
		sendText(element("input_fname"), firstName);
		element("input_fname").sendKeys(Keys.TAB);
		msg.log(firstName + " is entered");
		
		isElementDisplayed("input_lname");
		sendText(element("input_lname"), lastName);
		element("input_lname").sendKeys(Keys.TAB);
		msg.log(lastName + " is entered");
		
		isElementDisplayed("input_phoneNo");
		sendText(element("input_phoneNo"), phoneNumber);
		element("input_phoneNo").sendKeys(Keys.TAB);
		msg.log(phoneNumber + " is entered");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		
		isElementDisplayed("btn_placeOrder");
		try {
			element("btn_placeOrder").click();
		} catch (Exception e) {
			clickByJavascript(element("btn_placeOrder"));
		}
		
		msg.log("Actual Result: " + getCurrentURL());
		msg.log("Expected Result: CO");
		Assert.assertTrue(getCurrentURL().contains("CO"), 
				"[ASSERTION FAILED]: User is NOT navigated to Billing page !!!");
		msg.pass("'type your post code or the first line of your address' "
				+ "field is a required field when address fields are collapsed !!!");
	}
	
	public void clickOnEnterManuallyLink() {
		try {
			clickUsingJS(element("lnk_enterManually"));
			msg.log("Clicked on 'enter manually' link");
		} catch (AssertionError er) {
			msg.log("FIND ADDRESS input Field is ALREADY EXPANDED !!!");
		}
	}
	
	public void clickOnSearchLink() {
		isElementDisplayed("lnk_searchAgain");
		
		clickUsingJS(element("lnk_searchAgain"));
		msg.log("Clicked on 'search' link");
	}
	
}
