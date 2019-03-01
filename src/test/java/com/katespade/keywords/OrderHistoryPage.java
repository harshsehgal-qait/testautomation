package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class OrderHistoryPage extends GetPage {

	public OrderHistoryPage(WebDriver driver) {
		super(driver, "Katespade/OrderHistoryPage");
	}

	public void verifyOrderHistoryPage() {
		isElementDisplayed("signIn");
		isElementDisplayed("field_email");
		isElementDisplayed("field_password");
		isElementDisplayed("signIn");
		isElementDisplayed("checkYourOrder");
		isElementDisplayed("orderNumber");
		isElementDisplayed("billingPostalCode");
		isElementDisplayed("btn_checkStatus");
	}

	public void verifyUserIsNavigatedToCheckYourOrderPage() {
		wait.waitForLoad();
		isElementDisplayed("lbl_checkOrder");
		assertEquals(getCurrentURL().toLowerCase().contains("checkorder"), true,
				"Assert Fails: User is not navigated to check your order page");
		msg.log("Verified: User Is navigated to the check your order page");
	}

	public void checkStatusOfYourOrder(String orderNo, String orderEmail, String postalCode) {
		wait.waitForLoad();
		isElementDisplayed("orderNumber");
		sendText(element("orderNumber"), orderNo);
		isElementDisplayed("order_email");
		sendText(element("order_email"), orderEmail);
		isElementDisplayed("billingPostalCode");
		sendText(element("billingPostalCode"), postalCode);
		isElementDisplayed("btn_checkStatus");
		element("btn_checkStatus").click();
		msg.log("Check status button is clicked");
	}

	public void verifyErrorMsgDisplayedForWrongorderInformation() {
		wait.waitForLoad();
		isElementDisplayed("orderNumber");
		sendText(element("orderNumber"), "11111111");
		isElementDisplayed("order_email");
		sendText(element("order_email"), "test@katespade.com");
		isElementDisplayed("billingPostalCode");
		sendText(element("billingPostalCode"), "vsdvsddv");
		isElementDisplayed("btn_checkStatus");
		element("btn_checkStatus").click();
		msg.log("Check status button is clicked");
		isElementDisplayed("msg_error");
		msg.log("Verified: Error msg is displayed for the wrong order Information : " + element("msg_error").getText());
	}

	public void verifyAllPreviousOrdersPlacedAreDisplayedAndLimitOfOrderInPOrderHistoryPage() {
		wait.waitForLoad();
		isElementDisplayed("list_orderNo");
		List<WebElement> orderNo = elements("list_orderNo");
		int count = 0;
		for (WebElement el : orderNo) {
			count++;
			msg.log("Order No displayed : " + el.getText());
		}

		if (count > 5) {
			isElementDisplayed("list_paginationElement");
			msg.log("Pagination elements are displayed when no of orders are more than 5");
		}

		assertEquals(count <= 5, true, "[Assert Fail]: No of orders displayed in order history page are more than 5");
		msg.log("Verified: No of orders displayed in order history page are less or equal to 5 as expected");

	}

	public void clickFirstLinkOfOrderDetails() {
		wait.waitForLoad();
		isElementDisplayed("link_frstOrderDetails");
		element("link_frstOrderDetails").click();
		msg.log("User clicked on the first link of Order details ");
	}

	public void verifyUserIsNavigatedToOrderHistoryPage() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		}
		assertEquals(getCurrentURL().contains("orders"), true,
				"[Assert Fail]: User is not navigated to the order history page");
		msg.log("Verified: User is navigated to the order history page");
	}

	public void loginIntoAccountFromGuestOrderCheckPage(String email, String password) {
		wait.waitForLoad();
		isElementDisplayed("field_email");
		sendText(element("field_email"), email);
		isElementDisplayed("field_password");
		sendText(element("field_password"), password);
		isElementDisplayed("signIn");
		element("signIn").click();
		msg.log("Sign in button is clicked");

	}

	public void verifyElementsInOrderHistoryPage() {
		wait.waitForLoad();
		isElementDisplayed("list_orderNo");
		isElementDisplayed("list_orderDate");
		isElementDisplayed("list_orderStatus");
		isElementDisplayed("list_orderNumber");
		// isElementDisplayed("list_trackingNo");
		isElementDisplayed("list_shpngAddrsName");
		isElementDisplayed("list_shpngLocation");
		isElementDisplayed("list_items");
		isElementDisplayed("list_orderTotal");

		msg.log("Verified: Order History Page");
	}

	public void verifyThePricingStructureOfProductInOrderHistoryPage() {
		wait.waitForLoad();
		isElementDisplayed("list_orderTotal");
		msg.log("Total cost is " + element("list_orderTotal").getText());

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			assertEquals(element("list_orderTotal").getText().contains("£"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}

		else if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("fr-fr") || getCurrentURL().contains("en-de")) {
			assertEquals(element("list_orderTotal").getText().contains("€"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}
	}

	public void verifyEmailAndPwdAreMandatoryFieldsInGuestOrderCheckPage() {
		wait.waitForLoad();
		isElementDisplayed("field_email");
		element("field_email").sendKeys(Keys.TAB);
		isElementDisplayed("emailErrorMsg");
		msg.log("Error msg displayed : " + element("emailErrorMsg").getText());
		isElementDisplayed("field_password");
		element("field_password").sendKeys(Keys.TAB);
		isElementDisplayed("pwdErrorMsg");
		msg.log("Error msg displayed : " + element("pwdErrorMsg").getText());
	}

	public void verifyRememberMeChkbxIsUncheckedByDefault() {
		wait.waitForLoad();
		isElementDisplayed("chk_rememberMe");
		boolean chkbx = executeJavascriptReturnsBoolean(" return $('#dwfrm_login_rememberme')[0].checked");
		assertEquals(chkbx, false, "[Assert Fail] : Remember Me check box is checked");
		msg.log("Remember Me check box is unchecked by default");
	}

	public void verifyForgotPasswordLinkInOrderHistoryPage(String username) {
		isElementDisplayed("label_forgotPassword");
		executeJavascript("$('#password-reset').click()");
		msg.log("User clicks on Forgot password");
		isElementDisplayed("label_resetPassword");
		msg.log(element("label_resetPassword").getText());
		isElementDisplayed("text_resetPassword");
		msg.log(element("text_resetPassword").getText());
		isElementDisplayed("forgotPassword_field_email");
		isElementDisplayed("forgotpassword_send_button");
		sendText(element("forgotPassword_field_email"), username);
		msg.log(username + ": is entered");
		clickUsingJS(element("forgotpassword_send_button"));
		msg.log("send button is clicked");
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		List<WebElement> thankyou = elements("forgotpassword_thankyou_text");
		for (WebElement text : thankyou) {
			isElementsDisplayed(thankyou);
			msg.log(text.getText());
		}
		isElementDisplayed("goBackToHomePage");
		isElementDisplayed("cross_btn");
		element("cross_btn").click();
		msg.log("Cross button is clicked");
	}

	public void verifyCheckOrderFieldsCannotBeLeftEmpty() {
		wait.waitForLoad();
		isElementDisplayed("orderNumber");
		element("orderNumber").sendKeys(Keys.TAB);
		isElementDisplayed("orderNoError");
		isElementDisplayed("order_email");
		element("order_email").sendKeys(Keys.TAB);
		isElementDisplayed("orderEmailError");
		isElementDisplayed("billingPostalCode");
		element("billingPostalCode").sendKeys(Keys.TAB);
		isElementDisplayed("billingPostalCodeError");

		msg.log("Verified: Check Order fields can't be left empty ");
	}

	public void mobile_verifyCheckOrderFieldsCannotBeLeftEmpty() {
		wait.waitForLoad();
		isElementDisplayed("orderNumber");
		element("orderNumber").click();
		element("order_email").click();
		isElementDisplayed("orderNoError");
		isElementDisplayed("order_email");
		element("order_email").click();
		element("billingPostalCode").click();
		isElementDisplayed("orderEmailError");
		isElementDisplayed("billingPostalCode");
		element("billingPostalCode").click();
		element("order_email").click();
		isElementDisplayed("billingPostalCodeError");

		msg.log("Verified: Check Order fields can't be left empty ");
	}

	public void mobile_verifyEmailAndPwdAreMandatoryFieldsInGuestOrderCheckPage() {
		wait.waitForLoad();
		isElementDisplayed("field_email");
		element("field_email").click();
		element("field_password").click();
		isElementDisplayed("emailErrorMsg");
		msg.log("Error msg displayed : " + element("emailErrorMsg").getText());
		isElementDisplayed("field_password");
		element("field_password").click();
		element("field_email").click();
		isElementDisplayed("pwdErrorMsg");
		msg.log("Error msg displayed : " + element("pwdErrorMsg").getText());
	}

}
