package com.katespade.keywords;

import static org.testng.Assert.assertEquals;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.PropFileHandler;

public class WishList extends GetPage {

	WebDriver driver;

	public WishList(WebDriver driver) {
		super(driver, "Katespade/WishList");
		this.driver = driver;
	}

	public void verifyUserLandsOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeVisible(element("wishlistBanner"));

		msg.log("Actual Result: " + getCurrentURL());
		msg.log("Expected Result: 'wishlist'");

		Assert.assertTrue(getCurrentURL().toLowerCase().contains("wishlist"),
				"[ASSERTION FAILED]: User is not in the WISHLIST page !!!");
		msg.pass("User is on WISHLIST page !!!");
	}

	public void verfifyUserIsNotOnWishlistSignInPage() {
		wait.waitForLoad();
		isElementNotDisplayed("wishlist_signin");
		msg.log("User is not on Wishlist Sighn In Page");
	}

	public void verifyWishlistCanBeAccessdedFromFooter() {
		scrollDownToBottom();
		isElementDisplayed("wishlist_footer");
		msg.log("Wishlist Icon is displayed");
		click(element("wishlist_footer"));
		msg.log("User clicked on Wishlist Icon on Footer");
	}

	public void verifyUserLandsOnSighInPage() {
		isElementDisplayed("wishlist_page");
		msg.log("User is on account loging page");
	}

	public void verifyProductAddedInWishList(String skuValue) {
		isElementDisplayed("sku_value", "2");
		String sku_wishlist = (element("sku_value", "2").getText());
		msg.log("Product selected is : " + sku_wishlist);
		assertEquals(sku_wishlist.equalsIgnoreCase(skuValue), true, "Product is not same as selected");
		msg.log("Product is same as selected");
	}

	public void verifyProductNameOnWishlistPage() {
		isElementDisplayed("product_name", "2");
		msg.log("Product name is displayed on wishlist page");
	}

	public void verifyImageQuantityAndAddtoBagOnWishlistPage() {
		isElementDisplayed("item_image");
		wait.waitForElementToBeVisible(element("item_quantity", "1"));
		isElementDisplayed("item_quantity", "1");
		hardWait(2);
		Select quantity = new Select(driver.findElement(By.cssSelector(".quantity-select ")));
		quantity.selectByIndex(2);
		wait.waitForElementToBeVisible(element("selectQuantity"));
		// Assert the quantity selected
		String quantityVal = element("selectQuantity").getAttribute("value");
		msg.log("User selects :" + quantityVal);
		assertEquals(quantityVal, "3", "Product quantity selected is not" + quantityVal);
		msg.log("Product quantity selected is: " + quantityVal);

		isElementDisplayed("add_to_bag");
		element("add_to_bag").click();
		msg.log("User clicks on add_to_bag btn");
		wait.waitForLoad();
		isElementDisplayed("minicart_quantity");
		msg.log("Product is added to bag");
	}

	public void verifyProductDetailsOnWishlistPage() {
		isElementDisplayed("product_details", "1");
		msg.log("Name is displayed on wishlist page");
		isElementDisplayed("product_details", "2");
		msg.log("Style is displayed on wishlist page");
		isElementDisplayed("product_details", "3");
		msg.log("Size is displayed on wishlist page");
		isElementDisplayed("product_details", "4");
		msg.log("Color is displayed on wishlist page");
		isElementDisplayed("product_details", "5");
		msg.log("Price is displayed on wishlist page");
		isElementDisplayed("item_image");
		msg.log("Product image is displayed on the wishlist page ");
		isElementDisplayed("label_date");
		msg.log("Date Added is displayred on the wishlist page");
	}

	public void verifyRemoveBtnOnWishlistPage() {
		msg.log("Edit is displayed on wishlist page");
		isElementDisplayed("remove_button", "1");
		msg.log("Remove is displayed on wishlist page");
	}

	public void verifySideNavOnWishlistPage() {
		int sizeOfDateLabel = elements("side_nav").size();
		for (int i = 0; i < sizeOfDateLabel; i++) {
			elements("side_nav").get(i).isDisplayed();
			String leftNavText = elements("side_nav").get(i).getText();
			msg.log(" " + leftNavText);
		}
	}

	public void clickOnRegisterButton() {
		wait.waitForLoad();
		isElementDisplayed("button_CreateAnAccount");
		clickUsingJS(element("button_CreateAnAccount"));
		msg.log("User clicks on Create An Account button");
	}

	public void enterRegisterInformation() {
		long Random = System.currentTimeMillis();
		String firstName = "A" + Random;
		msg.log("New User firtName : " + firstName);
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

		isElementDisplayed("button_submit");
		clickUsingJS(element("button_submit"));
		msg.log("Submit button is clicked after filling information");
	}

	public void verifyUserIsNavigatedToWislistPage() {
		wait.waitForLoad();

		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			waitForLoad();
			if (getCurrentURL().contains("Possible Phishing Website"))
				;
			executeJavascript("PhishingAlertController.ignoreWarningSelected();");
			msg.log("User ignores the phishing alert");
		}
		isElementDisplayed("wishlist_page");
		isElementDisplayed("button_first_addTobag");

		msg.log("Actual URL (LowerCase): " + getCurrentURL());
		msg.log("Expected URL (LowerCase & contains): 'wishlist'");

		Assert.assertTrue(getCurrentURL().toLowerCase().contains("Wishlist".toLowerCase()),
				"[ASSERTION FAILED]: User has NOT navigated to the WISHLIST page !!!");
		msg.pass("User has navigated to WISHLIST page !!!");
	}

	public void verifyAllProductsAddedToWishlist() {
		wait.waitForLoad();
		String prodCountInPDP = PropFileHandler.readProperty("prodCountInPDP");
		isElementDisplayed("list_products");
		List<WebElement> products = elements("list_products");
		int prodCountInWishlist = 0;
		for (WebElement el : products) {
			prodCountInWishlist++;
			assertEquals(prodCountInWishlist, prodCountInPDP);
			msg.log("Products are : " + el.getText());
		}
	}

	public void removeTheProductAddedFromWishlist() {
		click(element("remove_button", "1"));
		msg.log("Üser clicks on remove button");
		wait.waitForLoad();
		isElementNotDisplayed("product_details", "1");
		msg.log("Product is removed from wishlist");

	}

	public void verifyProductThumbnailOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfThumbnail = elements("item_image").size();
		msg.log(" " + sizeOfThumbnail);
		for (int i = 0; i < sizeOfThumbnail; i++) {
			elements("item_image").get(i).isDisplayed();
			assertEquals(sizeOfThumbnail, 6, sizeOfThumbnail + "Product thumbnails are not displayed");

		}
		msg.log("Product thumbnails are displayed");

	}

	public void verifyProductNameForMultipleItemsOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfProductName = elements("product_details", "1").size();
		msg.log(" " + sizeOfProductName);
		for (int i = 0; i < sizeOfProductName; i++) {
			elements("product_details", "1").get(i).isDisplayed();
			assertEquals(sizeOfProductName, 6, sizeOfProductName + "Product Name is not displayed");

		}
		msg.log("Product Name is displayed");
	}

	public void verifyStyleNumberOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfStyleNumber = elements("product_details", "2").size();
		msg.log(" " + sizeOfStyleNumber);
		for (int i = 0; i < sizeOfStyleNumber; i++) {
			elements("product_details", "2").get(i).isDisplayed();
			assertEquals(sizeOfStyleNumber, 6, sizeOfStyleNumber + "Product style not displayed");
		}
		msg.log("Product style displayed");
	}

	public void verifySizeAttributeOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfSizeAttribute = elements("product_details", "3").size();
		msg.log(" " + sizeOfSizeAttribute);
		for (int i = 0; i < sizeOfSizeAttribute; i++) {
			elements("product_details", "3").get(i).isDisplayed();
			assertEquals(sizeOfSizeAttribute, 6, sizeOfSizeAttribute + "Product size is not displayed");
		}
		msg.log("Product size is displayed");
	}

	public void verifyColorAttributeOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfColorAttribute = elements("product_details", "4").size();
		msg.log(" " + sizeOfColorAttribute);
		for (int i = 0; i < sizeOfColorAttribute; i++) {
			elements("product_details", "4").get(i).isDisplayed();
			assertEquals(sizeOfColorAttribute, 6, sizeOfColorAttribute + "Product color is not displayed");
		}
		msg.log("Product color is displayed");
	}

	public void verifyPriceOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfPrice = elements("product_details", "5").size();
		msg.log(" " + sizeOfPrice);
		for (int i = 0; i < sizeOfPrice; i++) {
			elements("product_details", "5").get(i).isDisplayed();
			assertEquals(sizeOfPrice, 6, sizeOfPrice + "Product price is not displayed");
		}
		msg.log("Product price is displayed");
	}

	public void verifyInventoryOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfInventory = elements("inventory").size();
		msg.log(" " + sizeOfInventory);
		for (int i = 0; i < sizeOfInventory; i++) {
			elements("inventory").get(i).isDisplayed();
			assertEquals(sizeOfInventory, 6, sizeOfInventory + "Product inventory is not displayed");
		}
		msg.log("Product inventory is displayed");
	}

	public void verifyDateOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		int sizeOfDateLabel = elements("label_date").size();
		msg.log(" " + sizeOfDateLabel);
		for (int i = 0; i < sizeOfDateLabel; i++) {
			elements("label_date").get(i).isDisplayed();
			assertEquals(sizeOfDateLabel, 6, sizeOfDateLabel + "Product date is not displayed");
		}
		msg.log("Product date is displayed");
	}

	public void verifyQuantityIsSetToOneByDefaultOnWishlistPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("item_quantity", "1");
		/*
		 * String quantityVal = executeJavascriptReturnsString(
		 * "return $('.option-add-to-cart option[selected='selected']').val()");
		 */
		String quantityVal = element("item_quantity", "1").getAttribute("value");
		msg.log("" + quantityVal);
		assertEquals(quantityVal, "1", "Product quantity is not set to 1 by default");
		msg.log("Product quantity is set to 1 by default");
	}

	public void verifyUserIsAbleToaddProductTOBagFromWishlistPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("add_to_bag");
		element("add_to_bag").click();
		msg.log("User clicks on add to bag btn");
	}

	public void verifyContentAssetInWishlistPage() {
		wait.waitForLoad();
		isElementDisplayed("contentAsset_createBenifits");
		isElementDisplayed("contentAsset_accNavAsset");
		msg.log("Verified: Content Assets are displayed on the wishlist page");
	}

	public void verifyInventoryMsgIsDisplayedInWishlistPage() {
		wait.waitForLoad();
		isElementDisplayed("inventoryMsgInStock");
		msg.log("Inventory messaging is displayed on the wishlist page");
	}

	public void veriyQtyDropdownIsDisplayedAndItsDefaultValue() {
		wait.waitForLoad();
		isElementDisplayed("frst_qtyDrpdn");
		isElementDisplayed("frst_selectQtyDrpdn");
		assertEquals(element("frst_selectQtyDrpdn").getText(), "1", "Qty dropdown is not as expected");
		msg.log("Qty dropdown is as expected");
	}

	public void changeTheQuantityOfTheProduct(String qty) {
		wait.waitForLoad();
		isElementDisplayed("frst_qtyDrpdn");
		element("frst_qtyDrpdn").click();
		isElementDisplayed("frst_selectQtyDrpdn");
		selectProvidedTextFromDropDown(element("frst_selectQtyDrpdn"), qty);
		msg.log("User selected Qty as : " + qty);
	}

	public void verifyWishlistBannerAndAccLeftNavBannerContentAsset() {
		wait.waitForLoad();
		isElementDisplayed("wishlistBanner");
		isElementDisplayed("contentAsset_accNavText");
		assertEquals(element("contentAsset_accNavText").getCssValue("float"), "left",
				"[Assert Fail]: Content Asset Account Nav Left Text is not as expected");
		msg.log("Content Asset Account Nav Left Text is as expected");
	}

	public void verifyOptionLinkingInContentAssetLeftNavigationOnWishlistPage() {
		wait.waitForLoad();
		isElementDisplayed("lnk_myAccount");
		isElementDisplayed("lnk_myInfo");
		isElementDisplayed("lnk_addressBook");
		isElementDisplayed("lnk_creditCards");
		isElementDisplayed("lnk_orderHistory");
		isElementDisplayed("lnk_wishlist");
		isElementDisplayed("lnk_shopConfidently");

		msg.log("Options in the content assets  left navigation are linked as expected");
	}

	public void clickEditButtonOfFirstProduct() {
		wait.waitForLoad();
		isElementDisplayed("Product_Name", "1");
		PropFileHandler.writeProperty("prodName", element("Product_Name", "1").getText());
		isElementDisplayed("frstbtn_edit");
		element("frstbtn_edit").click();
		msg.log("Edit button is clicked");
	}

	public void verifyAddedItemsAreRemovedFromWishlistPage() {
		for (int i = 0; i < 6; i++) {
			if (elements("remove_button", "1").get(0).isDisplayed()) {
				click(element("remove_button", "1"));
				msg.log("Üser removes product " + i);
				wait.waitForLoad();
			}
		}
		isElementNotDisplayed("remove_button", "1");
		msg.log("products removed from wishlist");
	}

	public void verifyWishlistIsEmpty(String emptyWishlistText) {
		wait.waitForLoad();
		isElementDisplayed("label_emptyWishlist");
		String text = executeJavascriptReturnsString("return $('#primary h2').text()");
		msg.log(element("label_emptyWishlist").getText());
		assertEquals(text.contains(emptyWishlistText), true, "Recommendation header is not matched");
		msg.log("Recommendation header is matched");
	}

	public String getProductNameAddedToWishlist() {
		String name = element("Product_Name", "1").getText();
		msg.log("product added to wishlist is: " + name);
		return name;
	}

	public void addWishlistProductToBag(int addToCartIndx) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("btn_addToCart");
		List<WebElement> btnAddToCart = elements("btn_addToCart");
		clickUsingJS(btnAddToCart.get(addToCartIndx));
		msg.log("user clicked on the add to cart button for product " + addToCartIndx + " in  wishlist");
	}

	public void verifyCheckBoxAtWishListLogin(String userName, String password) {
		wait.waitForLoad();
		scrollUpToPage();

		isElementDisplayed("textbox_login_emailAddress");
		sendText(element("textbox_login_emailAddress"), userName);
		msg.log("'" + userName + "' is entered");

		isElementDisplayed("textbox_login_password");
		sendText(element("textbox_login_password"), password);
		msg.log("'" + password + "' is entered");

		isElementDisplayed("checkBox_RememberMe");
		executeJavascript("document.getElementById('dwfrm_login_rememberme').click()");
		msg.log("'remember me' checkbox is selected !!!");

		// Takes time to reflect the selection of 'remember me' Check box
		hardWait(2);

		String beforeCssValueOnSelection = executeJavascriptReturnsString(
				"return window.getComputedStyle(document.getElementsByTagName("
						+ "'label')[3], ':before').getPropertyValue('background-image')");

		msg.log("ACTUAL Before CSS value of 'remember me' checkbox: '" + beforeCssValueOnSelection + "'");
		msg.log("EXPECTED Value: 'checkmark.svg'");

		Assert.assertTrue(beforeCssValueOnSelection.contains("checkmark.svg"),
				"[ASSERTION FAILED]: 'remember me' check box is NOT SELECTED!!!");
		msg.pass("'remember me' check box is selected !!!");

		executeJavascript("document.getElementById('dwfrm_login_rememberme').click()");
		msg.log("'remember me' checkbox is un-selected !!!");

		// Takes time to reflect the selection of 'remember me' Check box
		hardWait(2);

		String beforeCssValueOnUnSelection = executeJavascriptReturnsString(
				"return window.getComputedStyle(document.getElementsByTagName("
						+ "'label')[3], ':before').getPropertyValue('background-image')");
		msg.log("ACTUAL Before CSS value of 'remember me' checkbox: '" + beforeCssValueOnUnSelection + "'");
		msg.log("EXPECTED Value: 'none'");

		Assert.assertTrue(beforeCssValueOnUnSelection.contains("none"),
				"[ASSERTION FAILED]: 'remember me' check box is STILL SELECTED !!!");
		msg.pass("'remember me' check box is un-selected !!!");

		msg.pass("Remember Me check box is checkable !!!");
	}

}
