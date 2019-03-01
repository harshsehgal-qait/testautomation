package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.PropFileHandler;

public class OrderConfirmationPage extends GetPage {

	public OrderConfirmationPage(WebDriver driver) {
		super(driver, "Katespade/OrderConfirmationPage");
	}

	public void verifyUserIsNavigatedToOrderConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("text_thankYou");
		isElementDisplayed("lbl_confirmationMsg");
		msg.log("Order Confirmation Message :" + element("text_thankYou").getText());
		msg.log("Content Asset Confirmation Msg :" + element("lbl_confirmationMsg").getText());
	}

	public void verifyFirstNameIsAppearingInOrderReciept() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("text_thankYou");
		assertEquals(element("text_thankYou").getText().contains(PropFileHandler.readProperty("user_fname")), true);
	}

	public void verifyThePricingStructureOfProductInOrderConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("orderTotal");
		msg.log("Total cost is " + element("orderTotal").getText());

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			assertEquals(element("orderTotal").getText().contains("£"), true, "Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}

		else if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("fr-fr") || getCurrentURL().contains("en-de")) {
			assertEquals(element("orderTotal").getText().contains("€"), true, "Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}
	}

	public void verifyPaymentRelatedInformationInOrderConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("subTotal");
		isElementDisplayed("shipngCost");
		isElementDisplayed("orderTotal");
		isElementDisplayed("creditCardLogo");
		isElementDisplayed("lbl_endingDigits");
		isElementDisplayed("lbl_expiryDate");
		msg.log("Verified: Payment Related Inforamtion on the order confirmation Page is verified");
	}

	public void verifyProductRelatedInformationOnConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("productImage");
		isElementDisplayed("productName");
		isElementDisplayed("productStyle");
		isElementDisplayed("productSize");
		isElementDisplayed("productColor");
		isElementDisplayed("productQty");
		isElementDisplayed("productPrice");

		msg.log("Verified: Product Related Inforamtion on the order confirmation Page is verified");
	}

	public void verifySelectedShippingMethodTypeIsDisplayedInOrderConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("shippingMethod");
		isElementDisplayed("shippingMthdType");
		msg.log("Verified: Shipping Method type selected is displayed : " + element("shippingMthdType").getText());
	}

	public void verifyOrderNumberIsDisplayed() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("orderNumber");
		int orderNumber = Integer.parseInt(element("orderNumber").getText().replaceAll("\\D+", ""));
		String orderNo = Integer.toString(orderNumber);
		PropFileHandler.writeProperty("orderNumber", orderNo);
		msg.log("Order number is : " + orderNumber);
	}

	public void verifyShippingAddressrelatedInfoInOrderConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_shipngAddrs");
		isElementDisplayed("shipngAddrs");
		isElementDisplayed("shpingPhone");

		// Pending due to bug KSEU-916

		msg.log("Verified: Shipping address related information is displayed");

	}

	public void verifyBillingAddressrelatedInfoInOrderConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_billingAddrs");
		isElementDisplayed("billingAddrs");
		isElementDisplayed("billingAddrsName");

		// Pending due to bug KSEU-916

		msg.log("Verified: Billing address related information is displayed");

	}

	public void verifyUserIsAbleToClickPlusBtnToExpandAndMinusBtnToCloseCreateAnAccountDrawer() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_+_createAccount");

		executeJavascript("$('.toggle').click()");

		isElementDisplayed("txtbox_email");
		isElementDisplayed("txtbox_pwd");
		isElementDisplayed("txtbox_confirmPwd");
		msg.log("Verified: User is able to tap the My account creation (+) to expand the drawer");

		isElementDisplayed("btn_-_createAccount");
		executeJavascript("$('.toggle.expanded').click()");
		isElementNotDisplayed("txtbox_email");
		msg.log("Verified: User is able to tap the My account creation (-) to close the drawer");
	}

	public void createAnAccountFromOrderReceipt(String email, String pwd) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_+_createAccount");
		executeJavascript("$('.toggle').click()");
		element("txtbox_email").clear();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			clickUsingJS(element("txtbox_pwd"));
		} else {
			element("txtbox_email").sendKeys(Keys.TAB);
		}
		isElementDisplayed("btn_createAnAccountDisbled");
		isElementDisplayed("txtbox_email");
		sendText(element("txtbox_email"), email);
		msg.log("User Entered: " + email);
		isElementDisplayed("txtbox_pwd");
		sendText(element("txtbox_pwd"), pwd);
		msg.log("User Entered: " + pwd);
		isElementDisplayed("txtbox_confirmPwd");
		sendText(element("txtbox_confirmPwd"), pwd);
		msg.log("User Entered: " + pwd);
		clickUsingJS(element("txtbox_pwd"));
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("btn_createAnAccount");
		element("btn_createAnAccount").click();
		msg.log("Create An Account button is clicked");
	}

	public void verifyContactUsContentAssetInOrderConfirmationPage() {
		wait.waitForPageToLoadCompletely();
		// isElementDisplayed("link_liveChat"); // live chat link is removed as per
		// comment in KSEU-819
		// msg.log("Live Chat link is displayed");
		isElementDisplayed("link_phone");
		msg.log("Phone number is displayed: " + element("link_phone").getText());
		isElementDisplayed("link_emailUs");
		msg.log("Email Us link is displayed :" + element("link_emailUs").getAttribute("href"));
	}

	public void verifyEnteredEmailIsAutopopulatedInEmailFieldWhileCreatingAcc(String guestEmail) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_+_createAccount");
		executeJavascript("$('.toggle').click()");
		isElementDisplayed("txtbox_email");
		assertEquals(element("txtbox_email").getAttribute("value"), guestEmail,
				"[Assert Fail]: Email is not autopopulated for the guest user");
		msg.log("Email is autopopulated for the guest user");
		isElementDisplayed("btn_-_createAccount");
		element("btn_-_createAccount").click();
	}

}
