package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class OrderReviewPage extends GetPage {

	public OrderReviewPage(WebDriver driver) {
		super(driver, "Katespade/OrderReviewPage");
	}

	public void orderConfirmation() {
		isElementDisplayed("text_thankyou");
		msg.log(element("text_thankyou").getText());
	}

	public void verifyStep3OrderReviewIsActive() {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}

		isElementDisplayed("lbl_stepNo");

		assertEquals(element("lbl_stepNo").getCssValue("background-color"), "rgba(72, 168, 66, 1)",
				"Color of the dot is not green");
		msg.log("Color of the dot is green");

		msg.log("Step 3 i.e Order Review is active");
	}

	public void verifyCustomerIsUnableToEditItemsWithinCartInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementNotDisplayed("link_cartImages");
		msg.log("Verified: Items are not editable");
	}

	public void clickShipngAddressEditBtnInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_editShipngAddrs");
		element("btn_editShipngAddrs").click();
		msg.log("Edit button is clicked");
	}

	public void clickBillingAddressEditBtnInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_editBillngAddrs");
		element("btn_editBillngAddrs").click();
		msg.log("Edit button is clicked");
	}

	public void clickShipngMethodEditBtnInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_editShipngMethod");
		element("btn_editShipngMethod").click();
		msg.log("Edit button is clicked");
	}

	public void verifyElementsInOrderSummaryAtOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_subTotal");
		isElementDisplayed("lbl_shipngMethod");
		isElementDisplayed("shp_method");
		isElementDisplayed("totalCost");
		isElementDisplayed("card_logo");
		isElementDisplayed("card_num");
		isElementDisplayed("card_exp");
		isElementDisplayed("lbl_disclaimer");
		isElementDisplayed("link_termsOfUse");
		isElementDisplayed("link_privacyPolicy");
		msg.log("Verified: Order Summary Box");

	}

	public void verifyThePricingStructureOfProductInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("totalCost");
		msg.log("Total cost is " + element("totalCost").getText());

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			assertEquals(element("totalCost").getText().contains("£"), true, "Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}

		else if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("fr-fr") || getCurrentURL().contains("en-de")) {
			assertEquals(element("totalCost").getText().contains("€"), true, "Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}
	}

	public void verifyTheStickyNatureOfOrderSummaryBoxInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("orderSummaryBox");
		String position = executeJavascriptReturnsString("return $('.place-order-totals').css('position')");
		System.out.println("************" + position);
		assertEquals(position, "static", "[Assert Fail] : Order Summary is not sticky");
		msg.log("[Assert Fail] : Order Summary is sticky");

	}

	public void verifyTermsOfUseAndPrivacyPolicyLinksInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_termsOfUse");
		assertEquals(element("link_termsOfUse").getAttribute("href").contains("terms-conditions"), true,
				"[Assert Fail]: Terms of user Link is not matched");
		msg.log("[Assert Pass] : Terms of use link is matched");
		isElementDisplayed("link_privacyPolicy");
		assertEquals(element("link_privacyPolicy").getAttribute("href").contains("privacy-policy"), true,
				"[Assert Fail]: Privacy Policy Link is not matched");
		msg.log("[Assert Pass] : Privacy Policy link is matched");
	}

	public void verifyCustomerServiceContactNoIsDisplayedOnOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_customerServiceNo");
		msg.log("Verified: Customer Service Number is displayed on the Order review page");
	}

	public void clickSubmitYourOrderButtonInOrderReviewPage() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
			scrollDown(element("btn_submitYourOrder"));
		}

		isElementDisplayed("btn_submitYourOrder");
		element("btn_submitYourOrder").click();
		msg.log("Submit your order button is clicked");
	}

	public void verifyElementsInOrderReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("list_cartImages");
		isElementDisplayed("list_productName");
		isElementDisplayed("list_productStyle");
		isElementDisplayed("list_productQty");
		isElementDisplayed("list_productCost");
		isElementDisplayed("list_productSize");
		isElementDisplayed("list_productColor");
		isElementDisplayed("lbl_shippingAddress");
		isElementDisplayed("lbl_billingAddress");
		isElementDisplayed("lbl_shipngMthd");
		msg.log("Verified: Elements in order review page is verified");

	}

	public void verifyPromoCodeWithDetailsAreDislayedOnOrderReviewPage(String promoApplied) {
		isElementDisplayed("promo_applied");
		isElementDisplayed("promo_details");
		msg.log(element("promo_applied").getText());
		msg.log(element("promo_details").getText());
		assertEquals(element("promo_applied").getText().contains(promoApplied), true,
				"Promo code is not applied successfully");
		msg.log("Promo Code is applied successfully");

	}

}
