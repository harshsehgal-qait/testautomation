package com.katespade.keywords;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class ProductShippingPage extends GetPage {

	public ProductShippingPage(WebDriver driver) {
		super(driver, "Katespade/ProductShippingPage");
	}

	public void enterShipingDetails(String guestMailId, String fname, String lname, String address, String zipcode,
			String city, String phno) {
		fillingOfDetails(guestMailId, fname, lname, address, zipcode, city, phno);

		scrollToElement(element("btn_continuePayment"));
		isElementDisplayed("btn_continuePayment");

		executeJavascript("$('.button-fancy-large').click()");
		msg.log("Clicked on 'Continue to payment' button");
	}

	public void fillingOfDetails(String guestMailId, String fname, String lname, String address, String zipcode,
			String city, String phno) {
		if (element("input_guestEmail").getAttribute("value").isEmpty()) {
			sendText(element("input_guestEmail"), guestMailId);
			msg.log("User enters guest user mail id" + guestMailId);
		}

		sendText(element("input_fname"), fname);
		msg.log("User entered first name: " + fname);

		sendText(element("input_lname"), lname);
		msg.log("User entered last name: " + lname);

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en_GB")) {
			isElementDisplayed("link_enterManually");
			clickUsingJS(element("link_enterManually"));
			hardWait(1);
		}

		sendText(element("input_address"), address);
		element("input_address").sendKeys(Keys.TAB);
		msg.log("Address entered: " + address);

		// if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
		// // element("input_city").click();
		// clickByJavascript(element("input_city"));
		// hardWait(2);
		// // element("input_city").sendKeys(Keys.TAB);
		// hardWait(2);
		// }

		sendText(element("input_city"), city);
		element("input_city").sendKeys(Keys.TAB);
		msg.log("User entered city: " + city);

		// if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
		// element("input_phone").sendKeys(Keys.TAB);
		// }
		// try {
		// element("input_phone").click();
		// } catch (Exception e) {
		// clickByJavascript(element("input_phone"));
		// }

		sendText(element("input_pcode"), zipcode);
		element("input_pcode").sendKeys(Keys.TAB);
		msg.log("User entered zip code: " + zipcode);

		sendText(element("input_phone"), phno);
		element("input_phone").sendKeys(Keys.TAB);
		msg.log("User entered phone: " + phno);
		;
	}

	public void enterCity(String city) {
		sendText(element("input_city"), city);
		msg.log("User entered city: '" + city + "'");
		hardWait(2);
	}

	public void verifyUserIsOnShippingPage() {
		wait.waitForLoad();
		isElementDisplayed("shipping_page");
		isElementDisplayed("lbl_stepNo");
		assertEquals(element("lbl_stepNo").getCssValue("background-color"), "rgba(72, 168, 66, 1)",
				"[ASSERTION FAILED]: Color of the dot is not green");
		msg.pass("Color of the dot is green");
		msg.pass("Verified User has navigated to Shipping page");
	}

	public void clickOnRegisterUserSignInNowButtonAndVerifyFieldsForLogin() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_regstrdUserSignInNow");
		clickUsingJS(element("btn_regstrdUserSignInNow"));
		wait.waitForPageToLoadCompletely();
		msg.log("registered User Sign In Now button is clicked ");
		isElementDisplayed("txtbox_emailAddress");
		isElementDisplayed("txtbox_password");
		isElementDisplayed("btn_SignIn");
		msg.log("Verified Fields for registered user to login are present");
	}

	public void verifyForgotPasswordModalOpensOnClickingForgotPasswordLink() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_forgotPassword");
		clickUsingJS(element("link_forgotPassword"));
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		assertEquals(getCurrentURL().contains("Account-PasswordResetDialog"), true,
				"[Assert Fail]: User is not navigated to the Forgot Password Modal");
		msg.log("User is navigated to the Forgot Password Modal");
		backButton();
	}

	public void loginToAccountDuringCheckoutFlow(String emailAddress, String password) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_regstrdUserSignInNow");

		clickUsingJS(element("btn_regstrdUserSignInNow"));
		msg.log("Clicked on registered User Sign In button");

		isElementDisplayed("txtbox_emailAddress");
		sendText(element("txtbox_emailAddress"), emailAddress);

		isElementDisplayed("txtbox_password");
		sendText(element("txtbox_password"), password);

		isElementDisplayed("btn_SignIn");
		clickUsingJS(element("btn_SignIn"));
		msg.actions("Clicked on 'Sign In' button");

		try {
			isElementDisplayed("drpdown_savedAddress");
		} catch (AssertionError er) {
			Assert.fail("'" + emailAddress + "' REGISTERED USER DOES NOT HAVE ANY SAVED ADDRESS !!!");
		}
		msg.pass("User is successfully login to the account");
	}

	public void verifyShippingPageAfterSucessfulLogin() {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("input_guestEmail");
		isElementDisplayed("input_fname");
		isElementDisplayed("input_lname");
		isElementDisplayed("input_postalCodeLookUp");

		try {
			isElementDisplayed("drpdown_savedAddress");
		} catch (AssertionError er) {
			Assert.fail("REGISTERED USER DOES NOT HAVE ANY SAVED ADDRESS !!!");
		}

		isElementDisplayed("textbox_FindAddress");
		isElementDisplayed("input_phone");
		isElementDisplayed("btn_continuePayment");

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			try {
				isElementDisplayed("link_enterManually");
			} catch (AssertionError er) {
				Assert.fail("Logged EEGI-240 bug for the same i.e., "
						+ "'enter manually' LINK IS NOT APPEARING ON STAGING ENVIRONMENT");
			}
		}
		msg.pass("Verified Checkout elements are present");
	}

	public void verifyRegisteredUserIsAbleToClickAndSelectFirstSavedAddress() {
		wait.waitForPageToLoadCompletely();
		try {
			isElementDisplayed("drpdown_savedAddress");
		} catch (AssertionError er) {
			Assert.fail("REGISTERED USER DOES NOT HAVE ANY SAVED ADDRESS !!!");
		}

		element("drpdown_savedAddress").click();
		msg.actions("Clicked on 'Saved Address' dropdown");

		isElementDisplayed("select_savedAddress");
		List<WebElement> savedAddress = elements("select_savedAddress");
		for (WebElement el : savedAddress) {
			el.isDisplayed();
			msg.log("Option on Saved Address Dropdown: '" + el.getText() + "'");
		}

		selectIndexFromDropDown(element("drpdown_savedAddress"), 1);
		msg.actions("First option from dropdown is selected");
	}

	public void verifyLoginFieldsAreNotVisibleInShipingPage() {
		wait.waitForPageToLoadCompletely();
		isElementNotDisplayed("btn_SignIn");
		msg.log("Verified: Login fields are not visible in shiping page");
	}

	public void verifyThatShippingFieldsArePrePopulated(String firstName, String lastName, String phoneNo) {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("input_fname");
		Assert.assertEquals(element("input_fname").getAttribute("value"), firstName,
				"[ASSERTION FAILED]: First name field is not pre populated !!!");
		msg.pass("First name field is pre populated with value: " + element("input_fname").getAttribute("value"));

		isElementDisplayed("input_lname");
		Assert.assertEquals(element("input_lname").getAttribute("value"), lastName,
				"[ASSERTION FAILED]: Last name field is not pre populated !!!");
		msg.pass("Last name field is pre populated with value: " + element("input_lname").getAttribute("value"));

		isElementDisplayed("input_phone");
		Assert.assertEquals(element("input_phone").getAttribute("value"), phoneNo,
				"[ASSERTION FAILED]: Phone no field is not pre populated !!!");
		msg.pass("Phone no field is pre populated with value: " + element("input_phone").getAttribute("value"));

		isElementNotDisplayed("errorCountryTxtbox");

		msg.pass("Shipping fields are pre populated");
	}

	public void verifyStep1ShippingIsActive() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_stepNo");

		Assert.assertEquals(element("lbl_stepNo").getCssValue("background-color"), "rgba(72, 168, 66, 1)",
				"[ASSERTION FAILED]: Color of the dot is not green !!!");
		msg.log("Color of the dot is green");

		msg.pass("Step 1 i.e Shipping is active");
	}

	public void verifyThatStepFooterIsDisplayed() {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("lbl_stepFooter");
		msg.pass("Verified: Step Footer is displayed : " + element("lbl_stepFooter").getText());
	}

	public void verifyUserCanNavigateBackFromBillingPageToShipppingPage() {
		wait.waitForLoad();

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		}
		executeJavascript("document.getElementsByClassName('t046-close')[0].click()");
		msg.log("Clicked on 'Close' icon of Feedback dialog box");

		// To avoid WebDriverException error
		hardWait(3);

		isElementDisplayed("lbl_stepNo");
		element("lbl_stepNo").click();
		msg.log("Clicked on 'Step No' label");

		Assert.assertEquals(element("lbl_stepNo").getCssValue("background-color"), "rgba(72, 168, 66, 1)",
				"[ASSERTION FAILED]: Color of the dot is not green");
		msg.log("Color of the dot is green");
		msg.pass("Verified : User can navigate to previously completed steps");
	}

	public void verifyContinueToPaymentButtonIsDisabledInShippingPage() {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("input_fname");
		String value = element("input_fname").getAttribute("value");
		element("input_fname").clear();
		element("input_fname").sendKeys(Keys.TAB);

		isElementDisplayed("list_fieldsError");
		isElementDisplayed("btn_disabledContinueToPayment");
		msg.pass("Verified Button is disabled");

		executeJavascript("document.getElementsByClassName('t046-close')[0].click()");
		msg.log("Clicked on 'Close' icon of Feedback dialog box");

		element("input_fname").click();
		element("input_fname").sendKeys(value);
	}

	public void mobile_verifyContinueToPaymentButtonIsDisabledInShippingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("input_fname");
		String value = element("input_fname").getAttribute("value");
		element("input_fname").clear();
		element("input_lname").click();
		isElementDisplayed("list_fieldsError");
		isElementDisplayed("btn_disabledContinueToPayment");
		msg.log("Verified: Button is disabled ");
		element("input_fname").click();
		element("input_fname").sendKeys(value);
		element("input_lname").click();
	}

	public void clickContinuePaymentButton() {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
			scrollDown(element("btn_continuePayment"));
		}
		scrollDown(element("btn_continuePayment"));
		isElementDisplayed("btn_continuePayment");
		// element("btn_continuePayment").click();
		executeJavascript("$('.button-fancy-large').click()");
		msg.log("User clicks on Continue to payment");
	}

	@SuppressWarnings("unused")
	public void verifyRadioButtonsAndTheirCountForShippingMethodsAreDisplayed(int shippingMethodCount) {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("radioBtn_standardShpng");
		boolean checked = executeJavascriptReturnsBoolean(" return $('#shipping-method-ground')[0].checked");
		assertEquals(checked, true, "[Assert Failed]: Standard shipping radio button is not selected");
		msg.log("[Assert Pass]: Standard shipping radio button is selected as expected");

		List<WebElement> shpngMethodsRadioBtns = elements("list_shpngMethodRadioBtns");
		int count = 0;
		for (WebElement el : shpngMethodsRadioBtns) {
			count++;
		}

		assertEquals(count, shippingMethodCount, "[Assert Fail] : Shipping method count is not matched ");
		msg.log("[Assert Pass]: Shipping method count is matched i.e " + count);

	}

	public void verifyOrderSummaryIsUpdatedAccordingToTheShippingMethod() {
		wait.waitForLoad();
		isElementDisplayed("radioBtn_expressShpng");
		executeJavascript("$('#shipping-method-2day')[0].click()");
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		isElementDisplayed("lbl_shpngMethodOrderSummary");
		hardWait(1);
		msg.log(element("lbl_shpngMethodOrderSummary").getText() + ".............");
		assertEquals(element("lbl_shpngMethodOrderSummary").getText().contains("EXPRESS"), true,
				"Order summary is not updated as expected");
		msg.log("Order summary is updated as expected");

		isElementDisplayed("radioBtn_standardShpng");
		executeJavascript("$('#shipping-method-ground')[0].click()");
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_shpngMethodOrderSummary");
		hardWait(1);
		msg.log(element("lbl_shpngMethodOrderSummary").getText() + "............");
		assertEquals(element("lbl_shpngMethodOrderSummary").getText().contains("STANDARD"), true,
				"Order summary is not updated as expected");
		msg.log("Order summary is updated as expected");

	}

	public void verifyErrorsOnTheMandatoryFieldsInShipingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("list_fieldsError");
		if (isElementDisplayed("list_fieldsError")) {
			List<WebElement> fieldError = elements("list_fieldsError");
			for (WebElement el : fieldError) {
				msg.log("Error is displayed" + el.getText());
			}
		} else {
			msg.log("All required fields are filled");
			;
		}
	}

	public void clickUseThisAddressForBillingCheckboxInShippingPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("chck_billingAddress");
		executeJavascript("$('#dwfrm_singleshipping_shippingAddress_useAsBillingAddress').click()");
		msg.log("User clicked on the Use this address for billing checkbox in shipping page");
	}

	public void verifyUseThisAddressForBillingIsByDefaultNotSelected() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("chck_billingAddress");
		boolean isCheckboxMarked = executeJavascriptReturnsBoolean(
				"return $('#dwfrm_singleshipping_shippingAddress_useAsBillingAddress').is(':checked')");
		assertEquals(isCheckboxMarked, false, "[Assert Fail]: Use this address for billing is by default selected");
		msg.log("[Assert Pass]: Use this address for billing is by default not selected");
	}

	public void clickOnPlusIconFromMiniCartLinkOnShippingPage() {
		isElementDisplayed("myBagSectionHeader");
		boolean val = executeJavascriptReturnsBoolean("return $('.checkout-mini-cart a').is(':visible')");
		assertEquals(val, true, "Plus icon is not displayed");
		// clickUsingJS(element("myBagSectionHeader"));
		clickByJavascript(element("myBagSectionHeader"));
		// executeJavascript("$('.mini-cart-link')[1].click()");
		msg.log("User clicked on plus icon to view mini cart on shipping page");
	}

	public void verifyMiniCartAttributes_Images() {
		int size = elements("prdct_img").size();
		for (int i = 0; i < size; i++) {
			elements("prdct_img").get(i).isDisplayed();
			msg.log("Products images are displayed");
		}
	}

	public void verifyMiniCartAttributes_ProductNames() {
		int size = elements("prdct_name").size();
		for (int i = 0; i < size; i++) {
			elements("prdct_name").get(i).isDisplayed();
			msg.log(elements("prdct_name").get(i).getText());
			msg.log("Products name are displayed");
		}
	}

	public void verifyMiniCartAttributes_ProductStyleNumber() {
		int size = elements("prdct_sku").size();
		for (int i = 0; i < size; i++) {
			elements("prdct_sku").get(i).isDisplayed();
			msg.log(elements("prdct_sku").get(i).getText());
			msg.log("Products sku are displayed");
		}
	}

	public void verifyMiniCartAttributes_ProductPrice() {
		int size = elements("prdct_price").size();
		for (int i = 0; i < size; i++) {
			elements("prdct_price").get(i).isDisplayed();
			msg.log(elements("prdct_price").get(i).getText().trim());
			msg.log("Products price are displayed");
		}

	}

	public void verifyMiniCartAttributes_ProductQuantity() {
		int size = elements("prdct_qty").size();
		for (int i = 0; i < size; i++) {
			elements("prdct_qty").get(i).isDisplayed();
			// msg.log(elements("prdct_qty").get(i).getText().trim());
			msg.log(element("prdct_qty").getText().trim());
			msg.log("Products quantity are displayed");
		}

	}

	public void verifyMyBagIsSticky() {
		isElementDisplayed("myBag_header");
		String sticky = executeJavascriptReturnsString(
				"return $('.section-header.toggle.expanded span').eq(0).css('position')");
		assertEquals(sticky, "static", "My Bag is not sticky");
		msg.log("My Bag is sticky");
	}

	public void verifyUserIsAbleToSelectAnAddressFromDropdown() {
		isElementDisplayed("drpdown_savedAddress");
		isElementDisplayed("select_savedAddress");
		int size = elements("select_savedAddress").size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			msg.log(elements("select_savedAddress").get(i).getText());
		}
		selectOptionFromDropDownList("select_savedAddress", "1");
		msg.log("User selects the first address");

	}

	public void verifyAfterSelectingAddressFRomDropdownShippingFieldsAeAutoPopulatedFromAddressSelected() {
		selectIndexFromDropDown(element("drpdown_savedAddress"), 1);
		msg.log("Selected an address from 'select from saved address' dropdown");

		// Wait for saved address to populate in 'select from saved address' drop down
		hardWait(2);

		String selectedAddress = elements("select_savedAddress").get(1).getText();

		scrollDown(element("input_address"));
		isElementDisplayed("input_address");
		String autoPopulatedAddress = executeJavascriptReturnsString(
				"return $('#dwfrm_singleshipping_shippingAddress_addressFields_address1').val()");

		msg.log("Selected Address: " + selectedAddress.toLowerCase().trim());
		msg.log("Auto Populate Address: " + autoPopulatedAddress.toLowerCase().trim());

		Assert.assertTrue(selectedAddress.toLowerCase().trim().contains(autoPopulatedAddress.toLowerCase().trim()),
				"[ASSERTION FAILED]: Address has NOT auto-populated !!!");
		msg.pass("Address is auto-populated !!!");
	}

	public void selectTheAddressDropdownAndPickanotherAddress() {
		executeJavascript(" $(window).scrollTop(0);");
		Select address = new Select(element("drpdown_savedAddress"));
		address.selectByIndex(1);
		String prev_address = elements("select_savedAddress").get(1).getText();
		msg.log(prev_address);
		scrollDown(element("input_address"));
		isElementDisplayed("input_address");
		String addressnew = executeJavascriptReturnsString(
				"return $('#dwfrm_singleshipping_shippingAddress_addressFields_address1').val()");
		msg.log(addressnew);
		assertEquals(prev_address.toLowerCase().trim().contains(addressnew.toLowerCase().trim()), true,
				"Address is not autopopulated");
		msg.log("Address is autopopulated and changed");

	}

	public String enterTheShippingDetailDataAndEnterNewDataManually(String username, String firstname, String lastName,
			String address, String zip, String phnnumber) {
		isElementDisplayed("input_guestEmail");
		sendText(element("input_guestEmail"), username);

		isElementDisplayed("input_fname");
		sendText(element("input_fname"), firstname);

		isElementDisplayed("input_lname");
		sendText(element("input_lname"), lastName);

		if (getCurrentURL().contains("en-gb")) {
			isElementDisplayed("input_postalCodeLookUp");
			sendText(element("input_postalCodeLookUp"), zip);
			hardWait(2);

			try {
				isElementDisplayed("lnk_searchAgain");
				executeJavascript("document.getElementsByClassName('pcaautocomplete pcatext')[0]."
						+ "getElementsByClassName('pcaitem pcafirstitem')[0].click()");
				msg.log("Clicked on first PCA list item");

				hardWait(1);
				zip = executeJavascriptReturnsString(
						"return " + "$('#dwfrm_singleshipping_shippingAddress_addressFields_postal').val()");
				msg.pass("Postal Code field is populated: " + zip);
			} catch (AssertionError er) {
				isElementDisplayed("link_enterManually");
				clickUsingJS(element("link_enterManually"));
			}
		} else {
			isElementDisplayed("input_pcode");
			sendText(element("input_pcode"), zip);

			isElementDisplayed("input_address");
			sendText(element("input_address"), address);
		}

		isElementDisplayed("input_phone");
		sendText(element("input_phone"), phnnumber);

		return zip;
	}

	public void selectTheCheckBoxAddToAddressBookAndUseThisBillingAddress() {
		isElementDisplayed("chck_billingAddress");
		clickUsingJS(element("chck_billingAddress"));
		msg.log("Checked on 'use this address for billing' checkbox");

		try {
			isElementDisplayed("checkbox_addtoaddressbook");
			clickUsingJS(element("checkbox_addtoaddressbook"));
			msg.log("Checked on 'add to address book' checkbox");
		} catch (AssertionError ex) {
			msg.log("Provided address is ALREADY added to Address book !!!");
		}
	}

	public void enterShippingDetailsAndDoNotContinue(String username, String firstname, String lastName, String address,
			String zip, String city, String phnnumber) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("input_guestEmail");
		sendText(element("input_guestEmail"), username);
		msg.log("User enters guest user mail id" + username);
		isElementDisplayed("input_fname");
		sendText(element("input_fname"), firstname);
		msg.log("User enter first name" + firstname);

		isElementDisplayed("input_lname");
		sendText(element("input_lname"), lastName);

		isElementDisplayed("input_pcode");
		sendText(element("input_pcode"), zip);
		msg.log("User enters zip code" + zip);

		isElementDisplayed("input_address");
		sendText(element("input_address"), address);
		msg.log("Address" + address);

		isElementDisplayed("input_city");
		sendText(element("input_city"), city);
		msg.log("User enters city" + city);

		isElementDisplayed("input_phone");
		sendText(element("input_phone"), phnnumber);
		msg.log("User enters city" + phnnumber);

	}

	public void verifyCountryDropdownWillShowCountryThatTheUserIsOn(String country) {
		isElementDisplayed("country_dropdown");
		msg.log("Coutry Displayed in Shipping page :" + element("country_dropdown").getText());
		assertEquals(element("country_dropdown").getText(), country,
				"[Assert Fail : Country Dropdown is not according to the selected country]");
		msg.log("[Assert Pass : Country Dropdown is according to the selected country]");
	}

	public void selectAshippingMethod() {
		isElementDisplayed("list_shpngMethodRadioBtns");
		clickUsingJS(element("list_shpngMethodRadioBtns"));
		msg.log("User clicks on shipping method");

	}

	public void verifyOnClickingPlusIconOfMyBagDrawerClosesIfItIsOpen() {
		executeJavascript("$('.mini-cart-link')[1].click()");
		msg.log("User clicks on plus icon of open drawer");
		isElementNotDisplayed("firstProduct_style");
	}

	public void verifyProductsAddedInCartAreDisplayedInTheSameOrderOnShippingPage(String product_1, String product_2,
			String product_3) {
		executeJavascript("$('.mini-cart-link')[1].click()");
		msg.log("User clicks on plus icon of open drawer");
		scrollDown(element("firstProduct_style"));
		isElementDisplayed("firstProduct_style");
		assertEquals(element("firstProduct_style").getText().toLowerCase().trim(), product_1.toLowerCase().trim(),
				"Product is not on first index");
		msg.log("Product is on first index");
		isElementDisplayed("secondProduct_style");
		assertEquals(element("secondProduct_style").getText().toLowerCase().trim(), product_2.toLowerCase().trim(),
				"Product is not on second index");
		msg.log("Product is on second index");
		isElementDisplayed("thirdProduct_style");
		assertEquals(element("thirdProduct_style").getText().toLowerCase().trim(), product_3.toLowerCase().trim(),
				"Product is not on third index");
		msg.log("Product is on third index");

	}

	public void clickOnGiftMessageAndGiftBox() {
		scrollDown(element("label_giftMessageandGiftBox"));
		isElementDisplayed("label_giftMessageandGiftBox");
		msg.log(element("label_giftMessageandGiftBox").getText());
		boolean checkbox = executeJavascriptReturnsBoolean(
				"return $('.field-wrapper .input-checkbox.gift-check').is(':visible')");
		assertEquals(checkbox, true, "Checkbox is not present");
		msg.log("Check box is present");
		isElementDisplayed("label_yesShowMeoptionGiftMsgGiftBox");
		msg.log(element("label_yesShowMeoptionGiftMsgGiftBox").getText());
		executeJavascript("$('.field-wrapper .input-checkbox.gift-check')[0].click()");
		msg.log("User clicks on checkbox for gift messaging and gift box");
		isElementDisplayed("giftBox_model");

	}

	public void verifyGiftBoxModal() {
		isElementDisplayed("header_giftOption");
		isElementDisplayed("label_gifyOption");
		isElementDisplayed("gift_productImg");
		isElementDisplayed("gift_productName");
		int size = elements("gift_prdctAttributes").size();
		for (int i = 0; i < size; i++) {
			elements("gift_prdctAttributes").get(i).isDisplayed();
			msg.log(elements("gift_prdctAttributes").get(i).getText());
		}

	}

	public void verifyAllCheckboxesInGiftBoxModel() {
		boolean checkbox1 = executeJavascriptReturnsBoolean(
				"return $('.field-wrapper .input-checkbox.gift-check').is(':visible')");
		assertEquals(checkbox1, true, "Checkbox is not present");
		msg.log("Checkbox is present");
		boolean flag = executeJavascriptReturnsBoolean(
				"return $('.field-wrapper .input-checkbox.gift-check').is(':selected')");
		assertEquals(flag, false, "checkbox freeGiftCardMessage is by default selected");
		msg.log("checkbox freeGiftCardMessage is not by default selected");
		isElementDisplayed("freeGiftCardMessage");
		msg.log(element("freeGiftCardMessage").getText());

		boolean checkbox2 = executeJavascriptReturnsBoolean(
				"return $('#dwfrm_multigift_giftOptions_productLineItems_i0_giftoptions_hasGiftWrap').is(':visible')");
		assertEquals(checkbox2, true, "Checkbox is not present");
		msg.log("Checkbox is present");
		boolean flag2 = executeJavascriptReturnsBoolean(
				"return $('#dwfrm_multigift_giftOptions_productLineItems_i0_giftoptions_hasGiftWrap').is(':selected')");
		assertEquals(flag2, false, "checkbox includeGiftWrapping is by default selected");
		msg.log("checkbox includeGiftWrapping is not by default selected");
		isElementDisplayed("includeGiftWrapping");
		msg.log(element("includeGiftWrapping").getText());

		boolean checkbox3 = executeJavascriptReturnsBoolean(
				"return $('#dwfrm_multigift_includeGiftReceipt').is(':visible')");
		assertEquals(checkbox3, true, "Checkbox is not present");
		msg.log("Checkbox is present");
		boolean flag3 = executeJavascriptReturnsBoolean(
				"return $('#dwfrm_multigift_includeGiftReceipt').is(':selected')");
		assertEquals(flag3, false, "checkbox hideAllPriceOnPackingSlip is by default selected");
		msg.log("checkbox hideAllPriceOnPackingSlip is not by default selected");
		isElementDisplayed("hideAllPriceOnPackingSlip");
		msg.log(element("hideAllPriceOnPackingSlip").getText());

	}

	public void verifyFreeGiftMessageFunctionaity(String text) {
		hardWait(2);
		executeJavascript("$('#dwfrm_multigift_giftOptions_productLineItems_i0_giftoptions_hasNote').click()");
		msg.log("User clicks on free gift message checkbox");
		isElementDisplayed("freegift_container");
		// String val = element("freegift_container").getAttribute("placeholder");
		String val = executeJavascriptReturnsString(
				"return $('.textareacontainer .gift-message-chars').attr('placeholder')");
		assertEquals(text, val, "Watermark/Placeholder not found");
		msg.log(val + ": is found");
	}

	public void verifyCountrySelectorIsNotPresentOnCheckout() {
		isElementNotDisplayed("country_toggle");
	}

	public void verifyOrderSummaryForDiscountedProductInShippingPg() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_subTotal");
		msg.log("Sub total is : " + element("lbl_subTotal").getText());
		isElementDisplayed("txt_coupnSuccessMsg");
		msg.log("Discounted coupon msg is : " + element("txt_coupnSuccessMsg").getText());
		isElementDisplayed("lbl_shpngMethodOrderSummary");
		msg.log("Shipping method info is : " + element("lbl_shpngMethodOrderSummary").getText());
		isElementDisplayed("lbl_total");
		msg.log("Total is : " + element("lbl_total").getText());
		msg.log("Verified: Order summary is  verified for the discounted product");
	}

	public void verifyTheStickyNatureOfOrderSummaryBoxInProductShipngPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("summaryBox");
		String position = executeJavascriptReturnsString("return $('.checkout-order-totals').css('position')");
		System.out.println("Checkout Order Total's position: " + position);
		Assert.assertEquals(position, "static", "[ASSERTION FAILED]: Order Summary is NOT sticky !!!");
		msg.pass("Order Summary is sticky");
	}

	public void verifyShippingDiscountIsAppliedInShippingPage() {
		wait.waitForLoad();
		isElementDisplayed("shippingOrderDiscount");
		msg.log("Shipping Discount is applied: " + element("shippingOrderDiscount").getText());
	}

	public void enterInvalidShippingDetails() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("input_guestEmail");
		sendText(element("input_guestEmail"), "fdadasd");
		isElementDisplayed("input_fname");
		sendText(element("input_fname"), "dvdsvd5354");
		isElementDisplayed("input_lname");
		sendText(element("input_lname"), "43535fsf@");
		isElementDisplayed("input_postalCodeLookUp");
		sendText(element("input_postalCodeLookUp"), "454543");
		hardWait(1);

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			try {
				isElementDisplayed("link_enterManually");
			} catch (AssertionError er) {
				Assert.fail("Logged EEGI-240 bug for the same i.e., "
						+ "'enter manually' LINK IS NOT APPEARING ON STAGING ENVIRONMENT");
			}
			clickUsingJS(element("link_enterManually"));
			hardWait(1);
		}

		isElementDisplayed("input_address");
		sendText(element("input_address"), "sfsdfsdf");
		isElementDisplayed("input_city");
		sendText(element("input_city"), "gdfsfd");

		isElementDisplayed("input_phone");
		sendText(element("input_phone"), "2421431");
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
			element("input_phone").sendKeys(Keys.TAB);
			element("input_city").click();
			element("input_city").sendKeys(Keys.TAB);
			hardWait(2);
		}
	}

	public void verifyLocaleIsSelectedByDefault(String country) {
		boolean flag = false;
		for (WebElement elem : elements("dropDown_Country_option")) {
			if (elem.getText().equalsIgnoreCase(country)) {
				if (elem.isSelected()) {
					flag = true;
				}
			}
		}
		Assert.assertTrue(flag, "[ASSERTION FAILED]: " + country + " is not pre- selected in country drop down!!");
		msg.pass(country + " is pre-selected in the country drop down in the shipping page!!");
	}

	public void verifyShippingMethod(String shippingMethod) {
		hardWait(2);
		scrollToElement("label_ShippingMethod");
		
		Assert.assertEquals(element("label_ShippingMethod").getText().trim(), shippingMethod,
				"[ASSERTION FAILED]: Shipping method not appearing as expected !!!");
		msg.pass("Shipping method: '" + shippingMethod + "'");
	}

	public void selectCountryFromDropDown(String selectCountry) {
		wait.waitForLoad();
		isElementDisplayed("dropDown_Country");
		
		msg.log("Selected Country: " + executeJavascriptReturnsString(
				"return $('#dwfrm_singleshipping_shippingAddress_addressFields_country option:selected').val();"));
		msg.log("Country drop down is displayed!!");

		selectProvidedTextFromDropDown(element("dropDown_Country"), selectCountry);
		
		hardWait(2);
		msg.log("Selected Country: " + executeJavascriptReturnsString(
				"return $('#dwfrm_singleshipping_shippingAddress_addressFields_country option:selected').val();"));
		msg.log("User selected '" + selectCountry + "' country");
	}

	public void verifyFieldsAreDisplayedForShippingAddress() {
		Assert.assertTrue(isElementDisplayed("input_guestEmail"), 
				"[ASSERTION FAILED]: Guest Email field is not displayed!!");
		msg.pass("Guest Email field is displayed!!");

		Assert.assertTrue(isElementDisplayed("input_fname"), 
				"[ASSERTION FAILED]: First Name field is not displayed!!");
		msg.pass("First Name field is displayed!!");

		Assert.assertTrue(isElementDisplayed("input_lname"), 
				"[ASSERTION FAILED]: Last Name field is not displayed!!");
		msg.pass("Last Name field is displayed!!");

		Assert.assertTrue(isElementDisplayed("dropDown_Country"), 
				"[ASSERTION FAILED]: Country field is not displayed!!");
		msg.pass("Country field is displayed!!");

		Assert.assertTrue(isElementDisplayed("textbox_FindAddress"),
				"[ASSERTION FAILED]: type your post code or the first line of your address field is not displayed!!");
		msg.pass("type your post code or the first line of your address is displayed!!");

		Assert.assertTrue(isElementDisplayed("input_pcode"), 
				"[ASSERTION FAILED]: Postal code field is not displayed!!");
		msg.pass("Postal code field is displayed!!");

		Assert.assertTrue(isElementDisplayed("input_city"), 
				"[ASSERTION FAILED]: City field is not displayed!!");
		msg.pass("City field is displayed!!");

		Assert.assertTrue(isElementDisplayed("input_phone"), 
				"[ASSERTION FAILED]: Phone Number field is not displayed!!");
		msg.pass("Phone Number field is displayed!!");
	}

	public void verifyNewAddressFindFieldByNameOnShippingPage(String placeholder) {
		isElementDisplayed("textbox_FindAddress");
		Assert.assertEquals(element("textbox_FindAddress").getAttribute("placeholder").trim(), placeholder);
		msg.pass("New Address Find Field with" + placeholder + "is displayed!!!");
	}

	@SuppressWarnings("unused")
	public void verifyPositionOfNewAddressFindFieldOnShippingPage() {
		int yCoordinateOfCountry = element("dropDown_Country").getLocation().getY();
		int yCoordinateOfNewField = element("textbox_FindAddress").getLocation().getY();
		int yCoordinateOfPhone = element("input_phone").getLocation().getY();

		Assert.assertTrue(yCoordinateOfPhone > yCoordinateOfNewField,
				"[ASSERTION FAILED] : Phone field is below New Address Find Field!!");

		Assert.assertTrue(isElementDisplayed("input_NewFindAdd_Relative_To_Country"),
				"[ASSERTION FAILED] : Country is above New Address Find Field!!");
	}

	public void verifyFieldsAppearOnShipngPage() {
		wait.waitForPageToLoadCompletely();
		// isElementDisplayed("drpdown_savedAddress");
		isElementDisplayed("input_guestEmail");
		isElementDisplayed("input_fname");
		isElementDisplayed("input_lname");
		// isElementDisplayed("input_pcode");
		isElementDisplayed("textbox_FindAddress");
		isElementDisplayed("link_enterManually");
		isElementDisplayed("input_phone");
		isElementDisplayed("btn_continuePayment");
		msg.pass("Verified Checkout elements are present");
	}

	@SuppressWarnings("unused")
	public void verifyPositionOfNewAddressFindFieldShipngPage() {
		int yCoordinateOfCountry = element("dropDown_Country").getLocation().getY();
		int yCoordinateOfNewField = element("textbox_FindAddress").getLocation().getY();
		int yCoordinateOfPhone = element("input_phone").getLocation().getY();

		Assert.assertTrue(yCoordinateOfPhone > yCoordinateOfNewField,
				"[ASSERTION FAILED] : Phone field is below New Address Find Field!!");

		Assert.assertTrue(isElementDisplayed("input_NewFindAdd_Relative_To_Country"),
				"[ASSERTION FAILED] : Country is above New Address Find Field!!");
	}

	public void verifyAddressFieldsPopulateAndInUpperCaseOnShipngPage(String city) {
		isElementDisplayed("textbox_FindAddress");
		try {
			element("textbox_FindAddress").click();
		} catch (Exception e) {
			clickByJavascript(element("textbox_FindAddress"));
		}
		msg.log("Clicked on 'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input field");
		
		element("textbox_FindAddress").clear();
		msg.log("Cleared 'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input field");
		
		for (int index = 0; index < city.length(); index++) {
			element("textbox_FindAddress").sendKeys(String.valueOf(city.charAt(index)));
			hardWait(1);
		}
		element("textbox_FindAddress").sendKeys(" ");
		hardWait(2);
		
		try {
			isElementDisplayed("dropdown_AddressSuggest");
			Assert.assertFalse(element("dropdown_AddressSuggest").getAttribute("style").contains("display: none;"),
					"[ASSERTION FAILED] : Suggestion DropDown of Addresses not shown when " + city
							+ " is entered in lower case");
			msg.pass("Suggestion DropDown of Addresses shown when " + city + " is entered in lower case");
			String addressFound = elements("dropdown_ListOfAddress").get(0).getText().toUpperCase();

			msg.log("Address Found: " + addressFound);
			elements("dropdown_ListOfAddress").get(0).click();

			// Waiting for drop down to populate data
			hardWait(3);
			element("textbox_FindAddress").click();
			msg.log("Clicked on 'TYPE YOUR POST CODE OR THE FIRST LINE OF YOUR ADDRESS' input field");

			msg.log("Address: " + executeJavascriptReturnsString(
					"return $('#dwfrm_singleshipping_shippingAddress_addressFields_address1').val()"));
			Assert.assertTrue(addressFound.toLowerCase().contains(executeJavascriptReturnsString(
					"return $('#dwfrm_singleshipping_shippingAddress_addressFields_address1').val()").toLowerCase()));
			msg.pass("Address field is populated !!!");
			
			Assert.assertTrue(addressFound.toLowerCase().contains(executeJavascriptReturnsString(
					"return $('#dwfrm_singleshipping_shippingAddress_addressFields_city').val()").toLowerCase()));
			msg.pass("City field is populated !!!");
			
			Assert.assertTrue(addressFound.toLowerCase().contains(executeJavascriptReturnsString(
					"return $('#dwfrm_singleshipping_shippingAddress_addressFields_postal').val()").toLowerCase()));
			msg.pass("Postal Code field is populated !!!");
		} catch (AssertionError er) {
			Assert.fail("AUTO SUGGESTIONS for Addresses are NOT appearing even when 3 CHARACTERS are typed !!!");
		}
	}

	public void verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(String firstName, String lastName,
			String phoneNumber) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		if (element("input_guestEmail").getAttribute("value").isEmpty()) {
			sendText(element("input_guestEmail"), "testautomation@qa.com");
			msg.log("User enters guest user mail id" + "testautomation@qa.com");
		}
		isElementDisplayed("input_fname");
		sendText(element("input_fname"), firstName);
		element("input_fname").sendKeys(Keys.TAB);
		msg.log(firstName + " is entered");
		
		isElementDisplayed("input_lname");
		sendText(element("input_lname"), lastName);
		element("input_lname").sendKeys(Keys.TAB);
		msg.log(lastName + " is entered");
		
		clickOnEnterManuallyLink();
		
		element("input_address").clear();
		element("input_city").clear();
		element("input_pcode").clear();
		
		isElementDisplayed("input_phone");
		sendText(element("input_phone"), phoneNumber);
		element("input_phone").sendKeys(Keys.TAB);
		msg.log(phoneNumber + " is entered");
		
		clickOnSearchLink();
		
		isElementDisplayed("textbox_FindAddress");
		element("textbox_FindAddress").sendKeys("");
		element("textbox_FindAddress").sendKeys(Keys.TAB);
		
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			try {
				elements("label_ShippingMethod").get(0).click();
			} catch (Exception e) {
				clickByJavascript(elements("label_ShippingMethod").get(0));
			}
			hardWait(2);
		}

		isElementDisplayed("btn_continuePayment");
		try {
			element("btn_continuePayment").click();
		} catch (Exception e) {
			clickByJavascript(element("btn_continuePayment"));
		}
		msg.log("Clicked on 'CONTINUE TO PAYMENT' button");
		msg.log("Verifing 'FIND ADDRESS' input field is REQUIRED when collapsed");
		
		// wait for error msg
		hardWait(2);

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollToElement("textbox_FindAddress");
			hardWait(2);
		}
	}

	public void verifyNewFindAddressFieldIsNotRequiredFieldWhenExpanded(String username, String firstName,
			String lastName, String postalCode, String phoneNumber, String address1, String city) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		clickOnEnterManuallyLink();

		isElementDisplayed("input_guestEmail");
		sendText(element("input_guestEmail"), username);
		element("input_guestEmail").sendKeys(Keys.TAB);
		msg.log("User enters guest user mail id" + username);
		
		isElementDisplayed("input_fname");
		sendText(element("input_fname"), firstName);
		element("input_fname").sendKeys(Keys.TAB);
		msg.log("User enter first name" + firstName);
		
		isElementDisplayed("input_lname");
		sendText(element("input_lname"), lastName);
		element("input_lname").sendKeys(Keys.TAB);

		isElementDisplayed("input_pcode");
		sendText(element("input_pcode"), postalCode);
		element("input_pcode").sendKeys(Keys.TAB);
		msg.log("User enters zip code" + postalCode);
		
		isElementDisplayed("input_address");
		sendText(element("input_address"), address1);
		element("input_address").sendKeys(Keys.TAB);
		msg.log("Address" + address1);

		isElementDisplayed("input_city");
		sendText(element("input_city"), city);
		element("input_city").sendKeys(Keys.TAB);
		msg.log("User enters city" + city);

		isElementDisplayed("input_phone");
		sendText(element("input_phone"), phoneNumber);
		element("input_phone").sendKeys(Keys.TAB);
		msg.log("User enters phoneNo" + phoneNumber);

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			try {
				elements("label_ShippingMethod").get(0).click();
			} catch (Exception e) {
				clickByJavascript(elements("label_ShippingMethod").get(0));
			}
			hardWait(2);
		}

		isElementDisplayed("btn_continuePayment");
		try {
			element("btn_continuePayment").click();
		} catch (Exception e) {
			clickByJavascript(element("btn_continuePayment"));
		}
		msg.log("Clicked on 'CONTINUE TO PAYMENT' button");
		msg.log("Verifing 'FIND ADDRESS' input field is NOT REQUIRED when expanded !!!");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(2);
		}

		msg.log("Actual Result: " + getCurrentURL());
		msg.log("Expected Result: SingleShipping");
		
		Assert.assertTrue(getCurrentURL().contains("SingleShipping"));
		msg.pass("'type your post code or the first line of your address' input "
				+ "field is not a required field when address fields are expanded !!!");
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

	public void clickOnContinuePayment() {
		isElementDisplayed("btn_continuePayment");
		clickByJavascript(element("btn_continuePayment"));
		msg.log("Button Click on Continue payment is clicked!!!");
	}

}
