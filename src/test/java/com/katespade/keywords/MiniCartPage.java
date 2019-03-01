package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class MiniCartPage extends GetPage {
	WebDriver driver;

	public MiniCartPage(WebDriver driver) {
		super(driver, "Katespade/MiniCart");
		this.driver = driver;
	}

	public void verifyCartPage() {
		msg.log(getPageTitle());
		isElementDisplayed("header_miniCart");
		isElementDisplayed("prdct_description");
		if ((ConfigPropertyReader.getProperty("browser").equals("safari"))
				|| (ConfigPropertyReader.getProperty("browser").startsWith("ios"))) {
			hardWait(1);
		} else {
			isElementDisplayed("label_item");
			isElementDisplayed("label_price");
			isElementDisplayed("label_quantity");
		}
	}

	public void verifyQuantityDropDown() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("header_summary");

		isElementDisplayed("order__pricesubtotal");
		String old_ordersummary = element("order__pricesubtotal").getText();
		msg.log(old_ordersummary + " is the old order summary price");

		executeJavascript("$('#dwfrm_cart_shipments_i0_items_i0_quantitySelectBoxIt')[0].click()");
		msg.log("User opens the quantity dropdown");

		element("qty_dropdown").click();
		msg.log("User selects a quantity");

		String new_ordersummary = element("order__pricesubtotal").getText();
		msg.log(new_ordersummary + " is the new order summary price");
		assertNotEquals(old_ordersummary, new_ordersummary, "Order summary is not updated");
		msg.log("Order summary is updated");
	}

	public void verifyEditLinkFunctionality() {
		isElementDisplayed("link_edit");
		element("link_edit").click();
		msg.log("User clicks on Edit Link");

		isElementDisplayed("edit_box_prodct_name");
		msg.log(element("edit_box_prodct_name").getText().trim() + " is displayed");

		isElementDisplayed("edit_box_updateItem");
		msg.log(element("edit_box_updateItem").getText().trim() + " is displayed");

		isElementDisplayed("edit_box_qty_label");
		msg.log(element("edit_box_qty_label").getText().trim() + " is displayed");

		isElementDisplayed("edit_box_seefulldetails");
		msg.log(element("edit_box_seefulldetails").getText().trim() + " is displayed");

		isElementDisplayed("edit_box_closebutton");
		msg.log(element("edit_box_closebutton").getText().trim() + " is displayed");

		element("edit_box_closebutton").click();
		msg.log("User clicks on close button");
	}

	public void verifySaveForLaterFuctionality() {
		isElementDisplayed("edit_box_saveforlater");
		msg.log(element("edit_box_saveforlater").getText() + " is displayed");
		assertEquals(element("edit_box_saveforlater").getText(), "SAVE FOR LATER", "Save for later no found");
		msg.log("Save for later verified");

		element("edit_box_saveforlater").click();
		msg.log("User clicks on save for later");

		isElementDisplayed("empty_cart_msg");
		msg.log(element("empty_cart_msg").getText() + " is displayed");

		isElementDisplayed("sfl_caption");
		msg.log(element("sfl_caption").getText().trim());

		isElementDisplayed("moveToBag");
		msg.log(element("moveToBag").getText() + " is displayed");
		assertEquals(element("moveToBag").getText(), "MOVE TO BAG", "Product didn't move to save for later");
		msg.log("Product moved to Move to Bag");

		element("moveToBag").click();
		msg.log("User clicks on Move to Bag");

		wait.waitForPageToLoadCompletely();
		isElementNotDisplayed("empty_cart_msg");
	}

	public void verifyRemoveFunctionality() {
		isElementDisplayed("remove_button");
		msg.log(element("remove_button").getText() + " is displayed");
		element("remove_button").click();
		msg.log("User clicks on Remove button");
		isElementDisplayed("empty_cart_msg");
	}

	public void verifyMiniCartWindow() {
		wait.waitForPageToLoadCompletely();
		hardWait(2);
		scrollUpToPage();
		isElementDisplayed("link_bagQuantity");
		isElementDisplayed("icon_bag");
		hover(element("icon_bag"));
		String quantity = element("link_bagQuantity").getText();
		quantity = quantity.replaceAll("\\D+", "");
		int qty = Integer.parseInt(quantity);
		msg.log("Quantity: " + qty);
		// hover(element("icon_bag"));

		if (qty != 0) {
			wait.waitForElementToBeVisible(element("miniCart"));
			String style = element("miniCart").getAttribute("style");
			Assert.assertTrue(style.contains("display: block"),
					"[Assert Failed]: Mini cart does not appear for quntity more than 0");
			msg.pass("Mini cart appears for quantity more than 0");
			isElementDisplayed("button_checkout");
		} else {
			Assert.assertTrue(element("emptyMiniCart").isDisplayed(),
					"[Assert Failed]: Mini cart display for 0 quantity");
			msg.pass("Mini Cart does not display for 0 quantity");
		}
	}

	public void verifyMiniCartInMobile() {
		wait.waitForLoad();
		isElementDisplayed("link_bagQuantity");
		isElementDisplayed("icon_bag");
		String quantity = element("link_bagQuantity").getText();
		msg.log(quantity);
		hardWait(1);
		executeJavascript("$('.mini-cart-content')[0].style.display='block'");
		msg.log("block mini cart");
		isElementDisplayed("miniCart");
		verifyDetailsOfProductsPresentInMiniCartPage();
		executeJavascript("$('.mini-cart-content')[0].style.display='none'");
	}

	public void verifyStickyBagIconFunctionality() {
		wait.waitForPageToLoadCompletely();
		scrollDownToBottom();
		int bagQty = Integer.parseInt(element("stickyBagQty").getText().replaceAll("\\D+", ""));

		if (bagQty != 0) {
			isElementDisplayed("header_miniCart");
			int quantity = Integer.parseInt(element("header_miniCart").getText().replaceAll("\\D+", ""));
			msg.log("Quantity is " + quantity);
			String style = element("miniCart").getAttribute("style");
			isElementDisplayed("button_checkout");
			Assert.assertTrue(style.contains("display: block"),
					"[Assert Failed]: Mini cart does not appear for quntity more than 0");
			msg.log("[Assert Passed]: Mini cart appears for quantity more than 0");
		} else {
			hover(element("icon_bag"));
			isElementNotDisplayed("header_miniCart");
			/*
			 * Assert.assertTrue(element("emptyMiniCart").isDisplayed(),
			 * "[Assert Failed]: Mini cart display for 0 quantity");
			 * msg.log("[Assert Passed]: Mini Cart does not display for 0 quantity");
			 */
		}
	}

	public void checkoutFromMiniCart() {
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			scrollUpToPage();
			executeJavascript("$('.mini-cart-content')[0].style.display='block'");

			clickByJavascript(element("btn_mincartCheckout"));
			msg.log("Clicked on 'VIEW BAG/CHECKOUT' button from Mini Cart popup");
		} else {
			scrollUpToPage();
			executeJavascript("$('.mini-cart-content')[0].style.display='block'");

			isElementDisplayed("button_checkout");
			executeJavascript("document.getElementsByClassName('button mini-cart-link-cart')[0].click()");
			msg.log("Clicked on 'VIEW BAG/CHECKOUT' button from Mini Cart popup");
		}
	}

	public void verifyDetailsOfProductsPresentInMiniCartPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("list_cartImages");
		isElementDisplayed("list_size");
		isElementDisplayed("list_color");
		isElementDisplayed("list_productPrice");
		isElementDisplayed("list_productQuantity");
		isElementDisplayed("list_productName");
		/*
		 * List<WebElement> productName = elements("list_productName"); for (WebElement
		 * el : productName) { msg.log("Product in Minicart : " + el.getText()); }
		 */
		msg.log("Verified cart pages details");
	}

	public void waitForMiniCartWindowToDisappear() {
		try {
			if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
				// isElementDisplayed("btn_mincartCheckout");
				// wait.waitForElementToDisappear(element("btn_mincartCheckout"));
			} else {
				isElementDisplayed("header_miniCart");
				wait.waitForElementToDisappear(element("header_miniCart"));
			}
		} catch (Exception e) {
			msg.log("Exception " + e);
		}
	}

	public void verifyBonusProductDetailsOnMiniCartPage() {
		isElementDisplayed("icon_bag");
		hover(element("icon_bag"));
		hardWait(2);
		msg.log("User hovers on min cart icon");
		// Product style Id on Bag Page
		String styleIdOfBonusProductOnCart = element("promoRowStyleId").getText().replaceAll(" ", "");
		msg.log("Style ID of bonus product on Cart Page:" + styleIdOfBonusProductOnCart.toUpperCase());
		// Product style Id on Mini Cart
		// String styleIdOnMiniCart =
		// elements("text_firstStyle").get(0).getText().replaceAll(" ", "");
		String styleIdOnMiniCart = executeJavascriptReturnsString(
				"return $('.sku span:nth-child(1)+span').eq(0).text()");
		msg.log("Style ID of bonus product on select bonus produt page: " + styleIdOnMiniCart);
		// Verify Product on bag page is same as on mini cart
		assertEquals(styleIdOfBonusProductOnCart.toUpperCase().contains(styleIdOnMiniCart), true,
				"Qualifying does not product displays first in cart");
		msg.log("Qualifying product displays first in cart");
	}

	public void verifyDetailsOfBonusProductsPresentInMiniCartPage(String bonusPrice) {
		isElementDisplayed("list_productName");
		isElementDisplayed("list_cartImages");
		isElementDisplayed("list_size");
		isElementDisplayed("list_color");
		isElementDisplayed("list_productQuantity");
		isElementDisplayed("list_bonusProductPrice");
		String subTtal = elements("list_bonusProductPrice").get(0).getText();
		msg.log(subTtal);
		assertEquals(subTtal.contains(bonusPrice), true,
				"Bonus product's subTotal is not displayed as BONUS on Mini Cart");
		msg.log("Bonus product's subTotal is displayed as BONUS on Mini Cart");
		msg.log("Verified cart pages details");
	}

	public void goToCartPage() {
		wait.waitForLoad();
		isElementDisplayed("icon_bag");
		executeJavascript("$('.mini-cart-link')[0].click()");
		// clickUsingJS(element("icon_bag"));
		msg.log("User clicks on icon_bag");
	}

	public void checkoutFromMiniCartInIPad() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("button_checkout");
		clickUsingJS(element("button_checkout"));
		msg.log("User clicks on checkout button");
	}

	public void clickBagIcon() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_bagQuantity");
		clickUsingJS(element("link_bagQuantity"));
		msg.log("Clicked on the bag icon");
	}

	public void closeMiniCartWindowIniPhone() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_closeMobileMiniCart");
		clickUsingJS(element("btn_closeMobileMiniCart"));
		msg.log("Mini Cart Window is closed");
	}
}
