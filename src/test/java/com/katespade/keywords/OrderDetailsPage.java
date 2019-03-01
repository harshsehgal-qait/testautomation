package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class OrderDetailsPage extends GetPage {

	public OrderDetailsPage(WebDriver driver) {
		super(driver, "Katespade/OrderDetailsPage");
	}

	public void verifyUserIsNavigatedToOrderDetailsPage() {
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		}
		assertEquals(getCurrentURL().contains("Order-Orders"), true,
				"[Assert Fail]: User is not navigated to the order details page");
		msg.log("Verified: User is navigated to the order details page");
	}

	public void verifyLinksInOrderDetailsPage() {
		isElementDisplayed("link_backToOrderHistory");
		isElementDisplayed("link_returnToShopping");
		msg.log("Verified Links in Order Details Page");
	}

	public void verifyOrderTrackingDetailsOnOrderDetailsPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("title_orderNo");
		msg.log("Order No : " + element("title_orderNo").getText());
		isElementDisplayed("orderDate");
		msg.log("Order Date : " + element("orderDate").getText());
		isElementDisplayed("orderStatus");
		msg.log("Order Status : " + element("orderStatus").getText());
		isElementDisplayed("orderNo");
		// isElementDisplayed("trackingNo");
		// msg.log("Tracking no : " + element("trackingNo").getText());
		msg.log("Verified : Verified Order tracking details in order details page");
	}

	public void verifyPaymentDetailsOnOrderDetailsPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("card_logo");
		isElementDisplayed("cardNo");
		// isElementDisplayed("cardExpiry");

		msg.log("Verified: Payment Details on order details page");

	}

	public void verifyProductDetailsOnOrderDetailsPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("subTotal");
		msg.log("Sub total : " + element("subTotal").getText());
		isElementDisplayed("shippingTotal");
		msg.log("Shipping total : " + element("shippingTotal").getText());
		isElementDisplayed("orderTotal");
		msg.log("Order total : " + element("orderTotal").getText());
		isElementDisplayed("list_itemImage");
		isElementDisplayed("list_itemName");
		isElementDisplayed("list_styleNo");
		// isElementDisplayed("list_trackingStatus");
		isElementDisplayed("list_itemQty");
		isElementDisplayed("list_itemPrice");
		msg.log("Verified: Product details on order details page");
	}

	public void verifyBillingAndShippingDetailsOnOrderDetailsPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("billingAddrsName");
		isElementDisplayed("billingLocation");
		isElementDisplayed("list_shipngAddrs");
		isElementDisplayed("list_shipingPhone");
		isElementDisplayed("list_shipngMthd");
		msg.log("Verified: Billing and shipping details on order details page");

	}

	public void verifyContentAssetAccNavTextAndItsLeftNavigation() throws InterruptedException {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("myAccountPlusIcon");
			element("myAccountPlusIcon").click();
			msg.log("My Account plus icon is clicked");
			isElementDisplayed("contentAsset_accNavText");
			msg.log("Content Asset Account Nav Text is aligned as expected");
		} else {
			isElementDisplayed("contentAsset_accNavText");
			assertEquals(element("contentAsset_accNavText").getCssValue("float"), "left",
					"[Assert Fail] : Content Asset Account Nav Text is not aligned as expected");
			msg.log("Content Asset Account Nav Text is aligned as expected");
		}

	}

	public void verifyOptionLinkingInContentAssetLeftNavigation() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lnk_myAccount");
		isElementDisplayed("lnk_myInfo");
		isElementDisplayed("lnk_addressBook");
		isElementDisplayed("lnk_creditCards");
		isElementDisplayed("lnk_orderHistory");
		isElementDisplayed("lnk_wishlist");
		isElementDisplayed("lnk_shopConfidently");

		msg.log("Options in the content assets  left navigation are linked as expected");
	}

	public void verifyLeftHandHelpText() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("helpText");
		msg.log("Left hand help text is displayed : " + element("helpText").getText());
	}

	public void clickOnBackToOrderHistoryLinkInOrderDetailsPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_backToOrderHistory");
		element("link_backToOrderHistory").click();
		msg.log("Back to order history link is clicked");
	}

	public void clickOnReturnToShoppingLinkInOrderDetailsPage() {
		wait.waitForPageToLoadCompletely();
		scrollDownToBottom();
		isElementDisplayed("link_returnToShopping");
		element("link_returnToShopping").click();
		msg.log("Return to shopping link is clicked");
		assertEquals(getCurrentURL().contains("homepage"), true,
				"[Assert Fail]: User is not navigated to the homepage after clicking Return to Shopping link");
		msg.log(" User is navigated to the homepage after clicking Return to Shopping link");

	}

	public void verifyOptionLinkingInContentAssetLeftNavigationForGuestUser() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lnk_createAccount");
		isElementDisplayed("lnk_shopConfidently");
		msg.log("Options in the content assets left navigation for guest user are linked as expected");
	}

}
