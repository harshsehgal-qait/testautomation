package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class ShoppingCartPage extends GetPage {

	public ShoppingCartPage(WebDriver driver) {
		super(driver, "Katespade/ShoppingCart");
	}

	public void verifyShoppingCartPage() {
		wait.waitForLoad();
		isElementDisplayed("lbl_cartHeader");
		isElementDisplayed("lbl_summary");
		isElementDisplayed("lbl_subTotal");
		isElementDisplayed("lbl_orderTotal");
		isElementDisplayed("btn_checkout");
		isElementDisplayed("btn_promoCode");
		isElementDisplayed("customerServiceWrapper");
		try {
			isElementDisplayed("btn_paypal");
		} catch(AssertionError er) {
			msg.log("As per comment on ESEU-479 bug: PayPal payment option is DISABLED for UK site !!!");
		}
		msg.log("Cart page verified");
	}

	public void verifyProductDetailOnCartPage() {
		wait.waitForLoad();
		isElementDisplayed("img_CartImage");
		isElementDisplayed("link_productName");
		msg.log(element("link_productName").getText());
		isElementDisplayed("lbl_productID");
		msg.log(element("lbl_productID").getText());
		isElementDisplayed("dropdown_quantity");
	}

	public void clickOnCheckoutButton() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
			scrollUpToPage();
		}
		clickUsingJS(element("btn_checkout"));
		msg.log("Clicked on 'Checkout' button");
	}

	public void verifyGlobalSlotBannerIsPresentOnBagPage() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(2);
		}
		isElementDisplayed("lbl_cartHeader");
		isElementNotDisplayed("lbl_globalSlotBanner");
		msg.log("Global Slot Banner DOES NOT APPEAR ANYMORE on Shopping Bag !!!");
	}

	public void verifyThePricingStructureOfOrderTotalInShoppingPage() {
		wait.waitForLoad();
		isElementDisplayed("lbl_orderTotal");
		msg.log("Order Total is " + element("lbl_orderTotal").getText());

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			msg.log("***********" + element("lbl_orderTotal").getText());
			assertEquals(element("lbl_orderTotal").getText().contains("£"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}

		else if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("fr-fr") || getCurrentURL().contains("en-de")) {
			assertEquals(element("lbl_orderTotal").getText().contains("€"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}
	}

	public void verifyThatCustomerServiceTelephoneNumberIsDisplayed() {
		wait.waitForLoad();

		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr-fr")) {
			isElementDisplayed("lbl_helplineNoFr");
			msg.log("Customer Service Telephone Number is displayed : " + element("lbl_helplineNoFr").getText());
		}

		else if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("en-gb") || getCurrentURL().contains("en-de")) {
			isElementDisplayed("lbl_helplineNo");
			msg.log("Customer Service Telephone Number is displayed : " + element("lbl_helplineNo").getText());
		}
	}

	public void verifyTheInformationOnOrderSummaryBox() {
		wait.waitForLoad();
		isElementDisplayed("lbl_subTotal");
		isElementDisplayed("lbl_orderTotal");
	}

	public void verifyProductIsAddedIntoTheBag() {
		isElementDisplayed("label_bag");
		isElementDisplayed("link_bagQuantity");
		assertEquals(element("link_bagQuantity").getText(), "(1)", "Product is not added in the bag");
		msg.log("Product is added in the cart");

	}

	public void verifyUserIsAbleToApplyPromoCodeInShopingCartPage(String promoCode) {
		wait.waitForLoad();
		isElementDisplayed("itemPrice");
		isElementDisplayed("lbl_subTotal");
		int befPromoCodeItemPrice = Integer.parseInt(element("itemPrice").getText().replaceAll("\\D+", ""));
		int befPromoCodeSubTotalPrice = Integer.parseInt(element("lbl_subTotal").getText().replaceAll("\\D+", ""));
		isElementDisplayed("btn_promoCode");
		clickUsingJS(element("btn_promoCode"));
		msg.log("Promo Code button is clicked");
		isElementDisplayed("txtbx_promoCode");
		sendText(element("txtbx_promoCode"), promoCode);
		isElementDisplayed("btnApply");
		clickUsingJS(element("btnApply"));
		msg.log("Apply button is clicked");
		wait.waitForLoad();
		isElementDisplayed("unadjstdStrikeOutPrice");
		isElementDisplayed("promocodeName");
		verifyTheRedColorOfAdjustedPriceAfterApplyingPromoCode();
		int aftPromoCodeItemPrice = Integer.parseInt(element("priceAdjstdAfterPromo").getText().replaceAll("\\D+", ""));
		int aftPromoCodeSubTotalPrice = Integer.parseInt(element("lbl_subTotal").getText().replaceAll("\\D+", ""));
		assertEquals(befPromoCodeItemPrice > aftPromoCodeItemPrice, true, "[Assert Fail]: Promo code is not applied");
		assertEquals(befPromoCodeSubTotalPrice > aftPromoCodeSubTotalPrice, true,
				"[Assert Fail]: Promo code is not applied");
		msg.log("Promo code is applied ");
		isElementDisplayed("lnk_sumaryBx_removeCpn");
		element("lnk_sumaryBx_removeCpn").click();
		msg.log("Remove btn in summary boxis clicked");
		wait.waitForLoad();
		int aftPromoCodeRemoveSubTotalPrice = Integer
				.parseInt(element("lbl_subTotal").getText().replaceAll("\\D+", ""));
		assertEquals(befPromoCodeSubTotalPrice, aftPromoCodeRemoveSubTotalPrice,
				"[Assert Fail]: Promo code is not removed");
		msg.log("Promo code is removed ");
	}

	public void verifyUserIsAbleToApplyPromoCodeAndVerifyDetailsInFooterOfShopingCartPage(String promoCode)
			throws InterruptedException {
		wait.waitForLoad();
		isElementDisplayed("itemPrice");
		isElementDisplayed("lbl_subTotal");
		isElementDisplayed("lbl_orderTotal");
		int befPromoCodeItemPrice = Integer.parseInt(element("itemPrice").getText().replaceAll("\\D+", ""));
		int befPromoCodeSubTotalPrice = Integer.parseInt(element("lbl_subTotal").getText().replaceAll("\\D+", ""));
		int befPromoCodeOrderTotalPrice = Integer.parseInt(element("lbl_orderTotal").getText().replaceAll("\\D+", ""));
		applyPromoCodeInShopingCartPage(promoCode);
		verifyTheRedColorOfAdjustedPriceAfterApplyingPromoCode();
		int aftPromoCodeItemPrice = Integer.parseInt(element("priceAdjstdAfterPromo").getText().replaceAll("\\D+", ""));
		int aftPromoCodeSubTotalPrice = Integer.parseInt(element("lbl_subTotal").getText().replaceAll("\\D+", ""));
		int aftPromoCodeOrderTotalPrice = Integer.parseInt(element("lbl_orderTotal").getText().replaceAll("\\D+", ""));
		assertEquals(befPromoCodeItemPrice > aftPromoCodeItemPrice, true,
				"[Assert Fail]: Promo code is not applied , item price is not as expected");
		assertEquals(befPromoCodeSubTotalPrice > aftPromoCodeSubTotalPrice, true,
				"[Assert Fail]: Promo code is not applied , sub total price is not as expected");
		assertEquals(befPromoCodeOrderTotalPrice > aftPromoCodeOrderTotalPrice, true,
				"[Assert Fail]: Promo code is not applied , order total is not as expected");
		msg.log("Promo code is applied ");
		isElementDisplayed("lbl_successCartCouponMsg");
		msg.log("Coupon successfully applied msg displayed : " + element("lbl_successCartCouponMsg").getText());
		removePromoCodeInShopingCartPage();
		wait.waitForLoad();
		int aftPromoCodeRemoveSubTotalPrice = Integer
				.parseInt(element("lbl_subTotal").getText().replaceAll("\\D+", ""));
		int aftPromoCodeRemoveorderTotalPrice = Integer
				.parseInt(element("lbl_subTotal").getText().replaceAll("\\D+", ""));
		assertEquals(befPromoCodeSubTotalPrice, aftPromoCodeRemoveSubTotalPrice,
				"[Assert Fail]: Promo code is not removed");
		assertEquals(befPromoCodeOrderTotalPrice, aftPromoCodeRemoveorderTotalPrice,
				"[Assert Fail]: Promo code is not removed");
		msg.log("Promo code is removed ");
	}

	public void verifyTheRedColorOfAdjustedPriceAfterApplyingPromoCode() {
		wait.waitForLoad();
		isElementDisplayed("priceAdjstdAfterPromo");
		assertEquals(element("priceAdjstdAfterPromo").getCssValue("color"), "rgba(237, 28, 36, 1)",
				"Red color not displaying");
		msg.log("Red color is displayed");
	}

	public void applyPromoCodeInShopingCartPage(String promoCode) {
		wait.waitForLoad();
		scrollDown(element("btn_promoCode"));
		isElementDisplayed("btn_promoCode");
		clickUsingJS(element("btn_promoCode"));
		msg.log("Promo Code button is clicked");
		isElementDisplayed("txtbx_promoCode");
		sendText(element("txtbx_promoCode"), promoCode);
		isElementDisplayed("btnApply");
		element("btnApply").click();
		msg.log("Apply button is clicked");
		wait.waitForLoad();
		try {
			isElementDisplayed("unadjstdStrikeOutPrice");
			isElementDisplayed("priceAdjstdAfterPromo");
		} catch (Exception e) {
			msg.log("Exception : " + e);
		}
		msg.log("Promo code is applied");
	}

	public void removePromoCodeInShopingCartPage() throws InterruptedException {
		wait.waitForLoad();
		isElementDisplayed("lnk_coupnDrawer_removeCpn");
		scrollToElement(element("lnk_coupnDrawer_removeCpn"));
		clickUsingJS(element("lnk_coupnDrawer_removeCpn"));
		msg.log("Remove btn in Coupon Drawer is clicked");
	}

	public void verifyErrorMsgForInvalidCouponCode() {
		wait.waitForLoad();
		isElementDisplayed("invalidCouponError");
		msg.log("Verified error msg is displayed for invalid promo code");
	}

	public void verifyBonusProductMsgAndSelectBonusProductLink(String bonusProductMsg) {
		isElementDisplayed("lbl_bonus_product_msg");
		String bonusMsg = element("lbl_bonus_product_msg").getText();
		msg.log(bonusMsg);
		assertEquals(bonusMsg.contains(bonusProductMsg), true, "Promotional message is not present");
		msg.log("Promotional message is present");
		isElementDisplayed("link_selectBonusProduct");
		clickUsingJS(element("link_selectBonusProduct"));
	}

	public void verifySelectBonusProductModal() {
		wait.waitForElementToBeVisible(element("header_selectBonusProduct"));
		isElementDisplayed("header_selectBonusProduct");
		isElementDisplayed("primaryImg");
		isElementDisplayed("productName");
		isElementDisplayed("productStyle");
		isElementDisplayed("swatch");
		isElementDisplayed("btn_select");
		isElementNotDisplayed("product_info");
	}

	public void verifyProductInfoAndAddToBagWhenBtnToSelectIsClicked() {
		element("btn_select").click();
		wait.waitForElementToBeClickable(element("btn_addToBagBonus"));
		isElementDisplayed("product_info");
	}

	public void verifyCartPageOnAddingBonusProductToCart(String productStyleOfItem) {
		String styleIdOfBonusProduct = element("productStyle").getText().replaceAll(" ", "");
		msg.log("Style ID of bonus product on select bonus produt page: " + styleIdOfBonusProduct);
		element("btn_addToBagBonus").click();
		msg.log("User clicks on Add to bag button");
		wait.waitForLoad();

		// Bonus products display as an additional line item in the bag
		String styleIdOfBonusProductOnCart = element("promoRowStyleId").getText().replaceAll(" ", "");
		msg.log("Style ID of bonus product on Cart Page:" + styleIdOfBonusProductOnCart);
		assertEquals(styleIdOfBonusProductOnCart.contains(styleIdOfBonusProduct), true,
				"Qualifying does not product displays first in cart");
		msg.log("Qualifying product displays first in cart");

		// Qualifying product added to the bag is displayed first
		String productIdOnCart = element("cartRowStyleId").getText();
		msg.log("Style ID of product qualifying Bounus Bonanza: " + productIdOnCart);
		assertEquals(productIdOnCart.equalsIgnoreCase(productStyleOfItem), true,
				"Qualifying does not product displays first in cart");
		msg.log("Qualifying product displays first in cart");
	}

	public void verifyBonusProductQuantityAndTotalCost(String bonusProductPrice, String bonusProductQnt,
			String bonusProductSubTotal) {
		// Bonus Product price is 1
		isElementDisplayed("bonusProductQuantity");
		String quantity = element("bonusProductQuantity").getText();
		msg.log(quantity);
		assertEquals(quantity.contains(bonusProductQnt), true, "Bonus product quantity is not 1");
		msg.log("Bonus product quantity is 1");

		// Bonus Product price is $0.00
		isElementDisplayed("bonusProductPrice");
		String price = element("bonusProductPrice").getText();
		msg.log(price);
		assertEquals(price.contains(bonusProductPrice), true, "Bonus product price is not 0.00");
		msg.log("Bonus product price is 0.00");

		// SubTotal price is displayed as BONUS
		isElementDisplayed("bonusProductSubTotal");
		String subTtal = element("bonusProductSubTotal").getText();
		msg.log(subTtal);
		assertEquals(subTtal.contains(bonusProductSubTotal), true,
				"Bonus product's subTotal is not displayed as BONUS");
		msg.log("Bonus product's subTotal is displayed as BONUS");
	}

	public void verifyBonusProductDetailsOnBagPage() {
		isElementDisplayed("img_bonusProduct");
		isElementDisplayed("bonusProductName_lnk");
		isElementDisplayed("promotionalMsg");
		isElementDisplayed("productAttributes");
		isElementDisplayed("inventory_msg");
		if (element("inventory_msg").isDisplayed()) {
			isElementDisplayed("addToWishlist");
		} else {
			isElementNotDisplayed("addToWishlist");
		}
	}

	public void verifyBonusProductNameLinkNavigatesUserToPDPPage() {
		isElementDisplayed("bonusProductName_lnk");
		clickUsingJS(element("bonusProductName_lnk"));
		wait.waitForLoad();
		isElementDisplayed("pdpPage");
		isElementDisplayed("pdp_ProductName");
		msg.pass("User lands on PDP page");
	}

	public void verifyProductAreaOfTheBagIsSepareatedInTwoColumn() {
		wait.waitForLoad();
		isElementDisplayed("form_cartItems");
		isElementDisplayed("orderSummary");
		msg.log("Verified Product area is separated in two column");
	}

	public void verifyUserIsAbleToRemoveProductFromShoppingCart(String productIndex) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(1);
			wait.waitForElementToBeClickable(element("btn_removeWithIndex", productIndex));
		}
		isElementDisplayed("btn_removeWithIndex", productIndex);
		element("btn_removeWithIndex", productIndex).click();
		msg.log("Remove button is clicked for product " + productIndex);
	}

	public void verifyUserIsAbleToEditAProduct(String prodIndex) {
		wait.waitForLoad();
		isElementDisplayed("link_productName");
		List<WebElement> prodName = elements("link_productName");
		String shoppingPageProdName = prodName.get(Integer.parseInt(prodIndex)).getText();
		msg.log(shoppingPageProdName.toLowerCase());
		isElementDisplayed("btn_edit", "1");
		clickUsingJS(element("btn_edit", "1"));
		msg.log("User clicks on Edit Button");
		wait.waitForElementToBeVisible(element("quickBuy_Dialog"));
		String quickBuyProdName = executeJavascriptReturnsString(" return $('h1.product-name').text()");
		msg.log(quickBuyProdName);
		assertEquals(shoppingPageProdName.toLowerCase().contains(quickBuyProdName), true,
				"[Assert Fail]: user is not navigated to the Quickbuy page after clicking edit button in shopping page");
		msg.pass("Verified user is navigated to the Quickbuy page after clicking edit button in shopping page");
	}

	public void verifyInformationOnOrderSummaryBoxAfterApplyingValidPromoCode() throws InterruptedException {
		wait.waitForLoad();
		isElementDisplayed("lbl_subTotal");
		isElementDisplayed("lbl_orderTotal");
		isElementDisplayed("order_discount");
		isElementDisplayed("estimated_shipping");
		isElementDisplayed("promocodeName");
		removePromoCodeInShopingCartPage();

	}

	public void verifyTheStickyNatureOfOrderSummaryBoxOnCartPage() {
		wait.waitForLoad();
		isElementDisplayed("orderSummaryBox");
		String position = executeJavascriptReturnsString("return $('.order-totals-table').css('position')");

		assertEquals(position, "static", "[ASSERTION FAILED]: Order Summary is not sticky");
		msg.pass("Order Summary is sticky !!!");
	}

	public void applyPromoCodeInShopingCartPageToVerifyInformation(String promoCode) {
		wait.waitForLoad();
		scrollDown(element("btn_promoCode"));
		isElementDisplayed("btn_promoCode");
		clickUsingJS(element("btn_promoCode"));
		msg.log("Promo Code button is clicked");
		isElementDisplayed("txtbx_promoCode");
		sendText(element("txtbx_promoCode"), promoCode);
		isElementDisplayed("btnApply");
		clickUsingJS(element("btnApply"));
		msg.log("Apply button is clicked");
		wait.waitForLoad();
	}

	public void verifyRecommendationsOnCartPage(String youMayAlsoLikeText, String youMayAlsoLikeFr) {
		wait.waitForLoad();
		isElementDisplayed("label_youMayAlsoLike");
		String text = (element("label_youMayAlsoLike").getText());
		msg.log(text);
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr-fr")) {
			assertEquals(text.contains(youMayAlsoLikeFr), true, "Recommendation header is not matched");
		} else {
			assertEquals(text.contains(youMayAlsoLikeText), true, "Recommendation header is not matched");
		}
		msg.log("Recommendation header is matched");
	}

	public void youHaveNoSavedItemsOnCartPage(String youHaveNoSavedItems) {
		isElementDisplayed("cart_wishlist");
		isElementDisplayed("text_noSavedItems");
		msg.log(element("text_noSavedItems").getText());
		String noSaveditemsText = executeJavascriptReturnsString("return $('.cart-recommendations h2+p').text()");
		assertEquals(noSaveditemsText, youHaveNoSavedItems,
				"Wishlist recommendation does not contains: you have no Saved Items");
		msg.log("Wishlist recommendation contains: you have no Saved Items");
	}

	public void verifyProductIsAddedToWishlistRecommendationOnCartPage(String productNameOnCart) {
		wait.waitForLoad();
		scrollDown(element("wishlist_productTile"));
		isElementDisplayed("wishlist_productTile");
		isElementDisplayed("wishlist_productName");
		msg.log(element("wishlist_productName").getText());
		assertEquals(element("wishlist_productName").getText().equalsIgnoreCase(productNameOnCart), true,
				"Product in wishlist is not same as on cart wistlist recommendation");
		msg.log("Product in wishlist is same as on cart wistlist recommendation");
	}

	public void verifyTheMaxLimitOfWishlistRecommendedItemsOnCartPage() {
		wait.waitForLoad();
		scrollDown(element("wishlist_productTile"));
		int sizeOfComponents = elements("wishlist_productTile").size();
		for (int i = 0; i < sizeOfComponents; i++) {
			elements("wishlist_productTile").get(i).isDisplayed();
		}
		msg.log("Count of the recommended image is " + sizeOfComponents);
		assertEquals(sizeOfComponents == 3, true, "Count of recommended product image is not matched");
		msg.log("Count of recommended product image is matched");
	}

	public void verifyFirstProductAddedToWishlistIsNotPresentInWishlistRecommendedItemsOnCartPage(String productName) {
		List<WebElement> prodName = elements("wishlist_productName");
		for (WebElement el : prodName) {
			msg.log(el.getText());
			assertEquals(el.getText().contains(productName), false, "First product added to wishlist is present");
			msg.log("First product added to wishlist is not present");
		}
		scrollUpToPage();
	}

	public void removeTheProductFromBag() {
		wait.waitForLoad();
		isElementDisplayed("btn_removeWithIndex", "1");
		clickUsingJS(element("btn_removeWithIndex", "1"));
		msg.log("User clicks on remove btn");
	}

	public void verifyCustomerServiceOnCartPage() {
		int sizeOfComponents = elements("customerService").size();
		for (int i = 0; i < sizeOfComponents; i++) {
			elements("customerService").get(i).isDisplayed();
			msg.log(elements("customerService").get(i) + " is displayed");
			scrollUpToPage();
		}
	}

	public void clickOnCheckOutWithPayPalBtn() {
		wait.waitForLoad();
		isElementDisplayed("btn_paypal");
		clickUsingJS(element("btn_paypal"));
		msg.log("Checkout with Pay Pal button is clicked ");
		wait.waitForPageToLoadCompletely();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		assertEquals(getCurrentURL().contains("www.sandbox.paypal.com"), true,
				"[Assert Fail]: User is not navigated to the Pay Pal page");
		msg.log("User is navigated to the pay pal page");
	}

	public void verifyDiscountIsNotAppliedInOrderSummary() {
		isElementNotDisplayed("order_discount");
	}

	public void verifyUserIsAbleToUpdateProductQuantityOnClickingEditBtn(String option) {
		selectOptionFromDropDownList("select_quantity", option);
		msg.log("User selects option: " + option);
		isElementDisplayed("quickbuy_applyChanges");
		clickUsingJS(element("quickbuy_applyChanges"));
		msg.log("User clicks on Apply Changes");
	}

	public void verifyTheOptionSelected() {
		wait.waitForElementToBeVisible(element("btn_checkout"));
		hardWait(1);
		String quantityVal = executeJavascriptReturnsString(
				"return $('#dwfrm_cart_shipments_i0_items_i0_quantity').find(':selected').text()");
		msg.log("Selected Option :" + quantityVal);
		assertEquals(quantityVal.contains("3"), true, "Product quantity selected is not" + quantityVal);
		msg.log("Product quantity selected is: " + quantityVal);
	}

	public void updateTheQuantityOfFirstProductInShoppingCartPage(String qty) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("dropdown_quantity");
		element("dropdown_quantity").click();
		selectOptionFromDropDownList("qtyDrpdwnOptions", qty);
	}

	public void verifyPromotionalMessageAndItemPriceAfterPromotionIsApplied() {
		wait.waitForLoad();
		isElementDisplayed("promoMsg");
		msg.log("Promo Msg displayed after promo is applied : " + element("promoMsg").getText());

		isElementDisplayed("unadjstdStrikeOutPrice");
		msg.log("Price of the product before promo is applied : " + element("unadjstdStrikeOutPrice").getText());

		isElementDisplayed("priceAdjstdAfterPromo");
		msg.log("Price of the product after promo is applied : " + element("priceAdjstdAfterPromo").getText());
	}

	public void verifyShippingDiscountIsApplied() {
		wait.waitForLoad();
		isElementDisplayed("shippingOrderDiscount");
		msg.log("Shiiping Discounyt is applied: " + element("shippingOrderDiscount").getText());
	}

	public void verifyTheHeaderOfTheRecommendationHeaderSectionInShopingCartPage(String recommendationHeader,
			String recommendationHeaderFr) {
		wait.waitForLoad();
		scrollDown(element("label_recommendationHeader"));
		isElementDisplayed("label_recommendationHeader");
		msg.log(element("label_recommendationHeader").getText());
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr-fr")) {
			assertEquals(element("label_recommendationHeader").getText(), recommendationHeaderFr,
					"Recommendation header is not matched");
		} else {
			assertEquals(element("label_recommendationHeader").getText(), recommendationHeader,
					"Recommendation header is not matched");
		}
		msg.log("Recommendation header is matched");
	}

	@SuppressWarnings("unused")
	public void verifyTheMaxLimitOfRecommendedItemsInCartPage() {
		wait.waitForLoad();
		isElementDisplayed("list_productRecommendationImage");
		List<WebElement> prodImage = elements("list_productRecommendationImage");
		int count = 0;
		for (WebElement el : prodImage) {
			count++;
		}
		msg.log("Count of the recommended image is " + count);
		assertEquals(count <= 4, true, "Count of recommended product image is not matched");
		msg.log("Count of recommended product image is matched");
	}

	public void verifyShoppingCartIsEmpty(String emptyCartHeader) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		Assert.assertTrue(getCurrentURL().contains("Cart-Show"),
				"[ASSERTION FAILED]: User is NOT on Shopping Cart page !!!");
		msg.pass("Verified user is on Shopping Cart page !!!");

		Assert.assertEquals(element("header_emptyCart").getText(), emptyCartHeader,
				"[ASSERTION FAILED]: Shopping Cart is NOT empty !!!");
		msg.pass("Verified Shopping Cart is EMPTY !!!");
	}

}
